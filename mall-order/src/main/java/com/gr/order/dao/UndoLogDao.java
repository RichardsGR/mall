package com.gr.order.dao;

import com.gr.order.entity.UndoLogEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 
 * 
 * @author Richard
 * @email sunlightcs@gmail.com
 * @date 2021-06-28 17:40:13
 */
@Mapper
public interface UndoLogDao extends BaseMapper<UndoLogEntity> {
	
}
