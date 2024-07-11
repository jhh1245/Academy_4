<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
     width: 600px;
     margin: auto;
     margin-top: 100px;
  }
  
  textarea {
	 resize: none;
  }
  
  th{
    width: 15%;
    vertical-align: middle  !important;
  }
  
</style>

<script type="text/javascript">
   function send(f){
	   
	   //입력값 검증
	   let name 	= f.name.value.trim();
	   let content	= f.content.value.trim();
	   let pwd		= f.pwd.value.trim();
	   
	   if(name==''){
		   alert("작성자명을 입력하세요!!");
		   f.name.value=""; //지우기
		   f.name.focus();
		   return;
	   }
	   
	   if(content==''){
		   alert("내용을 입력하세요!!");
		   f.content.value=""; //지우기
		   f.content.focus();
		   return;
	   }
	   
	   if(pwd==''){
		   alert("비밀번호를 입력하세요!!");
		   f.pwd.value=""; //지우기
		   f.pwd.focus();
		   return;
	   }
	   
		// 수정 확인 (꼭하기)
		if(confirm("정말 수정 하시겠습니까?") == false){
			 location.href=""; // 현재 자신의 페이지를 호출
			return;
		}
	  
	   // f.method = "POST";
	   f.action = "modify.do"; // 전송대상(VisitModifyAction)
	   f.submit(); //전송
   }
</script>


</head>
<body>
  <form>
  <!-- visit/modify_form.do?idx=12&no=1 -->
  	 <input type="hidden" name="idx" value="${param.idx}"> <!-- 파라미터로 넘어온 -->
  	 <input type="hidden" name="no" value="${param.no}">
  	 
     <div id="box">
		<div class="panel panel-info">
			<div class="panel-heading"><h4>수정하기</h4></div>
			<div class="panel-body">
		        
		        <table class="table">
		            <tr>
		               <th>작성자</th>
		               <td><input class="form-control"  name="name" value="${vo.name }"></td>
		            </tr>
		            
		            <tr>
		               <th>내용</th>
		               <td>
		                  <textarea class="form-control" rows="6" name="content">${vo.content}</textarea>
		               </td>
		            </tr>
		            
		            <tr>
		               <th>비밀번호</th>
		               <td><input class="form-control"  type="password" name="pwd" value="${vo.pwd }"></td>
		            </tr>
		            
		            <tr>
		               <td colspan="2" align="center">
		                   
		                   <input class="btn btn-success" type="button"  value="목록보기" 
		                          onclick="location.href='list.do'">
		                          
		                   <input class="btn btn-primary" type="button"  value="수정하기"
		                          onclick="send(this.form);">
		                   
		               </td>
		            </tr>
		            
		        </table>	    
			
			</div>
		</div>
	</div>
  </form>



</body>
</html>