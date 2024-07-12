package action;

import java.io.IOException;
import java.util.List;

import dao.DeptDao;
import db.vo.DeptVo;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DeptListAction
 */

@WebServlet("/dept/list.do") // 여기로 들어오면 
public class DeptListAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// 부서목록 읽어오기 
		List<DeptVo> list = DeptDao.getInstance().selectList();
		
		// request binding
		request.setAttribute("list", list);
		
		// Dispatcher 형식으로 호출 
		String forward_page = "dept_list.jsp";
		RequestDispatcher disp = request.getRequestDispatcher(forward_page);
		disp.forward(request, response);  

	}

}
