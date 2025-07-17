package org.kosa.myproject;

import org.kosa.myproject.model.board.BoardService;
import org.kosa.myproject.model.member.DuplicateIdException;
import org.kosa.myproject.model.member.MemberService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class PerformanceCheckRunner implements CommandLineRunner{
	
	private final BoardService boardService;
	private final MemberService memberService;

	public PerformanceCheckRunner(BoardService boardService, MemberService memberService) {
		super();
		this.boardService = boardService;
		this.memberService = memberService;
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("main:" + boardService.find());
		System.out.println("main:" + boardService.findAllList());
		memberService.findMember();
		memberService.findAllMember();
		try {
			memberService.register("java", "손흥민");
		} catch(DuplicateIdException e) {
			System.out.println("main:" + e.getMessage());
		}
	}

}
