package org.kosa.myproject.model;

import java.sql.SQLException;

import org.springframework.stereotype.Service;

@Service
public class MemberService {

	private final MemberDao memberDao;
	//@Autowired // DI 애너테이션 생략 가능 -> 생성자 1개 일 경우 자동 DI의존성 주입
	private MemberService(MemberDao memberDao) {
		super();
		this.memberDao = memberDao;
	}
	
	public MemberVo findMemberById(String id) throws SQLException {
		MemberVo member = memberDao.findMemberById(id);
		return member;
	}
}
