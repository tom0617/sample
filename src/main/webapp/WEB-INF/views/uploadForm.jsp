<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="uploadFormAction" method="post" enctype="multipart/form-data">
<!-- multipart 속성 : 여러개 파일 처리 가능 -->
<input type="file" name="uploadFile" multiple>
<input type="submit" value="전송">

</form>

</body>
</html>