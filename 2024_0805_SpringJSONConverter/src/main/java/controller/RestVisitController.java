package controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import dao.VisitDao;
import vo.VisitVo;

@Controller
public class RestVisitController {
	
	@Autowired
	VisitDao visit_dao;
	
	@Autowired
	HttpServletRequest request; // 인젝션 
	
	
	// 전체조회
	@RequestMapping(value = "/rest/visits", method=RequestMethod.GET) // 요청방식이 Get. Post는 insert다.
	@ResponseBody
	public Map<String, Object> selectList(){
		
		List<VisitVo> list = visit_dao.selectList();
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("size", list.size());
		map.put("search_date", new Date().toString());
		map.put("data", list);
		
		return map;
	}
	
	
	// 1건 조회
	@RequestMapping(value = "/rest/visit/{idx}", method=RequestMethod.GET) // 여기가 이름이 idx가 아닐 경우 꼭 path variable 써야된다.
	@ResponseBody
	public VisitVo selectOne(@PathVariable(name="idx") int idx){ // path variable 경로상의 변수 idx를 받아서 (문자열) -> 스프링이 int idx로 변환한다.
		
		VisitVo vo = visit_dao.selectOne(idx);
		
		return vo;
	}
	
	
	
	
	// 추가 : POST
	/*
	 * ## request Body ##	
	 * {
		    "name": "홍길동",
		    "content": "REST API POST 전송한 데이터",
		    "pwd": "1234"
		}
	 * 
	 */
	@RequestMapping(value = "/rest/visit", method=RequestMethod.POST) 
	@ResponseBody
	public Map<String, Object> insert(@RequestBody VisitVo vo){
		
		// \n -> <br>
		String content = vo.getContent().replace("\n", "<br>");
		vo.setContent(content);
		
		String ip = request.getRemoteAddr();
		vo.setIp(ip);
		
		int res = visit_dao.insert(vo);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result", (res == 1));
		
		return map;
	}
	
	// 삭제 
	@RequestMapping(value = "/rest/visit/{idx}", method=RequestMethod.DELETE ) // 여기가 이름이 idx가 아닐 경우 꼭 path variable 써야된다.
	@ResponseBody
	public Map<String, Object> delete(@PathVariable int idx){
		
		int res = visit_dao.delete(idx);
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("result", (res == 1));
		
		return map;
	}
	
	
	// 수정 : PUT
	/*
	 * ## request Body ##	
	 * {
		    "name": "홍길동",
		    "content": "REST API POST 전송한 데이터",
		    "pwd": "1234"
		}
	 * 
	 */
	@RequestMapping(value = "/rest/visit", method=RequestMethod.PUT) 
	@ResponseBody
	public Map<String, Object> update(@RequestBody VisitVo vo){
		
		// \n -> <br>
		String content = vo.getContent().replace("\n", "<br>");
		vo.setContent(content);
		
		String ip = request.getRemoteAddr();
		vo.setIp(ip);
		
		int res = visit_dao.update(vo);
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("result", (res == 1));
		
		return map;
	}
	
	@RequestMapping("/rest/modify_form.do")
	public String modify_form(int idx,Model model) {
		
		// 1. 수정할 게시물의 idx를 받는다.
		// int idx = Integer.parseInt(request.getParameter("idx"));
	
		// 2. idx에 해당하는 게시물 1건 얻어오기
		VisitVo vo = visit_dao.selectOne(idx);
	
		// textarea \n 기능처리 : <br>을 \n로 변환
		String content = vo.getContent().replaceAll("<br>", "\n");
		vo.setContent(content);
	
		// 3. request binding 아래 파일과 데이터 공유
		request.setAttribute("vo", vo);
				
		return "visit_modify_form";
	}
	
	
	// 비밀번호 체크 : /rest/visit/idx/{idx}/c-pwd/{c-pwd}
	@RequestMapping(value="/rest/visit/{idx}/c-pwd/{c_pwd}", method=RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> check_pwd(@PathVariable int idx, @PathVariable String c_pwd){
		
		VisitVo vo = visit_dao.selectOne(idx);
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("result", vo.getPwd().equals(c_pwd)); 
		// vo가 가지고 있는 pwd랑, 넘겨진 c_pwd(사용자가 입력한 비번)가 같은가? 
		
		return map;
	}
	
	// 비밀번호 체크 : /rest/visit/idx/{idx}/c-pwd/{c-pwd}
	@RequestMapping(value="/rest/visit/checkpwd/{idx}/{c_pwd}", method=RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> check_pwd2(@PathVariable int idx, @PathVariable String c_pwd){
		
		VisitVo vo = visit_dao.selectOne(idx);
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("result", vo.getPwd().equals(c_pwd)); 
		// vo가 가지고 있는 pwd랑, 넘겨진 c_pwd(사용자가 입력한 비번)가 같은가? 
		
		return map;
	}
	
	
}
