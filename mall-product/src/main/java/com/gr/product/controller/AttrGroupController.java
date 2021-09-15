package com.gr.product.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;


import com.gr.product.entity.AttrEntity;
import com.gr.product.service.AttrService;
import com.gr.product.service.CategoryService;
import com.gr.product.vo.AttrGroupWithAttrVo;
import com.gr.product.vo.AttrRelationVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.gr.product.entity.AttrGroupEntity;
import com.gr.product.service.AttrGroupService;
import com.gr.common.utils.PageUtils;
import com.gr.common.utils.R;



/**
 * 属性分组
 *
 * @author Richard
 * @email sunlightcs@gmail.com
 * @date 2021-06-28 16:47:18
 */
@RestController
@RequestMapping("product/attrgroup")
public class AttrGroupController {

    @Autowired
    private AttrGroupService attrGroupService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    AttrService attrService;

    @GetMapping("/{attrgroupId}/attr/relation")
    public R attrRelation(@PathVariable("attrgroupId") Long attrgroupId) {
        List<AttrEntity> entity = attrService.getAttrRelation(attrgroupId);
        return R.ok().put("data",entity);
    }

    @GetMapping("/{attrgroupId}/noattr/relation")
    public R attrNoRelation(@RequestParam Map<String,Object> params,
                            @PathVariable("attrgroupId") Long attrgroupId) {
        PageUtils page = attrService.getNoRelationAttr(params,attrgroupId);
        return R.ok().put("page",page);
    }



    @RequestMapping("list/{catelogId}")
    public R list(@RequestParam Map<String, Object> params,
                  @PathVariable("catelogId") long catelogId) {
        PageUtils page = attrGroupService.queryPage(params, catelogId);
        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{attrGroupId}")
   // @RequiresPermissions("product:attrgroup:info")
    public R info(@PathVariable("attrGroupId") Long attrGroupId){
		AttrGroupEntity attrGroup = attrGroupService.getById(attrGroupId);

		Long catId = attrGroup.getCatelogId();
		attrGroup.setCatelogPath(categoryService.findCatelogPath(catId));

        return R.ok().put("attrGroup", attrGroup);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("product:attrgroup:save")
    public R save(@RequestBody AttrGroupEntity attrGroup){
		attrGroupService.save(attrGroup);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("product:attrgroup:update")
    public R update(@RequestBody AttrGroupEntity attrGroup){
		attrGroupService.updateById(attrGroup);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("product:attrgroup:delete")
    public R delete(@RequestBody Long[] attrGroupIds){
		attrGroupService.removeByIds(Arrays.asList(attrGroupIds));

        return R.ok();
    }

    @PostMapping("/attr/relation/delete")
    public R deleteRelation(@RequestBody AttrRelationVo[] vos) {
        attrGroupService.deleteRelation(vos);
        return R.ok();
    }

    /**
     * /product/attrgroup/{catelogId}/withattr
     */
    @GetMapping("/{catelogId}/withattr")
    public R withAttr(@PathVariable("catelogId")Long catelogId) {


        List<AttrGroupWithAttrVo> vos = attrGroupService.getAttrGroupWithAttrVoByCatelogId(catelogId);


        return R.ok().put("data",vos);
    }

}
