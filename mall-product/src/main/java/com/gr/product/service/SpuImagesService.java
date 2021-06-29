package com.gr.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gr.common.utils.PageUtils;
import com.gr.product.entity.SpuImagesEntity;

import java.util.Map;

/**
 * spu图片
 *
 * @author Richard
 * @email sunlightcs@gmail.com
 * @date 2021-06-28 14:55:07
 */
public interface SpuImagesService extends IService<SpuImagesEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

