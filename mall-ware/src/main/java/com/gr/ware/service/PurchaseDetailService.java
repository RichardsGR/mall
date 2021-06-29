package com.gr.ware.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gr.common.utils.PageUtils;
import com.gr.ware.entity.PurchaseDetailEntity;

import java.util.Map;

/**
 * 
 *
 * @author Richard
 * @email sunlightcs@gmail.com
 * @date 2021-06-29 09:21:04
 */
public interface PurchaseDetailService extends IService<PurchaseDetailEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

