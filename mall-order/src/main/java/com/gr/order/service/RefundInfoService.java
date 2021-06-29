package com.gr.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gr.common.utils.PageUtils;
import com.gr.order.entity.RefundInfoEntity;

import java.util.Map;

/**
 * 退款信息
 *
 * @author Richard
 * @email sunlightcs@gmail.com
 * @date 2021-06-28 17:40:13
 */
public interface RefundInfoService extends IService<RefundInfoEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

