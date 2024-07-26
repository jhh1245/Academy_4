package controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import dao.DiaryDao;
import vo.WeightVo;

@Controller
/* @RequestMapping("/board/") */
public class DiaryController {

	public DiaryController() {
		System.out.println("--DiaryController()--");
	}

	@Autowired
	HttpServletRequest request;

	@Autowired
	HttpSession session;

	@Autowired
	DiaryDao diary_dao; // 인젝션 받아야 한다.

	// 다이어리 메인에 표시할 내용. 오늘날짜기준 데이터 표시  
	@RequestMapping("/diary_list.do")
	public String diary_list(Model model) {

		List<WeightVo> weight_list = diary_dao.selectList_weight(); 
		// 체중말고도 더하기 
		model.addAttribute("weight_list", weight_list);
		
		return "diary/diary_list";
	}
	
	
	// 선택한 날짜 기록
	@RequestMapping(value = "/diary_select_date.do", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
	public String diary_select_date(String date) {
		System.out.println("컨트롤러 : " + date);
		
		List<WeightVo> weight_list = diary_dao.diary_select_weight_date(date); 
		// 체중말고도 더하기
		
		JSONObject json = new JSONObject();
		json.put("weight_list", weight_list);
		
		return json.toString();
	}
	

	// 새글쓰기 폼 띄우기
	@RequestMapping("/diary_insert_form.do")
	public String diary_insert_form(Model model) {

		String select_date = request.getParameter("select_date");
	
		model.addAttribute("select_date", select_date);
		
		return "diary/diary_insert_form__";
	}
	
	
	// 체중 기록 추가 
	@RequestMapping("/diary_insert_weight.do")
	public String diary_insert_weight(WeightVo vo) {
		
		int res = diary_dao.diary_insert_weight(vo);

		return "redirect:diary_list.do";
	}

	
	// 체중 상세보기
	@RequestMapping("/diary_view_weight.do")
	public String diary_view_weight(int w_idx, Model model) {

		// b_idx에 해당되는 게시물 1건 얻어오기
		WeightVo vo = diary_dao.selectOne_weight(w_idx);

		model.addAttribute("vo", vo);

		return "diary/diary_view_weight";
	}

	
	// 체중 수정 폼 띄우기
	@RequestMapping("/diary_modify_form_weight.do")
	public String diary_modify_form_weight(int w_idx, Model model) {
		
		// 수정할 원본 데이터 
		WeightVo vo = diary_dao.selectOne_weight(w_idx);
		
		// String b_content = vo.getB_content().replaceAll("<br>", "\n");
		// vo.setB_content(b_content);
		
		model.addAttribute("vo", vo);

		return "diary/diary_modify_form_weight";
	}

	
	// 수정
	// /board/modify.do?b_idx=5&b_subject=제목&b_content=내용
	@RequestMapping("/diary_modify_weight.do")
	public String diary_modify_weight(WeightVo vo, RedirectAttributes ra) {
		
		
		//로그인 유저정보 얻어온다
		//MemberVo user = (MemberVo) session.getAttribute("user");
		
		
		//사용자정보 vo에 등록
		//vo.setMem_idx(user.getMem_idx());
		//vo.setMem_name(user.getMem_name());
		
				
		
		// \n -> <br>
		// String b_content = vo.getB_content().replaceAll("\n", "<br>");
		// vo.setB_content(b_content);
		
		//DB update
		int res = diary_dao.update_weight(vo);
		
		
		ra.addAttribute("w_idx", vo.getW_idx());
		
		return "redirect:diary_view_weight.do";
	}
	
	/*
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
	
	
	

	
	*/
}
