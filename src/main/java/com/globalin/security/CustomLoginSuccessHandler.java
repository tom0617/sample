package com.globalin.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

/*
 * 로그인 처리하다보면 로그인 성공 이후에 특정한 동작을 하도록 제어하고 싶은 경우
 * admi 계정 로그인 ==> admin 페이지로 이동
 * member 계정 로그인 ==> member 페이지로 이동
 * 쿠키설정등등
 * 
 * 이런경우 AuthenticationSuccessHandler 인터페이스로 구현해서 설정
 */
public class CustomLoginSuccessHandler implements AuthenticationSuccessHandler{

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {

		
		//로그인 성공시 실행되는 메소드
		//admin은 admin 페이지로 member 는 member페이지로
		
		//로그인 성공한 사용자의 역할(권한)들을 저장
		List<String> roleNames = new ArrayList<>();
		
		//현재 로그인한 사용자의 권한 알아내는 방법
		for(GrantedAuthority authority : authentication.getAuthorities()) {
			//리스트에 사용자의 권한 하나씩 추가
			roleNames.add(authority.getAuthority());
		}
		//사용자의 역할 목록을 검사해서 역할에 따라 redirect로 맞는 페이지에 보내주면 된다.
		
		if(roleNames.contains("ROLE_ADmin")) {
			response.sendRedirect("/security/admin");
			return;//메소드 종료
		}
		if(roleNames.contains("ROLE_MEMBER")) {
			response.sendRedirect("/security/member");
			return;
		}
		
		//여기까지 오면 아무 권한도 없음
		response.sendRedirect("/");
		
		
		
		
		
	}

}
