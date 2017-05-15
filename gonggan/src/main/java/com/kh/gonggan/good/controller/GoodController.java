package com.kh.gonggan.good.controller;


import java.sql.Connection;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.kh.gonggan.good.model.service.GoodService;
import com.kh.gonggan.good.model.vo.Good;


@Controller
public class GoodController {
	private GoodService goodService;
	
	@RequestMapping(value="/good.do", method=RequestMethod.POST)
	public ModelAndView goodCount(@RequestParam int postId){
		Good good = goodService.goodCount(postId);
		return null;
	}//good 카운트 세기
	
	@RequestMapping("insertGood.do")
	public ModelAndView goodInsert(@RequestParam int postId, String memberId){
		int goodInsert = goodService.goodInsert(postId, memberId);
		return null;
	}//good 카운트 증가
	@RequestMapping("deleteGood.do")
	public ModelAndView goodDelete(@RequestParam int postId, String memberId){
		int goodDelete =goodService.goodDelete(postId,memberId);
		return null;
	}//good 카운트 감소
	@RequestMapping("checkGood.do")
	public ModelAndView goodCheck(@RequestParam int postId, String memberId){
		int goodCheck = goodService.goodCheck(postId,memberId);
		return null;
	}

	

}
