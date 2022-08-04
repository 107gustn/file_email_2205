<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" 
                              prefix="c" %>
<c:set var="contextPath" value="${pageContext.request.contextPath }"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

	<script src="http://code.jquery.com/jquery-latest.js"></script>
	<script type="text/javascript">
		var cnt=1;//파일 업로드 name값을 다르게 하는 변수
		function addFile(){
			$("#addfile").append("<div id='"+cnt+"'><input type='file' name='file"+cnt+"' >"
			         +"<input type='button' value='삭제' onclick='delFile()'> </div>")
			cnt++
		}
		function delFile(){
			cnt--
			$("#"+cnt).remove()
		}
	</script>
	
</head>
<body>uploadForm.jsp<br>

	<h1>파일 업로드</h1>
	
	<form action="${contextPath }/upload2" enctype="multipart/form-data" method="post">
		<input type="text" name="id" placeholder="id"><br>
		<input type="text" name="name" placeholder="name"><br>
		<input type="button" value="파일추가" onclick="addFile()"><br>
		<div id="addfile"></div>
		<input type="submit" value="업로드">
	</form>
	
	<a href="${contextPath }/views02">파일 보기</a>
	
</body>
</html>