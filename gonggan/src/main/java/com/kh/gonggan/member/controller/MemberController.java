package com.kh.gonggan.member.controller;

import javax.servlet.http.HttpSession;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.kh.gonggan.blog.model.service.BlogService;
import com.kh.gonggan.comment.model.service.CommentService;
import com.kh.gonggan.comment.model.vo.Comment;
import com.kh.gonggan.diary.model.service.DiaryService;
import com.kh.gonggan.diary.model.vo.Diary;
import com.kh.gonggan.email.Email;
import com.kh.gonggan.email.EmailSender;
import com.kh.gonggan.good.model.service.GoodService;
import com.kh.gonggan.good.model.vo.Good;
import com.kh.gonggan.member.model.service.MemberService;
import com.kh.gonggan.member.model.vo.Member;
import com.kh.gonggan.movie.model.service.MovieService;
import com.kh.gonggan.movie.model.vo.Movie;
import com.kh.gonggan.music.model.service.MusicService;
import com.kh.gonggan.music.model.vo.Music;
import com.kh.gonggan.news.model.service.NewsService;
import com.kh.gonggan.news.model.vo.News;
import com.kh.gonggan.post.model.service.PostService;
import com.kh.gonggan.post.model.vo.Post;
import com.kh.gonggan.review.model.service.ReviewService;
import com.kh.gonggan.review.model.vo.Review;

@Controller
//@RequestMapping("member")

public class MemberController {
	//메소드가 controller가 됨 컨트롤러를 메소드 단위로 작성하면 된다.
	//공통으로 사용하는 것은 common에 넣어놓으면 됨
	@Autowired
	private MemberService memberService;
	@Autowired
	private CommentService commentService;
	@Autowired
	private GoodService goodService;
	@Autowired
	private BlogService blogService;
	@Autowired
	private PostService postService;
	@Autowired
	private MovieService movieService;
	@Autowired
	private DiaryService diaryService;
	@Autowired
	private MusicService musicService;
	@Autowired
	private NewsService newsService;
	@Autowired
	private ReviewService reviewService;
	
	@RequestMapping(value="/login2.do", method=RequestMethod.GET)
	public ModelAndView logincomplete(ModelAndView mv, HttpSession session){

		Member loginUser = (Member) session.getAttribute("loginUser");
		List<Member> neighborReqList = memberService.checkNeig(loginUser.getMember_id());
		List<Post> plist = postService.selectAll_index2();  
		List<Movie> movielist = movieService.selectAll_index2();
		List<Diary> diarylist = diaryService.selectAll_index2();
		List<Music> musiclist = musicService.selectAll_index2();
		List<News> newslist = newsService.selectAll_index2();
		List<Review> reviewlist = reviewService.selectAll_index2();
		List<Comment> commentReqList = commentService.checkCommentAlram(loginUser.getMember_id());

		mv.setViewName("index2");
		mv.addObject("reviewlist", reviewlist);
		mv.addObject("newslist", newslist);
		mv.addObject("musiclist", musiclist);
		mv.addObject("dlist", diarylist);
		mv.addObject("plist",plist);
		mv.addObject("movielist",movielist);
		mv.addObject("plistSize", plist.size());
		mv.addObject("neighborReqList", neighborReqList);
		mv.addObject("neighborReqListSize", neighborReqList.size());
		
		return mv;
	}
	
	@RequestMapping(value="/login.do", method=RequestMethod.POST)
	   public ModelAndView loginCheck(Member member, ModelAndView mv, HttpSession session){
	      Member loginUser  = memberService.loginCheck(member);
	      
	      if(loginUser != null) {
	         session.setAttribute("loginUser", loginUser);
	         System.out.println(loginUser.getMember_id() + "," + loginUser.getMember_pw());


	         mv.setViewName("redirect:login2.do");
	         mv.addObject("loginUser", loginUser);
	         return mv;
	      }
	      
	      else {
	         session.setAttribute("error", "실패");
	         System.out.println("로그인 실패");
	         mv.addObject("logmsg","failure");
	         mv.setViewName("home");
	         return mv;
	      }
	   }//로그인하기
	
	@RequestMapping("logOut.do")
	   public ModelAndView logOut(HttpSession session, ModelAndView mv){
	      
	      String vn = (String) session.getAttribute("currentView");
	      System.out.println("vn:"+vn);
	      //String wr = (String)session.getAttribute("wr_id");
	      
	      //mv.addObject("writer_id", wr);
	      mv.setViewName(vn);
	      
	      if(session != null)
	         session.invalidate();
	      return mv;
	   }
	@RequestMapping("updateform.do")
	public String updateform(HttpSession session){
		return "updateform";
	}
	
	@RequestMapping(value="/insert.do", method={RequestMethod.GET, RequestMethod.POST})
	public ModelAndView  memberInsert(Member member, ModelAndView  mv){
		int insertMem = memberService.insertMember(member);
		System.out.println(member);
		// 제대로 들어갔을 경우
		if(insertMem > 0){
			System.out.println(member.getMember_id()+member.getMember_pw()+member.getMember_name());
			mv.setViewName("index2");
		}else{
			System.out.println(member.getMember_id()+member.getMember_pw()+member.getMember_name());
			mv.setViewName("join");
			mv.addObject("error", "에러");
		}
		return mv;

	}//회원가입
	
	@RequestMapping("/update.do")
	public ModelAndView memberUpdate(Member member, ModelAndView mv, HttpSession session){
		System.out.println(member);
		Member loginUser  = memberService.loginCheck(member);
		int updateResult = memberService.updateMember(member);
		session.setAttribute("loginUser", loginUser);
		
		if(updateResult<0){
			session.setAttribute("error", "실패");
		}
		mv.setViewName("mypage");
		return mv;
	}
	
	@RequestMapping("delete.do")
	public String memberDelete(@RequestParam String member_id, Model model){
		int deleteMem = memberService.deleteMember(member_id);
		return null;
	}//회원 삭제

	@RequestMapping("memberList.do")
	public ModelAndView memberList(){
		return null;
	}//리스트로 회원 보기
	
	@Autowired
	private EmailSender emailSender;
	@Autowired
	private Email emaila;
	
	@RequestMapping("selectPw.do")
	//public ModelAndView selectPw(@RequestParam Map<String, Object> paramMap, ModelMap model)throws Exception{
	public ModelAndView selectPw(@RequestParam String memberId, String email/*, String phone*/)throws Exception{
		
		ModelAndView mav;
		
		/*
		String id=(String)paramMap.get("member_id");
		String e_mail=(String)paramMap.get("email");
		*/
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("memberId", memberId);
		paramMap.put("email", email);
		/*paramMap.put("phone", phone);*/
		
		System.out.println("memberId : " + paramMap.get("memberId"));
		System.out.println("email : " + paramMap.get("email"));
		
		String pw = memberService.getPw(paramMap);
		
		System.out.println(pw);
		
		if(pw != null){
			emaila.setContent("비밀번호는 " + pw + " 입니다");
			emaila.setReceiver(email);
			emailSender.SendEmail(emaila);
			mav =new ModelAndView("redirect:/findPwd.do");
			return mav;
			
		}else{
			mav = new ModelAndView("redirect:/findPwd.do");
			return mav;
		}
		
			
	}//비밀번호 찾을 때 회원 검색
	
	
	@RequestMapping("selectId.do")
	public String selectId(String email){
		Member selectId = memberService.selectId(email);
		return null;
	}//아이디 찾을 때 회원 검색
	
	@RequestMapping("selectMember.do")
	public Member selectMember(@RequestParam String member_id, Model model){
		Member selectMember = memberService.selectMember(member_id);
		return null;
	}//회원 정보 상제 조회
	
	@RequestMapping("request.do")
	public String requestNeig(@RequestParam String member_id, Model model){
		return null;
	}//이웃 신청
	
	@RequestMapping(value="naccept.do", produces="text/plain;charset=UTF-8")
	@ResponseBody
		public String acceptNeig(@RequestParam String member_id, @RequestParam String member_id2, Model model){
			
			String msg = "실패";
		
			if (memberService.acceptNeigh(member_id, member_id2) > 0)
				msg = "수락 성공";
			return msg;
		}//이웃 신청 수락/거절
	
	@RequestMapping(value="nreject.do", produces="text/plain;charset=UTF-8")
	@ResponseBody
		public String rejectNeig(@RequestParam String member_id, @RequestParam String member_id2, Model model){
		
			String msg = "실패";
	
			if (memberService.acceptNeigh(member_id, member_id2) > 0)
				msg = "거절 성공";
			return msg;
		}//이웃 신청 수락/거절

	@RequestMapping("ncheck.do")
	public List<Member> checkNeig(@RequestParam String member_id, Model model){
		
		return null;
	}

	@RequestMapping("nlist.do")
	public List<Member> neigList(@RequestParam String member_id, Model model){
		return null;
	}//이웃 목록 조회
	
	@RequestMapping(value="/palram.do", produces={"application/json"})
	@ResponseBody
	public String postAlarmList(@RequestParam String member_id){
		
		List<Comment> commentReqList = commentService.checkCommentAlram(member_id);
		List<Good> goodReqList = goodService.checkGoodAlram(member_id);
		
		
		return null;
	}
	
	
	
}
