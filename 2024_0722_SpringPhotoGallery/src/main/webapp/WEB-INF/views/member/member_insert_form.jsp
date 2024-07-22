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
     width: 800px;
     margin: auto;
     margin-top: 100px;
  
  }
  
  th{
     vertical-align: middle !important;
     text-align: right; 
     font-size: 16px;
  }

  #id_msg{
     display: inline-block;
     width: 300px;
     height: 20px;
     margin-left: 10px;
    /*  border : 1px solid red; */
  }

  
</style>

<!-- 주소검색 API  -->
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>

<script type="text/javascript">
	<!-- 여기서 쓰려면 input태그에 id가 있어야함-->
	function check_id(){
		// 회원가입 버튼은 비활성화 !! 
		// <input id="btn_register" type="button" ... disabled="disabled">
		$("#btn_register").prop("disabled", true);
		
		//            document.getElementById("mem_id").value
		let  mem_id = $("#mem_id").val();
		
		if(mem_id.length == 0){
			$("#id_msg").html("");
			return;
		}
		
		if(mem_id.length < 3){
			$("#id_msg").html("아이디는 3자리 이상이어야 됩니다.").css("color","red");
			return;
		}
		
		// 서버에 현재 입력된 ID를 체크 요청(jQuery Ajax이용)
		$.ajax({
			url 	: "check_id.do", 	   // MemberCheckIdAction // 어디로 넘길건지
			data 	: {"mem_id" : mem_id}, // parameter => check_id.do?mem_id=one // 어떤걸 넘길건지
			success : function(res_data){  
				
				// 서버가 넘겨주는 정보는 res_data = {"result":true} or {"result":false}로 하겠다.
				if(res_data.result){ // result는 JSON 안에 있는 키 
					$("#id_msg").html("사용 가능한 아이디입니다.").css("color", "blue");
					$("#btn_register").prop("disabled", false); // 비활성화옵션 false = 활성화시킨다.  
				} else {
					$("#id_msg").html("이미 사용중인 아이디입니다.").css("color", "red");
				}
				
			}, // 성공했을 때 호출되는 함수 
			error	: function(err) {
				alert(err.responseText);
			} // 실패했을 때 호출되는 함수 
					
		});
	} // check_id()
	
	
	function find_addr(){ // 주소 검색 함수 
		
		var themeObj = {
				   //bgColor: "", //바탕 배경색
				   searchBgColor: "#0B65C8", //검색창 배경색
				   //contentBgColor: "", //본문 배경색(검색결과,결과없음,첫화면,검색서제스트)
				   //pageBgColor: "", //페이지 배경색
				   //textColor: "", //기본 글자색
				   queryTextColor: "#FFFFFF" //검색창 글자색
				   //postcodeTextColor: "", //우편번호 글자색
				   //emphTextColor: "", //강조 글자색
				   //outlineColor: "", //테두리
				};
	
		new daum.Postcode({
			theme: themeObj, // 키 : value 형태니까 구분은 ,로 
			
	        oncomplete: function(data) {
	            // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분입니다.
	            // 예제를 참고하여 다양한 활용법을 확인해 보세요.
	            
	            $("#mem_zipcode").val(data.zonecode);  
	            // 우편번호 넣기. data.zonecode를 얻어와서 mem_zipcode에 넣겠다. (카카오 API 설명서 참고해서)
	            
	            $("#mem_addr").val(data.address);      // 주소 넣기 	            	 
	        }
	    }).open();
	} // find_addr
	
	function send(f){
		   let mem_name 	= f.mem_name.value.trim();
		   let mem_pwd  	= f.mem_pwd.value.trim();
		   let mem_zipcode 	= f.mem_zipcode.value.trim();
		   let mem_addr 	= f.mem_addr.value.trim();
		   
		   if(mem_name==''){
			   alert("이름을 입력하세요");
			   f.mem_name.value="";
			   f.mem_name.focus();
			   return;
		   }
		   
		   if(mem_pwd==''){
			   alert("비밀번호를 입력하세요");
			   f.mem_pwd.value="";
			   f.mem_pwd.focus();
			   return;
		   }
		   
		   if(mem_zipcode==''){
			   alert("우편번호를 입력하세요");
			   f.mem_zipcode.value="";
			   f.mem_zipcode.focus();
			   return;
		   }
		   
		   if(mem_addr==''){
			   alert("주소를 입력하세요");
			   f.mem_addr.value="";
			   f.mem_addr.focus();
			   return;
		   }
		   
		   
		   f.action = "insert.do";  //MemberInsertAction
		   f.submit(); //전송
		   
	}
	
</script>
</head>
<body>

<form class="form-inline">
   <div id="box">
		<div class="panel panel-info">
			<div class="panel-heading"><h4>회원가입</h4></div>
			<div class="panel-body">
			   <table class="table">
			      <tr>
			         <th>이름</th>
			         <td><input style="width:50%;" class="form-control" name="mem_name"></td>
			      </tr>
			      
			      <tr>
			         <th>아이디</th>
			         <td>
			         	<input style="width:50%;" class="form-control" name="mem_id" id="mem_id" onkeyup="check_id();" >
			         	<span id="id_msg"></span>
			         </td>
			      </tr>
			      
			      <tr>
			         <th>비밀번호</th>
			         <td>
			         	<input style="width:50%" class="form-control" name="mem_pwd">			 
			         </td>
			      </tr>
			      
			      <tr>
			         <th>우편번호</th>
			         <td>
			         	<input style="width:50%" class="form-control" name="mem_zipcode" id="mem_zipcode">
			         	<input class="btn btn-info" type="button" value="주소검색" onclick="find_addr();">			 
			         </td>
			      </tr>
			      
			      <tr>
			         <th>주소</th>
			         <td>
			         	<input style="width:65%" class="form-control" name="mem_addr" id="mem_addr">
			         		 
			         </td>
			      </tr>
			      
			      <tr>
			      	<td colspan="2" align="center">
			      		<input class="btn btn-success" type="button" value="메인화면" 
			      		onclick="location.href='../photo/list.do'">
			      		<input id="btn_register" class="btn btn-primary" type="button" value="가입하기" 
			      		disabled onclick="send(this.form)">
			      
			   </table>   
			</div>
		</div>
	</div>
</form>   
   
</body>
</html>