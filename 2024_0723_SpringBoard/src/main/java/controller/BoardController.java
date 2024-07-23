package controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import dao.BoardDao;
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
	
	
	@RequestMapping("list.do")
	public String list(Model model) {
		
		// 세션에 기록되어있는 show 삭제
		session.removeAttribute("show");
		
		
		
		// 게시판 목록 가져오기 
		List<BoardVo> list = board_dao.selectList(); 
		// 위에 선언한 dao에서 selectList()메서도를 통해서 가져온다.
		
		// System.out.println(list.size());
		
		// DS이 전달해준 Model 통해서 데이터를 넣는다. 
		// DS는 Model에 저장된 데이터를 request binding 시킨다. 
		
		model.addAttribute("list", list);
		
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
		
		MemberVo user = (MemberVo) session.getAttribute("user");
		
		if(user == null) {
			
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
	
	
	//상세보기
	// /board/view.do?b_idx=5
	@RequestMapping("view.do")
	public String view(int b_idx,Model model) {
		
		// b_idx에 해당되는 게시물 1건 얻어오기
		BoardVo vo = board_dao.selectOne(b_idx);

		
		// 안 봤으면
		if(session.getAttribute("show") == null) {
			//조회수 증가
			int res = board_dao.update_readhit(b_idx);
			
			session.setAttribute("show", true);
		}
		
		
		model.addAttribute("vo", vo);
		
		return "board/board_view";
	}
	
	
}
