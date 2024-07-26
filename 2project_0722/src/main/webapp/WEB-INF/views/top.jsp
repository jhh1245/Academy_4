<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="vo.*"%><%-- 
<%@page import="util.Util"%> --%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>


<!-- 공통 css 부분 링크 -->
<link rel="stylesheet" href="resources/css/common.css">


<script type="text/javascript">
	function post_insert() {
		// 로그인 체크 (안되어 있으면)
		if ("${empty member}" == "true") {

			if (confirm("글쓰기는 로그인 후 가능합니다.\n로그인 하시겠습니까?") == false) {
				return;
			}

			// 로그인 폼으로 이동 
			location.href = "login.jsp"; //현재 경로는 photo니까
			return;
		}

		// context Path를 구하는 방법 
		let hostIndex = location.href.indexOf(location.host)
				+ location.host.length;
		let contextPath = location.href.substring(hostIndex, location.href
				.indexOf('/', hostIndex + 1));

		// 로그인이 된 경우 => 사진 등록 폼으로 이동 
		location.href = contextPath + "/post/insert_form.do";
	}//end:photo_insert()
</script>


<style>
header {
	background-color: #FBE297;
	height:60px !important;
}
.d-flex
</style>


</head>
<body>

	<!--화면 메인 네비게이션바 -->
	<header class="">
		<nav class="navbar navbar-expand-sm">
			<div class="container-fluid">
				<a class="navbar-brand" href=""><img src="test.png"
					width="100px"></img>로고이미지/로고명</a>

				<div class="collapse navbar-collapse" id="mynavbar">

					<ul class="navbar-nav ms-auto">
						<%-- <% 
						MemberVo mv = (MemberVo) session.getAttribute("member");
						if (mv != null && mv.getM_type() == 1){	%> <!-- 일반회원이 로그인한 경우 -->
		
						<li class="nav-item"><div class="nav-link">${ sessionScope.member.m_name }님 환영합니다.</div></li>
						<li class="nav-item"><a class="nav-link" id="nav-link-admin" href="${pageContext.request.contextPath}/JSP/mypage/mypage.do">마이페이지</a></li>
						<li class="nav-item"><a class="nav-link" id="link1" href="${pageContext.request.contextPath}/logout.do">로그아웃</a></li>
						
					<% } else if (mv != null && mv.getM_type() == 2){ %> <!-- 관리자가 로그인한 경우 -->
						<li class="nav-item"><div class="nav-link">${ sessionScope.member.m_name }님 환영합니다.</div></li>
						<li class="nav-item"><a class="nav-link" id="nav-link-admin" href="${pageContext.request.contextPath}/admin/memberlist.do">관리자페이지</a></li>
						<li class="nav-item"><a class="nav-link" id="link1" href="${pageContext.request.contextPath}/logout.do">로그아웃</a></li>

					<%	} else { %> --%>
						<!-- 로그인 안 한 경우 -->
						<li class="nav-item">
							<form class="d-flex" style="margin-right: 50px;">
								<input class="form-control me-2" type="search"
									placeholder="검색어를 입력하세요." aria-label="Search"
									style="background-color: #FBE297; border-color: white;">
								<button class="btn" type="submit"
									style="background-color: #FBE297; border-color: white; width: 100px;">Search</button>
							</form> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						</li>
						<li class="nav-item"><a class="nav-link" id="link1" href="">로그인</a></li>

						<li class="nav-item"><a class="nav-link" id="link2" href="">회원가입</a></li>


						<%-- <% } 
						
						
						%> --%>
						<!-- <li class="nav-item">
							<button type="button" class="button1" onclick="post_insert();">블로그
								글쓰기</button>
						</li> -->
					</ul>

				</div>
			</div>
		</nav>

	</header>

</body>
</html>