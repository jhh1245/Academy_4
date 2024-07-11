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
 * Servlet implementation class MemberModifyFormAction
 */

@WebServlet("/member/modify.do")
public class MemberModifyAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		int mem_idx = Integer.parseInt(request.getParameter("mem_idx"));
		String mem_name = request.getParameter("mem_name");
		String mem_id = request.getParameter("mem_id");
		String mem_pwd = request.getParameter("mem_pwd");
		String mem_zipcode = request.getParameter("mem_zipcode");
		String mem_addr = request.getParameter("mem_addr");
		String mem_grade 	= request.getParameter("mem_grade");
		
		//2.ip받기
		String mem_ip = request.getRemoteAddr();
		
		// 3. VisitVo 포장 
		MemberVo vo = new MemberVo(mem_idx, mem_name, mem_id, mem_pwd, mem_zipcode, mem_addr, mem_ip, mem_grade);
				
		// 4. DB update 
		int res = MemberDao.getInstance().update(vo);
		
		HttpSession session = request.getSession();
		MemberVo loginUser = (MemberVo) session.getAttribute("user"); //session은 오브젝트 타입이라서 다운캐스팅
		
		// 현재 수정하려는 정보가 로그인한 유저 본인인 경우
		if(loginUser.getMem_idx() == mem_idx) {
			// 로그인 상태 정보 가져오기  
			MemberVo user = MemberDao.getInstance().selectOne(mem_idx);
			
			session.setAttribute("user", user);
			// Scope내 저장방식은 Map 형식 : key / value
			// 키 : user 
			// 값 : vo 객체 
		}
		
				
		// 5. 목록 보기 이동
		response.sendRedirect("list.do");
		

	}


}