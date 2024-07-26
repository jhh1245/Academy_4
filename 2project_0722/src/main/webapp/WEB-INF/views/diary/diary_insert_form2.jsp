<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<!--  Bootstrap  3.x  -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
<style type="text/css">
   #box{
      width: 600px;
      margin: auto;
      margin-top: 150px;
   }
 
   textarea {
	  resize: none;
   }
   
   h4{
      font-weight: bold;
   }
 
</style>


<script type="text/javascript">

	function send(f){
		
		
/* 		let b_subject = f.b_subject.value.trim();
		let b_content = f.b_content.value.trim();
		
		if(b_subject==''){
			alert("제목을 입력하세요!!");
			f.b_subject.value="";
			f.b_subject.focus();
			return;
		}
		
		if(b_content==''){
			alert("내용을 입력하세요!!");
			f.b_content.value="";
			f.b_content.focus();
			return;
		} */
		
		f.action = "diary_insert_weight.do";
		f.submit(); //
		
		
	}

	function showForm(category) {
	    const container = document.getElementById('input-container');
	    container.innerHTML = ''; // 기존의 내용 제거

	    let formHtml = '';

	    if (category === 'weight') {
	        formHtml = `
	            <label for="weight">체중:</label>
	            <input type="text" id="weight" name="weight">
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

<form>
	<div id="box">
		<!-- Bootstrap Panel -->
		<div class="panel panel-primary">
			<div class="panel-heading">
				<h4>새글쓰기</h4>
				
				저기서 가져온 select_date는 어떻게 꺼내지 ? 
				${ select_date }
				
				<button onclick="showForm('weight')">체중</button>
    			<button onclick="showForm('walk')">산책</button>
			</div>

			

				<div class="panel-body">
			   <!-- <table class="table"> -->
			   <div id="input-container" class="input-container">
				<!-- 입력 폼이 여기에 동적으로 삽입됩니다. -->
			</div>
					<!-- <tbody><tr>
						<th>날짜</th>
						<td><input class="form-control form-control-lg" type="text" name="w_rdate" value=""> </td>
					</tr>
					<tr>
						<th>반려동물</th>
						<td><input class="form-control form-control-lg" type="number" name="p_idx" ></td>
					</tr>
					<tr>
						<th>체중(kg)</th>
						<td><input class="form-control form-control-lg" type="number" name="w_weight"></td>
					</tr>
				</tbody></table> 

				<div style="margin-top: 10px;">
			      <input class="btn btn-info"     type="button"  value="목록보기"  
			             onclick="location.href='diary_list.do'">
			      <input class="btn btn-primary"  type="button"  value="추가하기"
			             onclick="send(this.form);">
			   </div> -->

			
			   <!-- <div>
			      <h4>제목 :</h4>
			      <input class="form-control" name="b_subject">
			   </div>
			   
			   <div>
			      <h4>내용 :</h4>
			      <textarea  class="form-control" rows="10" name="b_content"></textarea>
			   </div>
			   
			   <div style="margin-top: 10px;">
			      <input class="btn btn-info"     type="button"  value="목록보기"  
			             onclick="location.href='list.do'">
			      <input class="btn btn-primary"  type="button"  value="글올리기"
			             onclick="send(this.form);">
			   </div> -->
			   
			</div>
		</div>
	</div>
</form>

</body>
</html>