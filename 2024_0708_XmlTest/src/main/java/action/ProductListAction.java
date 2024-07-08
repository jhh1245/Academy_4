package action;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import util.MySearchUtil;
import xml.vo.ProductVo;

/**
 * Servlet implementation class ProductListAction
 */

@WebServlet("/product/list.do") // 여기로 들어오면 
public class ProductListAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		
		//product/list.do?p_name=%EB%85%B8%ED%8A%B8%EB%B6%81&start=1&display=10
		String p_name = request.getParameter("p_name");
		int start = 1;
		int display = 10; // 실패되면 기본값으로 조회
		
		try {
			start = Integer.parseInt(request.getParameter("start")); // "1 " 처럼 공백 포함시 에러 
			display = Integer.parseInt(request.getParameter("display"));
		} catch (NumberFormatException e) {
			// e.printStackTrace();
		}

		// Naver Open API를 이용해서 상품 검색 
		List<ProductVo> p_list = MySearchUtil.search_shop(p_name, start, display);
		
		
		// request binding 
		request.setAttribute("p_list", p_list);
		
		// Dispatcher 형식으로 호출 
		String forward_page = "product_list.jsp";
		RequestDispatcher disp = request.getRequestDispatcher(forward_page);
		disp.forward(request, response); 
	}

}
