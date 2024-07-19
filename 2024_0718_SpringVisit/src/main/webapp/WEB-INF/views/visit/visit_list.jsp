<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!-- JSTL Library -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- 반복문 쓰려고  -->
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<meta charset="UTF-8">
<title>Insert title here</title>

<%-- 현재 컨텍스트 경로를 구하는 식 : ${pageContext.request.contextPath}  --%>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/visit.css">
<script type="text/javascript">
	function del(f){
		let c_pwd = f.c_pwd.value.trim(); // 내가 입력한 비번 // forEach에 있는 vo안에 pwd 
		let no    = f.no.value;
		let idx   = f.idx.value;
		
		if(c_pwd==''){
			alert("삭제할 게시물의 비밀번호를 입력하세요.");
			f.c_pwd.value="";
			f.c_pwd.focus();
			return;
		}
		
		// jQuery Ajax : 비밀번호 체크 
		$.ajax({
			// 요청 옵션
			url		 : 	"check_pwd.do",				// VisitCheckPwdAction 서블릿 
			data 	 : 	{"idx":idx, "c_pwd":c_pwd}, // parameter : check_pwd.do?idx=5&c_pwd=1234
					
			// 응답 옵션		
			dataType : "json",
			success  : function(res_data){
				// res_data = {"result":true} or {"result":false}
				if(res_data.result == false){
					// 비번 틀린 경우
					alert("삭제할 게시물의 비밀번호가 틀립니다.");
					return ;
				}
				
				// 삭제 확인 (꼭하기)
				if(confirm("정말삭제하시겠습니까?") == false) return;
				
				// 삭제처리..
				// location.href=`delete.do?idx=\${idx}&no=\${no}`; // js 안의 idx와 no 
				location.href="delete.do?idx=" + idx + "&no=" + no;
			}, 
			error    : function(err){
				alert(err.responseText);
			}
			
		});
		
		
		
	} // end:del
	
	
	// 수정폼 띄우기 (요청)
	function modify_form(f){
		let c_pwd = f.c_pwd.value.trim(); // 내가 입력한 비번 // forEach에 있는 vo안에 pwd 
		let no    = f.no.value;
		let idx   = f.idx.value;
		
		if(c_pwd==''){
			alert("수정할 게시물의 비밀번호를 입력하세요.");
			f.c_pwd.value="";
			f.c_pwd.focus();
			return;
		}
		
		// jQuery Ajax : 비밀번호 체크 
		$.ajax({
			// 요청 옵션
			url		 : 	"check_pwd.do",				// VisitCheckPwdAction 서블릿 
			data 	 : 	{"idx":idx, "c_pwd":c_pwd}, // parameter : check_pwd.do?idx=5&c_pwd=1234
					
			// 응답 옵션		
			dataType : "json",
			success  : function(res_data){
				// res_data = {"result":true} or {"result":false}
				if(res_data.result == false){
					// 비번 틀린 경우
					alert("수정할 게시물의 비밀번호가 틀립니다.");
					return ;
				}
				
				// 수정 확인 (꼭하기)
				if(confirm("정말 수정 하시겠습니까?") == false){
					
					return;
				}
				
				
				// 수정폼 띄우기
				//location.href="modify_form.do?idx=" + idx + "&no=" + no;
				
				// JSP 내에서 back-tic사용시 자바스크립트 변수 표현 : \${ 자바스크립트 변수 }, 
				//                        \가 없을 경우 EL이다.
				location.href=`modify_form.do?idx=\${ idx }&page=${ empty param.page ? 1 : param.page }&search=${empty param.search ? 'all' : param.search}&search_text=${param.search_text}`;
			}, 
			error    : function(err){
				alert(err.responseText);
			}
			
		});
		
	}  // end:modify_form()
	
	
	
	function find(){
		let search = $("#search").val();
		let search_text = $("#search_text").val().trim();
		
		
		// 전체검색이 아니고, 검색어가 비어있으면 
		if(search!= "all" && search_text == ""){
			alert("검색어를 입력하세요!");
			
			$("#search_text").focus();
			return ;
		}
		
		// 자바스크립트 이용해서 호출
		location.href="list.do?search=" + search + "&search_text=" + encodeURIComponent(search_text, "utf-8");
		
	}

</script>

<script type="text/javascript"> 
//초기화 관련 스크립트

$(document).ready(function(){
	
	if("${not empty param.search}" == "true"){ // 파라미터가 있으면  
		$("#search").val("${ param.search }"); // 이름, 내용, 이름+내용 선택한 것이 유지되도록
		
		// 전체보기를 선택한 경우 입력창을 지우기
		if("${param.search eq 'all'}" == "true"){
			$("#search_text").val("");
			
		}
	}
	
});

</script>

</head>
<body>
	<div id="box">
		<h1 id="title" style="margin-top: 30px;">♬방명록♬</h1>
		<div style="margin-bottom: 30px;">
			<input class="btn btn-info" type="button" value="글쓰기" onclick="location.href='insert_form.do'">
			<!--  visit 폴더가 현재위치니까 생략해야됨  -->
		</div>

		<!-- 검색메뉴 -->
		<div style="text-align:right; margin-bottom:5px;">
			<form class="form-inline">
				<select id="search" class="form-control">
					<option value="all">전체보기</option>
					<option value="name">이름</option>
					<option value="content">내용</option>
					<option value="name_content">이름+내용</option>
				</select>
				
				<input id="search_text" class="form-control" value="${param.search_text}">
				<input type="button" value="검색" class="btn btn-info" onclick="find();">
			</form>
		</div>
		
		

		<!-- 방명록에 목록 없을 경우 -->
		<c:if test="${empty requestScope.list}">
			<!-- page 다음 request > .. > .. 를 참고한다. 그래서 생략 가능 -->
			<div id="empty_msg">등록된 게시물이 없습니다.</div>
		</c:if>
		
		
		<!-- 있을 경우 -->
		<!-- for(VisitVo vo : list) 동일 -->
		<c:forEach var="vo" items="${requestScope.list }">
			<form class="form-inline"> <!-- 한줄에 다 차지하지 않도록 -->
			
				<input type="hidden" name="idx" value="${vo.idx}">
				<input type="hidden" name="no" value="${vo.no }">
				
				<div class="panel panel-info" name="p_${vo.no}"> <!-- 목록 하나 지우면 다음 번 목록이 그 번호가 된다. 3번삭제 => 기존 4번이 3번이 되니까 -->
					<div class="panel-heading">
						<h4>
							<b>${vo.name}</b>님의 글
						</h4>
						
					</div>
					<div class="panel-body">
						<div class="mycommon content">${vo.content}</div>
						<div class="mycommon regdate">작성일 : ${fn:substring(vo.regdate,0,16)}</div>
						<div class="mycommon pwd">
							비밀번호(${vo.pwd}) : <input class="form-control" type="password" name="c_pwd">
									<input type="button" class="btn btn-info" value="수정" 
											onclick="modify_form(this.form);">
									<input type="button" class="btn btn-danger" value="삭제" 
											onclick="del(this.form);">
								<!-- this는 input 태그를 의미하는데, 이것의 form은 = forEach문 돌 때 1개의 form 정보를 넘긴다. -->
						</div>
					</div>
				</div>
			</form>
		</c:forEach>
		
		<!-- 페이지 메뉴 -->
		${ pageMenu }
		
		
	</div>

</body>
</html>