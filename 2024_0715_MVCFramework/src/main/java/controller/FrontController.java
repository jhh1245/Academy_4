package controller;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import annotation.RequestMapping;
import annotation.ResponseBody;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class FrontController
 */

// @WebServlet("*.do")

public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	//Object ob = null;
	List<Method> method_list = new ArrayList<Method>();
	List<Object> object_list = new ArrayList<Object>();
	
	Map<Object, Method> map_list = new HashMap<Object, Method>();
	
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		super.init(config);
		
		String action = config.getInitParameter("action").trim();
		String [] action_array = action.split(",");
		
		for(String action_name : action_array) {
			try {
				action_name = action_name.replaceAll("\r", "").replaceAll("\n", "").trim();
				
				// System.out.println(action_name);
				
				// Java Reflection : 클래스명이 변수일 때 객체 생성하는 방법으로 reflection
				// action_name = "action.controller.TestAction" 
				Class c = Class.forName(action_name);
				
				Object ob = c.newInstance(); 
				// = new action.controller.TestAction
				// 클래스 정보를 가지고 인스턴스화. 객체만든다 
				// 이 객체안에는 메소드 2개 TestAction객체에는 2개 
				
				Method [] method_array = c.getDeclaredMethods(); // 해당 클래스 내의 메소드 목록들 수집(list,view 메소드)  
				
				for(Method method : method_array) {
					object_list.add(ob);     // TestAction 또는 Test2Action 
					method_list.add(method); // list, view, hello 
				}
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	
	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
        		
		String uri = request.getRequestURI();
		// URI = "2024_0715_MVCFramework/list.do" 
		
		String forward_page="";
		boolean bResponseBody=false;
		String contentType="";
		
		for(Method method : method_list) {	
		
			if(method.isAnnotationPresent(RequestMapping.class)) { // RequestMapping는 annotation 폴더에 있는 직접 만든 파일 
				
				RequestMapping annotation = method.getAnnotation(RequestMapping.class);
				if(uri.contains(((RequestMapping)annotation).value())){ // 어노테이션 정보에 현재 요청한 값이 들어있나? 
					// list.do면 list 메소드가 URI에 있는지
					
					try {
						int index = method_list.indexOf(method);
						Object ob = object_list.get(index);
						forward_page = (String) method.invoke(ob, request, response); // 메소드를 찾았으니까 호출. req, res를 전달 
						
						if(method.isAnnotationPresent(ResponseBody.class)) {
							
							contentType = ((RequestMapping)annotation).produces();
							
							bResponseBody = true;
						}
						
						break;
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			
			
		}
		
		//forward or redirect������ ������ ������...
		if(forward_page.isEmpty())return;
		
		if(bResponseBody) {
			
			response.setContentType(contentType);
			response.getWriter().print(forward_page);
			
			return;
		}
		
		if(forward_page.contains("redirect:")) {
			
			String redirect_page = forward_page.replaceAll("redirect:", "");
			response.sendRedirect(redirect_page);
			
		}else {
			//forward
			RequestDispatcher disp = request.getRequestDispatcher(forward_page);
			disp.forward(request, response);
		}
				
	}
		
}