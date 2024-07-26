package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

	//자동연결(요청시 마다 Injection)
	@Autowired
	HttpServletRequest request;
	
	@Autowired
	HttpSession session;

	
	public MainController() {
		System.out.println("--MainController()--");
	}
	

	@RequestMapping("main.do")
	public String main() {
		return "main";
	}
	

	
	
}
