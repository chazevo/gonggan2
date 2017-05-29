package com.kh.gonggan.blog.controller;

import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.kh.gonggan.blog.model.service.BlogService;
import com.kh.gonggan.blog.model.vo.Blog;
import com.kh.gonggan.member.model.vo.Member;

@Controller
public class BlogController {

	// 메소드가 controller가 됨 컨트롤러를 메소드 단위로 작성하면 된다.
	// 공통으로 사용하는 것은 common에 넣어놓으면 됨
	@Autowired
	private BlogService blogService;
	
	@RequestMapping(value = "/bvisit.do", method = RequestMethod.GET)
 		@ResponseBody
		public String blogvisit(@RequestParam String writer_id, @RequestParam String visitor_id) {
			//blogService.blogvisit(writer_id, visitor_id);
			return "";
		}
	
	@RequestMapping("selectBlog.do")
	public ModelAndView selectBlog(@RequestParam String writer_id, ModelAndView mv) {

		Blog blog = blogService.selectBlog(writer_id);

		mv.setViewName("myhome");
		mv.addObject("blog", blog);

		return mv;
	}
	

	@RequestMapping(value = "/selectVisitorList.do", produces = { "application/json" }, method = RequestMethod.GET)
	 	@ResponseBody
			public String selectVisitorList(@RequestParam String writer_id) {
		
				List<Member> VisitorList = blogService.selectVisitorList(writer_id);

				JSONObject json = new JSONObject();
				JSONArray jarr = new JSONArray();
		
				for (Member m : VisitorList) {
		
					JSONObject job = new JSONObject();
					
					job.put("member_id", m.getMember_id());
					job.put("gender", m.getMember_gender());
					job.put("birth", m.getMember_birth()+"");
					
					jarr.add(job);
				}
				json.put("list", jarr);
		
				return json.toJSONString();

			}
	
	@RequestMapping(value = "/selectNeigborVisitorList.do", produces = { "application/json" }, method = RequestMethod.GET)
	 	@ResponseBody
		public String selectNeigborVisitorList(@RequestParam String writer_id) {
			
			List<Member> VisitorList = blogService.selectNeigborVisitorList(writer_id);
	
			JSONObject json = new JSONObject();
			JSONArray jarr = new JSONArray();
	
			for (Member m : VisitorList) {
	
				JSONObject job = new JSONObject();
	
				job.put("member_id", m.getMember_id());
	
				jarr.add(job);
			}
			json.put("list", jarr);
	
			return json.toJSONString();
		}
	@RequestMapping(value = "/selectMonNeigborVisitorList.do", produces = { "application/json" }, method = RequestMethod.GET)
 	@ResponseBody
	public String selectMonNeigborVisitorList(@RequestParam String writer_id) {
		
		List<Member> VisitorList = blogService.selectMonNeigborVisitorList(writer_id);

		JSONObject json = new JSONObject();
		JSONArray jarr = new JSONArray();

		for (Member m : VisitorList) {

			JSONObject job = new JSONObject();

			job.put("member_id", m.getMember_id());

			jarr.add(job);
		}
		json.put("list", jarr);

		return json.toJSONString();
	}
	@RequestMapping(value = "/selectMonNeiList.do", produces = { "application/json" }, method = RequestMethod.GET)
 		@ResponseBody
			public String selectMonNeiList(String writer_id) {
				
				List<Member> MonNeiList = blogService.selectMonNeiList(writer_id);
		
				JSONObject json = new JSONObject();
				JSONArray jarr = new JSONArray();
		
				for (Member m : MonNeiList) {
		
					JSONObject job = new JSONObject();
		
					job.put("member_id", m.getMember_id());
		
					jarr.add(job);
				}
				json.put("list", jarr);
		
				return json.toJSONString();
			}
			

}
