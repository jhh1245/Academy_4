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
					<th>회원</th>
					<td><input class="form-control form-control-lg" type="number" name="m_idx" ></td>
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
				<input class="diary-btn-yellow" type="submit" value="등록">
			</div>
			</form>
			
	        `;
	    } else if (category === 'stoll') {
	        formHtml = `
	        	<form action="diary_insert_stoll.do" method="post">
				<table class="table">
					<tbody><tr>
						<th>날짜</th>
						<td><input class="form-control form-control-lg" type="text" name="s_rdate" value=${ select_date }></td>
					</tr>
					<tr>
						<th>반려동물</th>
						<td><input class="form-control form-control-lg" type="text" name="p_idx"></td>
					</tr>
					<tr>
						<th>시작시간</th>
						<td><input class="form-control form-control-lg" type="time" name="s_stime"></td>
					</tr>
					<tr>
						<th>종료시간</th>
						<td><input class="form-control form-control-lg" type="time" name="s_etime"></td>
					</tr>
					<tr>
						<th>거리(m)</th>
						<td><input class="form-control form-control-lg" type="number" name="s_distance"></td>
					</tr>
					<tr>
						<th>메모</th>
						<td>
							<div class="">
								<textarea class="form-control form-control-lg" id="s_memo" name="s_memo" rows="3" placeholder="내용을 입력해주세요." oninput="this.style.height=&quot;&quot;, this.style.height= this.scrollHeight + &quot;px&quot;"></textarea>
							</div>
						</td>
					</tr>
					</tbody></table>
					
					<div class="container" style="text-align: center;">
					<input class="diary-btn-yellow" type="submit" value="등록">
					</div>

			</form>
				
		        `;
	    } else if (category === 'feeding') {
	        formHtml = `
	        	<form action="diary_insert_feeding.do" method="post">	   
				<table class="table">
					<tbody><tr>
						<th>날짜</th>
						<td><input class="form-control form-control-lg" type="text" name="f_rdate" value=${ select_date }></td>
					</tr>
					<tr>
						<th>반려동물</th>
						<td><input class="form-control form-control-lg" type="text" name="p_idx" value=></td>
					</tr>
					<tr>
						<th>카테고리</th>
						<td><select class="form-control  form-control-lg" id="f_type" name="f_type">
								<option value="">카테고리 선택</option>
								<option value="사료">사료</option>
								<option value="간식">간식</option>
								<option value="영양제">영양제</option>
						</select></td>
					</tr>
					<tr>
						<th>시간</th>
						<td><input class="form-control form-control-lg" type="time" name="f_time"></td>
					</tr>
					<tr>
						<th>제품명</th>
						<td><input class="form-control form-control-lg" type="text" name="f_pname"></td>
					</tr>
				</tbody></table>	
				
				<div class="container" style="text-align: center;">
					<input class="diary-btn-yellow" type="submit" value="등록">
				</div>
				
			</form>
				
		        `;
	    } else if (category === 'health') {
	        formHtml = `
	        	<form action="diary_insert_health.do" method="post">
				<table class="table">
					<tbody><tr>
						<th>날짜</th>
						<td><input class="form-control form-control-lg" type="text" name="h_rdate" value=${ select_date }></td>
					</tr>
					<tr>
						<th>반려동물</th>
						<td><input class="form-control form-control-lg" type="number" name="p_idx"></td>
					</tr>
					<tr>
						<th>카테고리</th>
						<td><select class="form-control  form-control-lg" id="h_type" name="h_type" required="">
								<option value="">카테고리 선택</option>
								<option value="진료">진료</option>
								<option value="접종">접종</option>
						</select></td>
					</tr>
					<tr>
						<th>시간</th>
						<td><input class="form-control form-control-lg" type="time" name="h_time"></td>
					</tr>
					<tr>
						<th>비용</th>
						<td><input class="form-control form-control-lg" type="number" name="h_cost"></td>
					</tr>
					<tr>
						<th>내용</th>
						<td><input class="form-control form-control-lg" type="text" name="h_content"></td>
					</tr>
					<tr>
						<th>병원명</th>
						<td><input class="form-control form-control-lg" type="text" name="h_hname"></td>
					</tr>
					<tr>
						<th>다음예약일</th>
						<td><input class="form-control form-control-lg" type="text" name="h_ndate" value=""></td>
					</tr>
				</tbody></table>
				
				<div class="container" style="text-align: center;">
					<input class="diary-btn-yellow" type="submit" value="등록">
				</div>
			</form>
				
		        `;
	    } else if (category === 'note') {
	        formHtml = `
	        	<form action="diary_insert_note.do" method="post">
				<table class="table">
					<tbody><tr>
						<th>날짜</th>
						<td><input class="form-control form-control-lg" type="text" name="o_rdate" value=${ select_date }></td>
					</tr>
					<tr>
						<th>반려동물</th>
						<td><input class="form-control form-control-lg" type="text" name="p_idx" ></td>
					</tr>
					<tr>
						<th>시간</th>
						<td><input class="form-control form-control-lg" type="time" name="o_time"></td>
					</tr>
					<tr>
						<th>메모</th>
						<td>
							<div class="">
								<textarea class="form-control form-control-lg" id="o_content" name="p_content" rows="3" placeholder="내용을 입력해주세요." oninput="this.style.height=&quot;&quot;, this.style.height= this.scrollHeight + &quot;px&quot;"></textarea>
							</div>
						</td>
					</tr>
				</tbody></table>
				
				<div class="container" style="text-align: center;">
					<input class="diary-btn-yellow" type="submit" value="등록">
				</div>
			</form>
				
		        `;
	    }
	
	    container.innerHTML = formHtml;
	}
</script>
</head>


<body>
    <h1>카테고리 선택</h1>
    <button onclick="showForm('stoll')">산책</button>
    <button onclick="showForm('weight')">체중</button>
	<button onclick="showForm('feeding')">사료/간식/영양제</button>
	<button onclick="showForm('health')">건강</button>
	<button onclick="showForm('note')">메모</button>

	<div class="container">
	    <div id="input-container" class="input-container">
	        <!-- 입력 폼이 여기에 동적으로 삽입됩니다. -->
	    </div>
	</div>


</body>
</html>