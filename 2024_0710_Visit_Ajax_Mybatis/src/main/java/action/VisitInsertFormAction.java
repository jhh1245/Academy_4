package action;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet implementation class VisitInsertFormAction
 */

@WebServlet("/visit/insert_form.do") // 여기로 들어오면 
public class VisitInsertFormAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Dispatcher 형식으로 호출 
		String forward_page = "visit_insert_form.jsp";
		RequestDispatcher disp = request.getRequestDispatcher(forward_page);
     	disp.forward(request, response);  

		// 이 서블릿과 dept_list.jsp 서블릿이 공유되는 공간 request이다. 
	}

}

