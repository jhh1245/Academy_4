package controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import service.ProductService;
import vo.ProductVo;

@Controller
public class ProductController {

	@Autowired
	ProductService product_service;
	
	@RequestMapping("/product/list.do")
	public String list(Model model) {
		
		Map map = product_service.selectTotalMap();
		
		model.addAttribute("map", map);
		
		return "product/product_list";
	}
	
	//입고처리
	// /product/insert_in.do?name=TV&cnt=100
	@RequestMapping("/product/insert_in.do")
	public String insert_in(ProductVo vo) {
		
		try {
			product_service.insert_in(vo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "redirect:list.do";
	}
}
