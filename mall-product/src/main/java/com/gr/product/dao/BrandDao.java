package com.gr.product.dao;

import com.gr.product.entity.BrandEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 品牌
 * 
 * @author Richard
 * @email sunlightcs@gmail.com
 * @date 2021-06-28 14:55:07
 */
@Mapper
public interface BrandDao extends BaseMapper<BrandEntity> {
	
}
