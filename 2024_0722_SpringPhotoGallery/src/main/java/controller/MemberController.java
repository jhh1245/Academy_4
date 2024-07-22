package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import dao.MemberDao;
import vo.MemberVo;

@Controller
@RequestMapping("/member/")
public class MemberController {

	// 자동연결 (요청시 마다 injection 인젝션)
	@Autowired
	HttpServletRequest request; // 디스패처 서블릿이 넣어준다. (지금 요청한 사항, 매번 달라진다)  
	
	@Autowired
	HttpSession session; // 디스패처 서블릿이 넣어준다. 
	
	// 처음 1회 연결 
	@Autowired
	MemberDao member_dao; 
	
	public MemberController() {
		System.out.println("--MemberController()--");
	}
	
	
	
	
	// class RequestMapping + method RequestMapping => /member/login_form.do
	@RequestMapping("login_form.do")
	public String login_form() {

		return "member/member_login_form";
	}
	
	
	// /member/login.do?mem_id=one&mem_pwd=1234
	@RequestMapping("login.do")
	public String login(String mem_id,String mem_pwd,RedirectAttributes ra) {
		
		MemberVo user = member_dao.selectOne(mem_id);
		
		if(user==null) {
			
			//RedirectAttributes=> redirect시 parameter로 이용된다
			ra.addAttribute("reason", "fail_id");
			
			return "redirect:login_form.do";
		}
		
		//비밀번호가 틀린경우
		if(user.getMem_pwd().equals(mem_pwd)==false) {
			
			//response.sendRedirect("login_form.do?reason=fail_pwd&mem_id=" + mem_id);
			//RedirectAttributes=> redirect시 parameter로 이용된다
			ra.addAttribute("reason", "fail_pwd");
			ra.addAttribute("mem_id", mem_id);
			
			return "redirect:login_form.do";
		}
		
		session.setAttribute("user", user);
		
		return "redirect:../photo/list.do";
	}

	
	// 로그아웃 
	@RequestMapping("logout.do")
	public String logout() { 
		session.removeAttribute("user");
		return "redirect:../photo/list.do";
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
