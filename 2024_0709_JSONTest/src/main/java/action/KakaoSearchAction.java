package action;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import json.vo.KakaoLocalVo;
import util.KakaoSearchUtils;

/**
 * Servlet implementation class KakaoSearchAction
 */

@WebServlet("/search.do") // 여기로 들어오면 
public class KakaoSearchAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// /search.do?query=약국&page=1&size=5&radius=1000&latitude=...&longitude=...
		
		
		// 0. 수신인코딩
		request.setCharacterEncoding("utf-8");

		
		// 1. parameter 받기 
		String query = request.getParameter("query");
		
		int page = 1; // 파라미터가 빈 값일 때 기본값 
		int size = 5; 
		int radius = 1000;
		
		try {
			page = Integer.parseInt(request.getParameter("page"));
		} catch (Exception e) {
			
		}
		
		try {
			size = Integer.parseInt(request.getParameter("size"));
		} catch (Exception e) {
			
		}
		
		try {
			radius = Integer.parseInt(request.getParameter("radius"));
		} catch (Exception e) {
			
		}
		
		String y = request.getParameter("latitude");
		String x = request.getParameter("longitude");
		
		// Kakao 검색
		// 1. JSON 방식 
		// List<KakaoLocalVo> list = KakaoSearchUtils.searchJson(query, y, x, page, size, radius);
		
		// 2. XML 방식
		List<KakaoLocalVo> list = KakaoSearchUtils.searchXML(query, y, x, page, size, radius);
		
		// request binding 
		request.setAttribute("list", list);
		
		// Dispatcher 형식으로 호출 
		String forward_page = "search_list.jsp";
		RequestDispatcher disp = request.getRequestDispatcher(forward_page);
		// clent가 주소를 가지고 호출하면 service()가 호출 (비지니스로직 = dao에게 데이터 가져와 시키는 등의 작업)
		disp.forward(request, response); // dept_list를 부른다. 

		// 이 서블릿과 dept_list.jsp 서블릿이 공유되는 공간 request이다. 
	}

}

