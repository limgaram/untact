package com.sbs.untact.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sbs.untact.dao.MemberDao;
import com.sbs.untact.dto.Member;
import com.sbs.untact.dto.ResultData;

@Service
public class MemberService {

	@Autowired
	private MemberDao memberDao;

	public ResultData join(Map<String, Object> param) {
		memberDao.addMember(param);

		return null;
	}

	public ResultData login(String loginId, String loginPw) {

		return memberDao.getMember();
	}

	public Member getMember(int id) {

		return memberDao.getMember(id);
	}

}
