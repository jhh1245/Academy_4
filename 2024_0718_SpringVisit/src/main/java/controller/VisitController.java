package controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import dao.VisitDao;
import util.MyCommon;
import util.Paging;
import vo.VisitVo;

@Controller
public class VisitController {

	public VisitController() {
		System.out.println("-- visit controller() -- ");
	}
	
	@Autowired
	VisitDao visit_dao;
	
	
	
	@RequestMapping("/visit/list.do")
	public String list(HttpServletRequest request, HttpServletResponse response) {
		
		// 1.parameter 받기
		String search 		= request.getParameter("search");
		String search_text 	= request.getParameter("search_text");
		
		if(search == null) search = "all"; // null 이라는 뜻 = 전체검색하겠다. 

		// 검색조건을 담을 맵 ---------------------------------------
		Map<String, Object> map = new HashMap<String, Object>();
				
		// 이름 + 내용 
		if(search.equals("name_content")) { // name 값 null 이면 에러 
			map.put("name", search_text);
			map.put("content", search_text);
			// name : 길동 
			// content : 길동
			
		} else if (search.equals("name")) { // search == "name"은 안된다. 
			// 이름 
			map.put("name", search_text);
			
		} else if (search.equals("content")) {
			// 내용 
			map.put("content", search_text);
		}
				
		
		//-------[ Begin :  Page Menu ]------------------------	
		
		int nowPage = 1;
		try {
			nowPage = Integer.parseInt(request.getParameter("page"));
		} catch(Exception e) {
			
		}
		
		// start & end 
		int start = (nowPage-1) * MyCommon.Visit.BLOCK_LIST + 1;
		int end = start + MyCommon.Visit.BLOCK_LIST - 1;

		map.put("start", start);
		map.put("end", end);
		
		
		// 총 게시물 수 (필터링 된)
		int rowTotal = visit_dao.selectRowTotal(map); 
		
		// 검색정보 filter : search_filter="search=name&search_text=길동"
		String search_filter = String.format("search=%s&search_text=%s", search, search_text);
		
		// pageMenu 만들기
		String pageMenu = Paging.getPaging("list.do", // pageURL
							 	search_filter,
								nowPage, 			  // 현재 페이지
								rowTotal, 			  // 전체 게시물 수
								MyCommon.Visit.BLOCK_LIST,  // 한 화면에 보여질 게시글 수 
								MyCommon.Visit.BLOCK_PAGE); // 한 화면에 보여질 페이지 수
		
		
		//-------[ End :  Page Menu ]------------------------	
		
		// 방명록 데이터 가져오기 
		List<VisitVo> list = visit_dao.selectList(map);
		
		// request binding ...
		request.setAttribute("list", list);
		request.setAttribute("pageMenu", pageMenu);				
				
		return "visit/visit_list";
		
	} // end:list()
	
	
	
	
	// 입력 폼 띄우기 
	@RequestMapping("/visit/insert_form.do")
	public String insert_form(HttpServletRequest request, HttpServletResponse response) {
		
		return "visit_insert_form.jsp";
	}
	
	
	
	// 입력하기 
	@RequestMapping("/visit/insert.do")
	public String insert(HttpServletRequest request, HttpServletResponse response) {
		// 1. parameter 받기 
		// url 통해서 전달받은 것 
		String name    = request.getParameter("name");
		String content = request.getParameter("content").replaceAll("\n", "<br>");
		String pwd     = request.getParameter("pwd");
		
		// 2. ip 정보 얻어온다.
		String ip      = request.getRemoteAddr();
		 
		// 3. VisitVo 포장
		VisitVo vo = new VisitVo(name, content, pwd, ip);
		
		// 4. DBinsert 
		int res = visit_dao.insert(vo);
		
		// 5. 목록보기 이동
		
		return "redirect:list.do";
	}
	
	
	@RequestMapping(value="/visit/check_pwd.do", produces="application/json;charset=utf-8")
	@ResponseBody
	public String check_pwd(HttpServletRequest request, HttpServletResponse response) {
		
		// 1. parameter 받기
		// /check_pwd.do?   idx=5  &   no=1245@ 
		int    idx   = Integer.parseInt(request.getParameter("idx"));
		String c_pwd = request.getParameter("c_pwd");
		
		// 2. idx에 해당되는 게시물 1건 얻어온다.
		VisitVo vo = visit_dao.selectOne(idx);
		
		// 3. 비밀번호 비교 
		boolean bResult = vo.getPwd().equals(c_pwd);
		
		// JSON Data 생성 전송 : {"result":true} 반드시 {"xxx":xxx} 형식으로 
		String json = String.format("{\"result\":%b}", bResult);
				
		return json;
		
	}
	
	
	@RequestMapping("/visit/delete.do")
	public String delete(HttpServletRequest request, HttpServletResponse response) {
		// 1. 삭제할 idx 수신 
		int idx = Integer.parseInt(request.getParameter("idx")); // "21" -> 21
		String no = request.getParameter("no"); 
		
		// 2. DB delete 
		int res = visit_dao.delete(idx);
		
		return "redirect:list.do#p_" + no;
		
	}
	
	@RequestMapping("/visit/modify_form.do")
	public String modify_form(HttpServletRequest request, HttpServletResponse response) {
		
		// visit/modify_form.do?idx=10&no=2
		// 1. 수정할 게시물의 idx를 받는다. 
		int idx = Integer.parseInt(request.getParameter("idx"));
		
		// 2. idx에 해당하는 게시물 1건 얻어오기 
		VisitVo vo = visit_dao.selectOne(idx);
		
		// textarea \n 기능처리 : <br>을 \n로 변환
		String content = vo.getContent().replaceAll("<br>", "\n");
		vo.setContent(content);
		
		// 3. request binding 아래 파일과 데이터 공유 
		request.setAttribute("vo", vo);
		
		return "visit_modify_form.jsp";
	}
	
	@RequestMapping("/visit/modify.do")
	public String modify(HttpServletRequest request, HttpServletResponse response) {
		// 1. parameter
		int 	idx 	= Integer.parseInt(request.getParameter("idx"));
		String 	no 		= request.getParameter("no");
		String 	name 	= request.getParameter("name");		
		String 	content = request.getParameter("content").replace("\n", "<br>");
		String  pwd		= request.getParameter("pwd");
		
		String  page		= request.getParameter("page");
		String  search		= request.getParameter("search");
		String  search_text		= request.getParameter("search_text");
		
		// 2. ip 주소 얻어온다.
		// 톰켓이 알아서 전달해준다. getParameter아니다.
		String ip		= request.getRemoteAddr();
		// tcp, http 
		
		// 3. VisitVo 포장 
		VisitVo vo = new VisitVo(idx, name, content, pwd, ip);
		
		// 4. DB update 
		int res = visit_dao.update(vo);
		
		return String.format("list.do?page=%s&search=%s&search_text=%s", page, search, search_text);
		
	}
	
}
