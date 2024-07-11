package action.member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.MemberDao;
import db.vo.MemberVo;

/**
 * Servlet implementation class MemberDeleteAction
 */

@WebServlet("/member/delete.do")
public class MemberDeleteAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		// /member/delete.do?mem_idx=3
		
		//1.parameter 받기
		int mem_idx = Integer.parseInt(request.getParameter("mem_idx"));
		
		
		
		//2.로그인한 유저가 일반 / 관리자 인지 판단
		HttpSession  session = request.getSession();
		MemberVo user = (MemberVo) session.getAttribute("user");
		
		//로그인 유저가 일반유저면 로그인 정보 삭제
		if(user.getMem_grade().equals("일반")) {
			session.removeAttribute("user");
		}
		
		if(user.getMem_grade().equals("관리자") && user.getMem_idx()==mem_idx) {
			response.sendRedirect("list.do?reason=not_admin_delete");
			return;
		}
		
		//3.DB delete : delete from member where mem_idx=?
		int res = MemberDao.getInstance().delete(mem_idx);
		
		
		//4.메인페이지 이동
		response.sendRedirect("list.do");
		
		

	}

}