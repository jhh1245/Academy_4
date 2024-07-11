package action;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import dao.VisitDao;

/**
 * Servlet implementation class VisitDeleteAction
 */

@WebServlet("/visit/delete.do") // 여기로 들어오면 
public class VisitDeleteAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// /visit/delete.do?idx=21?no=6		<- 여기서 idx를 받는다.
		
		// 1. 삭제할 idx 수신 
		int idx = Integer.parseInt(request.getParameter("idx")); // "21" -> 21
		String no = request.getParameter("no"); 
		
		// 2. DB delete 
		int res = VisitDao.getInstance().delete(idx);
		
		// 3. 목록보기 화면으로 이동
		response.sendRedirect("list.do#p_" + no);
	}

}

