package com.gr.ware.dao;

import com.gr.ware.entity.PurchaseEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 采购信息
 * 
 * @author Richard
 * @email sunlightcs@gmail.com
 * @date 2021-06-29 09:21:05
 */
@Mapper
public interface PurchaseDao extends BaseMapper<PurchaseEntity> {
	
}
