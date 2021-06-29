package com.gr.coupon.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gr.common.utils.PageUtils;
import com.gr.coupon.entity.HomeSubjectSpuEntity;

import java.util.Map;

/**
 * 专题商品
 *
 * @author Richard
 * @email sunlightcs@gmail.com
 * @date 2021-06-28 17:37:30
 */
public interface HomeSubjectSpuService extends IService<HomeSubjectSpuEntity> {

    PageUtils queryPage(Map<String, Object> params);
}
