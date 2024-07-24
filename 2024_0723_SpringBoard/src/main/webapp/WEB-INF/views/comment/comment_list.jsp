<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %> 

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<!-- for(CommentVo vo : list) -->
<c:forEach var="vo" items="${ list }">
	<div>${ vo.mem_name }</div>
	<div>${ vo.cmt_regdate }</div>
	<div>${ vo.cmt_content }</div>
	<hr>
</c:forEach>

</body>
</html>