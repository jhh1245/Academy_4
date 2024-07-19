package controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import dao.VisitDao;
import util.MyCommon;
import util.Paging;
import vo.VisitVo;

@Controller
public class VisitController {

	// @RequestMapping에 의해서 메소드 호출시 DS가 자동으로 Injection 주입
	// 컨텍스트에서 자동생성될 경우만 autowired된다. 
	// 수동생성시에는  < > 등록해야함 
	
	@Autowired
	HttpServletRequest request;
	
	public VisitController() {
		System.out.println("-- visit controller() -- ");
	}

	@Autowired
	VisitDao visit_dao;

	@RequestMapping("/visit/list.do")
	// search라는 파라미터를 받아서 String search 이 변수에 넣어주고, 만약 값이 없다면 기본값으로 all
	// 아래에선 page를 받아서 nowPage로. 파라미터는 String으로 들어오니까 초기값 주려면 문자열로 자동으로 Integer Parse
	// Int한다.
	public String list(@RequestParam(name = "search", defaultValue = "all") String search, String search_text,
			@RequestParam(name = "page", defaultValue = "1") int nowPage, Model model) {

		// 1.parameter 받기 // 여기 지워도 된다.
// 		String search 		= request.getParameter("search");
//		String search_text 	= request.getParameter("search_text");

		// if(search == null) search = "all"; // null 이라는 뜻 = 전체검색하겠다.

		// 검색조건을 담을 맵 ---------------------------------------
		Map<String, Object> map = new HashMap<String, Object>();

		// 이름 + 내용
		if (search.equals("name_content")) { // name 값 null 이면 에러
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

		// -------[ Begin : Page Menu ]------------------------

		/*
		 * int nowPage = 1; try { nowPage =
		 * Integer.parseInt(request.getParameter("page")); } catch(Exception e) {
		 * 
		 * }
		 */

		// start & end
		int start = (nowPage - 1) * MyCommon.Visit.BLOCK_LIST + 1;
		int end = start + MyCommon.Visit.BLOCK_LIST - 1;

		map.put("start", start);
		map.put("end", end);

		// 총 게시물 수 (필터링 된)
		int rowTotal = visit_dao.selectRowTotal(map);

		// 검색정보 filter : search_filter="search=name&search_text=길동"
		String search_filter = String.format("search=%s&search_text=%s", search, search_text);

		// pageMenu 만들기
		String pageMenu = Paging.getPaging("list.do", // pageURL
				search_filter, nowPage, // 현재 페이지
				rowTotal, // 전체 게시물 수
				MyCommon.Visit.BLOCK_LIST, // 한 화면에 보여질 게시글 수
				MyCommon.Visit.BLOCK_PAGE); // 한 화면에 보여질 페이지 수

		// -------[ End : Page Menu ]------------------------

		// 방명록 데이터 가져오기
		List<VisitVo> list = visit_dao.selectList(map);

		// request binding ...
		// request.setAttribute("list", list);
		// request.setAttribute("pageMenu", pageMenu);

		// 결과적으로 request binding
		model.addAttribute("list", list);
		model.addAttribute("pageMenu", pageMenu);

		return "visit/visit_list";

	} // end:list()

	// 입력 폼 띄우기
	@RequestMapping("/visit/insert_form.do")
	public String insert_form() {

		return "visit/visit_insert_form"; // view name 
	}

	
	
	// 입력하기
	// visit.do?name=홍길동&content=내용&pwd=1234 -> VO로 만들어준다. 
	@RequestMapping("/visit/insert.do")
	public String insert(VisitVo vo) {
		// 메소드 인자 : 디스패처 서블릿에 대한 요구사항 
		
		// 1. parameter 받기
		// url 통해서 전달받은 것
		// String name = request.getParameter("name");
		// String content = request.getParameter("content").replaceAll("\n", "<br>");
		// String pwd = request.getParameter("pwd");

		String content = vo.getContent().replaceAll("\n", "<br>");
		vo.setContent(content);
		
		// 2. ip 정보 얻어온다.
		String ip = request.getRemoteAddr();
		vo.setIp(ip);
		// 3. VisitVo 포장
		// VisitVo vo = new VisitVo(name, content, pwd, ip);

		// 4. DBinsert
		int res = visit_dao.insert(vo);

		// 5. 목록보기 이동
		// 디스패처 서블릿에게 반환
		// redirect: 접두어 확인 후 response.sendRedirect("list.do") 처리 
		return "redirect:list.do";
	}

	
	// 비밀번호 체크 
	// /check_pwd.do? idx=5 & no=1245
	@RequestMapping(value = "/visit/check_pwd.do", produces = "application/json;charset=utf-8")
	@ResponseBody
	public String check_pwd(int idx, String c_pwd) {

		// 1. parameter 받기
		// int idx = Integer.parseInt(request.getParameter("idx"));
		// String c_pwd = request.getParameter("c_pwd");

		// 2. idx에 해당되는 게시물 1건 얻어온다.
		VisitVo vo = visit_dao.selectOne(idx);

		// 3. 비밀번호 비교
		boolean bResult = vo.getPwd().equals(c_pwd);

		// JSON Data 생성 전송 : {"result":true} 반드시 {"xxx":xxx} 형식으로
		String json = String.format("{\"result\":%b}", bResult);

		// view가 아님 상단에 붙은 ResponseBody어노테이션 보고, 바로 전송 (응답 데이터)하는데 타입을 produces로
		// 반환값을 DS가 직접 전송 
		// 선생님이 만든 Spring MVC 샘플에서 FrontController 참고!!! 
		// conentType은 @RequestMapping에 produces값을 이용한다
		/*
		     response.setContentType("application/json; charset=utf-8");  
		     response.getWriter().print("{"result":true}");
		 */
		return json; 
		
		
	}

	//삭제하기
	// /visit/delete.do?idx=5
	@RequestMapping("/visit/delete.do")
	public String delete(int idx) {
		// 1. 삭제할 idx 수신
		// int idx = Integer.parseInt(request.getParameter("idx")); // "21" -> 21
		
		

		// 2. DB delete
		int res = visit_dao.delete(idx);

		return "redirect:list.do";

	}

	//수정폼 띄우기
	// /visit/modify_form.do?idx=5
	@RequestMapping("/visit/modify_form.do")
	public String modify_form(int idx,Model model) {

		// visit/modify_form.do?idx=10&no=2
		// 1. 수정할 게시물의 idx를 받는다.
		// int idx = Integer.parseInt(request.getParameter("idx"));

		// 2. idx에 해당하는 게시물 1건 얻어오기
		VisitVo vo = visit_dao.selectOne(idx);

		// textarea \n 기능처리 : <br>을 \n로 변환
		String content = vo.getContent().replaceAll("<br>", "\n");
		vo.setContent(content);

		// 3. request binding 아래 파일과 데이터 공유
		request.setAttribute("vo", vo);

		return "visit/visit_modify_form";
	}

	
	// 수정 
	@RequestMapping("/visit/modify.do")
	public String modify(VisitVo vo, 
			@RequestParam(name="page", defaultValue="1") int page,
			@RequestParam(name="search", defaultValue="all") String search, // search라는 파라미터가 들어오면 search라는 변수에 담는다. 
			String search_text,
			RedirectAttributes ra // redirect를 담을 변수를 달라고 요청 
		) throws Exception {
		
		// 1. parameter
		/* int idx = Integer.parseInt(request.getParameter("idx"));
		String no = request.getParameter("no");
		String name = request.getParameter("name");
		String content = request.getParameter("content").replace("\n", "<br>");
		String pwd = request.getParameter("pwd");
		*/
		
		String content = vo.getContent().replaceAll("\n", "<br>");
		vo.setContent(content);
		
		/*
		 * String page = request.getParameter("page"); String search =
		 * request.getParameter("search"); String search_text =
		 * request.getParameter("search_text");
		 */
		
		
		// 2. ip 주소 얻어온다.
		// 톰켓이 알아서 전달해준다. getParameter아니다.
		String ip = request.getRemoteAddr();
		vo.setIp(ip);

		// 3. VisitVo 포장
		// VisitVo vo = new VisitVo(idx, name, content, pwd, ip);

		// 4. DB update
		int res = visit_dao.update(vo);

		// return String.format("list.do?page=%s&search=%s&search_text=%s",page, search, search_text);

		// RedirectAttributes : redirect parameter 정보 담는 객체 
		ra.addAttribute("page", page);
		ra.addAttribute("search", search);
		ra.addAttribute("search_text", search_text);
		
		// DispatcherServlet이 response.sendRedirect("list.do?page=%s&search=%s&search_text=%s");
		
		return "redirect:list.do";
	}

}
