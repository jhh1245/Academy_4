package controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import dao.CommentDao;
import vo.CommentVo;

@Controller
@RequestMapping("/comment/")
public class CommentController {
	
	@Autowired
	CommentDao comment_dao;
	
	
	@Autowired
	HttpServletRequest request;
	
	public CommentController() {
		System.out.println("--CommentController()--");
	}
	
	
	// /comment/list.do?b_idx=5
	@RequestMapping("list.do")
	public String list(int b_idx, Model model) {
		
		List<CommentVo> list = comment_dao.selectList(b_idx);
		
		model.addAttribute("list", list);
		
		return "comment/comment_list";
	}

	// /comment/insert.do?cmt_content=내용&b_idx=5&mem_idx=2&mem_name=일길동
	@RequestMapping(value="insert.do" , produces="application/json; charset=utf-8;")
	@ResponseBody
	public String insert(CommentVo vo) {

		String cmt_ip = request.getRemoteAddr();
		vo.setCmt_ip(cmt_ip);
		
		String cmt_content = vo.getCmt_content().replaceAll("\n", "<br>");
		vo.setCmt_content(cmt_content);
		
		int res = comment_dao.insert(vo);
		
		JSONObject json = new JSONObject();
		json.put("result", res==1); // {"result": true } or {"result": false }
		
		
		return json.toString();
	}
}
