package com.kh.gonggan.post.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.ProtocolException;

import javax.net.ssl.HttpsURLConnection;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.joda.time.DateTime;
/*
import org.json.JSONArray;
import org.json.JSONObject;
*/
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.kh.gonggan.blog.model.service.BlogService;
import com.kh.gonggan.book.model.service.BookService;
import com.kh.gonggan.comment.model.service.CommentService;
import com.kh.gonggan.comment.model.vo.Comment;
import com.kh.gonggan.diary.model.service.DiaryService;
import com.kh.gonggan.diary.model.vo.Diary;
import com.kh.gonggan.free.model.service.FreeService;
import com.kh.gonggan.good.model.service.GoodService;
import com.kh.gonggan.movie.model.service.MovieService;
import com.kh.gonggan.movie.model.vo.Movie;
import com.kh.gonggan.music.model.service.MusicService;
import com.kh.gonggan.music.model.vo.Music;
import com.kh.gonggan.news.model.service.NewsService;
import com.kh.gonggan.news.model.vo.News;
import com.kh.gonggan.place.model.service.PlaceService;
import com.kh.gonggan.post.model.service.PostService;
import com.kh.gonggan.post.model.vo.Post;
import com.kh.gonggan.post.model.vo.PostBestSeller;
import com.kh.gonggan.review.model.service.ReviewService;
import com.kh.gonggan.review.model.vo.Review;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.sun.org.apache.xerces.internal.impl.xpath.regex.ParseException;

import sun.net.www.protocol.http.HttpURLConnection;

@Controller
public class PostController {
	
	//메소드가 controller가 됨 컨트롤러를 메소드 단위로 작성하면 된다.
	//공통으로 사용하는 것은 common에 넣어놓으면 됨
	@Autowired
	private PostService postService;
	@Autowired
	private CommentService commentService;
	@Autowired
	private GoodService goodService;
	@Autowired
	private MovieService movieService;
	@Autowired
	private DiaryService diaryService;
	@Autowired
	private BookService bookService;
	@Autowired
	private MusicService musicService;
	@Autowired
	private NewsService newsService;
	@Autowired
	private ReviewService reviewService;
	@Autowired
	private BlogService blogService;
	@Autowired
	private PlaceService placeService;
	@Autowired
	private FreeService freeService;
	
	@RequestMapping("pdetail.do")
		public ModelAndView postDetail(@RequestParam String postId, @RequestParam String writerId, ModelAndView mv) {
			
			List<Comment> commentList = commentService.selectPostComments(postId);
			int goodCnt = goodService.goodCount(Integer.parseInt(postId));
			Post postDetail = postService.postDetail(Integer.parseInt(postId));
		
			mv.addObject("postDetail",postDetail);
			mv.addObject("postDate", new DateTime(postDetail.getPost_date()).toString());
			mv.addObject("postId", postId);
			mv.addObject("writerId", writerId);
			mv.addObject("commentList", commentList);
			mv.addObject("goodCnt", goodCnt);
			mv.addObject("HideComment", 
					blogService.selectBlog(writerId).getHide_comment());
			mv.setViewName("postDetail");
			return mv;
	}
	
	@RequestMapping(value="/plikecnt.do")
	@ResponseBody
	public int postLikeCnt(@RequestParam int postId) {
		return goodService.goodCount(postId);
	}
	
	@RequestMapping(value="/postNeighborlist.do", produces={"application/json"})
	   @ResponseBody
	   public String selectNeighborPostList(@RequestParam String loginUser,
	         @RequestParam int rownum, @RequestParam int rownum2){
	   System.out.println("postNl.do");
	   System.out.println("rownum : " + rownum + " rownum2 : " + rownum2);
	   System.out.println("loginUser :" + loginUser);
	      List<Post> plist  = postService.selectUserNeighbor(loginUser, rownum, rownum2);
	      
	      JSONObject json = new JSONObject();
	      JSONArray jarr = new JSONArray();
	      
	      if (plist != null) {
	         for(Post p : plist) {
	            
	            JSONObject job = new JSONObject();
	            
	            Calendar cal = Calendar.getInstance();
	            cal.setTime(p.getPost_date());
	            
	            job.put("postId", p.getPost_id() + "");
	            job.put("writerId", p.getWriter_id());
	            try {
	               job.put("category", URLEncoder.encode(
	                     p.getCategory(), "UTF-8"));
	            } catch (UnsupportedEncodingException e) {
	               // TODO Auto-generated catch block
	               e.printStackTrace();
	            }
	            job.put("postId", p.getPost_id() + "");
	            job.put("sharYn", p.getShar_yn());
	            job.put("openYn", p.getOpen_yn());
	            job.put("writerId", p.getWriter_id());
	            job.put("goodCnt", p.getGoodCnt() + "");
	            job.put("photoPath", (p.getPhoto_path()==null ? "0" : p.getPhoto_path()));
	            job.put("year", cal.get(Calendar.YEAR) + "");
	            job.put("month", (cal.get(Calendar.MONTH) + 1) + "");
	            job.put("date", cal.get(Calendar.DATE) + "");
	   
	            jarr.add(job);
	         }
	         json.put("list", jarr);
	      }
	      
	      //ModelAndView mv = new ModelAndView();
	      /*
	      mv.setViewName("home");
	      mv.addObject("loginUser", loginUser);
	      */
	      
	      /*return "home";*/
	      //return mv;
	      
	      return json.toJSONString();
	   }
	
	@RequestMapping(value="/postlist.do", produces={"application/json"})
		@ResponseBody
		public String selectList(@RequestParam String writer_id,
				@RequestParam String category,
				@RequestParam int rownum, @RequestParam int rownum2){
			/*		String userId =request.getParameter("userid");
			String userPwd =request.getParameter("userpwd");
			Member member =new Member();
			member.setUserid(userId);
			member.setUserpwd(userPwd);*/
		
		System.out.println("rownum : " + rownum + " rownum2 : " + rownum2);
		
			List<Post> plist  = null;
			
			switch (category) {
			case "all":
				plist = (writer_id == "" ?
						postService.selectAll(rownum, rownum2)
						: postService.selectUserAll(writer_id, rownum, rownum2));
				break;
			case "free":
				plist = (writer_id == "" ?
						postService.selectFree(rownum, rownum2)
						: postService.selectUserFree(writer_id, rownum, rownum2));
				break;
			case "music":
				plist = (writer_id == "" ?
						postService.selectMusic(rownum, rownum2)
						: postService.selectUserMusic(writer_id, rownum, rownum2));
				break;
			case "movie":
				plist = (writer_id == "" ?
						postService.selectMovie(rownum, rownum2)
						: postService.selectUserMovie(writer_id, rownum, rownum2));
				break;
			case "diary":
				plist = (writer_id == "" ?
						postService.selectDiary(rownum, rownum2)
						: postService.selectUserDiary(writer_id, rownum, rownum2));
				break;
			case "review":
				plist = (writer_id == "" ?
						postService.selectReview(rownum, rownum2)
						: postService.selectUserReview(writer_id, rownum, rownum2));
				break;
			case "news":
				plist = (writer_id == "" ?
						postService.selectNews(rownum, rownum2)
						: postService.selectUserNews(writer_id, rownum, rownum2));
				break;
			case "book":
				plist = (writer_id == "" ?
						postService.selectBook(rownum, rownum2)
						: postService.selectUserBook(writer_id, rownum, rownum2));
				break;
			case "place":
				plist = (writer_id == "" ?
						postService.selectPlace(rownum, rownum2)
						: postService.selectUserPlace(writer_id, rownum, rownum2));
				break;
			}
			
			JSONObject json = new JSONObject();
			JSONArray jarr = new JSONArray();
			
			if (plist != null) {
				for(Post p : plist) {
					
					JSONObject job = new JSONObject();
					
					Calendar cal = Calendar.getInstance();
					cal.setTime(p.getPost_date());

					try {
						job.put("postId", p.getPost_id() + "");
						job.put("writerId", p.getWriter_id());
						job.put("content", contentSort(p));
						job.put("category", URLEncoder.encode(
								p.getCategory(), "UTF-8"));
						job.put("postId", p.getPost_id() + "");
						job.put("sharYn", p.getShar_yn());
						job.put("openYn", p.getOpen_yn());
						job.put("writerId", p.getWriter_id());
						job.put("bg", (p.getBg_image()==null ? "" : p.getBg_image()));
						job.put("diary_title", URLEncoder.encode(
								p.getDiary_title() == null ? "" : p.getDiary_title(), "UTF-8"));
						job.put("music_title", URLEncoder.encode(
								p.getMusic_title() == null ? "" : p.getMusic_title(), "UTF-8"));
						job.put("movie_title", URLEncoder.encode(
								p.getMovie_title() == null ? "" : p.getMovie_title(), "UTF-8"));
						job.put("music_info", (p.getMusic_info()==null ? "" : p.getMusic_info()));
						job.put("goodCnt", p.getGoodCnt() + "");
						job.put("photoPath", (p.getPhoto_path()==null ? "0" : p.getPhoto_path()));
						job.put("year", cal.get(Calendar.YEAR) + "");
						job.put("month", (cal.get(Calendar.MONTH) + 1) + "");
						job.put("date", cal.get(Calendar.DATE) + "");
						job.put("place_name", URLEncoder.encode(
								p.getPlace_name() == null ? "" : p.getPlace_name(), "UTF-8"));
					} catch (UnsupportedEncodingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					jarr.add(job);
				}
				json.put("list", jarr);
			}
			
			//ModelAndView mv = new ModelAndView();
			/*
			mv.setViewName("home");
			mv.addObject("loginUser", loginUser);
			*/
			
			/*return "home";*/
			//return mv;
			
			return json.toJSONString();
		}
	
	public String contentSort(Post p) {
		
		String content = null;
		
		switch(p.getCategory()) {
		case "free":
			content = p.getFree_content();
			break;
		case "music":
			content = p.getMusic_content();
			break;
		case "movie":
			content = p.getMovie_content();
			break;
		case "book":
			content = p.getBook_content();
			break;
		case "diary":
			content = p.getDiary_content();
			break;
		case "review":
			content = p.getReview_content();
			break;
		case "news":
			content = p.getNews_content();
			break;
		case "place":
			content = p.getPlace_content();
			break;
		}
		
		try {
			content = URLEncoder.encode(
					content + "", "UTF-8");
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return content;
	}
	
	@RequestMapping(value="calpostlist.do", produces={"application/json"})
	@ResponseBody
	public String calendarPostList(@RequestParam String writer_id, 
			@RequestParam int year, @RequestParam int month) {

		JSONObject json = new JSONObject();
		JSONArray jarr = new JSONArray();
		
		List<Post> plist  = postService.selectCalendarAll(writer_id, year, month);
		
		for(Post p : plist) {
			
			JSONObject job = new JSONObject();
			
			Calendar cal = Calendar.getInstance();
			cal.setTime(p.getPost_date());
			
			job.put("postId", p.getPost_id() + "");
			job.put("writerId", p.getWriter_id());
			try {
				job.put("category", URLEncoder.encode(
						p.getCategory(), "UTF-8"));
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			job.put("postId", p.getPost_id() + "");
			job.put("sharYn", p.getShar_yn());
			job.put("openYn", p.getOpen_yn());
			job.put("writerId", p.getWriter_id());
			job.put("goodCnt", p.getGoodCnt() + "");
			job.put("photoPath", (p.getPhoto_path()==null ? "0" : p.getPhoto_path()));
			job.put("year", cal.get(Calendar.YEAR) + "");
			job.put("month", (cal.get(Calendar.MONTH) + 1) + "");
			job.put("date", cal.get(Calendar.DATE) + "");

			jarr.add(job);
		}
		json.put("list", jarr);
		
		return json.toJSONString();
	}
	
	@RequestMapping(value="/plikelist.do", produces={"application/json"})
	@ResponseBody
	public String selectBloghomeLikeList(@RequestParam int rownum,
			@RequestParam int rownum2, @RequestParam String category, String writer_id){

	System.out.println("rownum : " + rownum + " rownum2 : " + rownum2);
	
		List<Post> plist  = null;
		
		if (category.equals("all"))
			plist = postService.selectLikeAll(rownum, rownum2);
		else if (category.equals("neighborlist"))
			plist = postService.selectLikeNpost(rownum, rownum2, writer_id);
		else
			plist = postService.selectLikeCategoryPost(rownum, rownum2, category);
			
		JSONObject json = new JSONObject();
		JSONArray jarr = new JSONArray();
		
		for(Post p : plist) {
			
			JSONObject job = new JSONObject();
			
			Calendar cal = Calendar.getInstance();
			cal.setTime(p.getPost_date());
			
			try {
				job.put("postId", p.getPost_id() + "");
				job.put("writerId", p.getWriter_id());
				job.put("content", contentSort(p));
				job.put("category", URLEncoder.encode(
						p.getCategory(), "UTF-8"));
				job.put("postId", p.getPost_id() + "");
				job.put("sharYn", p.getShar_yn());
				job.put("openYn", p.getOpen_yn());
				job.put("writerId", p.getWriter_id());
				job.put("bg", (p.getBg_image()==null ? "" : p.getBg_image()));
				job.put("diary_title", URLEncoder.encode(
						p.getDiary_title() == null ? "" : p.getDiary_title(), "UTF-8"));
				job.put("music_title", URLEncoder.encode(
						p.getMusic_title() == null ? "" : p.getMusic_title(), "UTF-8"));
				job.put("movie_title", URLEncoder.encode(
						p.getMovie_title() == null ? "" : p.getMovie_title(), "UTF-8"));
				job.put("music_info", (p.getMusic_info()==null ? "" : p.getMusic_info()));
				job.put("goodCnt", p.getGoodCnt() + "");
				job.put("photoPath", (p.getPhoto_path()==null ? "0" : p.getPhoto_path()));
				job.put("year", cal.get(Calendar.YEAR) + "");
				job.put("month", (cal.get(Calendar.MONTH) + 1) + "");
				job.put("date", cal.get(Calendar.DATE) + "");
				job.put("place_name", URLEncoder.encode(
						p.getPlace_name() == null ? "" : p.getPlace_name(), "UTF-8"));
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			jarr.add(job);
		}
		json.put("list", jarr);
		
		return json.toJSONString();
	}

	@RequestMapping(value="/psearchLikelist.do", produces={"application/json"})
	@ResponseBody
	public String selectSearchLikeList(@RequestParam int rownum,
			@RequestParam int rownum2, @RequestParam String keyword,
			@RequestParam int option) {

		List<Post> plist;
		
		plist  = postService.selectSearchLikeList(rownum, rownum2, keyword, option);
		
		JSONObject json = new JSONObject();
		JSONArray jarr = new JSONArray();
		
		for(Post p : plist) {
			
			JSONObject job = new JSONObject();
			
			try {
				job.put("postId", p.getPost_id() + "");
				job.put("writerId", p.getWriter_id());
				job.put("content", contentSort(p));
				job.put("category", URLEncoder.encode(
						p.getCategory(), "UTF-8"));
				job.put("postId", p.getPost_id() + "");
				job.put("sharYn", p.getShar_yn());
				job.put("openYn", p.getOpen_yn());
				job.put("writerId", p.getWriter_id());
				job.put("bg", (p.getBg_image()==null ? "" : p.getBg_image()));
				job.put("diary_title", URLEncoder.encode(
						p.getDiary_title() == null ? "" : p.getDiary_title(), "UTF-8"));
				job.put("music_title", URLEncoder.encode(
						p.getMusic_title() == null ? "" : p.getMusic_title(), "UTF-8"));
				job.put("movie_title", URLEncoder.encode(
						p.getMovie_title() == null ? "" : p.getMovie_title(), "UTF-8"));
				job.put("music_info", (p.getMusic_info()==null ? "" : p.getMusic_info()));
				job.put("goodCnt", p.getGoodCnt() + "");
				job.put("photoPath", (p.getPhoto_path()==null ? "0" : p.getPhoto_path()));
				job.put("place_name", URLEncoder.encode(
						p.getPlace_name() == null ? "" : p.getPlace_name(), "UTF-8"));
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			jarr.add(job);
		}
		json.put("list", jarr);
		
		return json.toJSONString();
	}
	
	@RequestMapping(value="psearchmax.do", produces={"application/json"})
	@ResponseBody
	public int postSearchMaxRnum(@RequestParam int option,
			@RequestParam String keyword) {
		
		int result = 0;
		
		if (option == 0)
			result = postService.postContentSearchMaxRnum(keyword);
		else if (option == 1)
			result = postService.postWriterSearchMaxRnum(keyword);
		
		return result;
	}
	
	@RequestMapping(value="/psearch.do", produces={"application/json"})
	@ResponseBody
	public String postSearch(@RequestParam int option, @RequestParam String keyword,
			@RequestParam int rownum, @RequestParam int rownum2) {
		
		List<Post> plist = null;

		String content;
		
		JSONObject json = new JSONObject();
		JSONArray jarr = new JSONArray();
		
		if (option == 0) 
			plist = postService.postContentSearch(keyword, rownum, rownum2);
		else if (option == 1)
			plist = postService.postWriterSearch(keyword, rownum, rownum2);
		
		for(Post p : plist) {
			
			JSONObject job = new JSONObject();
			
			System.out.println(p.getCategory());
			
			try {
				job.put("postId", p.getPost_id() + "");
				job.put("writerId", p.getWriter_id());
				job.put("content", contentSort(p));
				job.put("category", URLEncoder.encode(
						p.getCategory(), "UTF-8"));
				job.put("postId", p.getPost_id() + "");
				job.put("sharYn", p.getShar_yn());
				job.put("openYn", p.getOpen_yn());
				job.put("writerId", p.getWriter_id());
				job.put("bg", (p.getBg_image()==null ? "" : p.getBg_image()));
				job.put("diary_title", URLEncoder.encode(
						p.getDiary_title() == null ? "" : p.getDiary_title(), "UTF-8"));
				job.put("music_title", URLEncoder.encode(
						p.getMusic_title() == null ? "" : p.getMusic_title(), "UTF-8"));
				job.put("movie_title", URLEncoder.encode(
						p.getMovie_title() == null ? "" : p.getMovie_title(), "UTF-8"));
				job.put("music_info", (p.getMusic_info()==null ? "" : p.getMusic_info()));
				job.put("goodCnt", p.getGoodCnt() + "");
				job.put("photoPath", (p.getPhoto_path()==null ? "0" : p.getPhoto_path()));
				job.put("place_name", URLEncoder.encode(
						p.getPlace_name() == null ? "" : p.getPlace_name(), "UTF-8"));
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			jarr.add(job);
		}
		json.put("list", jarr);
		
		return json.toJSONString();
	}
	
	@RequestMapping(value="/plistDetail.do", produces={"text/plain;charset=UTF-8"})
	@ResponseBody
	public String postListDetail(@RequestParam int postId, @RequestParam String category) {

		JSONObject json = new JSONObject();
		JSONArray jarr = new JSONArray();
		String content = null;
		
		switch (category) {
		case "free":
			content = freeService.freeDetail(postId).getFree_content();
			break;
		case "music":
			content = musicService.musicDetail(postId).getMusic_content();
			break;
		case "book":
			content = bookService.bookDetail(postId).getBook_content();
			break;
		case "movie":
			content = movieService.movieDetail(postId).getMovie_content();
			break;
		case "diary":
			content = diaryService.diaryDetail(postId).getDiary_title();
			break;
		case "review":
			content = reviewService.reviewDetail(postId).getReview_content();
			break;
		case "news":
			content = newsService.newsDetail(postId).getNews_content();
			break;
		case "place":
			content = placeService.placeDetail(postId).getPlace_content();
			break;
		}
		
		content = content.replaceAll("<(/)?([a-zA-Z0-9]*)(\\s[a-zA-Z]*=[^>]*)?(\\s)*(/)?>", "");
		
		if (content.length() > 100)
			content = content.substring(0, 100) + "...";
		
		try {
			content = URLEncoder.encode(content, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return content; 
	}
	
	@RequestMapping(value="/weather.do", produces="application/json;charset=UTF-8")
	@ResponseBody
	public String weather(@RequestParam String lat, @RequestParam String lon/*,
			String city, String country, String village*/) {
		
		// 다음의 Request Parameter는 한 종류만 선택적으로 사용해야 한다.
		// - 1)lat/lon, 2)city/county/village, 3)stnid
		
		URL url = null;
		HttpURLConnection http = null;
		InputStreamReader in = null;
		BufferedReader br = null;
		String line;
		StringBuffer sb = new StringBuffer();
		
		try {

			url = new URL("http://apis.skplanetx.com/weather/current/minutely?"
					+ "version=1&lat=" + lat + "&lon=" + lon);
					//+ "&city=" + city + "country=" + country + "village" + village + "&stnid=108"
			
			http = (HttpURLConnection)url.openConnection();
			http.setRequestProperty("Accept", "application/json");
			http.setRequestProperty("appKey", "ba6dd3bc-1655-3ad9-a20d-de885d696b9b");
			http.connect();
		
			in = new InputStreamReader(http.getInputStream(),"utf-8");
			br = new BufferedReader(in);

			while ((line = br.readLine()) != null) {
				sb.append(line).append("\n");
			}
		
			br.close();
			in.close();
			
		} catch (IOException e2) {
		// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		System.out.println(sb.toString());
      
		return sb.toString();
	}

	@RequestMapping(value="/imgupload.do", method=RequestMethod.POST, produces="text/plain;charset=UTF-8")
	@ResponseBody
	public String uploadImg(HttpServletRequest req, HttpSession session) throws Exception{

		File originalFile = null;
		File renameFile = null;
		String originalFileName = null;
		
		req.setCharacterEncoding("utf-8");
		
		if(ServletFileUpload.isMultipartContent(req)) {
			// 전송 파일 용량 제한 : 10Mbyte 제한한 경우
			int maxSize = 1024 * 1024 * 10;

			String root = req.getSession().getServletContext().getRealPath("/");

			System.out.println("root : " + root);
			String[] roots = root.split("\\\\");
			String marger="";
			
			for(int i=0 ; i<roots.length-3; i++)
	         marger += roots[i] + "\\";
	         
			
			System.out.println("marger : " + marger);
			String savePath = marger + "src/main/webapp/uploadImages/";
			System.out.println("savepath : " + savePath);


			// 파일 저장 경로(ex : webapp/uploadImages/) 정함
			MultipartRequest multiRequest  = new MultipartRequest(req, savePath, maxSize, "UTF-8", new DefaultFileRenamePolicy());
			originalFileName = multiRequest.getFilesystemName("file");
		
			if(originalFileName != null){
				
				originalFile = new File(savePath + originalFileName);
				renameFile = new File(savePath + originalFileName);
				
				if(!originalFile.renameTo(renameFile)){
						
					FileInputStream originalRead = 	new FileInputStream(originalFile);
					FileOutputStream renameCopy = 	new FileOutputStream(renameFile);
	
					byte[] readText = new byte[1024];
					int readResult = 0;
					
					while((readResult = originalRead.read(readText, 0, readText.length))!= -1){
						renameCopy.write(readText, 0, readResult);
						renameCopy.flush();
					}
					
					originalRead.close();
					renameCopy.close();
					originalFile.delete();
				}
			}
		
		}
		return originalFileName;
	}
	
	@RequestMapping(value="/geocode.do", produces={"application/json"}, method=RequestMethod.GET)
	@ResponseBody
		public String map(@RequestParam String address){
			String searchaddr = null;
			
			//필수는 query로 주소만..나머지 옵션은 api참고.
			String api = null; 
			StringBuffer sb = null;

			JSONObject jsonObjectPoint = null;
	
			try {
				searchaddr = URLEncoder.encode(address,"UTF-8");
				api = "https://openapi.naver.com/v1/map/geocode?query=" + searchaddr;
				sb =new StringBuffer();
				
				URL url = new URL(api);
				
				HttpsURLConnection http = (HttpsURLConnection)url.openConnection();//고유아이디
				http.setRequestProperty("X-Naver-Client-Id", "JVmBHBSdqNcd5JKBkRhO");//비밀번호
				http.setRequestProperty("X-Naver-Client-Secret", "eg9UxPaF2b");
				//http.setDoOutput(true);
				//네이버는 반드시 GET방식으로 호출해야함.
				http.setRequestMethod("GET");
				http.connect();
				
				InputStreamReader in = new InputStreamReader(http.getInputStream(),"utf-8");
				BufferedReader br = new BufferedReader(in);
	
				String line;
				while ((line = br.readLine()) != null) {
					sb.append(line).append("\n");
				}
	
				JSONParser parser = new JSONParser();
				
				JSONObject jsonObject;
				JSONObject jsonObject2;
				
				JSONArray jsonArray;
				
				String x = "";
				String y = "";
				
				jsonObject = (JSONObject)parser.parse(sb.toString());
				//디버깅을 해보면 알겠지만 json구조가 트리형태로 리턴되서 몇번 파싱 해야 원하는 좌표가 나온다.
				jsonObject = (JSONObject)jsonObject.get("result");
				jsonArray = (JSONArray)jsonObject.get("items");
				
				for(int i=0;i<jsonArray.size();i++){
					jsonObject2 = (JSONObject) jsonArray.get(i);
					if (null != jsonObject2.get("point")){
						jsonObjectPoint = (JSONObject) jsonObject2.get("point");
						x = (String) jsonObjectPoint.get("x").toString();
						y = (String) jsonObjectPoint.get("y").toString();
					}
				}
				
				//System.out.println(sb.toString());
				System.out.println("x좌표==" + x + " y좌표==" + y);
				
				br.close();
				in.close();
				http.disconnect();
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (org.json.simple.parser.ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return jsonObjectPoint.toJSONString(); 
	
	
		}
	
	@RequestMapping(value="/mapAdd.do", produces={"text/html; charset=UTF-8"}, method=RequestMethod.GET)
	@ResponseBody
		public String mapAdd(@RequestParam String x, @RequestParam String y){
			String searchaddr = null;
			
			//필수는 query로 주소만..나머지 옵션은 api참고.
			String api = null; 
			StringBuffer sb = null;

			String address = null;
	
			try {
				
				api = "https://openapi.naver.com/v1/map/reversegeocode?encoding=utf-8&coordType=latlng&query="
						+ x + "," + y;
				sb =new StringBuffer();
				
				URL url = new URL(api);
				
				HttpsURLConnection http = (HttpsURLConnection)url.openConnection();//고유아이디
				http.setRequestProperty("X-Naver-Client-Id", "JVmBHBSdqNcd5JKBkRhO");//비밀번호
				http.setRequestProperty("X-Naver-Client-Secret", "eg9UxPaF2b");
				//http.setDoOutput(true);
				//네이버는 반드시 GET방식으로 호출해야함.
				http.setRequestMethod("GET");
				http.connect();
				
				InputStreamReader in = new InputStreamReader(http.getInputStream(),"utf-8");
				BufferedReader br = new BufferedReader(in);
	
				String line;
				while ((line = br.readLine()) != null) {
					sb.append(line).append("\n");
				}
	
				JSONParser parser = new JSONParser();
				
				JSONObject jsonObject;
				JSONObject jsonObject2;
				
				JSONArray jsonArray;
				
				jsonObject = (JSONObject)parser.parse(sb.toString());
				//디버깅을 해보면 알겠지만 json구조가 트리형태로 리턴되서 몇번 파싱 해야 원하는 좌표가 나온다.
				jsonObject = (JSONObject)jsonObject.get("result");
				jsonArray = (JSONArray)jsonObject.get("items");
				
				for(int i=0;i<jsonArray.size();i++){
					jsonObject2 = (JSONObject) jsonArray.get(i);
					if (null != jsonObject2.get("point")){
						address = (String) jsonObject2.get("address");
					}
				}
				
				br.close();
				in.close();
				http.disconnect();
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (org.json.simple.parser.ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return address; 
	
	
		}
	
	@RequestMapping(value="/mapImg.do", produces={"text/plain;charset=UTF-8"}, method=RequestMethod.GET)
	@ResponseBody
	public String mapImg(HttpServletRequest req, @RequestParam String mapx, @RequestParam String mapy) {

		URL url;
		StringBuffer sb = new StringBuffer();
		File renameFile = null;
		InputStream in = null;
		FileOutputStream fos = null;
		String originalFileName = null;
		String  renameFileName;
		
		try {
			/*url = new URL("https://openapi.naver.com/v1/map/staticmap.bin?"
					+ "clientId=JVmBHBSdqNcd5JKBkRhO"
					+ "&url=http://localhost:8577/gonggan/&crs=EPSG:4326"
					+ "&center=127.0328782,37.4989958"
					+ "&level=14&w=300&h=300&baselayer=default"
					+ "&markers=127.0328782,37.4989958");*/
			
			url = new URL("https://openapi.naver.com/v1/map/staticmap.bin?"
					+ "clientId=JVmBHBSdqNcd5JKBkRhO"
					+ "&url=http://localhost:8577/gonggan/&crs=NHN:128"
					+ "&center=" + mapx + "," + mapy
					+ "&level=14&w=300&h=300&baselayer=default"
					+ "&markers=" + mapx + "," + mapy);
		
			
			HttpsURLConnection http = (HttpsURLConnection)url.openConnection();//고유아이디
			http.setRequestProperty("X-Naver-Client-Id", "JVmBHBSdqNcd5JKBkRhO");//비밀번호
			http.setRequestProperty("X-Naver-Client-Secret", "eg9UxPaF2b");
			//http.setDoOutput(true);
			//네이버는 반드시 GET방식으로 호출해야함.
			http.setRequestMethod("GET");
			http.connect();
			
			// 전송 파일 용량 제한 : 10Mbyte 제한한 경우
			int maxSize = 1024 * 1024 * 10;

			String root = req.getSession().getServletContext().getRealPath("/");

			System.out.println("root : " + root);
			String[] roots = root.split("\\\\");
			String marger="";
			
			for(int i=0 ; i<roots.length-3; i++)
				marger += roots[i] + "\\";
	         
			marger = "/Users/jiseung/git/gonggan2/gonggan/";
			System.out.println("marger : " + marger);
			String savePath = marger + "src/main/webapp/uploadImages/";
			System.out.println("savepath : " + savePath);
			
			long current = System.currentTimeMillis();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
            
	        /*
	        변경할 파일명 만들기
	        renameFileName = sdf.format(new Date(current))+ "." + originalFileName.substring(
	              originalFileName.lastIndexOf(".") + 1);
	        */
            
            renameFileName = "map" + sdf.format(new Date(current))+ ".png";
            
			int input = 0;
			int cnt = 0;
			byte[] data = new byte[1024];
			in = http.getInputStream();
			fos = new FileOutputStream(renameFile = new File(savePath + renameFileName));
			while((input = in.read(data)) != -1) {
				fos.write(data, 0, input);
				cnt += input;
				fos.flush();
			}
		
		
			in.close();
			fos.close();
			http.disconnect();
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return renameFile.getName();
	}
		
	@RequestMapping(value="/upload.do", method=RequestMethod.POST)
	public ModelAndView upload(@RequestParam String loginUser,
			@RequestParam String category,@RequestParam String content,
			@RequestParam String open,
			@RequestParam String diary_title, @RequestParam String music_title,
			@RequestParam String movie_title, @RequestParam String music_info,
			@RequestParam String bg, @RequestParam String place_name,
			@RequestParam String pimg, ModelAndView mv) throws Exception{
		
		int pinsert = postService.pinsert(loginUser,category,content,
				diary_title, music_title, movie_title, open,
				bg, music_info, place_name, pimg);
		
		if (pinsert < 0) {
			System.out.println("안됨");
			mv.setViewName("redirect:uploadform.do?"
					+ "writer_id=" + loginUser);
		} else{
			System.out.println("된거");
			mv.setViewName("redirect:myhome.do?"
						+ "writer_id=" + loginUser);
		}
		return mv;
	}
	
	@RequestMapping(value="/pdelete.do")
	@ResponseBody
	public String postDelete(@RequestParam int postId,@RequestParam String loginUser){
		String msg = "실패";
    
		int pdelete = postService.postDelete(postId,loginUser);
    
		if(pdelete > 0) {
			msg="성공";
		}
		System.out.println(pdelete + " post controller run ...");
    
		return msg;
 
	}
	
	@RequestMapping(value="/uppostimg.do", method = RequestMethod.POST, produces="text/plain;charset=UTF-8")
	@ResponseBody
	public String uploadPostImg(MultipartHttpServletRequest request) {
		
		String msg = "실패";

		MultipartFile multipartFile = request.getFile("file");
		
		long current = System.currentTimeMillis();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		File  renameFile;
		String originalFileName = multipartFile.getOriginalFilename();
		String[] originalFileNameSplit;
		
		try {
			
			multipartFile.transferTo(renameFile = new File(
					"/Users/jiseung/git/gonggan2/gonggan/src/main/webapp/uploadImages/"
							+ "pimg" + sdf.format(new Date(current))
							+ ("." + (originalFileNameSplit =
							originalFileName.split("\\."))[originalFileNameSplit.length-1])));
			
			msg = renameFile.getName();
			
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return msg;
	}
	
	@RequestMapping(value="/delpostimg.do", produces="text/plain;charset=UTF-8")
	@ResponseBody
	public String deletePostImage(@RequestParam String postImg) {
		
		String msg = "";
		File existFile;
		
		File  renameFile;
		
		try {
			
			existFile = new File(
					"/Users/jiseung/git/gonggan2/gonggan/src/main/webapp/uploadImages/"
					+ postImg);
			if(existFile.exists())
				if(existFile.delete())
	                System.out.println("파일삭제 성공");
			
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return msg;
	}
	
	@RequestMapping(value="/updiarybg.do", method = RequestMethod.POST, produces="text/plain;charset=UTF-8")
	@ResponseBody
	public String uploadDiarybg(MultipartHttpServletRequest request) {
		
		String msg = "실패";

		MultipartFile multipartFile = request.getFile("file2");
		
		long current = System.currentTimeMillis();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		File  renameFile;
		String originalFileName = multipartFile.getOriginalFilename();
		String[] originalFileNameSplit;
		
		try {
			
			multipartFile.transferTo(renameFile = new File(
					"/Users/jiseung/git/gonggan2/gonggan/src/main/webapp/images/diaryBackgroundImages/"
							+ "diarybg" + sdf.format(new Date(current))
							+ ("." + (originalFileNameSplit =
							originalFileName.split("\\."))[originalFileNameSplit.length-1])));
			
			msg = renameFile.getName();
			
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return msg;
	}
	
	@RequestMapping(value="/deldiarybg.do", method = RequestMethod.POST, produces="text/plain;charset=UTF-8")
	@ResponseBody
	public String deleteDiarybg(@RequestParam String diaryBgImg) {
		
		String msg = "";
		File existFile;
		
		File  renameFile;
		
		try {
			
			existFile = new File(
					"/Users/jiseung/git/gonggan2/gonggan/src/main/webapp/images/diaryBackgroundImages/"
					+ diaryBgImg);
			if(existFile.exists())
				if(existFile.delete())
	                System.out.println("파일삭제 성공");
			
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return msg;
	}
	
}