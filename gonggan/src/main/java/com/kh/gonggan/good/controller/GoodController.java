package com.kh.gonggan.good.controller;


import java.sql.Connection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.kh.gonggan.good.model.service.GoodService;
import com.kh.gonggan.good.model.vo.Good;


@Controller
public class GoodController {
	
	@Autowired
	private GoodService goodService;
	
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
		public String goodDelete(@RequestParam int postId, @RequestParam String loginUser){
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
		public ModelAndView goodList(@RequestParam int postId, ModelAndView mv){
			List<Good> goodList = goodService.goodList(postId);
			mv.addObject("goodList",goodList);
			mv.setViewName("likepage");
			return mv;
		}

	

}
