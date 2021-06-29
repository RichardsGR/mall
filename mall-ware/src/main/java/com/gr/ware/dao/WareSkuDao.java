package com.gr.ware.dao;

import com.gr.ware.entity.WareSkuEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 商品库存
 * 
 * @author Richard
 * @email sunlightcs@gmail.com
 * @date 2021-06-29 09:21:04
 */
@Mapper
public interface WareSkuDao extends BaseMapper<WareSkuEntity> {
	
}