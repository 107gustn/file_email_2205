<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	
	<form action="upload" method="post" enctype="multipart/form-data"> <!-- 파일전송은 용량때문에 무조건 post방식을 사용해야 한다 --> <!-- 파일전송시 enctype지정 필요 -->
		<input type="text" name="id"><br>
		<input type="text" name="name"><br>
		<input type="file" name="f"><br>
		<input type="submit" value="전송">
	</form>
	
	<a href="views">파일 보기</a>

</body>
</html>