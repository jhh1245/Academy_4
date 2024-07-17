package controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TestController {
	public TestController() {
		System.out.println("-- TestController() --");
		
		
	}
	
	@RequestMapping("/test.do") //디스패처가스캔한다음 @이게 붙은걸 보고 /test.do로 요청온거는 이쪽으로온다. 
	public String test() {
		return "test"; // 뷰 이름이다.  
		// viewName(JSP 파일) : /WEB-INF/view/test.jsp
		// 내가 전달한 "test"앞에 prefix( /WEB-INF/view/), suffix (.jsp)가 붙었다. 
		// 누가해줬나? 뷰 리졸버가 
	
	}
	
	@RequestMapping(value="/hello.do", produces="text/html;charset=utf-8;")
	@ResponseBody
	public String hello() {
		return "hello : 안녕하세요";
	}
	
	
	@RequestMapping("/hi.do")
	public String hi(Model model) {
		
		String msg = "Hi~ Everyone!!"; // 이 데이터를 hi.jsp에 넘기고 싶다 => model이 필요하다.
		
		// Model을 통해서 전달된 데이터는 -> DispatcherServlet에게 전달
		// D.S.는 request binding or parameter  
		// 		 return 값이 뷰면 : request binding
		// 		 return 값이 redirect 면 : parameter로 사용한다. 
		model.addAttribute("msg", msg);
		
		return "hi";
	}
	
	@RequestMapping("/bye.do")
	public ModelAndView bye() {
		String msg="GoodBye!!";
		
		// Data + View
		ModelAndView mv = new ModelAndView();
		
		// DS는 전달된 데이터를 request binding 
		mv.addObject("msg", msg);
		
		// DS는 전달된 뷰 정보를 완성시키기 위해서 ViewResolver에 작업 지시 
		mv.setViewName("bye"); // /WEB-INF/views/ + bye + .jsp
		
		return mv;
	}
	
	
	@RequestMapping("/hi2.do") // 얘를 호출한 애는 D.S. 
	public String hi2(Model model) { // D.S에게 저장소 정보를 줘. 해서 받는다.  
		String name="Tom";
		model.addAttribute("name", name);
		
		return "redirect:test.do"; // 리다이렉트를 적으면 
	}
}
