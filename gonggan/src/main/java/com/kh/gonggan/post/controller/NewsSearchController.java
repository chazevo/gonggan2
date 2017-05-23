package com.kh.gonggan.post.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.annotation.ModelAndViewResolver;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.kh.gonggan.post.model.vo.Post;

import java.lang.reflect.Type;


@Controller
public class NewsSearchController{
	
	@RequestMapping(value="/NewsSearchController.do", produces={"application/json"}, method=RequestMethod.GET)
	@ResponseBody
    public String NewsSearchController(@RequestParam String keyword) {
		
		//json 형태의 String이 넘어 왔을 때, List로 변경 할 때는 Gson을 사용한다.
		//TestVO는 json에 key에 해당 하는 값들이 선언 되어 있는 VO 객체를 넣어준다.

		Gson gson = new Gson();
		Type type = new TypeToken<List<Post>>(){}.getType();          
		                        
		List<Post> testList  = null;
		
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
            }
            br.close();
            
            testList = gson.fromJson(response_.toString(), type);
            
            System.out.println(response_.toString());
            
        } catch (Exception e) {
            System.out.println(e);
        }
		return response_.toString();

    }

}
