package com.kh.gonggan.kakao.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.kh.gonggan.member.model.vo.Member;
import com.kh.gonggan.member.model.service.MemberService;

@Controller
public class KakaoLoginController {
	@Autowired
	private MemberService memberService;
	
	@RequestMapping(value = "/kakaoLogin.do", method = RequestMethod.GET)
	public ModelAndView kakaoLogin(HttpServletRequest request, HttpServletResponse response, HttpSession session){
		String kakao_id = request.getParameter("kakaoId");
		System.out.println("kakao_id : "+kakao_id);
		
		Member member = new Member();
		member.setMember_kakao_id(kakao_id);
		System.out.println("member:"+member);
		
		Member loginUser = memberService.kakaoIdCheck(member);
		System.out.println(loginUser);
		if(loginUser != null){	// 한번 연동 후 재 연동 방지
			session.setAttribute("loginUser", loginUser);
			return new ModelAndView("index2", "loginUser", loginUser);
		}
		else{
			return new ModelAndView("kakaoConnect", "kakao_id", kakao_id);
		}
		
	}
	@RequestMapping("/kConnect.do")
	public ModelAndView naverconnect(Member member, ModelAndView mv, HttpSession session) {
		
		Member loginUser  = memberService.loginCheck(member);
		int kConnectResult = memberService.kakaoConnect(member);
		
		session.setAttribute("loginUser", loginUser);
		System.out.println("loginUser:"+loginUser);
		if (kConnectResult < 0) {
			session.setAttribute("error", "실패");
		}
		mv.addObject("loginUser", loginUser);
		mv.setViewName("index2");
		return mv;
	}
}
