<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>/security/admin page</h1>
	<!-- principal : 현재사용자 
	hasRole(role) : 해당 권한이 있으면 true
	hasAuthority(Authority)
	--
	hasAnyRole(role,role2) : ()안에 있는 권중 하나라도 가지욌으면 true
	hasAnyAuthority(Auth1,Auth2)
	--
	permitAll: 모든사용자허용
	denyAll: 모든 사용자 거부
	isAnonymous() : 익명의 사용자인경우(로그인 하지않은경우도 포함) true
	isAuthenticated() : 인증된 사용자면 true
	-->
	
	<p>현재 사용자 정보: <sec:authentication property="principal"/>
	<p>MemberVO: <sec:authentication property="principal.member"/>
	<p>사용자이름: <sec:authentication property="principal.member.userName"/>
	<p>사용자아이디: <sec:authentication property="principal.username"/>
	<p>사용자권한리스트: <sec:authentication property="principal.member.authList"/>
	<!-- 인증된 사용자의 경우에만 안에 꺼 보임 -->
	<sec:authorize access="isAuthenticated()">
	<a href="/customLogout">logout</a>
	</sec:authorize>
	<!-- 아무나 사용자의 경우에만 안에 꺼 보임 -->
	<sec:authorize access="isAnonymous()">
	<a href="/customLogout">logout</a>
	</sec:authorize>
</body>
</html>