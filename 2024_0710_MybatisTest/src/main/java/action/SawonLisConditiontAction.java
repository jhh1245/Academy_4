package action;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dao.SawonDao;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vo.SawonVo;

/**
 * Servlet implementation class SawonListConditionAction
 */

@WebServlet("/sawon/list_condition.do") // 여기로 들어오면 
public class SawonLisConditiontAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// /sawon/list.do
		// /sawon/list.do?deptno=0&sajob=all 
		// /sawon/list.do?deptno=0&sajob=부장
		// /sawon/list.do?deptno=10&sajob=사원
		
		// 0. 수신 인코딩
		request.setCharacterEncoding("utf-8");
		
		int deptno = 0; // deptno가 안들어왔을 때 기본 0값이 된다. 
		try {
			deptno = Integer.parseInt(request.getParameter("deptno"));
		} catch(Exception e) {
			
		}
		
		int sahire = 0; // deptno가 안들어왔을 때 기본 0값이 된다. 
		try {
			sahire = Integer.parseInt(request.getParameter("sahire"));
		} catch(Exception e) {
			
		}
		
		String sajob = request.getParameter("sajob");
		String sasex = request.getParameter("sasex");
		
		if(sajob == null) sajob = "all";
		if(sasex == null) sasex = "all";
		
		// 검색 조건을 전달할 Map 
		Map<String, Object> map = new HashMap<String, Object>();
		if(deptno != 0) { // 부서가 전체(0)가 아니면
			map.put("deptno", deptno);
		}
		
		if( !sajob.equals("all") ) { // 직급이 전체가 아니면
			map.put("sajob", sajob);
		}
		
		if( !sasex.equals("all") ) { // 직급이 전체가 아니면
			map.put("sasex", sasex);
		}
		
		if( sahire != 0 ) { // 직급이 전체가 아니면
			map.put("sahire", sahire);
		}
		
		
		// 사원목록 가져오기 
		List<SawonVo> list = SawonDao.getInstance().selectList(map);
		
		// request Binding
		request.setAttribute("list", list);
		
		// Dispatcher 형식으로 호출 
		String forward_page = "sawon_list.jsp";
		RequestDispatcher disp = request.getRequestDispatcher(forward_page);
		disp.forward(request, response); 
	}

}
