package com.gr.product.vo;

import lombok.Data;

@Data
public class AttrVo {
    /**
     *
     * 属性id
     */
    private Long attrId;

    /**
     *
     * 属性名
     */
    private String attrName;

    /**
     *
     * 是否需要检索
     */
    private Integer searchType;

    /**
     *
     * 值类型
     */
    private Integer valueType;

    /**
     *
     * 属性图标
     */
    private String icon;

    /**
     *
     * 可选值列表
     */
    private String valueSelect;

    /**
     *
     * 属性类型
     */
    private Integer attrType;

    /**
     *
     * 启用状态
     */
    private Long enable;

    /**
     *
     * 所属分类
     */
    private Long catelogId;

    /**
     *
     * 快速展示
     */
    private Integer showDesc;

    private Long attrGroupId;
}
