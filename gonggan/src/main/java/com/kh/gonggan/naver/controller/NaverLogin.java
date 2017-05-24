package com.kh.gonggan.naver.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.github.scribejava.core.model.OAuth2AccessToken;
import com.kh.gonggan.member.model.service.MemberService;
import com.kh.gonggan.member.model.vo.Member;
import com.kh.gonggan.naver.oauth.bo.NaverLoginBO;

import org.json.simple.parser.JSONParser;

@Controller
public class NaverLogin {
	@Autowired
	private MemberService memberService;

	/* NaverLoginBO */
	private NaverLoginBO naverLoginBO;

	/* NaverLoginBO */
	@Autowired
	private void setNaverLoginBO(NaverLoginBO naverLoginBO) {
		this.naverLoginBO = naverLoginBO;
	}

	@RequestMapping("/nlogin.do")
	public ModelAndView login(HttpSession session) {
		/* 네아로 인증 URL을 생성하기 위하여 getAuthorizationUrl을 호출 */
		String naverAuthUrl = naverLoginBO.getAuthorizationUrl(session);

		/* 생성한 인증 URL을 View로 전달 */
		return new ModelAndView("naverLogin", "url", naverAuthUrl);
	}

	@RequestMapping("/callback.do")
	public ModelAndView callback(@RequestParam String code, @RequestParam String state, HttpSession session)
			throws IOException {
		OAuth2AccessToken oauthToken = naverLoginBO.getAccessToken(session, code, state);
		String apiResult = naverLoginBO.getUserProfile(oauthToken);

		try {
			String json = apiResult; // string 값
			System.out.println("json : " + json); // 값 콘솔 출력
			JSONObject jsonObject = (JSONObject) new JSONParser().parse(json); // Json으로  파싱
			JSONObject joResponse = (JSONObject) jsonObject.get("response"); // get으로 값 가져옴
																				
			String naver_id = (String) joResponse.get("id");
			System.out.println("naver_id : " + naver_id);
			
			Member member = new Member();
			member.setMember_naver_id(naver_id);
			System.out.println("member:"+member);
			
			Member loginUser = memberService.naverIdCheck(member);
			//Member loginUser = memberService.loginCheck(member);
			
			if(loginUser != null){	// 한번 연동 후 재 연동 방지
				session.setAttribute("loginUser", loginUser);
				return new ModelAndView("index2", "loginUser", loginUser);
			}
			else{
				return new ModelAndView("naverConnect", "naver_id", naver_id);
			}
		} catch (Exception e) {
			System.out.println(" log : " + e.getMessage());
		}
		
		return new ModelAndView("naverConnect", "result", apiResult);
	}

	@RequestMapping("/nConnect.do")
	public ModelAndView naverconnect(Member member, ModelAndView mv, HttpSession session) {
		
		Member loginUser  = memberService.loginCheck(member);
		int nConnectResult = memberService.naverConnect(member);
		
		session.setAttribute("loginUser", loginUser);
		System.out.println("loginUser:"+loginUser);
		if (nConnectResult < 0) {
			session.setAttribute("error", "실패");
		}
		mv.addObject("loginUser", loginUser);
		mv.setViewName("index2");
		return mv;
	}
}