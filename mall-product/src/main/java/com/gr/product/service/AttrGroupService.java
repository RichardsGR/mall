package com.gr.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gr.common.utils.PageUtils;
import com.gr.product.entity.AttrGroupEntity;
import com.gr.product.vo.AttrGroupWithAttrVo;
import com.gr.product.vo.AttrRelationVo;

import java.util.List;
import java.util.Map;

/**
 * 属性分组
 *
 * @author Richard
 * @email sunlightcs@gmail.com
 * @date 2021-06-28 14:55:07
 */
public interface AttrGroupService extends IService<AttrGroupEntity> {

    PageUtils queryPage(Map<String, Object> params, long catelogId);

    void deleteRelation(AttrRelationVo[] vos);

    List<AttrGroupWithAttrVo> getAttrGroupWithAttrVoByCatelogId(Long catelogId);
}

