package com.kh.gonggan.facebook.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.kh.gonggan.member.model.service.MemberService;
import com.kh.gonggan.member.model.vo.Member;
@Controller
public class FacebookLoginController {
	@Autowired
	private MemberService memberService;
	
	@RequestMapping(value="/facebookLogin.do", method = RequestMethod.GET)
	public ModelAndView facebookLogin(HttpServletRequest request, HttpServletResponse response, HttpSession session){
		String facebook_id = request.getParameter("facebookId");
		System.out.println("facebook_id : "+facebook_id);
		
		Member member = new Member();
		member.setMember_facebook_id(facebook_id);
		System.out.println("member : "+member);
		
		Member loginUser = memberService.facebookIdCheck(member);
		if(loginUser != null){	// 한번 연동 후 재 연동 방지
			session.setAttribute("loginUser", loginUser);
			return new ModelAndView("index2", "loginUser", loginUser);
		}
		else{
			return new ModelAndView("facebookConnect", "facebook_id", facebook_id);
		}
	}
	@RequestMapping("/fConnect.do")
	public ModelAndView facebookconnect(Member member, ModelAndView mv, HttpSession session){
		
		Member loginUser = memberService.loginCheck(member);
		int fConnectResult = memberService.facebookConnect(member);
		
		session.setAttribute("loginUser", loginUser);
		System.out.println("loginUser :" +loginUser);
		if(fConnectResult < 0){
			session.setAttribute("error", "실패");
		}
		mv.addObject("loginUser", loginUser);
		mv.setViewName("index2");
		return mv;
	}
}
