<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>/security/all page</h1>
<!-- 인증된 사용자의 경우에만 안에 꺼 보임 -->
	<sec:authorize access="isAuthenticated()">
	<a href="/customLogout">logout</a>
	</sec:authorize>
	<!-- 아무나 사용자의 경우에만 안에 꺼 보임 -->
	<sec:authorize access="isAnonymous()">
	<a href="/customLogin">login</a>
	</sec:authorize>
</body>
</html>