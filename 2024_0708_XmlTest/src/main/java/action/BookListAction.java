package action;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import util.MySearchUtilBook;
import xml.vo.BookVo;

/**
 * Servlet implementation class ProductListAction
 */

@WebServlet("/book/list.do") // 여기로 들어오면 
public class BookListAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		
		//product/list.do?p_name=%EB%85%B8%ED%8A%B8%EB%B6%81&start=1&display=10
		String b_name = request.getParameter("b_name");
		int start = 1;
		int display = 10; // 실패되면 기본값으로 조회
		
		try {
			start = Integer.parseInt(request.getParameter("start")); // "1 " 처럼 공백 포함시 에러 
			display = Integer.parseInt(request.getParameter("display"));
		} catch (NumberFormatException e) {
			// e.printStackTrace();
		}

		// Naver Open API를 이용해서 상품 검색 
		List<BookVo> b_list = MySearchUtilBook.search_book(b_name, start, display);
		
		
		// request binding 
		request.setAttribute("b_list", b_list);
		
		// Dispatcher 형식으로 호출 
		String forward_page = "book_list.jsp";
		RequestDispatcher disp = request.getRequestDispatcher(forward_page);
		// clent가 주소를 가지고 호출하면 service()가 호출 (비지니스로직 = dao에게 데이터 가져와 시키는 등의 작업)
		disp.forward(request, response); // dept_list를 부른다. 

		// 이 서블릿과 dept_list.jsp 서블릿이 공유되는 공간 request이다. 
	}

}
