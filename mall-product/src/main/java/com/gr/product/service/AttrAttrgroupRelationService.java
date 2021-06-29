package com.gr.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gr.common.utils.PageUtils;
import com.gr.product.entity.AttrAttrgroupRelationEntity;

import java.util.Map;

/**
 * 属性&属性分组关联
 *
 * @author Richard
 * @email sunlightcs@gmail.com
 * @date 2021-06-28 14:55:07
 */
public interface AttrAttrgroupRelationService extends IService<AttrAttrgroupRelationEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

