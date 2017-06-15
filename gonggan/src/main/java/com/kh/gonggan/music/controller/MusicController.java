package com.kh.gonggan.music.controller;

import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.ResourceId;
import com.google.api.services.youtube.model.SearchListResponse;
import com.google.api.services.youtube.model.SearchResult;
import com.google.api.services.youtube.model.Thumbnail;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.kh.gonggan.music.model.vo.Music;
import com.kh.gonggan.news.model.vo.News;
import com.kh.gonggan.post.model.vo.PostMusic;
import com.kh.gonggan.post.model.vo.PostNews;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
//import java.util.Properties;
import java.util.Map;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MusicController {
	
	//private static String PROPERTIES_FILENAME = "youtube.properties";
	private static final HttpTransport HTTP_TRANSPORT = new NetHttpTransport();
	private static final JsonFactory JSON_FACTORY = new JacksonFactory();
	private static final long NUMBER_OF_VIDEOS_RETURNED = 25;
	private static YouTube youtube;
	
	@RequestMapping(value="/lyrics.do", produces={"text/plain;charset=UTF-8"})
	@ResponseBody
	public String lyrics(@RequestParam String title, String singer) {
		
		ArrayList<String> musicCodeList = new ArrayList<String>();
		String lyrics = null;
		
		Document document = null;
		
		try {
			document = Jsoup.connect("http://www.melon.com/search/lyric/index.htm?q="
					+ title + "&section=&searchGnbYn=Y&ipath=srch_form").get();

			Elements elements = document.select("dl.cntt_lyric");
			
			for(int i=0 ; i<elements.size()-1 ; i++){
				Element element = elements.get(i);
				musicCodeList.add(element.select("a").get(0).attr("data-song-no"));
			}
			
			document = Jsoup.connect("http://www.melon.com/song/detail.htm?songId="
					+ musicCodeList.get(0)).get();
			
			lyrics = document.select("div.lyric").text();
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		
		return lyrics;
	}
	
	@RequestMapping(value="/musicpost.do", produces="text/plain;charset=UTF-8", method=RequestMethod.GET)
	public ModelAndView musicpost(@RequestParam String keyword,
			ModelAndView mv){
		
		List<Music> searchMusicList = null;
		
		/*
		Properties properties = new Properties();
		
		try {
			InputStream in = MusicController.class.getResourceAsStream(
					"/" + PROPERTIES_FILENAME);
			properties.load(in);
		} catch (IOException e) {
			System.err.println("There was an error reading " + PROPERTIES_FILENAME + ": " + e.getCause()
	          + " : " + e.getMessage());
			System.exit(1);
		}
		*/
		try {
			
			youtube = new YouTube.Builder(HTTP_TRANSPORT, JSON_FACTORY, new HttpRequestInitializer() {
				public void initialize(HttpRequest request) throws IOException {}
			}).setApplicationName("youtube-cmdline-search-sample").build();
			
			//String queryTerm = getInputQuery();
			String queryTerm = keyword;
			
			YouTube.Search.List search = youtube.search().list("id,snippet");
			//String apiKey = properties.getProperty("youtube.apikey");
			String apiKey = "AIzaSyC7QrEyk_66z2x-d5l2jRkgpheT9yf1VLQ";
			search.setKey(apiKey);
			search.setQ(queryTerm);
			search.setType("video");
			search.setFields("items(id/kind,id/videoId,snippet/title,snippet/thumbnails/high/url"
					+ ",snippet/thumbnails/default/url)");
			search.setMaxResults(NUMBER_OF_VIDEOS_RETURNED);
			SearchListResponse searchResponse = search.execute();
			List<SearchResult> searchResultList = searchResponse.getItems();
			
			if (searchResultList != null)
				//prettyPrint(searchResultList.iterator(), queryTerm);
				searchMusicList = prettyPrint(searchResultList.iterator(), queryTerm);
			
		} catch (GoogleJsonResponseException e) {
			System.err.println("There was a service error: " + e.getDetails().getCode() + " : "
					+ e.getDetails().getMessage());
		} catch (IOException e) {
			System.err.println("There was an IO error: " + e.getCause() + " : " + e.getMessage());
		} catch (Throwable t) {
			t.printStackTrace();
	    }
		System.out.println(searchMusicList);
		
		mv.setViewName("searchAll");
		mv.addObject("searchMusicList", searchMusicList);
		mv.addObject("keyword", keyword);
		mv.addObject("category", 2);
		
		return mv;
	}
	@RequestMapping(value="/musicpost2.do", produces="application/json")
	@ResponseBody
	public String musicpost2(@RequestParam String keyword){
		
		List<Music> searchMusicList = new ArrayList<Music>();

		Map<String, Object> dataMap = new HashMap<String, Object>();
		
		try {
			
			youtube = new YouTube.Builder(HTTP_TRANSPORT, JSON_FACTORY, new HttpRequestInitializer() {
				public void initialize(HttpRequest request) throws IOException {}
			}).setApplicationName("youtube-cmdline-search-sample").build();
			
			//String queryTerm = getInputQuery();
			String queryTerm = keyword;
			
			YouTube.Search.List search = youtube.search().list("id,snippet");
			//String apiKey = properties.getProperty("youtube.apikey");
			String apiKey = "AIzaSyC7QrEyk_66z2x-d5l2jRkgpheT9yf1VLQ";
			search.setKey(apiKey);
			search.setQ(queryTerm);
			search.setType("video");
			search.setFields("items(id/kind,id/videoId,snippet/title,snippet/thumbnails/default/url)");
			search.setMaxResults(NUMBER_OF_VIDEOS_RETURNED);
			SearchListResponse searchResponse = search.execute();
			List<SearchResult> searchResultList = searchResponse.getItems();
			
			if (searchResultList != null)
				//prettyPrint(searchResultList.iterator(), queryTerm);
				searchMusicList = prettyPrint(searchResultList.iterator(), queryTerm);
			
		} catch (GoogleJsonResponseException e) {
			System.err.println("There was a service error: " + e.getDetails().getCode() + " : "
					+ e.getDetails().getMessage());
		} catch (IOException e) {
			System.err.println("There was an IO error: " + e.getCause() + " : " + e.getMessage());
		} catch (Throwable t) {
			t.printStackTrace();
	    }
		System.out.println(searchMusicList);
		
		dataMap.put("list", searchMusicList); 
		
		return new Gson().toJson(dataMap);
	}

	
	private static String getInputQuery() throws IOException {
		
		String inputQuery = "";
		
		System.out.print("Please enter a search term: ");
		BufferedReader bReader = new BufferedReader(new InputStreamReader(System.in));
		inputQuery = bReader.readLine();
		
		if (inputQuery.length() < 1)
		// If nothing is entered, defaults to "YouTube Developers Live."
			inputQuery = "YouTube Developers Live";
		
		return inputQuery;
		
	}
	
	private static List<Music> prettyPrint(Iterator<SearchResult> iteratorSearchResults, 
			String query) {

		List<Music> searchMusicList = new ArrayList<Music>();
		Thumbnail thumbnail_default = null;
		Thumbnail thumbnail_high = null;
		
		/*
		System.out.println("\n=============================================================");
		System.out.println(
				"   First " + NUMBER_OF_VIDEOS_RETURNED
				+ " videos for search on \"" + query + "\".");
		System.out.println("=============================================================\n");
		 
		
		if (!iteratorSearchResults.hasNext())
			System.out.println(" There aren't any results for your query.");
		*/
		
		while (iteratorSearchResults.hasNext()) {

			SearchResult singleVideo = iteratorSearchResults.next();
			ResourceId rId = singleVideo.getId();

			// Double checks the kind is video.
			if (rId.getKind().equals("youtube#video")) {
				thumbnail_default = singleVideo.getSnippet().getThumbnails().get("default");
				thumbnail_high = singleVideo.getSnippet().getThumbnails().get("high");
			}
			
			/*
			System.out.println(" Video Id" + rId.getVideoId());
			System.out.println(" Title: " + singleVideo.getSnippet().getTitle());
			System.out.println(" Thumbnail: " + thumbnail.getUrl());
			System.out.println("\n-------------------------------------------------------------\n");
			 */
		
			try {
				searchMusicList.add(new Music(rId.getVideoId(), 
						URLEncoder.encode(
								singleVideo.getSnippet().getTitle(), "UTF-8"),
						thumbnail_default.getUrl(), 
						(thumbnail_high != null ? thumbnail_high.getUrl() : "")));
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		return searchMusicList;
	}
	    
}
