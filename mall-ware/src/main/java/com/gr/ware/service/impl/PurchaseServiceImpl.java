package com.gr.ware.service.impl;

import com.gr.common.constant.WareConstant;
import com.gr.ware.entity.PurchaseDetailEntity;
import com.gr.ware.service.PurchaseDetailService;
import com.gr.ware.service.WareSkuService;
import com.gr.ware.vo.MergeVo;
import com.gr.ware.vo.PurchaseDoneVo;
import com.gr.ware.vo.PurchaseItemDoneVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gr.common.utils.PageUtils;
import com.gr.common.utils.Query;

import com.gr.ware.dao.PurchaseDao;
import com.gr.ware.entity.PurchaseEntity;
import com.gr.ware.service.PurchaseService;
import org.springframework.transaction.annotation.Transactional;


@Service("purchaseService")
public class PurchaseServiceImpl extends ServiceImpl<PurchaseDao, PurchaseEntity> implements PurchaseService {


    @Autowired
    PurchaseDetailService purchaseDetailService;

    @Autowired
    WareSkuService wareSkuService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<PurchaseEntity> page = this.page(
                new Query<PurchaseEntity>().getPage(params),
                new QueryWrapper<PurchaseEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public PageUtils queryPageUnreceiveOurchase(Map<String, Object> params) {
        IPage<PurchaseEntity> page = this.page(
                new Query<PurchaseEntity>().getPage(params),
                new QueryWrapper<PurchaseEntity>().eq("status",0).or().eq("status",1)
        );

        return new PageUtils(page);
    }

    @Transactional
    @Override
    public void mergePurchase(MergeVo mergeVo) {
        Long purchaseId = mergeVo.getPurchaseId();
        if(purchaseId == null) {
            PurchaseEntity purchaseEntity = new PurchaseEntity();
            purchaseEntity.setStatus(WareConstant.PurchaseStatusEnum.CREATE.getCode());
            purchaseEntity.setCreateTime(new Date());
            purchaseEntity.setUpdateTime(new Date());
            this.save(purchaseEntity);
            purchaseId = purchaseEntity.getId();
        }
        List<Long> items = mergeVo.getItems();
        Long finalPurchaseId = purchaseId;
        List<PurchaseDetailEntity> collect = items.stream().map((i) -> {
            PurchaseDetailEntity purchaseDetailEntity = new PurchaseDetailEntity();
            purchaseDetailEntity.setId(i);
            purchaseDetailEntity.setPurchaseId(finalPurchaseId);
            purchaseDetailEntity.setStatus(WareConstant.PurchaseDetailStatusEnum.ASSIGN.getCode());
            return purchaseDetailEntity;
        }).collect(Collectors.toList());
        purchaseDetailService.updateBatchById(collect);
        PurchaseEntity purchaseEntity = new PurchaseEntity();
        purchaseEntity.setId(purchaseId);
        purchaseEntity.setUpdateTime(new Date());
        this.updateById(purchaseEntity);
    }

    @Override
    public void received(List<Long> ids) {
        //确认当前采购单是新建或者已分配状态
        List<PurchaseEntity> collect = ids.stream().map(id -> {
            PurchaseEntity byId = this.getById(id);
            return byId;
        }).filter(item -> {
            if (item.getStatus() == WareConstant.PurchaseStatusEnum.CREATE.getCode() ||
                    item.getStatus() == WareConstant.PurchaseStatusEnum.ASSIGNED.getCode()) {
                return true;
            }
            return false;
        }).map(item -> {
            item.setStatus(WareConstant.PurchaseStatusEnum.RECEIVE.getCode());
            return item;
        }).collect(Collectors.toList());

        //改变采购单的状态
        this.updateBatchById(collect);
        //改变采购项的状态
        collect.forEach((item) -> {
            List<PurchaseDetailEntity> entities = purchaseDetailService.listDetailByPurchaseId(item.getId());
            List<PurchaseDetailEntity> collect1 = entities.stream().map(entity -> {
                PurchaseDetailEntity entity1 = new PurchaseDetailEntity();
                entity1.setId(entity.getId());
                entity1.setStatus(WareConstant.PurchaseDetailStatusEnum.BUYING.getCode());
                return entity1;
            }).collect(Collectors.toList());
            purchaseDetailService.updateBatchById(collect1);
        });
    }

    @Transactional
    @Override
    public void done(PurchaseDoneVo vo) {
        //改变采购单状态
        Long id = vo.getId();


        //改变采购项的状态
        Boolean flag = true;
        List<PurchaseDetailEntity> updates = new ArrayList<>();
       List<PurchaseItemDoneVo> items = vo.getItems();
       for(PurchaseItemDoneVo item : items) {
           PurchaseDetailEntity detailEntity = new PurchaseDetailEntity();
           if(item.getStatus() == WareConstant.PurchaseDetailStatusEnum.HASERROR.getCode()) {
               flag = false;
               detailEntity.setStatus(WareConstant.PurchaseDetailStatusEnum.HASERROR.getCode());
           }else{
               detailEntity.setStatus(WareConstant.PurchaseDetailStatusEnum.Finish.getCode());
               //入库操作
               PurchaseDetailEntity byId = purchaseDetailService.getById(item.getItemId());
               wareSkuService.addStock(byId.getSkuId(),byId.getWareId(),byId.getSkuNum());
           }
           detailEntity.setId(item.getItemId());
           updates.add(detailEntity);

       }
       purchaseDetailService.updateBatchById(updates);


       PurchaseEntity purchaseEntity = new PurchaseEntity();
       purchaseEntity.setId(id);
       purchaseEntity.setStatus(flag? WareConstant.PurchaseStatusEnum.Finish.getCode() : WareConstant.PurchaseStatusEnum.HASERROR.getCode());
       purchaseEntity.setUpdateTime(new Date());
       this.updateById(purchaseEntity);
        //将成功的采购入库
    }

}