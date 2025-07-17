package org.kosa.myproject.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class MemberService{
//	private static final Logger logger=LoggerFactory.getLogger(MemberService.class);
	public void findMemberById(String id) {
		System.out.println(getClass().getName() + " core convern findMemberById");
//		logger.debug("cross cutting MemberService findMemberById 검색어 {}", id);
	}

	public void findMemberByAddress(String address) {
		System.out.println(getClass().getName() + " core convern findMemberByAddress");
	}

	public void findMemberListByDept(String dept) {
		System.out.println(getClass().getName() + " core concern findMemberList");
	}

	public void register(String id, String name) {
		System.out.println(getClass().getName() + " core concern register");
	}
}
