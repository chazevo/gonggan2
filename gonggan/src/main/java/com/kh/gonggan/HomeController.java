package com.kh.gonggan;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.kh.gonggan.blog.model.service.BlogService;
import com.kh.gonggan.comment.model.service.CommentService;
import com.kh.gonggan.comment.model.vo.Comment;
import com.kh.gonggan.member.model.service.MemberService;
import com.kh.gonggan.member.model.vo.Member;
import com.kh.gonggan.post.model.service.PostService;
import com.kh.gonggan.post.model.vo.Post;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	@Autowired
	   private BlogService blogService;
	   @Autowired
	   private MemberService memberService;
	   @Autowired
	   private PostService postService;
		@Autowired
		private CommentService commentService;
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "start.do", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! ");
		return "home";
	}
	
	@RequestMapping(value = "index2.do", method = RequestMethod.GET)
	   public ModelAndView index2(ModelAndView mv) {
	      logger.info("Welcome index2! ");
	      List<Post> plist = postService.selectAll_index2();
	      mv.addObject("plist",plist);
	      mv.setViewName("index2");
	      return mv;
	   }
	
	@RequestMapping(value = "findIdPwd.do", method = RequestMethod.GET)
	public String findIdPwd(Locale locale, Model model) {
		logger.info("Welcome findIdPwd! ");
		return "findIdPwd";
	}
	
	@RequestMapping(value = "controll.do", method = RequestMethod.GET)
	public String controll(Locale locale, Model model) {
		logger.info("Welcome controll! ");
		return "controll";
	}
	
	@RequestMapping("myhome.do")
	   public ModelAndView selectBlog(Member member, ModelAndView mv, HttpSession session) {
	      Member loginUser  = memberService.loginCheck(member);
	  		
	      String wr = loginUser.getMember_id();
	      System.out.println(wr);
	      mv.addObject("writer_id", wr);
	      mv.setViewName("myhome");
	      return mv;
	   }
	
	@RequestMapping("mypage.do")
	public ModelAndView mypage(Locale locale, Model model,String writer_id ,  ModelAndView mv) {
		
		logger.info("Welcome mypage! ");
		List<Comment> mylist = commentService.myCommentList(writer_id);
	
		mv.addObject("mylist",mylist);
		mv.addObject("writer_id", writer_id);
		mv.setViewName("mypage");
		

		return mv;
	}

	@RequestMapping(value = "uploadform.do", method = RequestMethod.GET)
	public String uploadform(Locale locale, Model model) {
		logger.info("Welcome uploadform! ");
		return "uploadform";
	}

	@RequestMapping(value = "uploadHtml.do", method = RequestMethod.GET)
	public String uploadHtml(Locale locale, Model model) {
		logger.info("Welcome uploadHtml! ");
		return "uploadHtml";
	}
	
	@RequestMapping(value = "join.do", method = RequestMethod.GET)
	public String join(Locale locale, Model model) {
		logger.info("Welcome join! ");
		return "join";
	}
	@RequestMapping(value = "test.do", method = RequestMethod.GET)
	public String test(Locale locale, Model model) {
		logger.info("Welcome test! ");
		return "test";
	}
	@RequestMapping(value = "massage.do", method = RequestMethod.GET)
	public String massage(Locale locale, Model model) {
		logger.info("Welcome massage! ");
		return "massage";
	}

	@RequestMapping(value = "postDetail.do", method = RequestMethod.GET)
	public String postDetail(Locale locale, Model model) {
		logger.info("Welcome postDetail! ");
		return "postDetail";
	}

	@RequestMapping(value = "likepage.do", method = RequestMethod.GET)
	public String likepage(Locale locale, Model model) {
		logger.info("Welcome likepage! ");
		return "likepage";
	}
	
	@RequestMapping(value = "searchAll.do", method = RequestMethod.GET)
	public String searchAll(Locale locale, Model model) {
		logger.info("Welcome searchAll! ");
		return "searchAll";
	}
	@RequestMapping(value = "kakao.do", method = RequestMethod.GET)
	   public String kakao(Locale locale, Model model) {
	      logger.info("Welcome kakao! ");
	      return "kakaoLogin";
	   }
	   @RequestMapping(value = "redirect.do", method = RequestMethod.GET)
	   public String redirect(Locale locale, Model model) {
	      logger.info("Welcome redirect! ");
	      return "redirect";
	   }
	   @RequestMapping(value = "facebook.do", method = RequestMethod.GET)
	   public String facebook(Locale locale, Model model) {
	      logger.info("Welcome facebook! ");
	      return "facebookLogin";
	   }

	   @RequestMapping(value = "map.do", method = RequestMethod.GET)
	   public String map(Locale locale, Model model) {
	      logger.info("Welcome map! ");
	      return "map";
	   }
}
