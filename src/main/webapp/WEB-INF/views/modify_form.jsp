<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

	<script src="http://code.jquery.com/jquery-latest.min.js"></script>
	<script type="text/javascript">
		function readURL(input) { //첨부파일 이미지 미리보기
			var file = input.files[0] //파일에 대한 정보
			console.log(file)
			if (file != '') { //파일이 있다면
				var reader = new FileReader(); //해당하는 파일 정보를 불러옴
				reader.readAsDataURL(file); //파일의 정보를 토대로 파일을 읽고 
				reader.onload = function (e) { // 파일 로드한 값을 표현한다
				//e : 이벤트 안에 result값이 파일의 정보를 가지고 있다.
				console.log( e.target.result ) //파일에 대한 위치정보
				$('#preview').attr('src', e.target.result); //해당하는 위치 src에 값이 들어감
				}
			}
		}  
	</script>

</head>
<body>modify_form
	
	<form action="modify" method="post" enctype="multipart/form-data">
		<input type="hidden" value="${dto.imgName }" name="origin" >
		<input type="text" name="id" readonly value="${dto.id }"><br>
		<input type="text" name="name" value="${dto.name }"><br>
		<input type="file" name="file" onchange="readURL(this)"> <!-- this에 file에 대한 위치정보, 이름이 들어간다 -->
		<img src="download?file=${dto.imgName }" id="preview" width="100px" height="100px">
		<hr>
		<input type="submit" value="수정">
	</form>

</body>
</html>