package kr.ac.readingbetter.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.ac.readingbetter.service.MemberService;
import kr.ac.readingbetter.service.ScoresService;
import kr.ac.readingbetter.vo.MemberVo;
import kr.ac.readingbetter.vo.ScoresVo;

@Controller
@RequestMapping("/mypage")
public class MyPageController {

	@Autowired
	private MemberService memberService;

	@Autowired
	private ScoresService scoresService;

	// 내 정보
	// 회원 정보 화면 열기
	@RequestMapping("/info")
	public String Info(HttpSession session, Model model) {
		MemberVo authUser = (MemberVo) session.getAttribute("authUser");

		MemberVo memberVo = memberService.selectMyinfo(authUser.getNo());
		ScoresVo scoresVo = scoresService.selectScores(authUser.getNo());

		model.addAttribute("memberVo", memberVo);
		model.addAttribute("scoresVo", scoresVo);

		return "mypage/info";
	}

	// 회원 정보 수정 화면 열기
	@RequestMapping("/modifyform")
	public String ModifyForm() {
		return "mypage/modifyform";
	}
	////////////////////////////////////////////////////////////////////////////
	
	// 내 활동
	// 히스토리 화면 열기
	@RequestMapping("/history")
	public String History() {
		return "mypage/history";
	}
}