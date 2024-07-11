package action.photo;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import dao.PhotoDao;
import db.vo.PhotoVo;

/**
 * Servlet implementation class PhotoUploadAction
 */

@WebServlet("/photo/photo_upload.do") // 여기로 들어오면 
public class PhotoUploadAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// /photo/photo_upload.do?p_idx=5&photo=aaa.jpg
		
		// 화일업로드 처리 --------------------------------------------------
        String webPath = "/images/"; // 웹(URL)경로
		
		//현재 웹어플리케이션의 전역관리객체(상대경로->절대경로)
		ServletContext application = request.getServletContext();
		//                웹(상대)경로->절대경로 구하기
		
		String saveDir = application.getRealPath(webPath);
		
		//System.out.println(saveDir);
		
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
		
		if(f != null) { //업로드화일 존재하면
			p_filename = f.getName();
		}
		//화일업로드 처리 끝 --------------------------------------------------
		
		
		int p_idx = Integer.parseInt(mr.getParameter("p_idx")); // 여기에서 request.이 아니라 mr.이어야 한다. 
		
		// p_idx에 저장된 이전 파일은 삭제 
		PhotoVo vo = PhotoDao.getInstance().selectOne(p_idx);
		File delFile = new File(saveDir, vo.getP_filename());
		delFile.delete();
		
		// update된 파일 이름 수정
		vo.setP_filename(p_filename); // DB에 새로 등록한 파일으로 수정하기 위해서 
		int res = PhotoDao.getInstance().update_filename(vo);
		
		
		
		// 응답처리
		response.setContentType("application/json; charset=utf-8;");
	
		// {"p_filename":"%s"}
		String json = String.format("{\"p_filename\":\"%s\"}", p_filename);
		response.getWriter().print(json);
	}
}

