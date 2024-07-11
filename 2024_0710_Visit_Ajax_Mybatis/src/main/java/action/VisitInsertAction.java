package action;

import java.io.IOException;

import dao.VisitDao;
import db.vo.VisitVo;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class VisitInsertAction
 */

@WebServlet("/visit/insert.do") // 여기로 들어오면 
public class VisitInsertAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request // client -> server로 들어오는 정보 처리하는 객체 
			, HttpServletResponse response) // server -> client로 응답하는 객체 
			throws ServletException, IOException {
		
		// visit/insert.do?name=gg&content=gggg&pwd=ggg
		
		// 0. 수신 인코딩 설정 
		request.setCharacterEncoding("utf-8"); // 리퀘스트 받기 전에 수신 인코딩을 정한다. 
		
		// 1. parameter 받기 
		// url 통해서 전달받은 것 
		String name    = request.getParameter("name");
		String content = request.getParameter("content").replaceAll("\n", "<br>");
		String pwd     = request.getParameter("pwd");
		
		// 2. ip 정보 얻어온다.
		String ip      = request.getRemoteAddr();
		 
		// 3. VisitVo 포장
		VisitVo vo = new VisitVo(name, content, pwd, ip);
		
		// 4. DBinsert 
		int res = VisitDao.getInstance().insert(vo);
		
		// 5. 목록보기 이동
		response.sendRedirect("list.do"); 
		// 서버측에서 전달하는 거니까 response (같은 경로에 있으니까 visit_list가 아니라 list.do)
	}

}

