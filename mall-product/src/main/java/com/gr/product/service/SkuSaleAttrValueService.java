package com.gr.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gr.common.utils.PageUtils;
import com.gr.product.entity.SkuSaleAttrValueEntity;

import java.util.Map;

/**
 * sku销售属性&值
 *
 * @author Richard
 * @email sunlightcs@gmail.com
 * @date 2021-06-28 14:55:07
 */
public interface SkuSaleAttrValueService extends IService<SkuSaleAttrValueEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

