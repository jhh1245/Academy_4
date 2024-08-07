package controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import dao.DeptDao;
import vo.DeptVo;

@Controller
public class DeptController {
	
	DeptDao dept_dao;

	public DeptController(DeptDao dept_dao) {
		super();
		this.dept_dao = dept_dao;
	}
	
	@RequestMapping("/dept/list.do") // 이렇게 요청이 오면 
	public String list(Model model) {
		
		List<DeptVo> list = dept_dao.selectList();
		//System.out.println(list.size());
		
		model.addAttribute("list", list);
		
		return "dept/dept_list"; // view이다. 
		// /WEB-INF/views/ + dept/dept_list + .jsp
	}
	
	
}
