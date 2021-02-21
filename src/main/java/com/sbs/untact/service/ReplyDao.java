package com.sbs.untact.service;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.sbs.untact.dto.Reply;

@Mapper
public interface ReplyDao {
	List<Reply> getForPrintReplies(@Param("relTypeCode") String relTypeCode, @Param("relId") int relId);
	
	Reply getReply(@Param("id") int id);

	void deleteReply(@Param("id") int id);

	void modifyReply(@Param("id") int id, @Param("body") String body);
}
