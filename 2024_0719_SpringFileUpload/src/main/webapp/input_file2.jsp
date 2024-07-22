<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<script type="text/javascript">

   function send(f){
	   
	   let title = f.title.value.trim();
	   let photo1 = f.photo[0].value; // 배열에 0번째 아래에서 photo 같은이름으로 왔기때문에 배열로 처리 
	   let photo2 = f.photo[1].value; // 배열에 1번째 
	   
	   if(title==''){
		   alert("제목을 입력하세요!!");
		   f.title.value="";
		   f.title.focus();
		   return;
	   }
	   
	   if(photo1 == ""){
		   alert("사진1을 선택하세요!!");
		   return;
	   }
	   
	   if(photo2 == ""){
		   alert("사진2을 선택하세요!!");
		   return;
	   }
	   
	   f.action = "upload3.do"; 
	   f.submit();

   }
   
	
</script>


</head>
<body>

<hr>
  파일 2개 업로드 <br>
  (Spring에서는 2개 이상의 파일 업로드시 parameter이름은 동일하게 부여해야 한다. )
<hr>
<form method="POST"  enctype="multipart/form-data">
  <div>
     제목 : <input name="title">
  </div>
  
   <div>
     사진1 : <input type="file" name="photo">
  </div>
    
  <div>
     사진2 : <input type="file" name="photo">
  </div>
  
<!-- 이렇게 해도 못받는다. 1, 2 로 구분하면    
  <div>
     사진1 : <input type="file" name="photo1">
  </div>
    
  <div>
     사진2 : <input type="file" name="photo2">
  </div> -->
  
  <div>
      <input type="button"  value="2개 파일 업로드"  onclick="send(this.form);">
  </div>


</form>



</body>
</html>