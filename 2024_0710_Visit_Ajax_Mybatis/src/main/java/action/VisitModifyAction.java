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
 * Servlet implementation class VisitModifyAction
 */

@WebServlet("/visit/modify.do") // 여기로 들어오면 
public class VisitModifyAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		 // visit/modify.do?name=gg&content=dddd&pwd=aa
		// 0. 수신 인코딩 설정 현재 이 파일은 UTF-8 파일이니까 같이 맞춰야된다.  
		request.setCharacterEncoding("utf-8");
		
		// 1. parameter
		int 	idx 	= Integer.parseInt(request.getParameter("idx"));
		String 	no 		= request.getParameter("no");
		String 	name 	= request.getParameter("name");		
		String 	content = request.getParameter("content").replace("\n", "<br>");
		String  pwd		= request.getParameter("pwd");
		
		// 2. ip 주소 얻어온다.
		// 톰켓이 알아서 전달해준다. getParameter아니다.
		String ip		= request.getRemoteAddr();
		// tcp, http 
		
		// 3. VisitVo 포장 
		VisitVo vo = new VisitVo(idx, name, content, pwd, ip);
		
		// 4. DB update 
		int res = VisitDao.getInstance().update(vo);
		
		// 5. 목록 보기 이동
		response.sendRedirect("list.do#p_" + no);
		
		
		
	}

}
