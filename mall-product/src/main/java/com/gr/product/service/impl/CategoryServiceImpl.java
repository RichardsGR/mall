package com.gr.product.service.impl;

import com.gr.product.dao.CategoryDao;
import com.gr.product.service.CategoryBrandRelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gr.common.utils.PageUtils;
import com.gr.common.utils.Query;

import com.gr.product.entity.CategoryEntity;
import com.gr.product.service.CategoryService;
import org.springframework.transaction.annotation.Transactional;


@Service("categoryService")
public class CategoryServiceImpl extends ServiceImpl<CategoryDao, CategoryEntity> implements CategoryService {

    @Autowired
    CategoryBrandRelationService categoryBrandRelationService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<CategoryEntity> page = this.page(
                new Query<CategoryEntity>().getPage(params),
                new QueryWrapper<CategoryEntity>()
        );
        return new PageUtils(page);
    }

    @Override
    public List<CategoryEntity> listWithTree() {
        List<CategoryEntity> entities = baseMapper.selectList(null);
        // 查询1级分类以及其下的所有子分类
        List<CategoryEntity> categoryEntityList = entities.stream().filter(categoryEntity ->
                categoryEntity.getParentCid() == 0
        ).map(categoryEntity -> {
            categoryEntity.setChildren(getChildren(categoryEntity, entities));
            return categoryEntity;
        }).sorted((o1, o2) -> (o1.getSort() == null ? 0 : o1.getSort()) - (o2.getSort() == null ? 0 : o2.getSort())
        ).collect(Collectors.toList());

        return categoryEntityList;

    }

    @Override
    public void removeMenu(List<Long> asList) {
        //TODO 1、检查当前删除的菜单是否被其它地方引用

        //逻辑删除
        baseMapper.deleteBatchIds(asList);
    }

    @Override
    public Long[] findCatelogPath(Long catId) {
        List<Long> path = new ArrayList<>();
        findPath(catId,path);
        return (Long[]) path.toArray(new Long[path.size()]);
    }

    @Transactional
    @Override
    public void updateCascade(CategoryEntity category) {
        this.updateById(category);
        categoryBrandRelationService.updateCategory(category.getCatId(),category.getName());
    }

    public void findPath(Long catId, List<Long> paths) {
        CategoryEntity ce = this.getById(catId);
        if(ce.getParentCid() != 0) {
            findPath(ce.getParentCid(),paths);
        }
        paths.add(catId);
    }

    private List<CategoryEntity> getChildren(CategoryEntity root, List<CategoryEntity> all) {
        List<CategoryEntity> childrenList = all.stream().filter(categoryEntity -> {
            // 当前分类的父id等于要查找分类的id，就说明当前分类是要查找分类的子分类
            return categoryEntity.getParentCid().equals(root.getCatId());
        }).map(categoryEntity -> {
            // 开始递归查找匹配到的分类
            categoryEntity.setChildren(getChildren(categoryEntity, all));
            return categoryEntity;
        }).sorted((o1, o2) -> (o1.getSort() == null ? 0 : o1.getSort()) - (o2.getSort() == null ? 0 : o2.getSort())
        ).collect(Collectors.toList());

        return childrenList;
    }

}