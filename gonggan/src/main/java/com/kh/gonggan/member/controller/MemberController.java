package com.kh.gonggan.member.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.kh.gonggan.member.model.service.MemberService;
import com.kh.gonggan.member.model.vo.Member;

@Controller
@RequestMapping("member")
public class MemberController {
	//메소드가 controller가 됨 컨트롤러를 메소드 단위로 작성하면 된다.
	//공통으로 사용하는 것은 common에 넣어놓으면 됨
	@Autowired
	private MemberService memberService;
	
	@RequestMapping(value="/login.do", method=RequestMethod.POST)
	public ModelAndView loginCheck(Member member, ModelAndView mv){

		Member loginUser  = memberService.loginCheck(member);
		
		System.out.println(member.getMember_id()+","+member.getMember_pw());
		//ModelAndView mv = new ModelAndView();
		mv.setViewName("home");
		mv.addObject("loginUser", loginUser);
		
		/*return "home";*/
		return mv;
	}//로그인하기
	
	@RequestMapping("insert.do")
	public ModelAndView memberInsert(@ModelAttribute Member member, Model model){
		int insertMem = memberService.insertMember(member);
		return null;
	}//회원가입
	
	@RequestMapping("update.do")
	public ModelAndView memberUpdate(@RequestParam Member member) {
		int updateMem = memberService.updateMember(member);
		return null;
	}//회원 수정
	
	@RequestMapping("delete.do")
	public String memberDelete(@RequestParam String member_id, Model model){
		int deleteMem = memberService.deleteMember(member_id);
		return null;
	}//회원 삭제

	@RequestMapping("memberList.do")
	public ModelAndView memberList(){
		return null;
	}//리스트로 회원 보기
	
	@RequestMapping("selectPw.do")
	public ModelAndView selectPw(String email, String member_id ){
		return null;
	}//비밀번호 찾을 때 회원 검색
	
	@RequestMapping("selectId.do")
	public String selectId(String email){
		Member selectId = memberService.selectId(email);
		return null;
	}//아이디 찾을 때 회원 검색
	
	@RequestMapping("selectMember.do")
	public Member selectMember(@RequestParam String member_id, Model model){
		Member selectMember = memberService.selectMember(member_id);
		return null;
	}//회원 정보 상제 조회
}
