package action.photo;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.PhotoDao;
import db.vo.PhotoVo;
import util.MyCommon;
import util.Paging;

/**
 * Servlet implementation class PhotoListAction
 */

@WebServlet("/photo/list.do")
public class PhotoListAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// /photo/list.do
		// /photo/list.do?page=2

		int nowPage = 1; // 초기에는 페이지 없이 호출했으니까 
		try {
			nowPage = Integer.parseInt(request.getParameter("page"));
		} catch(Exception e) {
				
		}
		
		
		// 게시물 범위 계산 (start / end) 
		int start = (nowPage-1) * MyCommon.Photo.BLOCK_LIST + 1;
		// nowPage 1일 때 0 * 8 + 1 => 1
		// 2일 때 1 * 8 + 1 => 9
		
		int end = start + MyCommon.Photo.BLOCK_LIST - 1;
		// 1 + 8 - 1 => 8
		// 9 + 8 - 1 => 16
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("start", start);
		map.put("end", end);
		
		int rowTotal = PhotoDao.getInstance().selectRowTotal(); 
		
		// pageMenu 만들기
		String pageMenu = Paging.getPaging("list.do", // pageURL
								nowPage, 			  // 현재 페이지
								rowTotal, 			  // 전체 게시물 수
								MyCommon.Photo.BLOCK_LIST,  // 한 화면에 보여질 게시글 수 
								MyCommon.Photo.BLOCK_PAGE); // 한 화면에 보여질 페이지 수
		
		List<PhotoVo> list = PhotoDao.getInstance().selectList(map);
		
		//request binding
		request.setAttribute("list", list);
		request.setAttribute("pageMenu", pageMenu);
		
		//Dispatcher형식으로 호출
		String forward_page = "photo_list.jsp";
		RequestDispatcher disp = request.getRequestDispatcher(forward_page);
		disp.forward(request, response);

	}

}