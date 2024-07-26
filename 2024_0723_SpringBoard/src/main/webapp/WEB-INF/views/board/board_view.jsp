<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<!--  Bootstrap  3.x  -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

<style type="text/css">
   #box{
      width: 800px;
      margin: auto;
      margin-top: 20px;
   }
   
   .common{
      border: 1px solid #cccccc;
      padding: 5px;
      margin-bottom: 5px;
      box-shadow: 1px 1px 1px #333333;
   }
   
   .content{
      min-height: 150px;
   }
   
   #cmt_content{
      width: 100%;
      height: 80px;
      resize: none;
      
   }
   
   #btn_cmt_register{
      width: 100%;
      height: 80px;
   }
   
</style>

<script type="text/javascript">
	function del(){
		if(confirm("정말 삭제하시겠습니까?") == false) return;
		location.href = "delete.do?b_idx=${ vo.b_idx }";
		
	}

</script>



<script type="text/javascript">
/* 댓글쓰기 기능  */
function comment_insert(){
	   
	   //로그인이 안되었으면
	   if("${ empty user }" == "true"){
		   
		   if(confirm("로그인후 댓글쓰기가 가능합니다\n로그인 하시겠습니까?")==false) return;
		   
		   //alert(location.href);
		   // 로그인폼으로 이동
		   location.href="../member/login_form.do?url=" + encodeURIComponent(location.href) ;
		   
		   return;
	   }
	   
	   
	   // 댓글쓰기 작성
	   let cmt_content = $("#cmt_content").val().trim();
	   if(cmt_content==''){
		   alert("댓글내용을 입력하세요!!");
		   $("#cmt_content").val("");
		   $("#cmt_content").focus();
		   return;
	   }
	   
	   //Ajax통해서 댓글 등록
	   $.ajax({
		   url		:	"../comment/insert.do",
		   data		:	{
			              "cmt_content": cmt_content,
			              "b_idx":"${ vo.b_idx }",
			              "mem_idx":"${user.mem_idx}",
			              "mem_name":"${user.mem_name}"
			            },
		   dataType	:	"json",
		   success	:	function(res_data){
			   // res_data = {"result": true }
			   
			   //작성했던 댓글 입력창에서 지우기
			   $("#cmt_content").val("");
			   
			   
			   if(res_data.result==false){
				   alert("댓글등록 실패!!");
				   return;
			   }
			   
			   comment_list(1);
			   
		   },
		   error	:	function(err){
			   alert(err.responseText);
		   }
	   });
	   
}//end:comment_insert()


var g_page = 1; // 전역변수!!! 

//댓글목록 요청
function comment_list(page){
	   g_page = page;
	   $.ajax({
		   
		   url		:	"../comment/list.do",
		   data		:	{"b_idx":"${ vo.b_idx}", "page": page },
		   success	:	function(res_data){
			   
			   $("#comment_display").html(res_data);
			   
		   },
		   error	:	function(err){
			   alert(err.responseText);
		   }

	   });
	   
}//end:comment_list()


//초기화 : 시작시
$(document).ready(function(){
    
	   //현재 게시물에 달린 댓글목록 출력
	   comment_list(1);
});

</script>

</head>
<body>
  <div id="box">
        <!-- Bootstrap Panel -->
		<div class="panel panel-primary">
		     <div class="panel-heading"><h4><b>${ vo.mem_name }</b>님의 글</h4></div>
		     <div class="panel-body">
		        <div class="common subject">${ vo.b_subject }</div>
		        <div class="common content">${ vo.b_content }</div>
		        <div class="common regdate">${ vo.b_regdate }(${ vo.b_ip })</div>
		        
		        <div>
		           <input class="btn btn-primary" type="button"  value="목록보기"
		                  onclick="location.href='list.do'" >
		                  
		           <!-- 로그인된 상태에서만 등록가능 -->
		           <c:if test="${ (not empty user) and (vo.b_depth eq 0) }"> <!-- (vo.b_depth le 1) : 메인글 + 메인글 바로 하위만 댓글 달 수 있도록 -->
		           		<input class="btn btn-success" type="button"  value="답글쓰기"
		           		       onclick="location.href='reply_form.do?b_idx=${ vo.b_idx }'">
		           </c:if>
		           
		           <!-- 글의 주인만 수정/삭제 가능 -->
		           <c:if test="${ vo.mem_idx  eq  user.mem_idx }">
			           <input class="btn btn-info"    type="button"  value="수정하기"
			           		  onclick="modify_form( ' ${vo.b_idx} ' );">
			           <input class="btn btn-danger"  type="button"  value="삭제하기"
			           		  onclick="del();">
		           </c:if>
		           
		        </div>
		     </div>
		</div>


		<!-- 댓글 처리-->
		<div class="row">
			<div class="col-sm-10" >
			   <textarea rows="3" id="cmt_content" placeholder="로그인후에 댓글쓰기가 가능합니다"></textarea>
			</div>
			<div class="col-sm-2" >
			   <input id="btn_cmt_register" type="button"  value="댓글쓰기" onclick="comment_insert();">
			</div>
		</div>
		
		<hr>
		
		<div id="comment_display"></div>
  </div>
</body>
</html>