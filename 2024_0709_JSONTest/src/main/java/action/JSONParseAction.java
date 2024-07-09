package action;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import json.vo.PersonVo;

/**
 * Servlet implementation class JSONParseAction
 */

@WebServlet("/json_parse.do") // 여기로 들어오면 
public class JSONParseAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// {"name":"일길동", "age":30, "hobby":["독서", "낚시"]} <- 이렇게 밖에서 만들고 
		
		/*
		String json_str = "";
		JSONObject json = new JSONObject("{\"name\":\"일길동\", \"age\":30, \"hobby\":[\"독서\", \"낚시\"]}"); // <- 붙여넣기 
		// json이 "{\"name\":\"일길동\", \"age\":30}" 이거다. 
		// lib에 있는 라이브러리를 사용 
		
		
		String name = json.getString("name"); // name키 값을 String 으로 가져와라 
		int age     = json.getInt("age");
		
		System.out.printf("이름:%s 나이:%d\n", name, age);
		
		JSONArray hobbyArray = json.getJSONArray("hobby");
		for(int i = 0; i < hobbyArray.length(); i++) {
			String hobby = hobbyArray.getString(i);
			System.out.printf("취미 %d : %s\n", i+1, hobby);
		}
		
		*/
		
		List<PersonVo> p_list = new ArrayList<PersonVo>(); 
		
		try {
			String str_url = "http://localhost:8080/2024_0709_JSONTest/person.jsp";
			URL url = new URL(str_url);
			
			// 이용하는 이유 : 요청시 헤더에 OpenAPI가 제공하는 인증키 전달하려고 (한번 더 필터링하기 위함) 
			URLConnection urlConn = url.openConnection();
			
			InputStream is = urlConn.getInputStream(); // 읽어오기 
			
			// line 단위로 읽어온다 // person.jsp의 파일을 한 줄 씩읽는다. 
			BufferedReader br = new BufferedReader(new InputStreamReader(is, "utf-8"));  
			
			
			StringBuilder sb = new StringBuilder();
			
			while(true) {
				String data = br.readLine(); // line 단위로 읽음 
				if(data == null) break; // 다 읽었으면 break
				
				sb.append(data);
			}
			
			
			System.out.println(sb.toString());
			//JSON Parsing 
			
			JSONObject json = new JSONObject(sb.toString());
			int size = json.getInt("size");
			JSONArray personArray = json.getJSONArray("list");
			for(int i = 0; i < personArray.length(); i++) {
				JSONObject person = personArray.getJSONObject(i);
				// person = {"name":"일길동", "age":31, "tel":"010-111-1234"}
				
				String name = person.getString("name");
				int    age  = person.getInt("age");
				String tel  = person.getString("tel");
						
				PersonVo vo = new PersonVo(name, age, tel);
				p_list.add(vo);
			}
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		
		request.setAttribute("p_list", p_list);
		
		
		// Dispatcher 형식으로 호출 
		String forward_page = "result_json.jsp";
		RequestDispatcher disp = request.getRequestDispatcher(forward_page);
		disp.forward(request, response);  	 
	}

}

