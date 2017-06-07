package com.kh.gonggan.book.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.kh.gonggan.book.model.vo.Book;
import com.kh.gonggan.post.model.vo.PostBook;

@Controller
public class BookController {

		//메소드가 controller가 됨 컨트롤러를 메소드 단위로 작성하면 된다.
		//공통으로 사용하는 것은 common에 넣어놓으면 됨
	
	@RequestMapping(value="/booksearch2.do", produces={"application/json"})
	@ResponseBody
	public String searchBook2(@RequestParam String keyword, HttpSession session) {

		List<Book> searchBookList = new ArrayList<Book>();

		JSONObject json = new JSONObject();
		JSONArray jarr = new JSONArray();
     
		Gson gson = new Gson();
		Type type = new TypeToken<PostBook>(){}.getType();       
                          
		String clientId = "wby5y_qdDk0ASqaBNnEt"; //애플리케이션 클라이언트 아이디값";
		String clientSecret = "bmJftKj85P"; //애플리케이션 클라이언트 시크릿값";
		StringBuffer response_ = null;
  
		try {
			
			String text = URLEncoder.encode(keyword, "UTF-8");
			String apiURL = "https://openapi.naver.com/v1/search/book?query="+ text; // json 결과
			//String apiURL = "https://openapi.naver.com/v1/search/blog.xml?query="+ text; // xml 결과
 
			URL url = new URL(apiURL);
			HttpURLConnection con = (HttpURLConnection)url.openConnection();
 
			con.setRequestMethod("GET");
			con.setRequestProperty("X-Naver-Client-Id", clientId);
			con.setRequestProperty("X-Naver-Client-Secret", clientSecret);
 
			int responseCode = con.getResponseCode();
 
			BufferedReader br;
			
			if(responseCode == 200) { // 정상 호출
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
	      
	      List<Book> result = ((PostBook) gson.fromJson(response_.toString(), type)).getItems();

	      for(Book b : result) {
	            
	            JSONObject job = new JSONObject();
	            
	            try {

	                job.put("image", b.getImage());
	                job.put("title", URLEncoder.encode(
	                   b.getTitle(), "UTF-8"));
	                job.put("author", URLEncoder.encode(
	                       b.getAuthor(), "UTF-8"));
	                job.put("publisher", URLEncoder.encode(
	                       b.getPublisher(), "UTF-8"));
	                job.put("pubdate", URLEncoder.encode(
	                       b.getPubdate(), "UTF-8"));
	                
	                
	            } catch (Exception e) {
	               // TODO Auto-generated catch block
	               e.printStackTrace();
	            }
	            jarr.add(job);
	         }
	         json.put("list", jarr);
	         
	      return json.toJSONString();
	      
	   }
	
		@RequestMapping(value="/booksearch.do", method=RequestMethod.GET)
		public ModelAndView searchBook(ModelAndView mv, @RequestParam String keyword, HttpSession session){
			
			JSONObject job = new JSONObject();
			
			Gson gson = new Gson();
			Type type = new TypeToken<PostBook>(){}.getType();          
			                        
			//List<Movie> searchMovieList  = movieService.searchMovie();
			List<Book> searchBookList = new ArrayList<Book>();
			
			String clientId = "wby5y_qdDk0ASqaBNnEt"; //애플리케이션 클라이언트 아이디값";
			String clientSecret = "bmJftKj85P"; //애플리케이션 클라이언트 시크릿값";
			StringBuffer response_ = null;
			
			try {
				
				String text = URLEncoder.encode(keyword, "UTF-8");
				String apiURL = "https://openapi.naver.com/v1/search/book?query="+ text; // json 결과
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
					//System.out.println(inputLine);
	            }
	            br.close();
	            
	            
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
			
			List<Book> result = ((PostBook) gson.fromJson(response_.toString(), type)).getItems();

			mv.setViewName("searchAll");
			mv.addObject("searchBookList", result);
			mv.addObject("keyword", keyword);
			mv.addObject("category", 0);
			
			return mv;
		}
}
