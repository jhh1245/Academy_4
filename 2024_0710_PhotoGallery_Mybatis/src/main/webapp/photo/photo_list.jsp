<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<!--  Bootstrap  3.x  -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>


<style type="text/css">
#box {
	width: 800px;
	margin: auto;
	margin-top: 30px;
}

#title {
	text-align: center;
	font-size: 32px;
	font-weight: bold;
	color: skyblue;
	/* text-shadow: 1px 1px 1px black; */
	margin-bottom: 25px;
}

#photo-box {
	height: 600px;
	border: 2px solid skyblue;
	margin-top: 10px;
	/* 상하스크롤 */
	overflow-y: scroll;
}

.photo {
	width: 140px;
	height: 180px;
	border: 1px solid skyblue;
	margin: 20px;
	padding: 10px;
	float: left;
}

  .photo:hover{
     border-color: red;
  }
  
.photo>img {
	width: 120px;
	height: 120px;
	border: 1px solid gray;
	outline: 1px solid black;
}

.title {
	width: 120px;
	border: 1px solid gray;
	outline: 1px solid black;
	padding: 5px;
	margin-top: 5px;
	
	/* ellipsis : 글이 길어지면 줄임표로 표시 */
     overflow: hidden;
	 white-space: nowrap;
	 text-overflow: ellipsis;
	 word-break: break-all;
}
</style>

<script type="text/javascript">
	function photo_insert(){
		
		// 로그인 체크 (안되어 있으면)
		if("${empty user}" == "true"){
			if(confirm("사진 등록은 로그인 후 가능합니다.\n로그인 하시겠습니까?") == false){
				return
			}
			
			// 로그인 폼으로 이동 
			location.href = "../member/login_form.do"; //현재 경로는 photo니까
			return;
		}
		
		// 로그인이 된 경우 => 사진 등록 폼으로 이동 
		location.href = "insert_form.do"; // PhotoInsertForm Action 
	}//end:photo_insert()
	
	
	function showPhoto(p_idx){
		
		// alert(p_idx + " 그림정보 조회");
		$("#photoModal").modal({backdrop:"static"}); //static : 닫기 누르기전까지는 밖에 다른부분 눌러도 안닫힘 
		
		// p_idx에 대한 사진정보 가져오기(Ajax이용 JSON형식)
		$.ajax({
			url		:	"photo_one.do",     //PhotoOneAction
			data	:	{"p_idx": p_idx},   //parameter => photo_one.do?p_idx=5
			dataType:	"json",
			success	:	function(res_data) {
				// res_data = {"p_idx":5, "p_title":"제목" , "p_content":"내용" , "p_filename":"a.jpg",....}
				console.log(res_data);
				
				//download받을 화일명...
				g_p_filename = res_data.p_filename;
				g_p_idx      = res_data.p_idx;
				
				$("#pop_mem_name").html("올린이 : " + res_data.mem_name);
				$("#pop_image").prop("src","../images/" + res_data.p_filename);
				$("#pop_title").html(res_data.p_title);
				$("#pop_content").html(res_data.p_content);
				$("#pop_regdate").html(res_data.p_regdate);
				
				
				//버튼 보여지기 유/무
				//버튼숨기기 (일단은 숨기고 상황에 따라서 보이도록)
				$("#btn_popup_download").hide();//다운
				$("#btn_popup_update").hide();//수정
				$("#btn_popup_delete").hide();//삭제

				//로그인된 상태에는 다운로드 가능
				if("${ not empty user}" == "true"){
					$("#btn_popup_download").show();
				}
				
				//현재 사진을 올린 유저가 로그인한 유저면(수정/삭제)
				//alert("${ user.mem_idx }" == res_data.mem_idx);
				if("${ user.mem_idx }" == res_data.mem_idx){
					
					$("#btn_popup_update").show();//수정
					$("#btn_popup_delete").show();//삭제
				}
			},
			error	:	function(err){
				alert(err.responseText);
			}
		});
	}

</script>
</head>
<body>

	<!-- popup: Modal -->
	<%@include file="popup.jsp" %>

	<div id="box">
		<h1 id="title">::::PhotoGallery::::</h1>

		<!-- 메뉴 -->
		<div class="row">
			<div class="col-sm-6">
				<input class="btn btn-primary" type="button" value="사진올리기" onclick="photo_insert();">
			</div>
			<div class="col-sm-6" style="text-align: right;">
				<!-- 로그인이 안된 경우 -->
				<c:if test="${empty user }">
					<input class="btn btn-primary" type="button" value="회원가입"
						onclick="location.href='../member/insert_form.do'">
					<input class="btn btn-primary" type="button" value="로그인"
						onclick="location.href='../member/login_form.do'">
				</c:if>
				
				<!-- 로그인이 된 경우 -->
				<c:if test="${not empty user}">
					<input class="btn btn-primary" type="button" value="로그아웃"
						onclick="location.href='../member/logout.do'">
				</c:if>
			</div>
		</div>

		<div id="photo-box">
		   <%-- <c:forEach begin="1"  end="30" > --%>
		   <!-- for(PhotoVo  vo: list )  -->
		   <c:forEach var="vo"  items="${ list }">
			    <div class="photo" onclick="showPhoto('${ vo.p_idx }');">
			       <img src="../images/${ vo.p_filename }">
			       <div class="title">${ vo.p_title }</div>
			    </div>
		   </c:forEach> 
		</div>
	</div>
</body>
</html>