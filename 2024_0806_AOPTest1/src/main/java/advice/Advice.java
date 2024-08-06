package advice;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.springframework.beans.factory.annotation.Autowired;

public class Advice {
	
	@Autowired
	HttpServletRequest request; // 스프링이 before 호출할 때 자동으로 넣어줘 
	
	long start = 0; 
	
	public void before(JoinPoint jp){
		Signature s =  jp.getSignature();
		
		start = System.currentTimeMillis();
		
		request.setAttribute("start", start);
		
		System.out.println("----before:" + s);
	}
	
	public void after(JoinPoint jp){
		Signature s =  jp.getSignature();
		
		Long start = (Long) request.getAttribute("start"); 
		// 자바의모든 객체가 다 담기기 때문에 Long으로 다운캐스팅
		
		long end = System.currentTimeMillis();
		
		System.out.println("----after:" + s.toLongString());
		System.out.printf("수행시간 : %d(ms)\n", end-start);
	}
}
