package com.gr.coupon.dao;

import com.gr.coupon.entity.CouponEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 优惠券信息
 * 
 * @author Richard
 * @email sunlightcs@gmail.com
 * @date 2021-06-28 17:37:30
 */
@Mapper
public interface CouponDao extends BaseMapper<CouponEntity> {
	
}
