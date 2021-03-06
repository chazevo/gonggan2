package com.kh.gonggan.member.controller;

import javax.servlet.http.HttpSession;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.kh.gonggan.blog.model.service.BlogService;
import com.kh.gonggan.blog.model.vo.Blog;
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
import com.kh.gonggan.neighbor.model.service.NeighborService;
import com.kh.gonggan.neighbor.model.vo.Neighbor;
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
	@Autowired
	private NeighborService neighborService;
	
	@RequestMapping(value="/login.do")
	public ModelAndView loginCheck(Member member, ModelAndView mv, HttpSession session){
		Member loginUser  = memberService.loginCheck(member);
		Object writer_id = session.getAttribute("writer_id");
		Object cv = session.getAttribute("currentView");
		
		if(loginUser != null) {
			session.setAttribute("loginUser", loginUser);
			System.out.println(loginUser.getMember_id() + "," + loginUser.getMember_pw());

			if (cv != null) {
				System.out.println("cv : " + cv);
				mv.setViewName("redirect:" + (String) cv + ".do?"
						+ "writer_id=" + (writer_id != null ? (String) writer_id : ""));
			}

			else mv.setViewName("redirect:index2.do");
	         
			return mv;
		}
	      
		else {
			session.setAttribute("error", "실패");
			System.out.println("로그인 실패");
			mv.addObject("logmsg","failure");
			mv.setViewName("home");
			return mv;
		}
	} //로그인하기
	
	@RequestMapping(value="/joinidcheck.do", produces="text/plain;charset=UTF-8")
	@ResponseBody
	public String joinIdCheck(@RequestParam String member_id){
	    
		String result = "";
		Member idCheck = memberService.joinIdCheck(member_id);
		if(idCheck == null)
			 result = "성공";
		else if(idCheck != null )   result= "실패";
		return  result;
		
	   }//id/email check
	
	@RequestMapping(value="nrequest.do", produces="text/plain;charset=UTF-8")
	@ResponseBody
	   public ModelAndView requestNeig(@RequestParam String member_id1,@RequestParam String member_id2, ModelAndView mv){

		String msg = "실패";
		int nrequest = neighborService.requestNeig(member_id1, member_id2);
		List<Neighbor> neigList =neighborService.neigList(member_id1,member_id2);
		System.out.println(neigList);
		mv.addObject("neigList", neigList);
		mv.setViewName("likepage");
		if(nrequest > 0) msg="성공";
		
		return mv;
	
	} //이웃 신청
	
	@RequestMapping(value="neighyn.do", produces="text/plain;charset=UTF-8")
	@ResponseBody
	   public String requestNeig(@RequestParam String loginUser,@RequestParam String writer_id){
		
		String neighYn =neighborService.neighYn(loginUser,writer_id);
		return neighYn;
	
	} //이웃 신청

	
	@RequestMapping(value="/joinemailcheck.do", method=RequestMethod.GET, produces="text/plain;charset=UTF-8")
	@ResponseBody
	public String joinEmailCheck(@RequestParam String email, @RequestParam String member_id) {
	    
		String result = "";
		Member emailCheck = memberService.joinEmailCheck(email);
		Member member = memberService.selectMember(member_id);
		
		if (member == null) {
			if (emailCheck == null)
				result = "성공";
			else if (emailCheck != null)
				result = "실패";
		}
		else if (!member.getEmail().equals(email)) {
			if (emailCheck == null)
				result = "성공";
			else if (emailCheck != null)
				result = "실패";
		}
		else
			result = "성공";
		
		return  result;
		
	   } //id/email check
	
	
	@RequestMapping("logOut.do")
	public ModelAndView logOut(HttpSession session, ModelAndView mv) {
		
		Object writer_id = session.getAttribute("writer_id");
		
		mv.setViewName("redirect:"
				+ (String) session.getAttribute("currentView") + ".do"
						+ "?writer_id=" + (writer_id != null ? (String) writer_id : ""));
	    // redirect는 다시한번 더 Controller를 거치게 되고
		//redirect가 없다면 바로 jsp페이지를 불러오게 된다.
		
		if(session != null)
			session.invalidate();
		
		return mv;
	}

	@RequestMapping(value="/profileupdate.do", method=RequestMethod.POST)
	public ModelAndView updateProfile(MultipartHttpServletRequest request,
			ModelAndView mv) {
		
		String member_id = request.getParameter("member_id");
		long current = System.currentTimeMillis();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		MultipartFile file = request.getFile("file");
		String originalFileName = memberService.selectMember(member_id).getProfile_photo();
		String[] originalFileNameSplit;
		File existFile;
		
		String root = request.getSession().getServletContext().getRealPath("/");

		System.out.println("root : " + root);
		String[] roots = root.split("\\\\");
		String marger="";
		
		for(int i=0 ; i<roots.length-3; i++)
         marger += roots[i] + "\\";
         
		
		System.out.println("marger : " + marger);
		String savePath = marger + "src/main/webapp/images/profileImages/";
		System.out.println("savepath : " + savePath);
		savePath = "/Users/jiseung/git/gonggan2/gonggan/src/main/webapp/images/profileImages/";

		
		if (file.isEmpty()) {
			
			if (originalFileName != null) {
				existFile = new File(
						savePath + originalFileName);
				if(existFile.exists())
					if(existFile.delete())
		                System.out.println("파일삭제 성공");
			
				memberService.updateProfile(new Member(
						member_id, request.getParameter("profile"), ""));
			}
		}
		
		else {
			originalFileName = file.getOriginalFilename();
			
			File copy = new File(
					savePath + "profile" + sdf.format(new Date(current))
					+ ("." + (originalFileNameSplit =
					originalFileName.split("\\."))[originalFileNameSplit.length-1]));
			
			try {
				
				file.transferTo(copy);
				
			} catch(Exception e) {
				e.printStackTrace();
			}
			
			memberService.updateProfile(new Member(
					member_id = request.getParameter("member_id"), 
					request.getParameter("profile"),
					copy.getName()));
		}
		
		HttpSession ss = request.getSession(false);
		ss.setAttribute("loginUser", memberService.selectMember(member_id));
		
		mv.setViewName("redirect:mypage.do?writer_id=" + member_id);
		
		return mv;
	}
	
	@RequestMapping("updateform.do")
	public String updateform(HttpSession session){
		return "updateform";
	}
	
	@RequestMapping(value="/insert.do", method={RequestMethod.GET, RequestMethod.POST})
	public ModelAndView  memberInsert(Member member, ModelAndView  mv, HttpSession session){
		System.out.println(member);
		
		int insertMem = memberService.insertMember(member);
		int insertBlog =  blogService.insertBlog(new Blog(member.getMember_id()));
		
		// 제대로 들어갔을 경우
		if(insertMem > 0 && insertBlog > 0){
			System.out.println(member.getMember_id()+member.getMember_pw()+member.getMember_name());
			mv.setViewName("home");
		}else{
			System.out.println(member.getMember_id()+member.getMember_pw()+member.getMember_name());
			mv.setViewName("join");
			mv.addObject("error", "에러");
		}
		return mv;

	} //회원가입
	
	@RequestMapping("/update.do")
	@ResponseBody
	public String memberUpdate(Member member, HttpSession session) {
		
		System.out.println(member);
		String result = null;
		Member loginUser  = memberService.loginCheck(member);
		int updateResult = memberService.updateMember(member);
		session.setAttribute("loginUser", loginUser);
		
		if (updateResult < 0)
			result = "fail";
			//session.setAttribute("error", "실패");
		
		else
			result = "success";
		
		return result;
	}
	
	@RequestMapping("delete.do")
	@ResponseBody
	public String memberDelete(@RequestParam String member_id, HttpSession session){
		int deleteMem = memberService.deleteMember(member_id);
		session.invalidate();
		return "";
	}

	@RequestMapping("memberList.do")
	public ModelAndView memberList(){
		return null;
	} //리스트로 회원 보기
	
	@Autowired
	private EmailSender emailSender;
	@Autowired
	private Email emaila;
	
	@RequestMapping(value="selectPw.do", produces="text/plain;charset=UTF-8")
	@ResponseBody
	//public ModelAndView selectPw(@RequestParam Map<String, Object> paramMap, ModelMap model)throws Exception{
	public String selectPw(@RequestParam String memberId, String email/*, String phone*/)throws Exception{
		
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
		
		if (!pw.equals("실패"))  {
			emaila.setSubject("GONG:GAN 비밀번호 찾기 메일 ");
			emaila.setContent("비밀번호는 " + pw + " 입니다");
			emaila.setReceiver(email);
			emailSender.SendEmail(emaila);
		}
		
		return pw;
		
	} // 비밀번호 찾을 때 회원 검색
	
	
	@RequestMapping(value="selectIdByEmail.do", produces="text/plain;charset=UTF-8")
	@ResponseBody
	public String selectIdByEmail(@RequestParam String email){
		System.out.println("selectIdByEmail.do");
		String selectId = memberService.selectIdByEmail(email);
		if (selectId == null)
			selectId = "실패";
		return selectId;
		
	} //아이디 찾을 때 회원 검색
	
	@RequestMapping("selectMember.do")
	public Member selectMember(@RequestParam String member_id, Model model){
		Member selectMember = memberService.selectMember(member_id);
		return selectMember;
	}//회원 정보 상제 조회
	@RequestMapping(value="selectMemberphoto.do", produces="text/plain;charset=UTF-8")
	@ResponseBody
	public String selectPhoto(@RequestParam String writer_id){
		String result="없음";
		Member selectMember = memberService.selectMember(writer_id);
		System.out.println("selectmember .. :" +selectMember.getProfile_photo()+"member_id"+selectMember.getMember_id());
		if(selectMember.getProfile_photo()!=null){
			result=selectMember.getProfile_photo();
		}
		
		return result;
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
	
			if (memberService.rejectNeigh(member_id, member_id2) > 0)
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
	
	@RequestMapping(value="neigdelete.do", produces="text/plain;charset=UTF-8")
	@ResponseBody
	public String neigDelete(@RequestParam String member_id, @RequestParam String member_id2, Model model){

		String msg = "실패";
		if (memberService.neigDelete(member_id, member_id2) > 0)
			msg = "삭제 성공";
		return msg;
	} //이웃 신청 수락/거절
	
	@RequestMapping(value="nsearch.do", produces="application/json")
	@ResponseBody
	public String neighborSearch(@RequestParam String member_id,
			@RequestParam String member_id2) {

		List<Member> nlist = neighborService.neighborSearch(member_id, member_id2);
		
		JSONObject json = new JSONObject();
		JSONArray jarr = new JSONArray();

		if (nlist != null) {
			for(Member n : nlist) {
				
				JSONObject job = new JSONObject();
				
				job.put("member_id", n.getMember_id());
				
				jarr.add(job);
			}
			json.put("list", jarr);
		}
		return json.toString();
	}
	
}
