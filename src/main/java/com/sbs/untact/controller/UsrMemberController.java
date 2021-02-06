package com.sbs.untact.controller;

import java.net.http.HttpRequest;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sbs.untact.dto.Member;
import com.sbs.untact.dto.ResultData;
import com.sbs.untact.service.MemberService;

public class UsrMemberController {

	@Autowired
	private MemberService memberService;

	@RequestMapping("/usr/member/dojoin")
	@ResponseBody
	public ResultData dojoin(@RequestParam Map<String, Object> param) {

		if (param.get("loginId") == null) {
			return new ResultData("F-1", "loginId를 입력해주세요. ");
		}

		Member existingMember = memberService.getMemberByLoginId((String) param.get("loginId"));

		if (existingMember != null) {
			return new ResultData("F-2", String.format("%s(은)는 이미 사용 중인 아이디입니다.", param.get("loginId")));
		}

		if (param.get("loginPw") == null) {
			return new ResultData("F-1", "loginPw를 입력해주세요. ");
		}
		if (param.get("name") == null) {
			return new ResultData("F-1", "name를 입력해주세요. ");
		}
		if (param.get("nickname") == null) {
			return new ResultData("F-1", "nickname를 입력해주세요. ");
		}
		if (param.get("cellphoneNo") == null) {
			return new ResultData("F-1", "cellphoneNo를 입력해주세요. ");
		}
		if (param.get("email") == null) {
			return new ResultData("F-1", "email를 입력해주세요. ");
		}

		for (Member member : members) {
			if (member.getLoginId() == loginId) {
				return new ResultData("F-2", ("로그인 아이디" + loginId + "(은)는 이미 사용 중입니다."));
			}

		}

		return memberService.join(param);
	}

	@RequestMapping("/usr/member/doLogin")
	@ResponseBody
	public ResultData doLogin(String loginId, String loginPw, HttpSession session) {

		if (session.getAttribute("loginedMemberId") != null) {
			return new ResultData("F-4", "이미 로그인 되었습니다.");
		}

		if (loginId == null) {
			return new ResultData("F-1", "loginId를 입력해주세요.");
		}

		Member existingMember = memberService.getMemberByLoginId(loginId);

		if (existingMember == null) {
			return new ResultData("F-2", "존재하지 않는 로그인 아이디 입니다.", "loginId", loginId);
		}

		if (loginPw == null) {
			return new ResultData("F-1", "loginPw를 입력해주세요.");
		}

		if (existingMember.getLoginPw().equals(loginPw) == false) {
			return new ResultData("F-3", "비밀번호가 일치하지 않습니다.");
		}

		session.setAttribute("loginMemberId", existingMember.getId());
		return new ResultData("S-1", String.format("%s님 환영합니다.", existingMember.getNickname()));
	}

	@RequestMapping("/usr/member/doLogout")
	@ResponseBody
	public ResultData doLogout(int id) {

		Member member = memberService.getMember(id);
		return memberService.logoutMember(id);
	}

	@RequestMapping("/usr/member/doModify")
	@ResponseBody
	public ResultData doModify() {

	}
}
