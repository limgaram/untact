package com.sbs.untact.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
	// beforeActionInterceptor 인터셉터 불러오기
	@Autowired
	@Qualifier("beforeActionInterceptor")
	HandlerInterceptor beforeActionInterceptor;

	// needToLoginInterceptor 인터셉터 불러오기
	@Autowired
	@Qualifier("needToLoginInterceptor")
	HandlerInterceptor needToLoginInterCeptor;

	// needToLoginoutInterceptor 인터셉터 불러오기
	@Autowired
	@Qualifier("needToLogoutIntercepter")
	HandlerInterceptor needToLogoutIntercepter;

	// 이 함수는 인터셉터를 적용하는 역할을 합니다.
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// beforeActionInterceptor 인터셉터가 모든 액션 실행 전에 실행되도록 처리
		registry.addInterceptor(beforeActionInterceptor).addPathPatterns("/**").excludePathPatterns("resource/**");

		// 로그인 필요
		registry.addInterceptor(needToLoginInterCeptor)
			.addPathPatterns("/**")
			.excludePathPatterns("/")
			.excludePathPatterns("/resource/**")
			.excludePathPatterns("/usr/home/main")
			.excludePathPatterns("/usr/member/login")
			.excludePathPatterns("/usr/member/doLogin")
			.excludePathPatterns("/usr/member/join")
			.excludePathPatterns("/usr/member/doJoin")
			.excludePathPatterns("/usr/article/list")
			.excludePathPatterns("/usr/article/detail")
			.excludePathPatterns("/usr/member/findLoginId")
			.excludePathPatterns("/usr/member/doFindLoginId")
			.excludePathPatterns("/usr/member/findLoginPw")
			.excludePathPatterns("/usr/member/doFindLoginPw")
			.excludePathPatterns("/usr/file/test*")			
			.excludePathPatterns("/usr/file/doTest*")
			.excludePathPatterns("/usr/test/**")
			.excludePathPatterns("/error");

		// 로그인 상태에서 접속학 수 없는 URL 전부 기술
		registry.addInterceptor(needToLogoutIntercepter)
			.addPathPatterns("/usr/member/login")
			.addPathPatterns("/usr/member/doLogin")
			.addPathPatterns("/usr/member/join")
			.addPathPatterns("usr/member/doJoin");
	}
}
