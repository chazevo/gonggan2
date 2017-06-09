package com.kh.gonggan.movie.controller;


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.annotation.ModelAndViewResolver;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.kh.gonggan.post.model.vo.PostMovie;
import com.kh.gonggan.movie.model.service.MovieService;
import com.kh.gonggan.movie.model.vo.Movie;
import com.kh.gonggan.news.model.vo.News;

import java.lang.reflect.Type;


@Controller
public class MovieController {

	//메소드가 controller가 됨 컨트롤러를 메소드 단위로 작성하면 된다.
	//공통으로 사용하는 것은 common에 넣어놓으면 됨
	@Autowired
	private MovieService movieService;
	
	@RequestMapping(value="/moviesearch.do", method=RequestMethod.GET)
	public ModelAndView searchMovie(ModelAndView mv, @RequestParam String keyword, HttpSession session){
		
		JSONObject job = new JSONObject();
		
		Gson gson = new Gson();
		Type type = new TypeToken<PostMovie>(){}.getType();          
		                        
		//List<Movie> searchMovieList  = movieService.searchMovie();
		
		String clientId = "wby5y_qdDk0ASqaBNnEt"; //애플리케이션 클라이언트 아이디값";
		String clientSecret = "bmJftKj85P"; //애플리케이션 클라이언트 시크릿값";
		StringBuffer response_ = null;
		
		try {
			
			String text = URLEncoder.encode(keyword, "UTF-8");
			String apiURL = "https://openapi.naver.com/v1/search/movie?query="+ text; // json 결과
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
		
		List<Movie> result = ((PostMovie) gson.fromJson(response_.toString(), type)).getItems();
		for (Movie m : result)  {
			m.setDirector(m.getDirector().split("\\|")[0]);
			//System.out.println(m.getDirector());
		}
		
		mv.setViewName("searchAll");
		mv.addObject("searchMovieList", result);
		mv.addObject("keyword", keyword);
		mv.addObject("category", 1);
		
		return mv;
	}
	@RequestMapping(value="/moviesearch2.do",produces={"application/json"} )
	@ResponseBody
	public String searchMovie2(ModelAndView mv, @RequestParam String keyword, HttpSession session){

		List<Movie> searchMovieList = new ArrayList<Movie>();
		
		JSONObject json = new JSONObject();
		JSONArray jarr = new JSONArray();
		
		Gson gson = new Gson();
		Type type = new TypeToken<PostMovie>(){}.getType();          
		                        
		
		String clientId = "wby5y_qdDk0ASqaBNnEt"; //애플리케이션 클라이언트 아이디값";
		String clientSecret = "bmJftKj85P"; //애플리케이션 클라이언트 시크릿값";
		StringBuffer response_ = null;
		
		try {
			
			String text = URLEncoder.encode(keyword, "UTF-8");
			String apiURL = "https://openapi.naver.com/v1/search/movie?query="+ text; // json 결과
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
		
		List<Movie> result = ((PostMovie) gson.fromJson(response_.toString(), type)).getItems();
		for (Movie m : result)  {
			
				JSONObject job = new JSONObject();
            
            try {

                job.put("title", URLEncoder.encode(
                	m.getTitle(), "UTF-8"));
                job.put("actor", URLEncoder.encode(
                    	m.getActor(), "UTF-8"));
                job.put("director", URLEncoder.encode(
                    	m.getDirector(), "UTF-8"));
                job.put("image", m.getImage());
                job.put("pubDate", m.getpubDate());
                
            } catch (UnsupportedEncodingException e) {
               // TODO Auto-generated catch block
               e.printStackTrace();
            }
            jarr.add(job);
         }
         json.put("list", jarr);
         
		return json.toJSONString();
	}

}
