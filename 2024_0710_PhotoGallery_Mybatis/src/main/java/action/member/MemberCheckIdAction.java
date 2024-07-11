package action.member;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.MemberDao;
import db.vo.MemberVo;

/**
 * Servlet implementation class MemberCheckIdAction
 */

@WebServlet("/member/check_id.do") // 여기로 들어오면 
public class MemberCheckIdAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// url 형식 : /member/check_id.do?mem_id=one
		
		// 0. 수신인코딩 
		request.setCharacterEncoding("utf-8");
		
		// 1.parameter 받기
		String mem_id = request.getParameter("mem_id");
		
		// 2. mem_id 에 해당되는 유저 정보 검색 
		MemberVo vo = MemberDao.getInstance().selectOne(mem_id);
		
		boolean bResult = (vo == null); // vo가 null이면 mem_id가 없다는 뜻 = 사용가능한 아이디이다.
		
		// 응답처리 
		response.setContentType("application/json; charset=utf-8;");
		PrintWriter out = response.getWriter();
		
		String json = String.format("{\"result\":%b}", bResult);
		response.getWriter().print(json);
	}

}
