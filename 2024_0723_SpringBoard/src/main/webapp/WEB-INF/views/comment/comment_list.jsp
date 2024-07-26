<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %> 

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<script type="text/javascript">
	function comment_delete(cmt_idx) {
		if(confirm("정말 삭제하시겠습니까?") == false) return;
	}
	
	$.ajax({
		url		  : "../comment/delete.do",
		data	  : {"cmt_idx" : cmt_idx},
		dataType  : "json", 
		success   : function(res_data){
			// res_data = {"result" : true} or {"result" : false}
			if(res_data.result == false){
				alert("삭제 실패했습니다.")
				return; 
			}
			
			comment_list(g_page); // g_page는 전역변수 board_view에 있는
			// board_view 맨 하단 <div>에 들어간다. <= 현재 문서는 전체가 다 여기 안에 들어감!!!!!!   
		},
		error	  : function(err){
			alert(err.responseText);
		}
	})
</script>

</head>
<body>

<!-- Page Menu -->

<c:if test="${ not empty list }">
<div style="font-size: 10px;">
   ${ pageMenu }
</div>   
</c:if>

<!-- for(CommentVo vo : list) -->
<c:forEach var="vo" items="${ list }">

	<!-- 자신의 글만 삭제 메뉴 활성화 -->
	<c:if test="${ user.mem_idx eq vo.mem_idx }">
		<div style="text-align: right;"><input type="button" value="x" onclick="comment_delete('${ vo.cmt_idx }');"></div>
	</c:if>	
	<div>${ vo.mem_name }</div>
	<div>${ vo.cmt_regdate }</div>
	<div>${ vo.cmt_content }</div>
	<hr>
</c:forEach>

</body>
</html>