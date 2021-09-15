package com.gr.product.service.impl;

import com.aliyuncs.utils.StringUtils;
import com.gr.product.dao.AttrAttrgroupRelationDao;
import com.gr.product.dao.AttrGroupDao;
import com.gr.product.entity.AttrAttrgroupRelationEntity;
import com.gr.product.entity.AttrEntity;
import com.gr.product.service.AttrService;
import com.gr.product.vo.AttrGroupWithAttrVo;
import com.gr.product.vo.AttrRelationVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gr.common.utils.PageUtils;
import com.gr.common.utils.Query;

import com.gr.product.entity.AttrGroupEntity;
import com.gr.product.service.AttrGroupService;


@Service("attrGroupService")
public class AttrGroupServiceImpl extends ServiceImpl<AttrGroupDao, AttrGroupEntity> implements AttrGroupService {


    @Autowired
    AttrAttrgroupRelationDao relationDao;

    @Autowired
    AttrService attrService;
    /*@Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<AttrGroupEntity> page = this.page(
                new Query<AttrGroupEntity>().getPage(params),
                new QueryWrapper<AttrGroupEntity>()
        );

        return new PageUtils(page);
    }*/

    @Override
    public PageUtils queryPage(Map<String, Object> params, long catelogId) {

        QueryWrapper<AttrGroupEntity> queryWrapper = new QueryWrapper<>();
        //select * from pms_attr_group where catelog_id=? and (attr_group_id=key or attr_group_name like %key%)
        if (catelogId != 0){
            queryWrapper.eq("catelog_id", catelogId);
        }

        String key = (String) params.get("key");
        if (!StringUtils.isEmpty(key)){
            queryWrapper.and((obj)->
                    obj.eq("attr_group_id",key).or().like("attr_group_name",key));
        }
        IPage<AttrGroupEntity> page = this.page(new Query<AttrGroupEntity>().getPage(params), queryWrapper);
        return new PageUtils(page);
    }

    @Override
    public void deleteRelation(AttrRelationVo[] vos) {
        //relationDao.delete(new QueryWrapper<>().eq("attr_id",).eq("attr_group_id"));
        List<AttrAttrgroupRelationEntity> relations = Arrays.asList(vos).stream().map((vo)->{
            AttrAttrgroupRelationEntity relationEntity = new AttrAttrgroupRelationEntity();
            BeanUtils.copyProperties(vo,relationEntity);
            return relationEntity;
        }).collect(Collectors.toList());

        relationDao.deleteRelationBatch(relations);
    }

    @Override
    public List<AttrGroupWithAttrVo> getAttrGroupWithAttrVoByCatelogId(Long catelogId) {
        //查询当前分类下所有的分组信息
        List<AttrGroupEntity> entities = baseMapper.selectList(new QueryWrapper<AttrGroupEntity>().eq("catelog_id",catelogId));

        List<AttrGroupWithAttrVo> attrGroupWithAttrVos = entities.stream().map((item) -> {
            AttrGroupWithAttrVo attrGroupWithAttrVo = new AttrGroupWithAttrVo();
            BeanUtils.copyProperties(item,attrGroupWithAttrVo);
            List<AttrEntity> relationAtr = attrService.getRelationAtr(attrGroupWithAttrVo.getAttrGroupId());
            attrGroupWithAttrVo.setAttrs(relationAtr);
            return attrGroupWithAttrVo;
        }).collect(Collectors.toList());
        return attrGroupWithAttrVos;
    }

}