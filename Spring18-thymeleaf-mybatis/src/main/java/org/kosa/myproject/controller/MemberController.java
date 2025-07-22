package org.kosa.myproject.controller;

import org.kosa.myproject.mapper.MemberMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/member")
public class MemberController {
	
	private final MemberMapper memberMapper;
	public MemberController(MemberMapper memberMapper) {
		super();
		this.memberMapper = memberMapper;
	}


	@GetMapping("/member-totalcount")
	public String getTotalMemberCount(Model model) {
		int memberCount = memberMapper.getTotalMemberCount();
		model.addAttribute("memberCount", memberCount);
		return "/member/total-count";
	}
}
