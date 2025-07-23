package org.kosa.myproject.controller;

import java.util.ArrayList;
import java.util.List;

import org.kosa.myproject.domain.Customer;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class MyTestController {
	@GetMapping("/hello") // client가 get방식으로 요청시 찾는 url pattern 지정
	public String hello(Model model) { //Model : 클라이언트에게 응답할 정보를 공유하기 위한 객체
		// view html에 정보 공유 : request.setAttribute("info", "떡볶이");
		model.addAttribute("info", "떡볶이");
		// forward 방식 : ViewResolver에 의해 src/main/resources/template/result1.html
		// 로 제어가 이동되어 타임리프 템플릿 엔진에 의해 파싱되어 클라이언트에게 html 응답한다
		return "result1"; 
	}
	
	@GetMapping("/paramTest") //HandlerMapping url 등록
	public String paramTest(String customerId, int customerAge, Model model) { // Handler Adapter 가 수행
		System.out.println("paramTest:" + customerId);
		System.out.println(customerAge);
		if(customerAge > 18)
			model.addAttribute("adult", "성인");
		else
			model.addAttribute("adult", "미성년");
		return "result2"; //ViewResolver
	}
	
	/**
	 * client에서 post 방식으로 요청하여 @PostMapping 으로 처리
	 * client의 form에서 동일한 name(menu)으로 여러 개의 value를 전송한다
	 * 이 경우 Servlet/JSP에서는 request.getParameterValues : String[] 으로 요청을 처리
	 * 이것을 HandlerAdapter가 요청 데이터에 대한 배열 객체를 생성해서 우리의 컨트롤러 메서드에 전달해준다
	 * @return
	 */
	@PostMapping("/paramTest2")
	public String paramTest2(String[] menu) {
		System.out.println(menu.length);
		return "redirect:/result3-view"; // Spring 환경에서 redirect는 FrontController인
										 // DispatcherServlet으로 가게 되어 있고 적절한 컨트롤러가 있을 때 응답할 수 있다
	}
	
	@GetMapping("/result3-view")
	public String paramTest2Result() {
		return "result3";
	}
	
	@PostMapping("/paramTest3")
	public String paramTest3(String menu[], Model model) {
		System.out.println("menu array:" + menu);
		model.addAttribute("myMenu", menu);
		return "result4";
	}
	
	@GetMapping("/customer-list")
	public String showCustomerList(Model model) {
		List<Customer> list = new ArrayList<>();
		list.add(new Customer("java","손흥민","런던"));
		list.add(new Customer("spring","아이유","종로"));
		list.add(new Customer("thymeleaf","이강인","파리"));
		model.addAttribute("customerList", list);
		return "result5";
	}
	
	@PostMapping("/has-a-test")
	public String hasATest(Customer customer, Model model) {// Handler Adapter 가 만들어서 준다
		System.out.println(customer);
		model.addAttribute("customer", customer);
		return "result6";
	}
	
	// 스프링 컨트롤러의 메서드 매개변수로 세션을 받는 경우
	// request.getSession() 과 동일 : 세션이 없으면 새로 생성, 있으면 기존 세션 리턴
	@GetMapping("/sessionTest0")
	public String sessionTest0(HttpSession session) {
		return "result7";
	}
	
	// 스프링 컨트롤러의 메서드 매개변수로 세션을 받는 경우
	// request.getSession() 과 동일 : 세션이 없으면 새로 생성, 있으면 기존 세션 리턴
	@GetMapping("/sessionTest1")
	public String sessionTest1(HttpSession session) { //Handler Adapter 가 만들어서 준다
		session.setAttribute("customer", new Customer("java", "손흥민", "런던"));
		return "result7";
	}
	
	@GetMapping("/sessionTest2")
	public String sessionTest2(HttpServletRequest request, Model model) { 
		// request.getSession(false): 기존 세션이 없으면 null 반환, 기존 세션이 있으면 기존 세션 반환
		HttpSession session = request.getSession(false);
		String chekcMessage = null;
		if(session == null)
			chekcMessage="세션이 존재하지 않습니다";
		else if(session!=null && session.getAttribute("customer") == null)
			chekcMessage="인증 정보가 존재하지 않습니다.";
		else if(session!=null && session.getAttribute("customer") !=null)
			chekcMessage="로그인 상태입니다";
		model.addAttribute("checkMessage", chekcMessage);
		return "result7-2";
	}
}















