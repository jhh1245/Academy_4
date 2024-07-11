package action;

import java.io.IOException;

import dao.VisitDao;
import db.vo.VisitVo;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class VisitModifyFormAction
 */

@WebServlet("/visit/modify_form.do") // 여기로 들어오면 
public class VisitModifyFormAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		
		// visit/modify_form.do?idx=10&no=2
		// 1. 수정할 게시물의 idx를 받는다. 
		int idx = Integer.parseInt(request.getParameter("idx"));
		
		// 2. idx에 해당하는 게시물 1건 얻어오기 
		VisitVo vo = VisitDao.getInstance().selectOne(idx);
		
		// textarea \n 기능처리 : <br>을 \n로 변환
		String content = vo.getContent().replaceAll("<br>", "\n");
		vo.setContent(content);
		
		// 3. request binding 아래 파일과 데이터 공유 
		request.setAttribute("vo", vo);
		
		// Dispatcher 형식으로 호출 
		String forward_page = "visit_modify_form.jsp";
		RequestDispatcher disp = request.getRequestDispatcher(forward_page);
		// clent가 주소를 가지고 호출하면 service()가 호출 (비지니스로직 = dao에게 데이터 가져와 시키는 등의 작업)
		disp.forward(request, response);  

		// 이 서블릿과 dept_list.jsp 서블릿이 공유되는 공간 request이다. 
	}

}
