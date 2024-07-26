<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="resources/css/common.css">

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>


<title>카테고리 입력폼 변경</title>
<style type="text/css">
   .input-container {
       margin-top: 20px;
   }
   
body {
	margin: 40px 10px;
	padding: 0;
	font-size: 14px;
}
#calendar {
	max-width: 1100px;
	margin: 0 auto;
}

#memo-form-container {
	margin-top: 20px;
	text-align: center;
}


.box {
	width: 800px;
	height: 600px;
	margin: auto;
	display: flex;
	justify-content: center;
	align-items: center;
	overflow: hidden;
}

.container {
	width: 80%;
}

/* .th_h{
	margin-top:15px !important;
} */
.container th {
	/* color:red !important; */
	font-size: 18px;
	text-align:center;
	color:#3C4043 !important;
	padding:20px !important;
	vertical-align: middle;
	margin:0px !important;
}
.container td {
	padding:20px !important;
	
}

.container td, .container th{
	border:none !important;
}




/*  diary-btn-yellow  */
.diary-btn-yellow {
	background-color: #FADA5A;
	position: relative;
	padding: 15px 30px;
	border-radius: 15px;
	border: none;
	text-decoration: none;
	font-weight: 600;
	transition: 0.25s;
	letter-spacing: 2px;
	width: 200px;
}

.diary-btn-yellow:hover {
	transform: scale(1.1);
	cursor: pointer;
}

.diary-btn-yellow:active {
	transform: scale(0.9);
}
</style>
    
<script>
	function showForm(category) {
	    const container = document.getElementById('input-container');
	    container.innerHTML = ''; // 기존의 내용 제거
	
	    let formHtml = '';
	
	    if (category === 'weight') {
	        formHtml = `
        	<form action="diary_insert_weight.do" method="post">
	        	<table class="table">
				<tbody><tr>
					<th>날짜</th>
					<td><input class="form-control form-control-lg" type="text" name="w_rdate" value=${ select_date }></td>
				</tr>
				<tr>
					<th>반려동물</th>
					<td><input class="form-control form-control-lg" type="number" name="p_idx" ></td>
				</tr>
				<tr>
					<th>체중(kg)</th>
					<td><input class="form-control form-control-lg" type="number" name="w_weight" step="0.01"></td>
				</tr>
				
				
			</tbody></table>
			<div class="container" style="text-align: center;">
				<input class="diary-btn-yellow" type="submit" value="추가">
			</div>
			</form>
			
	        `;
	    } else if (category === 'walk') {
	        formHtml = `
	            <label for="walk">산책 거리:</label>
	            <input type="text" id="walk" name="walk">
	        `;
	    }
	
	    container.innerHTML = formHtml;
	}
</script>
</head>


<body>
    <h1>카테고리 선택</h1>
    <button onclick="showForm('weight')">체중</button>
    <button onclick="showForm('stoll')">산책</button>

	<div class="container">
	    <div id="input-container" class="input-container">
	        <!-- 입력 폼이 여기에 동적으로 삽입됩니다. -->
	    </div>
	</div>


</body>
</html>