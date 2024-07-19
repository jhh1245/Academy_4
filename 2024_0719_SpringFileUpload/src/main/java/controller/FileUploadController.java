package controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import vo.PhotoVo;

@Controller
public class FileUploadController {

	
	// DispatcherServlet Injection(주입)시켜준다
	
	@Autowired
	ServletContext application;
	
	
	// /upload1.do?title=제목&photo=a.jpb
	// @RequestParam(name="photo") MultipartFile photo <= 이름이 동일하면 name="photo" 생략가능
	@RequestMapping("/upload1.do")
	public String upload1(String title, 
			              @RequestParam(name="photo") MultipartFile photo,
			              Model model) throws Exception {
		
		//웹경로
		String webPath = "/resources/images/";
		//상대경로를 이용해서 절대경로 구한다
		String absPath = application.getRealPath(webPath);
		
		System.out.println(absPath);
		
		String filename="no_file";
		
		if(!photo.isEmpty()) {
			//업로드된 화일명을 구한다
			filename = photo.getOriginalFilename();
			
			File f = new File(absPath,filename);
			
			if(f.exists()) {//동일화일이 존재하냐?
				
				// 시간_화일명 이름변경
				long tm = System.currentTimeMillis();
				filename = String.format("%d_%s", tm,filename);
				
				f = new File(absPath,filename);
			}
			
			//spring이 저장해놓은 임시화일을 복사한다
			// photo가 가지고 있는 파일을 복사 
			photo.transferTo(f);
			
		}
		
		
		//결과적으로 request binding
		model.addAttribute("title", title);
		model.addAttribute("filename",filename);
				
		
		return "result1";
	}
	
	
	@RequestMapping("/upload2.do")
	public String upload2(PhotoVo vo, Model model) throws Exception {
		
		//웹경로
		String webPath = "/resources/images/";
		//상대경로를 이용해서 절대경로 구한다
		String absPath = application.getRealPath(webPath);
		
		System.out.println(absPath);
		
		String filename = "no_file";
		
		MultipartFile photo = vo.getPhoto();
		
		if(!photo.isEmpty()) {
			//업로드된 화일명을 구한다
			filename = photo.getOriginalFilename();
			
			File f = new File(absPath,filename);
			
			if(f.exists()) {//동일화일이 존재하냐?
				
				// 시간_화일명 이름변경
				long tm = System.currentTimeMillis();
				filename = String.format("%d_%s", tm,filename);
				
				f = new File(absPath,filename);
			}
			
			//spring이 저장해놓은 임시화일을 복사한다
			// photo가 가지고 있는 파일을 복사 
			photo.transferTo(f);
			
		}
		
		vo.setFilename(filename);
		
		//결과적으로 request binding
		model.addAttribute("vo",vo);

		
		return "result2";
	}
}
