package com.gr.ware.dao;

import com.gr.ware.entity.WareInfoEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 仓库信息
 * 
 * @author Richard
 * @email sunlightcs@gmail.com
 * @date 2021-06-29 09:21:04
 */
@Mapper
public interface WareInfoDao extends BaseMapper<WareInfoEntity> {
	
}
