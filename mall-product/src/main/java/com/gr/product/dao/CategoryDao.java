package com.gr.product.dao;

import com.gr.product.entity.CategoryEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 商品三级分类
 * 
 * @author Richard
 * @email sunlightcs@gmail.com
 * @date 2021-06-28 14:55:07
 */
@Mapper
public interface CategoryDao extends BaseMapper<CategoryEntity> {
	
}
