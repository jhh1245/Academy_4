<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">
<title>도서 목록</title>

<!--  Bootstrap 3.x  -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

<style type="text/css">
  img{
     width: 120px;
     height: 100px;
  }
  td{
    vertical-align: middle  !important;
  }

</style>

<script type="text/javascript">

  function showDescription(title,description){
	  
	  $("#myModal").modal({backdrop: "static"});
	  
	  $("#title").html(title);
	  $("#description").html(description);
	  
	  
  }

</script>
</head>
<body>

  <%@include file="modal.jsp" %>
  
	<table class="table">
		<tr class="success">
			<th>순번</th>
			<th>이미지</th>
			<th>도서명</th>
			<th>작가</th>
			<th>가격</th>
			<th>할인가</th>
			<th>설명</th>
		</tr>
		
		<!--  for(ProductVo vo : p_list)  -->
		<c:forEach var="vo" items="${b_list}" varStatus="i">
			<tr>
				<td>${vo.no}</td>
				<td><img src="${vo.image}"></td>
				<td><a href="${vo.link }">${vo.title }</a></td>
				<td>${vo.author}</td>
				<td><fmt:formatNumber type="currency" value="${vo.price }"/></td>
				<td><fmt:formatNumber type="currency" value="${vo.discount }"/></td>
				<td><input type="button" value="설명보기" onclick="showDescription(`${ vo.title }`,`${vo.description}`);"></td>
			</tr>
		</c:forEach>
		
	</table>
</body>
</html>