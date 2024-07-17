<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<hr>
	객체로 받기 결과
	<hr>
	이름 : ${ requestScope.vo.name }<br>
	나이 : ${ vo.age }<br>
	전화번호 : ${ vo.tel }<br>
</body>
</html>