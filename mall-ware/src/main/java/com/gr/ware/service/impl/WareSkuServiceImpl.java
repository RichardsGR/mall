package com.gr.ware.service.impl;

import com.gr.common.utils.R;
import com.gr.ware.feign.ProductFeignService;
import org.apache.commons.lang.StringUtils;
import org.eclipse.jetty.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gr.common.utils.PageUtils;
import com.gr.common.utils.Query;

import com.gr.ware.dao.WareSkuDao;
import com.gr.ware.entity.WareSkuEntity;
import com.gr.ware.service.WareSkuService;
import org.springframework.transaction.annotation.Transactional;


@Service("wareSkuService")
public class WareSkuServiceImpl extends ServiceImpl<WareSkuDao, WareSkuEntity> implements WareSkuService {

    @Autowired
    WareSkuDao wareSkuDao;

    @Autowired
    ProductFeignService productFeignService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        QueryWrapper<WareSkuEntity> wrapper = new QueryWrapper<>();

        String skuId = (String) params.get("skuId");
        if(!StringUtils.isEmpty(skuId)) {
            wrapper.eq("sku_id",skuId);
        }

        String wareId = (String)params.get("wareId");
        if(!StringUtils.isEmpty(wareId)) {
            wrapper.eq("ware_id",wareId);
        }

        IPage<WareSkuEntity> page = this.page(
                new Query<WareSkuEntity>().getPage(params),
                wrapper
        );

        return new PageUtils(page);
    }

    @Transactional
    @Override
    public void addStock(Long skuId, Long wareId, Integer skuNum) {
        //如果没有库存记录就是新增操作
        List<WareSkuEntity> wareSkuEntities = wareSkuDao.selectList(new QueryWrapper<WareSkuEntity>().eq("sku_id", skuId).eq("ware_id", wareId));
        if(wareSkuEntities == null || wareSkuEntities.size() == 0) {
            WareSkuEntity wareSkuEntity = new WareSkuEntity();
            wareSkuEntity.setSkuId(skuId);
            wareSkuEntity.setStock(skuNum);
            wareSkuEntity.setStockLocked(0);
            //使用 try 语句，当查询 name 失败时事务不需要回滚
            try{
                R info = productFeignService.info(skuId);
                if(info.getCode() == 0) {
                    Map<String,Object> res = (Map<String, Object>) info.get("skuInfo");
                    wareSkuEntity.setSkuName((String)res.get("skuName"));
                }
            }catch (Exception e) {

            }
            wareSkuEntity.setWareId(wareId);
            wareSkuDao.insert(wareSkuEntity);
        }else {
            wareSkuDao.addStock(skuId, wareId, skuNum);
        }
    }

}