package com.kh.gonggan.location.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.kh.gonggan.location.model.service.WlocationService;
import com.kh.gonggan.location.model.vo.Location;
import com.kh.gonggan.location.model.vo.Wlocation;
import com.kh.gonggan.post.model.vo.PostLocation;

@Controller
public class LocationController {
	
	@Autowired
	private WlocationService wlocationService;
	
	@RequestMapping(value="/wloc.do", produces={"application/json"})
	@ResponseBody
	public String selectWlocation() {
		
		List<Wlocation> wlocList = wlocationService.selectWlocationList();

		JSONObject json = new JSONObject();
		JSONArray jarr = new JSONArray();
		
		if (wlocList != null) {
			
			for(Wlocation wloc : wlocList) {
				
				JSONObject job = new JSONObject();
				
				try {
					
					job.put("city", URLEncoder.encode(wloc.getCity() + "", "UTF-8"));
					job.put("lat", wloc.getLat());
					job.put("lon", wloc.getLon());

				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				jarr.add(job);
			}
			json.put("list", jarr);
		}
		
		return json.toJSONString();
	}
	
	@RequestMapping(value="/locationsearch.do", method=RequestMethod.GET)
	public ModelAndView searchLocation(ModelAndView mv, @RequestParam String keyword, HttpSession session){
		
		JSONObject job = new JSONObject();
		
		Gson gson = new Gson();
		Type type = new TypeToken<PostLocation>(){}.getType();          
		                        
		//List<Movie> searchMovieList  = movieService.searchMovie();
		List<Location> searchLocationList = new ArrayList<Location>();
		
		String clientId = "wby5y_qdDk0ASqaBNnEt"; //애플리케이션 클라이언트 아이디값";
		String clientSecret = "bmJftKj85P"; //애플리케이션 클라이언트 시크릿값";
		StringBuffer response_ = null;
		
		try {
			
			String text = URLEncoder.encode(keyword, "UTF-8");
			String apiURL = "https://openapi.naver.com/v1/search/local?query="+ text; // json 결과
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
		
		List<Location> result = ((PostLocation) gson.fromJson(response_.toString(), type)).getItems();

		mv.setViewName("map");
		mv.addObject("searchLocationList", result);
		mv.addObject("keyword", keyword);
		
		return mv;
	}

}
