package action;

import java.io.IOException;
import java.util.List;

import dao.VisitDao;
import db.vo.VisitVo;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

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

		// 방명록 데이터 가져오기
		List<VisitVo> list = VisitDao.getInstance().selectList();
		
		// request binding ...
		request.setAttribute("list", list);
		
		// Dispatcher 형식으로 호출 
		String forward_page = "visit_list.jsp";
		RequestDispatcher disp = request.getRequestDispatcher(forward_page);
		// clent가 주소를 가지고 호출하면 service()가 호출 (비지니스로직 = dao에게 데이터 가져와 시키는 등의 작업)
		disp.forward(request, response); // dept_list를 부른다. 

		
		// 이 서블릿과 dept_list.jsp 서블릿이 공유되는 공간 request이다. 
	}

}

