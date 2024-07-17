package controller;

import java.net.http.HttpRequest;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import vo.PersonVo;

@Controller
public class ParamTestController { // servlet-context.xml 에 자동 등록
	
	// DS에게 RequestMapping에 따른 메소드 호출(invoke)시 request 정보를 넣어줘라
	// ParamTestController가 auto-dectecting(자동생성)시에만 Autowire 실행된다.
	// 단, 수동생성시에는 servlet-context.xml 수동생성 코드 위에 아래 코드 작성 
	// <context:annotation-config/>
	
	@Autowired
	HttpServletRequest request;
	
	@Autowired
	HttpSession session;
	
	@Autowired
	ServletContext application;
	
	
	public ParamTestController() {
		System.out.println("-- ParamTestController() --");
	}
	
	
	// DS는 서블릿이니까 request 가지고 있다. 아래처럼 URL에 변수 들어오면 어노테이션 보고 메서드 찾고, 
	// 알아서 메소드 ( ) 파라미터에 넣어준다.
		// DS가 이름이 같은걸 받아서. 알아서 파라미터로 넣을 때 int형이구나 하고 캐스팅 해서 넣어준다.
	// 변수에도 어노테이션 붙일 수 있다. 
	
	// /insert1.do?name=홍길동&age=20&tel=010-111-1234
	@RequestMapping("/insert1.do")
	public String insert1(@RequestParam(name="name") String irum, //parameter명과 수신될변수명이 틀릴경우
            int age,  //생략하면 parameter명과 동일한 변수명에 값을 넣어준다 (DS에게 (메인컨트롤러에게) 파라미터에서 age 받아서 int형으로 달라고 요청)
			String tel, // 예전방식 integer.parseInt(get param.... )이 생략됨  
			@RequestParam(name="nation", required=false, defaultValue="한국") String nation, // 기본값, 필수여부, 쓸 수 있다. 
			Model model) {

		//메소드 인자 : DispacherServlet에 대한 요구사항
		
		String ip = request.getRemoteAddr();
		System.out.println("요청자 IP : " + ip);
		
		//model통한 request binding
		model.addAttribute("name", irum);
		model.addAttribute("age", age);
		model.addAttribute("tel", tel);
		
		return "result1";
	}
	
	
	// 해석을 잘 해야된다. 그냥 쓰지 말기 
	@RequestMapping("/insert2.do")
	public String insert2(PersonVo vo, Model model) { // DS가 변수 다 받아서 -> person vo로 포장해서 + model과 -> 전달해준다. 단, 변수명 동일해야됨!! 
		
		// 각 parameter 받기 -> Vo 생성 후 값을 넣어주고 -> 전달
		// 메소드 인자 : DispacherServlet에 대한 요구사항 
		
		// request binding 
		model.addAttribute("vo", vo);
		
		return "result2";
	}
	
	
	
	@RequestMapping("/insert3.do")
	public String insert3(@RequestParam Map map, Model model, HttpServletRequest request) { // map으로 만들어서 달라고 요청 @@RequestParam를 붙여야 한다. 
	
		String ip = request.getRemoteAddr();
		
		// 메소드 인자 : DispacherServlet에 대한 요구사항 
		model.addAttribute("map", map);
		model.addAttribute("ip", ip);
		
		return "result3";
	}
}
