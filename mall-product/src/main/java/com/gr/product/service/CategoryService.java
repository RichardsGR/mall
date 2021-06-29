package com.gr.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gr.common.utils.PageUtils;
import com.gr.product.entity.CategoryEntity;

import java.util.Map;

/**
 * 商品三级分类
 *
 * @author Richard
 * @email sunlightcs@gmail.com
 * @date 2021-06-28 14:55:07
 */
public interface CategoryService extends IService<CategoryEntity> {

    PageUtils queryPage(Map<String, Object> params);
}
