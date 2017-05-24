package com.kh.gonggan.news.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.kh.gonggan.news.model.service.NewsService;
import com.kh.gonggan.news.model.vo.News;
import com.kh.gonggan.post.model.vo.Post;
import com.kh.gonggan.post.model.vo.PostNews;

@Controller
public class NewsController {
	
	/*@Autowired
	private NewsService newsService;
	*/
	@RequestMapping(value="/newssearch.do", method=RequestMethod.GET)
public ModelAndView searchNews(ModelAndView mv, @RequestParam String keyword, HttpSession session){
		
		JSONObject job = new JSONObject();
		
		Gson gson = new Gson();
		Type type = new TypeToken<PostNews>(){}.getType();          
		                        
		//List<Movie> searchMovieList  = movieService.searchMovie();
		List<News> searchNewsList = new ArrayList<News>();
		
		String clientId = "wby5y_qdDk0ASqaBNnEt"; //애플리케이션 클라이언트 아이디값";
		String clientSecret = "bmJftKj85P"; //애플리케이션 클라이언트 시크릿값";
		StringBuffer response_ = null;
		
		try {
			
			String text = URLEncoder.encode(keyword, "UTF-8");
			String apiURL = "https://openapi.naver.com/v1/search/news?query="+ text; // json 결과
			//String apiURL = "https://openapi.naver.com/v1/search/blog.xml?query="+ text; // xml 결과
			
			URL url = new URL(apiURL);
			HttpURLConnection con = (HttpURLConnection)url.openConnection();
			
			con.setRequestMethod("GET");
			con.setRequestProperty("X-Naver-Client-Id", clientId);
			con.setRequestProperty("X-Naver-Client-Secret", clientSecret);
			
			int responseCode = con.getResponseCode();
			
			BufferedReader br;
			
			if(responseCode==200) { // 정상 호출
			    br = new BufferedReader(new InputStreamReader(con.getInputStream()));
			} else {  // 에러 발생
			    br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
			}
			
			String inputLine;
			response_ = new StringBuffer();
			while ((inputLine = br.readLine()) != null) {
				response_.append(inputLine + "\n");
				System.out.println(inputLine);
            }
            br.close();
            
            
        } catch (Exception e) {
            e.printStackTrace();
        }
		
		List<News> result = ((PostNews) gson.fromJson(response_.toString(), type)).getItems();
		/*
		for (News n : result)  {
			System.out.println(n.getTitle());
		}
		*/
		mv.setViewName("searchAll");
		mv.addObject("searchNewsList", result);
		mv.addObject("keyword", keyword);
		
		
		return mv;
	}
}