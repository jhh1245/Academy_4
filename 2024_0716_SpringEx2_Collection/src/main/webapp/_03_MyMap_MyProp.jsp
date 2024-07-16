<%@page import="java.util.Map"%>
<%@page import="myutil.MyMap"%>
<%@page import="java.util.Properties"%>
<%@page import="myutil.MyProp"%>
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

	MyMap myMap = wac.getBean("myMapBean", MyMap.class); // id로 준 것 가져옴
	pageContext.setAttribute("myMap", myMap);

	
	MyProp myProp = wac.getBean("myPropBean", MyProp.class); // id로 준 것 가져옴
	pageContext.setAttribute("myProp", myProp);
	

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>


<hr>
   Word Map정보
<hr>
<dl>
<c:forEach var="entry"  items="${ myMap.wordMap }">
   <dt><b>${ entry.key }</b></dt>
   <dd>${ entry.value }</dd>
   <dd>${ entry }</dd>
</c:forEach>
</dl>


<hr>
	DB접속정보(Prop정보)
<hr>
<dl>
<c:forEach var="prop"  items="${ myProp.dbProp }">
   <dt><b>${ prop.key }</b></dt>
   <dd>${ prop.value }</dd>
</c:forEach>
</dl>
</body>
</html>