package action;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import xml.vo.PersonVo;

/**
 * Servlet implementation class PersonListAction
 */

@WebServlet("/person/list.do") // 여기로 들어오면 
public class PersonListAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		List<PersonVo> p_list = new ArrayList<PersonVo>();
		
		// XML Parser : SAXBuilder 
		SAXBuilder builder = new SAXBuilder();
		
		// web 경로를 -> 절대경로로 바꿔줌 
		String absPath = request.getServletContext().getRealPath("/"); // ServletContext = application이다.
		File f = new File(absPath, "person.xml"); // 파일이 필요한 경우는 항상 절대경로가 필요하다. 
		
		try {
			// XML의 문서정보를 읽어온다.
			// 주의. Document는 jdom를 선택해야 한다.
			// builder가 문서 전체 = document를 먼저 구하고 -> 자식들 구한다.
			Document doc = builder.build(f); // 1. 전체 문서 

			
			// XML 내의 RootElement를 얻어온다.
			Element root = doc.getRootElement(); // 2. 최상위 엘리먼트 persons를 구한다. 
			
			// root element 밑의 자식element를 구한다.
			List<Element> person_list = root.getChildren("person");
			// person 밑의 name, age, tel를 가져와서 list에 담는다. 여기선 총 3개 
			
			for(Element person : person_list) { 
				// 첫번째 person은 첫번째 <person>을 가르킴. 그래서 첫번째 <person>의 하위 자식들을 가짐
				// <person>의 자식은 3개, 그 중 <name>을 가져온다. 일길동를 의미
				String name     = person.getChildText("name"); 
				
				// <name nickname="원님" familyname="일">일길동</name> 전체를 의미
				String nickName = person.getChild("name")
								   .getAttributeValue("nickname"); 
				
				
				int age         = 0; // 비어있는 경우면 뒤에 내용은 파싱을 안하니까 try-catch  
				try {
					age = Integer.parseInt(person.getChildText("age"));
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				String tel      = person.getChildText("tel");
				String homeTel  = person.getChild("tel")
										.getAttributeValue("hometel");
				
				// Vo 포장
				PersonVo vo = new PersonVo(name, nickName, age, tel, homeTel);
				
				// ArrayList에 추가 
				p_list.add(vo);
				
			} //end:for
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} 
		  
		
		// request binding
		request.setAttribute("p_list", p_list);
		
		
		// Dispatcher 형식으로 호출 
		String forward_page = "person_list.jsp";
		RequestDispatcher disp = request.getRequestDispatcher(forward_page);
		// clent가 주소를 가지고 호출하면 service()가 호출 (비지니스로직 = dao에게 데이터 가져와 시키는 등의 작업)
		disp.forward(request, response); // dept_list를 부른다. 

		// 이 서블릿과 dept_list.jsp 서블릿이 공유되는 공간 request이다. 
	}

}

