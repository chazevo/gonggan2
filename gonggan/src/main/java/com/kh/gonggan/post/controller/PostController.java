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
import org.json.JSONString;
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
import org.springframework.web.servlet.ModelAndView;

import com.kh.gonggan.comment.model.service.CommentService;
import com.kh.gonggan.comment.model.vo.Comment;
import com.kh.gonggan.diary.model.service.DiaryService;
import com.kh.gonggan.diary.model.vo.Diary;
import com.kh.gonggan.good.model.service.GoodService;
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
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.sun.org.apache.xerces.internal.impl.xpath.regex.ParseException;

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
	private MusicService musicService;
	@Autowired
	private NewsService newsService;
	@Autowired
	private ReviewService reviewService;
	
	@RequestMapping("pdetail.do")
		public ModelAndView postDetail(@RequestParam String postId, @RequestParam String writerId, ModelAndView mv) {
		
			List<Comment> commentList = commentService.selectPostComments(postId);
			int goodCnt = goodService.goodCount(Integer.parseInt(postId));
			Post postDetail = postService.postDetail(Integer.parseInt(postId));
			
			mv.addObject("postDetail",postDetail);
			mv.addObject("postId", postId);
			mv.addObject("writerId", writerId);
			mv.addObject("commentList", commentList);
			mv.addObject("goodCnt", goodCnt);
			mv.setViewName("postDetail");
			return mv;
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
			
			if (writer_id == ""){
				switch (category) {
				case "all":
					plist = postService.selectAll(rownum, rownum2);
					break;
				case "music":
					plist = postService.selectMusic(rownum, rownum2);
					break;
				case "movie":
					plist = postService.selectMovie(rownum, rownum2);
					break;
				case "diary":
					plist = postService.selectDiary(rownum, rownum2);
					break;
				case "review":
					plist = postService.selectReview(rownum, rownum2);
					break;
				case "news":
					plist = postService.selectNews(rownum, rownum2);
					break;
				case "book":
					plist = postService.selectBook(rownum, rownum2);
					break;
				}
				
			} else {
				plist = postService.selectUserAll(writer_id);
			}
	
			JSONObject json = new JSONObject();
			JSONArray jarr = new JSONArray();
			
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
			
			//ModelAndView mv = new ModelAndView();
			/*
			mv.setViewName("home");
			mv.addObject("loginUser", loginUser);
			*/
			
			/*return "home";*/
			//return mv;
			
			return json.toJSONString();
		}
	
	@RequestMapping(value="/plikelist.do", produces={"application/json"})
	@ResponseBody
	public String selectLikeList(@RequestParam int rownum,
			@RequestParam int rownum2){

	System.out.println("rownum : " + rownum + " rownum2 : " + rownum2);
	
		List<Post> plist  = null;
		
		plist = postService.selectLikeAll(rownum, rownum2);

		JSONObject json = new JSONObject();
		JSONArray jarr = new JSONArray();
		
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

	
	@RequestMapping(value="/plistDetail.do", produces={"text/plain;charset=UTF-8"})
	@ResponseBody
	public String postListDetail(@RequestParam int postId, @RequestParam String category) {

		JSONObject json = new JSONObject();
		JSONArray jarr = new JSONArray();
		String content = null;
		
		switch (category) {
		case "music":
			content = musicService.musicDetail(postId).getMusic_info();
			break;
		case "movie":
			content = movieService.movieDetail(postId).getMovie_info();
			break;
		case "diary":
			content = diaryService.diaryDetail(postId).getDiary_content();
			break;
		case "review":
			content = reviewService.reviewDetail(postId).getReview_content();
			break;
		case "news":
			content = newsService.newsDetail(postId).getNews_info();
			break;
		}
		
		if (content.length() > 100)
			content = content.substring(0, 100);
		
		try {
			content = URLEncoder.encode(content, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return content; 
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
		
	
}