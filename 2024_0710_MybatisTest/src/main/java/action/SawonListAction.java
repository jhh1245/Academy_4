package action;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vo.SawonVo;

import java.io.IOException;
import java.util.List;

import dao.SawonDao;

/**
 * Servlet implementation class SawonListAction
 */

@WebServlet("/sawon/list.do") // 여기로 들어오면 
public class SawonListAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// /sawon/list.do
		// /sawon/list.do?deptno=0 or deptno=10 or deptno=20
		
		int deptno = 0; // deptno가 안들어왔을 때 기본 0값이 된다. 
		
		try {
			deptno = Integer.parseInt(request.getParameter("deptno"));
		} catch(Exception e) {
			
		}
		
		// 사원목록 가져오기 
		List<SawonVo> list = null;
		
		if(deptno == 0) {
			list = SawonDao.getInstance().selectList();
		} else {
			list = SawonDao.getInstance().selectListFromDeptno(deptno);
			// 10 or 20 or 30 or 40이 넘어갈 수 있다. 
		}
		
		// request Binding
		request.setAttribute("list", list);
		
		// Dispatcher 형식으로 호출 
		String forward_page = "sawon_list.jsp";
		RequestDispatcher disp = request.getRequestDispatcher(forward_page);
		disp.forward(request, response); 
	}

}
