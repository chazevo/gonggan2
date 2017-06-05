package com.kh.gonggan;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import javax.net.ssl.HttpsURLConnection;
import sun.net.www.protocol.http.HttpURLConnection;
import javax.servlet.http.HttpSession;

import org.codehaus.jackson.map.ObjectMapper;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.kh.gonggan.blog.model.service.BlogService;
import com.kh.gonggan.blog.model.vo.Blog;
import com.kh.gonggan.book.model.vo.BestSeller;
import com.kh.gonggan.comment.model.service.CommentService;
import com.kh.gonggan.comment.model.vo.Comment;
import com.kh.gonggan.diary.model.service.DiaryService;
import com.kh.gonggan.diary.model.vo.Diary;
import com.kh.gonggan.good.model.service.GoodService;
import com.kh.gonggan.good.model.vo.Good;
import com.kh.gonggan.member.model.service.MemberService;
import com.kh.gonggan.member.model.vo.Member;
import com.kh.gonggan.message.model.service.MessageService;
import com.kh.gonggan.message.model.vo.Message;
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
import com.kh.gonggan.post.model.vo.PostBestSeller;
import com.kh.gonggan.post.model.vo.PostMovie;
import com.kh.gonggan.review.model.service.ReviewService;
import com.kh.gonggan.review.model.vo.Review;

import kr.or.kobis.kobisopenapi.consumer.rest.KobisOpenAPIRestService;

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
	@Autowired
	private GoodService goodService;
	@Autowired
	private NeighborService neighborService;
	@Autowired
	private MessageService messageService;
   
	@Autowired
	private MusicService musicService;
	@Autowired
	private DiaryService diaryService;
	@Autowired
	private ReviewService reviewService;
	@Autowired
	private MovieService movieService;
	@Autowired
	private NewsService newsService;
   
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
   
	/*** Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "start.do", method = RequestMethod.GET)
	public ModelAndView home(Locale locale, ModelAndView mv) {
      
		logger.info("Welcome home! ");
      
		mv.addObject("movieMaxRownum", postService.maxRownum("movie"));
		mv.addObject("musicMaxRownum", postService.maxRownum("music"));
		mv.addObject("reviewMaxRownum", postService.maxRownum("review"));
		//mv.addObject("placeMaxRownum", postService.maxRownum("place"));
		mv.addObject("newsMaxRownum", postService.maxRownum("news"));
		mv.addObject("diaryMaxRownum", postService.maxRownum("diary"));
		mv.setViewName("home");
      
		return mv;
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
   
	@RequestMapping(value = "controll.do")
	public ModelAndView controll(Locale locale, Model model,String writer_id, ModelAndView mv) {
		List<Post> likeInOrder = postService.likeInOrder(writer_id);
		List<Post> commentInOrder = postService.commentInOrder(writer_id);
		List<Comment> commentAll = commentService.commentAll(writer_id);
		List<Music> musiclist = new ArrayList<Music>();
		List<Diary> diarylist = new ArrayList<Diary>();
		List<Review> reviewlist =  new ArrayList<Review>();
		List<Movie> movielist =  new ArrayList<Movie>();
		List<News> newslist =  new ArrayList<News>();
		Blog blog = blogService.selectBlog(writer_id);
        
		String category = "";
         
		for(int i = 0; i< likeInOrder.size(); i++){
			
			int postId = likeInOrder.get(i).getPost_id();
			
			if ((category =  likeInOrder.get(i).getCategory()).equals("music")) {
				musiclist.add(musicService.musicDetail(postId));
			} else if (category.equals("diary")) {
				diarylist.add(diaryService.diaryDetail(postId));
			} else if (category.equals("review")) {
				reviewlist.add(reviewService.reviewDetail(postId));
			} else if (category.equals("news")) {
				newslist.add(newsService.newsDetail(postId));
			} else if (category.equals("movie")) {
				movielist.add(movieService.movieDetail(postId));
			}
		}
        
		for(int i = 0; i< commentInOrder.size(); i++){
			
			int postId = commentInOrder.get(i).getPost_id();
			
			if ((category =  commentInOrder.get(i).getCategory()).equals("music")) {
				musiclist.add(musicService.musicDetail(postId));
			} else if (category.equals("diary")) {
				diarylist.add(diaryService.diaryDetail(postId));
			} else if (category.equals("review")) {
				reviewlist.add(reviewService.reviewDetail(postId));
			} else if (category.equals("news")) {
				newslist.add(newsService.newsDetail(postId));
			} else if (category.equals("movie")) {
				movielist.add(movieService.movieDetail(postId));
			}
		}
		mv.addObject("likeInOrder", likeInOrder);
		mv.addObject("commentInOrder", commentInOrder);
		mv.addObject("musiclist", musiclist);
		mv.addObject("dlist", diarylist);
		mv.addObject("reviewlist", reviewlist);
		mv.addObject("newslist", newslist);
		mv.addObject("movielist", movielist);
		mv.addObject("blog", blog);
		mv.addObject("commentAll",commentAll);
		mv.setViewName("controll");
		
		return mv;
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
	public ModelAndView mypage(Locale locale, Model model,String writer_id , ModelAndView mv) {
      
		logger.info("Welcome mypage! ");
		System.out.println(writer_id);
		List<Comment> mylist = commentService.myCommentList(writer_id);
		List<Member> neighborReqList = memberService.checkNeig(writer_id);
		List<Good> goodMyList = goodService.goodMyList(writer_id);
		List<Comment> commentMyList = commentService.CommentMyList(writer_id);
		List<Comment> commentNeigList = commentService.commentNeigList(writer_id);
		List<Member> neighborlist = neighborService.selectNeighborList(writer_id);
		List<Message> lastMessage = messageService.lastMessage(writer_id);
		for (int i = 0; i< lastMessage.size(); i++) {
			System.out.println(lastMessage.get(i));
		}

		mv.addObject("mylist",mylist);
		mv.addObject("writer_id", writer_id);
		mv.addObject("neighborReqList", neighborReqList);
		mv.addObject("goodMyList", goodMyList);
		mv.addObject("commentMyList", commentMyList);
		mv.addObject("commentNeigList", commentNeigList);
		mv.addObject("neighborlist",neighborlist);
		mv.addObject("lastMessage",lastMessage);
		mv.setViewName("mypage");
   
		return mv;
	}

	@RequestMapping(value = "uploadform.do", method = RequestMethod.GET)
	public ModelAndView uploadform(Locale locale,@RequestParam String writer_id, ModelAndView mv) {

		logger.info("Welcome uploadform! ");

		Blog blog = blogService.selectBlog(writer_id);
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyymmdd");
      
		KobisOpenAPIRestService service = 
				new KobisOpenAPIRestService("5337c5e39d3dd2ffebecbd935e09e9c2");
		String weeklyResponse;
		ObjectMapper mapper = new ObjectMapper();
		HashMap<String, Object> weeklyResult = null;
      
		URL url = null;
		HttpURLConnection http = null;
		InputStreamReader in = null;
		BufferedReader br = null;
		String line;
		StringBuffer sb = new StringBuffer();
		
		Gson gson = new Gson();
		Type type = new TypeToken<PostBestSeller>(){}.getType();       
		List<BestSeller> bestSellerList = null;
		
		try {

			url = new URL("http://book.interpark.com/api/bestSeller.api?key="
					+ "0D761725CFD0E7D2DE9CE40D40155553F96A20F387185F0B6F260EC034CFB6D1"
					+ "&categoryId=100&output=json");
			http = (HttpURLConnection)url.openConnection();
			http.connect();
		
			in = new InputStreamReader(http.getInputStream(),"utf-8");
			br = new BufferedReader(in);

			while ((line = br.readLine()) != null) {
				sb.append(line).append("\n");
			}
		
			bestSellerList = ((PostBestSeller) gson.fromJson(sb.toString(), type)).getItem();
		
			br.close();
			in.close();
			
		} catch (IOException e2) {
		// TODO Auto-generated catch block
			e2.printStackTrace();
		}
      
		try {
         
			weeklyResponse = service.getWeeklyBoxOffice(
					true, sdf.format(new Date()), "10", "0", "", "", "");

			mapper = new ObjectMapper();
			weeklyResult = 
					mapper.readValue(weeklyResponse, HashMap.class);
         
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
      
      

		ArrayList<String> popKeyword = new ArrayList<String>();
      
		Document document = null;
      
		try {
			document = Jsoup.connect("http://www.naver.com/").get();

			Elements elements = document.select(".ah_roll_area .ah_l li");
         
			for (int i=0 ; i<elements.size()-1 ; i++) {
				Element element = elements.get(i);
				popKeyword.add(element.select("span.ah_k").text());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
      
		
		
		mv.setViewName("uploadform");
		mv.addObject("weeklyResult", weeklyResult);
		mv.addObject("popKeyword", popKeyword);
		mv.addObject("bestSellerList", bestSellerList);
		mv.addObject("blog", blog);
      
		return mv;
	}

	@RequestMapping(value = "uploadHtml.do", method = RequestMethod.GET)
	public String uploadHtml(Locale locale, Model model) {
		logger.info("Welcome uploadHtml! ");
		return "uploadHtml";
	}

	@RequestMapping(value = "clipboard.do", method = RequestMethod.GET)
	public String clipboard(Locale locale, Model model) {
		logger.info("Welcome clipboard! ");
		return "clipboard";
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
  
	@RequestMapping(value="/neighborlist.do", produces={"application/json"})
	@ResponseBody
	public String neighborList(@RequestParam String loginUser){
		List<Member> neighborlist = neighborService.selectNeighborList(loginUser);
       
		JSONObject json = new JSONObject();
		JSONArray jarr = new JSONArray();
		for (Member m : neighborlist) {
			JSONObject job = new JSONObject();
			job.put("memberId", m.getMember_id() + "");
			jarr.add(job);
		}       
		json.put("list", jarr);
		return json.toJSONString();
       
	}
}