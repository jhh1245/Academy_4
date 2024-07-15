package action;

import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

// POJO (Plain Old Java Object) : 순수 자바 객체 

public class CommandAction {
	
	// 목록 요청에 대한 처리 
	public String list(HttpServletRequest request, HttpServletResponse response) {
		
		List<String> list = new ArrayList<String>();
		
		list.add("Java");
		list.add("Oracle");
		list.add("HTML");
		list.add("CSS");
		list.add("JavaScript");
		
		// request Binding 
		request.setAttribute("list", list);
		
		return "list.jsp";
	}
}
