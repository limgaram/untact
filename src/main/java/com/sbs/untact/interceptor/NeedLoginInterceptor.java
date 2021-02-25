package com.sbs.untact.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component("needLoginInterceptor")
public class NeedLoginInterceptor implements HandlerInterceptor {
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// 이 인터셉터 실행 전에 beforeActionIntercepetor가 실행되고 거기서 isLogined라는 속성 생성
		// 그래서 여기서 단순히 request.getAttribute("isLogined"); 이것만으로 로그인 여부를 알 수 있음.
		boolean isLogined = (boolean) request.getAttribute("isLogined");

		// 이 인터셉터 실행 전에 beforeActionInterceptor가 실행되고 거기서 isAjax라는 속성 생성
		// 그래서 여기서 단순히 request.getAttribute("isAjax"); 이것만으로 해당 요청이 ajax인지 구분 가능
		boolean isAjax = true;

		if (isLogined == false) {
			if (isAjax == false) {
				response.setContentType("text/html; charset=UTF-8");
				response.getWriter().append("<script>");
				response.getWriter().append("alert('로그인 후 이용해주세요.');");
				response.getWriter().append("location.replace('/usr/member/login?redirectUri="
						+ request.getAttribute("encodedAfterLoginUri") + "');");
				response.getWriter().append("</script>");
				// return false; 이후에 실행될 인터셉터와 액션이 실행되지 않음
			} else {
				response.setContentType("applicatuib/json; charset=UTF-8");
				response.getWriter().append("{\"resultCode\":\"F-A\",\"msg\":\"로그인 후 이용해주세요.\"}");
			}

			return false;
		}

		return HandlerInterceptor.super.preHandle(request, response, handler);
	}
}
