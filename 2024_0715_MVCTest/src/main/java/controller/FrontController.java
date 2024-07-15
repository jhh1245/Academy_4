package controller;

import java.io.IOException;

import action.CommandAction;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class FrontController
 */
public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	CommandAction action = new CommandAction();
	// 컨트롤러 만들어질 때 1번만 생성된다. 
	
	
	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String uri = request.getRequestURI();
		String url = request.getRequestURL().toString();
		
		// System.out.println(url);
		// System.out.println(uri);
		
		int index = uri.lastIndexOf("/");  // 뒤에서부터 검색 /list.do  
		String cmd = uri.substring(index+1).replaceAll(".do", ""); // /빼고 .do  빼고 list만
		
		System.out.println("cmd = " + cmd);
		
		
		
		if(cmd.equals("list")) { // cmdAction에서 list메서드 요청 
			String forward_page = action.list(request, response); // action은 CommandAction 서블릿을 의미함
		
			// 결과 정보 (forward 시킬 뷰) forward 
			request.getRequestDispatcher(forward_page).forward(request, response);
		} 
		
		else if(cmd.equals("view")) {
			String forward_page = action.view(request, response);
			
			// 결과 정보 (forward 시킬 뷰) forward 
			request.getRequestDispatcher(forward_page).forward(request, response);
		}
		
		// System.out.println("-- 2. FrontController : service() --");
	}

}
