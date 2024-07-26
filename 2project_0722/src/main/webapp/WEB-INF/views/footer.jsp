<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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

 

<style type="text/css">
body {
	font-family: 'Noto Sans KR', sans-serif;
}

footer{
	margin-top: 50px;
	width:100%;
}
.foot{
	font-size:13px;
 	background: #F7F9FA;
 	color:#54585C;
	padding : 20px;
	padding-left:100px;
	padding-right:100px;
}

a {
	text-decoration-line: none;
	color:#2F3438;
}



</style>


</head>
<body>
<footer>
		<div class="row gy-4 foot">
			<div class="col-sm-6 col-sm-6 footer-links">
				<h4>회사소개</h4>
				<p>(주)스위트홈</p>
				<p>서울 관악구 남부순환대로 1820 에그엘로우 빌딩 7층</p>
				<p>
					이메일 <a href="#">SweetHome@naver.com</a>
				</p>
				<p>
					<a href="#">개인정보 처리방침</a>
				</p>

			</div>
			<div class="col-sm-3 col-sm-3 footer-about">
				<h4 class="d-flex align-items-center">CateGory</h4>
				<p>
					<a href="${pageContext.request.contextPath}/main.do?p_cate=침대">침대</a>
				</p>
				<p>
					<a href="${pageContext.request.contextPath}/main.do?p_cate=소파">소파</a>
				</p>
				<p>
					<a href="${pageContext.request.contextPath}/main.do?p_cate=식탁">식탁</a>
				</p>
				<p>
					<a href="${pageContext.request.contextPath}/main.do?p_cate=의자">의자</a>
				</p>
				<p>
					<a href="${pageContext.request.contextPath}/main.do?p_cate=수납장">수납장</a>
				</p>
			</div>
			<div class="col-sm-3 col-sm-3 footer-about">
				<h4><a href="main.do?p_cate=침대" class="d-flex align-items-center" style="color:#2F3438;">Sweet Home</a></h4>
					<p>김수만</p>
					<p>손유정</p>
					<p>송은호</p>
					<p>천태광</p>
					<p>한지혜</p>
				</div>
			</div>

		</div>
	</footer>
	

</body>
</html>


