<%@page import="java.util.Set"%>
<%@page import="myutil.MySet"%>
<%@page import="java.util.List"%>
<%@page import="myutil.MyList"%>
<%@page import="org.springframework.web.context.support.WebApplicationContextUtils"%>
<%@page import="org.springframework.web.context.WebApplicationContext"%>
<%@page import="org.apache.catalina.startup.WebAnnotationSet"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
	// applicationContext.xml에 등록한 bean을 읽어오기 위해서
	WebApplicationContext wac = WebApplicationContextUtils.getWebApplicationContext(application);

	MyList myList = wac.getBean("myListBean", MyList.class); // id로 준 것 가져옴
	
	List sido_list = myList.getSido_list();
	
	pageContext.setAttribute("sido_list", sido_list);
	
	
	
	MySet mySet = wac.getBean("mySetBean", MySet.class); // id로 준 것 가져옴
	
	Set fruit_set = mySet.getFruit_set();
	
	pageContext.setAttribute("fruit_set", fruit_set);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<hr>
	시도 목록
<hr>
<ul>
	<!--  for(String sido: sido_list) -->
	<c:forEach var="sido" items="${sido_list }">
		<li>${ sido }</li>
	</c:forEach>
</ul>
<hr>
	과일 목록
<hr>
<ul>	
	<!--  for(String fruit: fruit_set) -->
	<c:forEach var="fruit" items="${fruit_set }">
		<li>${ fruit }</li>
	</c:forEach>
</ul>
</body>
</html>