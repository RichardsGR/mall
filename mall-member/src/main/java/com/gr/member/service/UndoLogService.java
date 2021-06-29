package com.gr.member.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gr.common.utils.PageUtils;
import com.gr.member.entity.UndoLogEntity;

import java.util.Map;

/**
 * 
 *
 * @author Richard
 * @email sunlightcs@gmail.com
 * @date 2021-06-28 17:43:49
 */
public interface UndoLogService extends IService<UndoLogEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

