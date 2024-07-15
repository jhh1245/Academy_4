package action.controller;

import annotation.RequestMapping;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class Test2Action {

	public Test2Action() {
		System.out.println("--Test2Action()--");
	}
	
	@RequestMapping("/hello.do")
	public String list(HttpServletRequest request, HttpServletResponse response) {
		return "안녕";
		
	}
}
