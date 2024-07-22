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
	   console.log(f.photo.files.length);  
	    
	   if(title==''){
		   alert("제목을 입력하세요!!");
		   f.title.value="";
		   f.title.focus();
		   return;
	   }
	   
	   if(f.photo.files.length == 0 || f.photo.files.length > 5){ // 선택 안했을 때
		   alert("업로드할 이미지를 1~5로 선택하세요.");
		   return; 
	   }
	   
	   f.action = "upload4.do"; 
	   f.submit();

   }
   
	
</script>


</head>
<body>

<hr>
  파일 n개 업로드 <br>
  (Spring에서는 n개 이상의 파일 업로드시 parameter 이름은 동일하게 부여해야 한다. )
<hr>
<form method="POST"  enctype="multipart/form-data">
  <div>
     제목 : <input name="title">
  </div>
  
   <div>
     사진 : <input type="file" name="photo" multiple="multiple">
  </div>

  
  <div>
      <input type="button"  value="n개파일업로드"  onclick="send(this.form);">
  </div>


</form>



</body>
</html>