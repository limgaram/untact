package com.sbs.untact.dao;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.sbs.untact.dto.Member;

@Mapper
public class MemberDao {

	public Member getMember(@Param(value = "id") int id);

	public void addMember(Map<String, Object> param);

}
