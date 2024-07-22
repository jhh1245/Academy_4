package controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import dao.PhotoDao;
import util.MyCommon;
import util.Paging;
import vo.MemberVo;
import vo.PhotoVo;

@Controller
@RequestMapping("/photo/")
public class PhotoController {
	
	@Autowired
	PhotoDao photo_dao;
	
	@Autowired
	HttpServletRequest request;
	
	@Autowired
	HttpSession  session;
	
	@Autowired
	ServletContext application;
	
	public PhotoController() {
		System.out.println("--PhotoController()--");
	}
	
	
	// /photo/list.do
	// /photo/list.do?page=2
	@RequestMapping("list.do")
	public String list(@RequestParam(name="page", defaultValue="1") int nowPage, Model model) {
		// url 파라미터이름이 page면 nowPage라고 하고, 기본값은 1로 하겠다. 문자열로 줘야 한다. 파라미터는 String 형이니까 
		
		
		//게시물의 범위 계산(start/end)
		int start = (nowPage-1) * MyCommon.Photo.BLOCK_LIST + 1 ;
		int end   = start + MyCommon.Photo.BLOCK_LIST - 1 ;
		
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("start", start);
		map.put("end", end);
		
		
		List<PhotoVo> list = photo_dao.selectList(map);
		
		//전체 게시물수
		int rowTotal = photo_dao.selectRowTotal();
		
		//pageMenu만들기
		String pageMenu = Paging.getPaging("list.do",                     // pageURL 
				                           nowPage,                       // 현재페이지
				                           rowTotal,                      // 전체게시물수
				                           MyCommon.Photo.BLOCK_LIST,    // 한화면에 보여질 게시물수
				                           MyCommon.Photo.BLOCK_PAGE);   // 한화면에 보여질 페이지수

		//request binding. 디스패처 서블릿으로 
		model.addAttribute("list", list);
		model.addAttribute("pageMenu", pageMenu);
		
		
		return "photo/photo_list";
	}
	
	
	// 사진등록폼 띄우기 
	@RequestMapping("insert_form.do")
	public String insert_form() {
		
		return "photo/photo_insert_form";
				
	}
	
	
	// 사진 등록
	@RequestMapping("insert.do") 
	public String insert(PhotoVo vo, @RequestParam MultipartFile photo,
			RedirectAttributes ra) {  
		// parameter 이름과 받는 변수명 동일하면 생략 가능
				

		MemberVo user = (MemberVo) session.getAttribute("user");
		
		// session timeout
		if(user==null) {
			
			//response.sendRedirect("../member/login_form.do?reason=session_timeout");
			
			return "../member/login_form.do";
		}
		return "redirect:list.do";
				
	}
	
	
	
	
}
