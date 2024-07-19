package action;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dao.VisitDao;
import db.vo.VisitVo;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import util.MyCommon;
import util.Paging;

/**
 * Servlet implementation class VisitListAction
 */

@WebServlet("/visit/list.do") // 여기로 들어오면 
public class VisitListAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// /visit/list.do
		// /visit/list.do?search=name&search_text=길동
		// 이외에도 여러가지 경우의 수가 있으니까 경우의수는 8가지 => if문이 3개가 필요함 => 마이바티스가 지원하는 동적쿼리필요
		
		// 0. 수신인코딩 설정 
		request.setCharacterEncoding("utf-8");
		
		// 1.parameter 받기
		String search 		= request.getParameter("search");
		String search_text 	= request.getParameter("search_text");
		
		if(search == null) search = "all"; // null 이라는 뜻 = 전체검색하겠다. 

		// 검색조건을 담을 맵 ---------------------------------------
		Map<String, Object> map = new HashMap<String, Object>();
				
		// 이름 + 내용 
		if(search.equals("name_content")) { // name 값 null 이면 에러 
			map.put("name", search_text);
			map.put("content", search_text);
			// name : 길동 
			// content : 길동
			
		} else if (search.equals("name")) { // search == "name"은 안된다. 
			// 이름 
			map.put("name", search_text);
			
		} else if (search.equals("content")) {
			// 내용 
			map.put("content", search_text);
		}
				
		
		//-------[ Begin :  Page Menu ]------------------------	
		
		int nowPage = 1;
		try {
			nowPage = Integer.parseInt(request.getParameter("page"));
		} catch(Exception e) {
			
		}
		
		// start & end 
		int start = (nowPage-1) * MyCommon.Visit.BLOCK_LIST + 1;
		int end = start + MyCommon.Visit.BLOCK_LIST - 1;

		map.put("start", start);
		map.put("end", end);
		
		
		// 총 게시물 수 (필터링 된)
		int rowTotal = VisitDao.getInstance().selectRowTotal(map); 
		
		// 검색정보 filter : search_filter="search=name&search_text=길동"
		String search_filter = String.format("search=%s&search_text=%s", search, search_text);
		
		// pageMenu 만들기
		String pageMenu = Paging.getPaging("list.do", // pageURL
							 	search_filter,
								nowPage, 			  // 현재 페이지
								rowTotal, 			  // 전체 게시물 수
								MyCommon.Visit.BLOCK_LIST,  // 한 화면에 보여질 게시글 수 
								MyCommon.Visit.BLOCK_PAGE); // 한 화면에 보여질 페이지 수
		
		
		//-------[ End :  Page Menu ]------------------------	
		
		
		
		
		List<VisitVo> list = VisitDao.getInstance().selectList(map);
		
		// request binding ...
		request.setAttribute("list", list);
		request.setAttribute("pageMenu", pageMenu);
		
		// Dispatcher 형식으로 호출 
		String forward_page = "visit_list.jsp";
		RequestDispatcher disp = request.getRequestDispatcher(forward_page);
		disp.forward(request, response); 
		
	}

}

