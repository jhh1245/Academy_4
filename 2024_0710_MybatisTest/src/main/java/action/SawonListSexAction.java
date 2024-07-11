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

@WebServlet("/sawon/list_sasex.do") // 여기로 들어오면 
public class SawonListSexAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// /sawon/list_job.do				=> null 
		// /sawon/list_job.do?sajob=		=> ""
		// /sawon/list_job.do?sajob=all
		// /sawon/list_job.do?sajob=부장
		
		// 0. 수신인코딩 
		request.setCharacterEncoding("utf-8");
		
		String sasex = request.getParameter("sasex");
		
		if(sasex == null || sasex.isEmpty()) {
			sasex = "all";
		}
		
		
		// 사원목록 가져오기 
		List<SawonVo> list = null;
		
		if(sasex.equals("all")) {
			list = SawonDao.getInstance().selectList();
		} else {
			// 직급별 조회 
			list = SawonDao.getInstance().selectListFromSasex(sasex);
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
