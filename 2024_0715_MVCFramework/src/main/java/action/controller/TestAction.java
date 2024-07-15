package action.controller;

import java.util.ArrayList;
import java.util.List;

import annotation.RequestMapping;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class TestAction {
	public TestAction() {
		
		System.out.println("--TestAction()--");
	}
	
	@RequestMapping("/list.do")
	public String list(HttpServletRequest request, HttpServletResponse response) {
		
		List<String> list = new ArrayList<String>();
		
		list.add("JAVA");
		list.add("Oracle");
		list.add("HTML");
		list.add("CSS");
		list.add("JavaScript");
		list.add("Spring");
		
		request.setAttribute("list", list);
		
		return "list.jsp";
	}
	
	@RequestMapping("/view.do")
	public String view(HttpServletRequest request, HttpServletResponse response) {
		// /view.do?book=JAVA
		
		String book = request.getParameter("book");
		String description = "뭐지?";
		
		switch(book.toUpperCase()) {
		case "JAVA" : description = "제임스 고슬링이 만든 언어 / 전자제품 제어용 언어로 만듦"; break;
		case "ORACLE" : description = "현존하는 DBMS 중 독보적인 성능"; break;
		case "HTML" : description = "HyperText Markup Language / 브라우저에서 사용"; break;
		case "CSS" : description = "Cascading Style Sheet로 모양 지정"; break;
		case "JAVASCRIPT" : description = "브라우저 제어용 언어"; break;
		case "SPRING" : description = "자바 개발 플랫폼"; break;
		}
		
		// request binding
		request.setAttribute("book", book);
		request.setAttribute("description", description);
		
		return "view.jsp";
	}
	
}
