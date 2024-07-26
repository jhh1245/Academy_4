package controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import dao.BoardDao;
import util.MyCommon;
import util.Paging;
import vo.BoardVo;
import vo.MemberVo;

@Controller
@RequestMapping("/board/")
public class BoardController {

	public BoardController() {
		System.out.println("--BoardController()--");

	}

	@Autowired
	HttpServletRequest request;

	@Autowired
	HttpSession session;

	@Autowired
	BoardDao board_dao; // 인젝션 받아야 한다.

	// list.do
	// list.do?page=1
	@RequestMapping("list.do")
	public String list(@RequestParam(name="page",defaultValue = "1") int nowPage,
			Model model) {

		// 세션에 기록되어있는 show 삭제
		session.removeAttribute("show");

		Map<String, Object> map = new HashMap<String, Object>(); // 페이징 메뉴가 들어갈 정보
		
		int start = (nowPage-1) * MyCommon.Board.BLOCK_LIST + 1;
		int end   = start + MyCommon.Board.BLOCK_LIST - 1;
		
		map.put("start", start);
		map.put("end", end);
		
		// 게시판 목록 가져오기
		List<BoardVo> list = board_dao.selectList(map); 
		// 위에 선언한 dao에서 selectList()메서도를 통해서 가져온다.

		// System.out.println(list.size());

		// 전체게시물수
		int rowTotal = board_dao.selectRowTotal(map);
		
		// page Menu 생성하기
		String pageMenu = Paging.getPaging("list.do", nowPage, rowTotal, MyCommon.Board.BLOCK_LIST, MyCommon.Board.BLOCK_PAGE);
		
		
		// DS이 전달해준 Model 통해서 데이터를 넣는다.
		// DS는 Model에 저장된 데이터를 request binding 시킨다.
		model.addAttribute("list", list);
		model.addAttribute("pageMenu", pageMenu);
		
		return "board/board_list";
	}

	// 새글쓰기 폼 띄우기
	@RequestMapping("insert_form.do")
	public String insert_form() {

		return "board/board_insert_form";
	}

	// /board/insert.do?b_subject=제목&b_content=내용
	@RequestMapping("insert.do")
	public String insert(BoardVo vo, RedirectAttributes ra) {
		// 로그인 정보 얻어옴
		MemberVo user = (MemberVo) session.getAttribute("user");

		if (user == null) {

			ra.addAttribute("reason", "session_timeout");
			return "redirect:../member/login_form.do";
		}

		// 사용자 정보 vo에 등록
		vo.setMem_idx(user.getMem_idx());
		vo.setMem_name(user.getMem_name());

		// 작성자 IP
		String b_ip = request.getRemoteAddr();
		vo.setB_ip(b_ip);

		// \n -> <br>
		String b_content = vo.getB_content().replaceAll("\n", "<br>");
		vo.setB_content(b_content);

		// DB insert
		int res = board_dao.insert(vo);

		return "redirect:list.do";
	}

	// 상세보기
	// /board/view.do?b_idx=5
	@RequestMapping("view.do")
	public String view(int b_idx, Model model) {

		// b_idx에 해당되는 게시물 1건 얻어오기
		BoardVo vo = board_dao.selectOne(b_idx);

		// 안 봤으면
		if (session.getAttribute("show") == null) {
			// 조회수 증가
			int res = board_dao.update_readhit(b_idx);

			session.setAttribute("show", true);
		}

		model.addAttribute("vo", vo);

		return "board/board_view";
	}

	// 답글쓰기 폼 띄우기
	@RequestMapping("reply_form.do")
	public String reply_form() {

		return "board/board_reply_form";
	}

	// /b_idx=26&b_subject=zaa&b_content=zzssssss
	// b_idx는 기존글, b_subject, b_content는 새로 답글

	@RequestMapping("reply.do")
	public String reply(BoardVo vo, RedirectAttributes ra) {
		
		// 로그인 정보 얻어옴
		MemberVo user = (MemberVo) session.getAttribute("user");
		
		if(user == null) {
			
			ra.addAttribute("reason", "session_timeout");
			return "redirect:../member/login_form.do";
		}
		
		// 사용자 정보 vo에 등록 
		vo.setMem_idx(user.getMem_idx());
		vo.setMem_name(user.getMem_name());
		
		// 기준글 정보 얻어온다
		BoardVo baseVo = board_dao.selectOne(vo.getB_idx());
		
		// 기존글보다 step이 큰 게시물의 step을 1씩 증가 
		// 답글달 자리 뒤에 있는 글들을 한칸씩 밑으로 내리기 위함 
		int res = board_dao.update_step(baseVo); // 기존글 정보 들어감  
		
		
		// 답글의 b_ref, b_step, b_depth 설정 
		vo.setB_ref(baseVo.getB_ref());          // 기준글의 b_ref를 넣는다. 
		vo.setB_step(baseVo.getB_step() + 1);    // 답글의 step = 기준글의 step + 1
		vo.setB_depth(baseVo.getB_depth() + 1);  // 답글의 depth = 기준글의 depth + 1 
		
		// IP 넣기 
		String b_ip = request.getRemoteAddr();
		vo.setB_ip(b_ip);
		
		// 엔터처리 \n -> <br> 
		String b_content = vo.getB_content().replaceAll("\n", "<br>");
		vo.setB_content(b_content);
		
		// 답글 추가 
		res = board_dao.reply(vo);
		
		
		return "redirect:list.do";
	}
	
	
	// 삭제 
	// /bbs/board/delete.do?b_idx=16
	@RequestMapping("delete.do")
	public String delete(int b_idx) {
		
		// 삭제처리 : b_use = 'n' 변경 
		int res = board_dao.update_delete(b_idx);
		
		return "redirect:list.do";
	}
	
	
	
	// 수정 폼 띄우기
	@RequestMapping("modify_form.do")
	public String modify_form(int b_idx, Model model) {
		
		// 수정할 원본 데이터 
		BoardVo vo = board_dao.selectOne(b_idx);
		
		String b_content = vo.getB_content().replaceAll("<br>", "\n");
		vo.setB_content(b_content);
		
		model.addAttribute("vo", vo);

		return "board/board_modify_form";
	}

	
	// 수정
	// /board/modify.do?b_idx=5&b_subject=제목&b_content=내용
	@RequestMapping("modify.do")
	public String modify(BoardVo vo, RedirectAttributes ra) {
		
		
		//로그인 유저정보 얻어온다
		MemberVo user = (MemberVo) session.getAttribute("user");
		
		if(user==null) {
			
			ra.addAttribute("reason", "session_timeout");
			
			return "redirect:../member/login_form.do";
		}
		
		//사용자정보 vo에 등록
		//vo.setMem_idx(user.getMem_idx());
		//vo.setMem_name(user.getMem_name());
		
		//작성자 IP
		String b_ip = request.getRemoteAddr();
		vo.setB_ip(b_ip);
				
		
		// \n -> <br>
		String b_content = vo.getB_content().replaceAll("\n", "<br>");
		vo.setB_content(b_content);
		
		//DB update
		int res = board_dao.update(vo);
		
		
		ra.addAttribute("b_idx", vo.getB_idx());
		
		return "redirect:view.do";
	}
}
