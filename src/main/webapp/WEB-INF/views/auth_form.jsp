<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

	<c:if test="${userid == null }">
		<a href="auth">이메일 인증</a>
	</c:if>
	<c:if test="${userid != null }">
		<h3>${userid}님 이메일 인증 되었습니다</h3>
	</c:if>

</body>
</html>