package com.kh.gonggan.comment.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Calendar;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.kh.gonggan.comment.model.service.CommentService;
import com.kh.gonggan.comment.model.vo.Comment;
import com.kh.gonggan.post.model.vo.Post;

@Controller
public class CommentController {
	//메소드가 controller가 됨 컨트롤러를 메소드 단위로 작성하면 된다.
	//공통으로 사용하는 것은 common에 넣어놓으면 됨
	@Autowired
	private CommentService commentService;
	
	@RequestMapping(value="/colist.do", produces={"application/json"}, method=RequestMethod.GET)
		@ResponseBody
		public String selectList(@RequestParam String postId){
			List<Comment> clist = commentService.selectPostComments(postId);
			String content;
			
			JSONObject json = new JSONObject();
			JSONArray jarr = new JSONArray();
			
			for(Comment c : clist) {
				
				JSONObject job = new JSONObject();
				
				job.put("postId", c.getPost_id() + "");
				job.put("writerId", c.getWriter_id());
				try {
					job.put("commentContent", URLEncoder.encode(
							(content = c.getComment_content())==null ? " " : content, "UTF-8") + "");
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				job.put("commentDate", c.getComment_date() + "");
	
				jarr.add(job);
			}
			json.put("list", jarr);

			return json.toJSONString();
		}
	
	@RequestMapping("coinsert.do")
	public ModelAndView memberInsert(@ModelAttribute Comment comment, Model model){
		int insertComm = commentService.insertComment(comment);
		return null;
	}//회원가입
	
	@RequestMapping("coupdate.do")
	public ModelAndView commentUpdate(@RequestParam Comment comment) {
		int updateComm = commentService.updateComment(comment);
		return null;
	}//회원 수정
	
	@RequestMapping("codelete.do")
	public String commentDelete(@RequestParam int comment_num, Model model){
		int deleteComm = commentService.deleteComment(comment_num);
		return null;
	}//회원 삭제

}
