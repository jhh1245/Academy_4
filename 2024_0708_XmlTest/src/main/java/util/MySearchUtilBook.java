package util;

import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;

import xml.vo.BookVo;

public class MySearchUtilBook {

	public static List<BookVo> search_book(String p_name, int start, int display)
	{
		List<BookVo> list = new ArrayList<BookVo>();
		String clientId = "0didOwKiC9WUhHvd1xsY";
		String clientSecret = "_yK7QmnsYl";

		try {
			p_name = URLEncoder.encode(p_name, "utf-8");
			String urlStr = String.format("https://openapi.naver.com/v1/search/book.xml?query=%s&start=%d&display=%d",
					         p_name,start,display
					);

			
			
			URL url = new URL(urlStr); // 헤더에 넣기 위함 
			HttpURLConnection connection = (HttpURLConnection)url.openConnection();
			//발급받은 ID
			connection.setRequestProperty("X-Naver-Client-Id", clientId); 
			//발급받은 PW
			connection.setRequestProperty("X-Naver-Client-Secret", clientSecret); 
			// 받을요청타입
			connection.setRequestProperty("Content-Type", "application/xml"); 
			connection.connect();
			
			
			

			SAXBuilder builder = new SAXBuilder();
			Document   doc = builder.build (connection.getInputStream());

			Element  root     = doc.getRootElement();
			List<Element>   element_list = (List<Element>)root.getChild("channel").getChildren("item");
			
			int no = start;

			for(Element item : element_list){
				String title = item.getChildText("title");
				String link  = item.getChildText("link");
				String image = item.getChildText("image");
				String author = item.getChildText("author");
				String description = item.getChildText("description");
				int price = 0, discount = 0;
				try {
					price = Integer.parseInt(item.getChildText("price"));
				} catch (Exception e) {
					// TODO: handle exception
				}
				
				try {
					discount = Integer.parseInt(item.getChildText("discount"));
				} catch (Exception e) {
					// TODO: handle exception
				}
				
				
				//상품목록을 포장
				BookVo vo = new BookVo();
				vo.setNo(no++);
				vo.setTitle(title);
				vo.setLink(link);
				vo.setImage(image);
				vo.setAuthor(author);
				vo.setPrice(price);
				vo.setDiscount(discount);
				vo.setDescription(description);
								
				//ArrayList에 넣기
				list.add(vo);
				

			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		return list;
	}
	
	
}
