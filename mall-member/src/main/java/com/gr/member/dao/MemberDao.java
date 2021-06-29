package com.gr.member.dao;

import com.gr.member.entity.MemberEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 会员
 * 
 * @author Richard
 * @email sunlightcs@gmail.com
 * @date 2021-06-28 17:43:49
 */
@Mapper
public interface MemberDao extends BaseMapper<MemberEntity> {
	
}
