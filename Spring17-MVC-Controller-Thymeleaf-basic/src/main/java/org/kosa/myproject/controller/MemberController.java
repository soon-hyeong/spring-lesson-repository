package org.kosa.myproject.controller;

import org.kosa.myproject.domain.Member;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
//@RequestMapping : 클래스 레벨에서 공통 URL 경로를 정의, 모든 요청 방식을 지원 get,post 등
//해당 컨트롤러의 모든 메서드 URL 앞에 "/member"가 자동으로 붙음
@RequestMapping("/member")
public class MemberController {
	@GetMapping("/member-test")
	public String memberTest() {
		// 아래의 return value 는 view name을 반환하는 것임
		// Thymeleaf Template Engine의 ViewResolver 가 동작됨
		// src/main/resources/templates/viewName(컨트롤러 반환 view 이름).html을 찾아서
		// Thymeleaf Parser 가 동작, 클라이언트가 응답받은 일반 html을 생성해 응답한다
		// 아래의 view name은 src/main/resources/templates/member/member-test.html
		// 을 찾아서 파싱한다
		return "/member/member-test";
	}
	
	@GetMapping("/member-find")
	// client 에서 보낸 텍스트 입력폼의 name과 매개변수명이 일치하면
	// request.getParameter("memberId")처리를 해준다
	// @RequestParam : name이 매개변수명과 일치하면 생략 가능
	public String findMemberById(@RequestParam String memberId, Model model) {
		System.out.println(memberId);
		//  Service or Mapper 계층과 연동 후 회원 정보를 반환
		// view에 전달한 데이터를 담는 객체 : request.setAttribute(name,value)와 동일
		model.addAttribute("member", new Member(memberId, "a", "손흥민", "런던"));
		//src/main/resources/templates/member/find-by-id.html 로 Thymeleaf ViewResolver가 찾아서 파싱하고 렌더링한다
		
		return "/member/findbyid-result";
	}
	
	@GetMapping("/find-by-id2")
	public String findMemberById2(@RequestParam String memId, Model model){
		String path = "member/findbyid-fail";
		
		if(memId.equals("java")) {
			path = "member/findbyid-ok";
			model.addAttribute("memberInfo", "이강인 파리생제르망");
		}
		
		return path;
	}
	
	@GetMapping("/param-test")
	public String paramTest(@RequestParam String nick, @RequestParam int age, Model model) { // HandlerAddapter가 자동 형변환 해줌
		if(age > 18) {
			model.addAttribute("type", "성인");
		}
		else {
			model.addAttribute("type", "미성년");
		}
		
		return "/member/param-result.html";
	}
	
	@PostMapping("/register")
	public String register(Member member) {// form에서 전송한 회원 정보가 객체로 생성되어 매개변수로 전달된다 : HandlerAdpater
		System.out.println("회원 가입 정보 등록:" + member);
//		return "member/register-result"; //forward 방식 : reqeust 유지, 재동작 O
		return "redirect:/member/register-result"; //redirect  방식 : request 유지x, 재동작x
	}// 위 코드는 클라이언트에게 localhost:8080/member/register-result에 가서 응답받아라
	// 아래 메서드는 그 요청에 응답하는 컨트롤러 메서드
	@GetMapping("/register-result")
	public String registerResult() {
		return "member/register-result";
	}
	
	
	
	
	
	
	
	

}
