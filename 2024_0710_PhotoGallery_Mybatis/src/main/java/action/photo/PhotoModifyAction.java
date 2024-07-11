package action.photo;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.PhotoDao;
import db.vo.PhotoVo;

/**
 * Servlet implementation class PhotoModifyAction
 */

@WebServlet("/photo/modify.do")
public class PhotoModifyAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// /photo/modify.do?p_idx=9&p_title=노트북&p_content=복사본
		
		//0.수신인코딩
		request.setCharacterEncoding("utf-8");
		
		//1.parameter
		int    p_idx 		= Integer.parseInt(request.getParameter("p_idx"));
		String p_title 		= request.getParameter("p_title");
		String p_content 	= request.getParameter("p_content").replaceAll("\n", "<br>");
		
		String p_ip			= request.getRemoteAddr();
		
		//3.PhotoVo포장
		PhotoVo vo = new PhotoVo();
		vo.setP_idx(p_idx);
		vo.setP_title(p_title);
		vo.setP_content(p_content);
		vo.setP_ip(p_ip);
		
		//4.DB update
		int res = PhotoDao.getInstance().update(vo);
		
		//5.메인화면
		response.sendRedirect("list.do");
		
		
		
	}

}