package com.kh.gonggan.naver.controller;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.kh.gonggan.member.model.service.MemberService;
import com.kh.gonggan.member.model.vo.Member;

@Controller
public class NaverLogin {
	@Autowired
	private MemberService memberService;

	@RequestMapping(value = "/naverLogin.do", method = RequestMethod.GET)
	public ModelAndView naverLogin(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		String naver_id = request.getParameter("naverId");
		System.out.println("naver_id : " + naver_id);

		Member member = new Member();
		member.setMember_naver_id(naver_id);
		System.out.println("member:" + member);

		Member loginUser = memberService.naverIdCheck(member);
		System.out.println(loginUser);
		if (loginUser != null) { // 한번 연동 후 재 연동 방지
			session.setAttribute("loginUser", loginUser);
			return new ModelAndView("index2", "loginUser", loginUser);
		} else {
			return new ModelAndView("naverConnect", "naver_id", naver_id);
		}
	}

	@RequestMapping("/callback.do")
	public String callback2(Locale locale, Model model) {

		return "naverCallback";

	}

	@RequestMapping("/nConnect.do")
	public ModelAndView naverconnect(Member member, ModelAndView mv, HttpSession session) {

		Member loginUser = memberService.loginCheck(member);
		int nConnectResult = memberService.naverConnect(member);

		session.setAttribute("loginUser", loginUser);
		System.out.println("loginUser:" + loginUser);
		if (nConnectResult < 0) {
			session.setAttribute("error", "실패");
		}
		mv.addObject("loginUser", loginUser);
		mv.setViewName("index2");
		return mv;
	}
}