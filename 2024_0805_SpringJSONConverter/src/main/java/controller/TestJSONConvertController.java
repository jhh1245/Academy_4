package controller;



import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import vo.PersonVo;

@Controller
public class TestJSONConvertController {
	
	@RequestMapping("/map_to_json.do")
	@ResponseBody
	public Map<String, Object> map_to_json() {
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("name", "홍길동");
		map.put("age", 20);
		map.put("addr", "서울시 관악구 남부순환로");
		
		return map; 
	}
	
	@RequestMapping("/object_to_json.do")
	@ResponseBody
	public PersonVo object_to_json() {
				
		PersonVo p = new PersonVo("홍길동", 20, "서울시 관악구 봉천동");
		
		return p; 
	}
	
	@RequestMapping("/list_to_json.do")
	@ResponseBody
	public List<String> list_to_json() {
		
		List<String> sido_list = new ArrayList<String>();
		
		sido_list.add("서울");
		sido_list.add("경기");
		sido_list.add("강원");
		sido_list.add("제주");
		
		return sido_list;
	}
	
	@RequestMapping("/person_list_to_json.do")
	@ResponseBody
	public List<PersonVo> person_list_to_json() {
		
		List<PersonVo> list = new ArrayList<PersonVo>();
		
		list.add(new PersonVo("일길동", 20, "남부순환로1"));
		list.add(new PersonVo("이길동", 21, "남부순환로2"));
		list.add(new PersonVo("삼길동", 22, "남부순환로3"));
		
		
		return list;
	}
	
	@RequestMapping("/person_map_to_json.do")
	@ResponseBody
	public Map<String, Object> person_map_to_json() {
		
		List<PersonVo> list = new ArrayList<PersonVo>();
		
		list.add(new PersonVo("일길동", 20, "남부순환로1"));
		list.add(new PersonVo("이길동", 21, "남부순환로2"));
		list.add(new PersonVo("삼길동", 22, "남부순환로3"));
		
		// Map에 List의 정보들도 포함시키고 싶을 때 
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("size", list.size());
		map.put("search_date", new Date().toString());
		map.put("data", list);
		
		
		return map;
	}
	
}
