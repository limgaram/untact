package com.sbs.untact.interceptor;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.sbs.untact.dto.Member;
import com.sbs.untact.service.MemberService;
import com.sbs.untact.util.Util;

@Component("beforeActionInterceptor") // 컴포넌트 이름 설정
public class BeforeActionInterceptor implements HandlerInterceptor {
	@Autowired
	private MemberService memberService;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		// 기타 유용한 정보를 request에 담는다.
		Map<String, Object> param = Util.getParamMap(request);
		String paramJson = Util.toJsonStr(param);

		String requestUrl = request.getRequestURI();
		String queryString = request.getQueryString();

		if (queryString != null && queryString.length() > 0) {
			requestUrl += "?" + queryString;
		}

		String encodedRequestUrl = Util.getUrlEncoded(requestUrl);

		request.setAttribute("requestUrl", requestUrl);
		request.setAttribute("encodedRequestUrl", encodedRequestUrl);

		request.setAttribute("afterLoginUrl", requestUrl);
		request.setAttribute("encodedAfterLoginUrl", encodedRequestUrl);

		request.setAttribute("paramMap", param);
		request.setAttribute("paramJson", paramJson);

		int loginedMemberId = 0;
		Member loginedMember = null;

		String authkey = request.getParameter("authKey");

		if (authkey != null && authkey.length() > 0) {
			loginedMember = memberService.getMemberByAuthKey(authkey);

			if (loginedMember == null) {
				request.setAttribute("authkeyStatus", "invalid");
			} else {
				request.setAttribute("authkeyStatus", "valid");
				loginedMemberId = loginedMember.getId();
			}
		} else {
			HttpSession session = request.getSession();
			request.setAttribute("authkeyStatus", "none");

			if (session.getAttribute("loginedMemberId") != null) {
				loginedMemberId = (int) session.getAttribute("loginedMemberId");
				loginedMember = memberService.getMember(loginedMemberId);
			}
		}

		// 로그인 여부에 관련된 정보를 request에 담는다.
		boolean isLogined = false;
		boolean isAdmin = false;

		if (loginedMember != null) {
			isLogined = true;
			isAdmin = memberService.isAdmin(loginedMemberId);
		}

		request.setAttribute("loginedMemberId", loginedMemberId);
		request.setAttribute("isLogined", isLogined);
		request.setAttribute("isAdmin", isAdmin);
		request.setAttribute("loginedMember", loginedMember);

		return HandlerInterceptor.super.preHandle(request, response, handler);
	}
}