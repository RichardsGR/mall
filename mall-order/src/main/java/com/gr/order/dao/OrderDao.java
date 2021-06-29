package com.gr.order.dao;

import com.gr.order.entity.OrderEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 订单
 * 
 * @author Richard
 * @email sunlightcs@gmail.com
 * @date 2021-06-28 17:40:13
 */
@Mapper
public interface OrderDao extends BaseMapper<OrderEntity> {
	
}
