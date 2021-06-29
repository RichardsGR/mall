package com.gr.ware.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gr.common.utils.PageUtils;
import com.gr.ware.entity.UndoLogEntity;

import java.util.Map;

/**
 * 
 *
 * @author Richard
 * @email sunlightcs@gmail.com
 * @date 2021-06-29 09:21:05
 */
public interface UndoLogService extends IService<UndoLogEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

