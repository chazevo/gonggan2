package com.kh.gonggan.good.controller;


import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.kh.gonggan.good.model.service.GoodService;
import com.kh.gonggan.good.model.vo.Good;
import com.kh.gonggan.member.model.service.MemberService;
import com.kh.gonggan.member.model.vo.Member;
import com.kh.gonggan.neighbor.model.service.NeighborService;


@Controller
public class GoodController {
	
	@Autowired
	private GoodService goodService;
	@Autowired
	private NeighborService neighborService;
	@Autowired
	private MemberService memberService;
	
/*	@RequestMapping(value="/good.do", method=RequestMethod.POST)
	public ModelAndView goodCount(@RequestParam int postId){
		Good good = goodService.goodCount(postId);
		return null;
	}
	*/
	@RequestMapping("insertGood.do")
		@ResponseBody
		public String goodInsert(@RequestParam int postId, @RequestParam String loginUser){
			int goodInsert = goodService.goodInsert(postId, loginUser);
			System.out.println(goodInsert);
			return "" + goodInsert;
		}//good 카운트 증가
	
	@RequestMapping("deleteGood.do")
		@ResponseBody
		public String goodDelete(@RequestParam int postId, @RequestParam String loginUser) {
			int goodDelete = goodService.goodDelete(postId,loginUser);
			System.out.println(goodDelete);
			return "" + goodDelete;
		}//good 카운트 감소
	
	@RequestMapping("checkGood.do")
		@ResponseBody
		public String goodCheck(@RequestParam int postId, @RequestParam String loginUser){
			if (goodService.goodCheck(postId,loginUser) != null)
				return "good";
			else
				return "nogood";
		}
	
	@RequestMapping("goodList.do")
	public ModelAndView goodList(@RequestParam int postId,
			ModelAndView mv, HttpSession session) {
		List<Good> goodList = goodService.goodList(postId);
		String nlist = null;
		ArrayList<String> neighYn = new ArrayList<String>();
		Member mlist = null;
		ArrayList<Member> memberList = new ArrayList<Member>();

		for(int i = 0 ; i < goodList.size(); i++) {
			nlist = neighborService.neighYn(((Member)session.getAttribute("loginUser")).getMember_id(),goodList.get(i).getMember_id());
			mlist = memberService.selectMember(goodList.get(i).getMember_id());
			neighYn.add(nlist); 
			memberList.add(mlist);
		}
	      
		mv.addObject("goodList",goodList);
		mv.addObject("neighYn",neighYn);
		mv.addObject("memberList",memberList);
		mv.setViewName("likepage");
	      
		return mv;
	}
	
	@RequestMapping(value = "/gsearch.do", produces = { "application/json" }, method = RequestMethod.GET)
		@ResponseBody
			public String goodSearch(@RequestParam String member_id, @RequestParam int post_id){
				List<Good> gSearchList = goodService.goodSearch(member_id,post_id);

				JSONObject json = new JSONObject();
				JSONArray jarr = new JSONArray();
		
				for (Good g : gSearchList) {
		
					JSONObject job = new JSONObject();
					
					job.put("member_id", g.getMember_id());
					
					jarr.add(job);
				}
				json.put("list", jarr);
		
				return json.toJSONString();

			}

	

}
