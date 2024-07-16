<%@page import="vo.PersonVo"%>
<%@page import="org.springframework.web.context.support.WebApplicationContextUtils"%>
<%@page import="org.springframework.web.context.WebApplicationContext"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<% 
	// Spring이 사용하고 있는 bean의 저장소를 관리하는 객체정보 얻어온다 
	WebApplicationContext wac = WebApplicationContextUtils.getWebApplicationContext(application);
	// 스프링이 사용하고 있는 저장소 갖다줘 
	
	// Spring이 생성한 Bean 정보를 얻어온다 
	// Spring이 만든 객체를 여기서 (사용자가) 사용한다 = Ioc방식이다 !! 
	PersonVo p1 = (PersonVo)wac.getBean("p1"); // 자바의 모든객체를 ac.xml에 정의할 수 있다. = 리턴 타입이 Object라서 강제로 다운캐스팅이 필요
	PersonVo p2 = wac.getBean("p2", PersonVo.class); //이 타입으로 캐스팅해서 달라고 한다 
	
	pageContext.setAttribute("p1", p1);
	pageContext.setAttribute("p2", p2);
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<hr>
Ioc : p1's info 
<hr>
이름 : ${p1.name }<br>
나이 : ${p1.age }<br>
주소 : ${p1.addr }<br>

<hr>
Ioc : p2's info 
<hr>
이름 : ${p2.name }<br>
나이 : ${p2.age }<br>
주소 : ${p2.addr }<br>


</body>
</html>