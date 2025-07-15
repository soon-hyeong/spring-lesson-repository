package org.kosa.myproject.test;

import java.sql.SQLException;

import org.kosa.myproject.config.AppConfig;
import org.kosa.myproject.model.MemberService;
import org.kosa.myproject.model.MemberVo;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class TestDIAndDBCP {
	public static void main(String[] args) {
		/*
		 * org.kosa.myproject.config.AppConfig org.kosa.myproject.model.MemberDao
		 * org.kosa.myproject.model.MemberService
		 */

		// Spring Container : ApplicationContext , AppConfig : 개발 진영에서 명시한 설정한 스프링 설정 경로
		AnnotationConfigApplicationContext factory = new AnnotationConfigApplicationContext(AppConfig.class);
		MemberService memberService = (MemberService) factory.getBean("memberService");
		MemberVo memberInfo = null;
		try {
			memberInfo = memberService.findMemberById("java");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println(memberInfo); //java 아이디 회원 정보
		factory.close();
	}
}
