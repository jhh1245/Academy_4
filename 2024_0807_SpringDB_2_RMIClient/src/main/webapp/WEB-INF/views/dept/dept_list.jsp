<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
  <!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<style type="text/css">
#box {
	width:600px;
	margin:auto;
	margin-top: 50px;
	box-shadow: 2px 2px 2px black;
}
table {
	border: 2px solid blue !important; <!-- 강제 부트스트랩 보다 우선시 -->
}
</style>
</head>

<body>
부서목록...
<!-- 그래서 여기서 request에 담은 list를 꺼내올 수 있다. -->
<div id="box"> 
	<table border="1" class="table table-bordered">
		<tr class="info">
			<th>부서번호</th>
			<th>부서명</th>
			<th>부서위치</th>
		</tr>
		
		<!-- for(DeptVo vo : list)와 동일함 -->
		<c:forEach var="vo" items="${requestScope.list}"><!-- el문법만 가능. el은 scope에 저장된것만 사용가능하다. -->
			<!-- 여기에 쓰는 임시변수는 pageContext에 저장된다. -->
			<!-- el은 requestScope, jsp는 request로 표현한다. -->
			<!-- requestScope는 생략 가능 -->
			<tr>
				<td>${pageScope.vo.deptno}</td>
				<td>${vo.dname}</td>
				<td>${vo['loc']}</td>
			</tr>
		</c:forEach>
	</table>
</div>
</body>
</html>