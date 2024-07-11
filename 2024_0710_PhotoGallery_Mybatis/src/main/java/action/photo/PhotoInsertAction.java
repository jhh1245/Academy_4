package action.photo;

import java.io.File;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import dao.PhotoDao;
import db.vo.MemberVo;
import db.vo.PhotoVo;

/**
 * Servlet implementation class PhotoInsertAction
 */

@WebServlet("/photo/insert.do")
public class PhotoInsertAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		// /photo/insert.do?p_title=제목&p_content=내용&photo=notebook.png
		
		//로그인 상태 체크
		HttpSession session = request.getSession();
		MemberVo user = (MemberVo) session.getAttribute("user");
		
		// session timeout
		if(user==null) {
			
			response.sendRedirect("../member/login_form.do?reason=session_timeout");
			
			return;
		}
		
		//유저정보 얻어오기
		int    mem_idx  = user.getMem_idx();
		String mem_name = user.getMem_name();
		
		//화일업로드 처리
        String webPath = "/images/"; // 웹(URL)경로
		
		//현재 웹어플리케이션의 전역관리객체(상대경로->절대경로)
		ServletContext application = request.getServletContext();
		//                웹(상대)경로->절대경로 구하기
		String saveDir = application.getRealPath(webPath);
		System.out.println(saveDir);
		int    maxSize = 1024 * 1024 * 100; //최대업로드크기(100MB)
		// FileUpload객체 => MultipartRequest
		MultipartRequest mr = new MultipartRequest(
				                         request,  // request위임            
				                         saveDir,  // 저장위치             
				                         maxSize,  // 최대업로드 크기           
				                         "utf-8",  // 수신인코딩
				                          // 동일화일명->이름변경해서 저장
				                          new DefaultFileRenamePolicy() 
				                        );
		
		//업로드화일명을 얻어온다
		String p_filename ="no_file";
		//mr에 의해서 업로드된 화일정보 얻어온다
		File f = mr.getFile("photo");
		
		if(f!=null) { //업로드화일 존재하면
			
			p_filename = f.getName();
		}
		
		
		// 업로드화일외에 다른 파라메터는 mr로 수신해야 된다
		String p_title   = mr.getParameter("p_title");
		String p_content = mr.getParameter("p_content").replaceAll("\n", "<br>");
		//System.out.println(title);
		
		//parameter외의 정보 처리 시에는 request사용
		String p_ip = request.getRemoteAddr();
		
		//PhotoVo포장
		PhotoVo vo = new PhotoVo(p_title, p_content, p_filename, p_ip, mem_idx, mem_name);
		
		//DB Insert
		int res = PhotoDao.getInstance().insert(vo);
		
		//메인화면 이동
		response.sendRedirect("list.do");
		
		

	}

}