package com.kh.gonggan.blog.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
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
import com.kh.gonggan.blog.model.vo.Blog;
import com.kh.gonggan.member.model.vo.Member;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

@Controller
public class BlogController {

	// 메소드가 controller가 됨 컨트롤러를 메소드 단위로 작성하면 된다.
	// 공통으로 사용하는 것은 common에 넣어놓으면 됨
	@Autowired
	private BlogService blogService;
	
	@RequestMapping(value = "/bvisit.do", method = RequestMethod.GET)
 		@ResponseBody
		public String blogvisit(@RequestParam String blog_id, @RequestParam String visitor_id) {
			if (blog_id.equals("") || blogService.checkVisit(blog_id, visitor_id) == 0)
				blogService.blogvisit(blog_id, visitor_id);
			return "";
		}

	@RequestMapping(value = "/selectVisitorList.do", produces = { "application/json" }, method = RequestMethod.GET)
	 	@ResponseBody
			public String selectVisitorList(@RequestParam String writer_id) {
		
				List<Member> VisitorList = blogService.selectVisitorList(writer_id);

				JSONObject json = new JSONObject();
				JSONArray jarr = new JSONArray();
		
				for (Member m : VisitorList) {
		
					JSONObject job = new JSONObject();
					job.put("member_id", m.getMember_id());
					job.put("gender", m.getMember_gender());
					job.put("birth", m.getMember_birth()+"");
					
					jarr.add(job);
				}
				json.put("list", jarr);
		
				return json.toJSONString();

			}
	@RequestMapping(value = "/selectMonVisitorList.do", produces = { "application/json" }, method = RequestMethod.GET)
 	@ResponseBody
		public String selectMonVisitorList(@RequestParam String writer_id) {
	
			List<Member> VisitorList = blogService.selectMonVisitorList(writer_id);

			JSONObject json = new JSONObject();
			JSONArray jarr = new JSONArray();
	
			for (Member m : VisitorList) {
	
				JSONObject job = new JSONObject();
				job.put("member_id", m.getMember_id());
				job.put("gender", m.getMember_gender());
				job.put("birth", m.getMember_birth()+"");
				
				jarr.add(job);
			}
			json.put("list", jarr);
	
			return json.toJSONString();

		}
	
	@RequestMapping(value = "/selectNeigborVisitorList.do", produces = { "application/json" }, method = RequestMethod.GET)
	 	@ResponseBody
		public String selectNeigborVisitorList(@RequestParam String writer_id) {
			
			List<Member> VisitorList = blogService.selectNeigborVisitorList(writer_id);
	
			JSONObject json = new JSONObject();
			JSONArray jarr = new JSONArray();
	
			for (Member m : VisitorList) {
	
				JSONObject job = new JSONObject();
	
				job.put("member_id", m.getMember_id());
	
				jarr.add(job);
			}
			json.put("list", jarr);
	
			return json.toJSONString();
		}
	@RequestMapping(value = "/selectMonNeigborVisitorList.do", produces = { "application/json" }, method = RequestMethod.GET)
 	@ResponseBody
	public String selectMonNeigborVisitorList(@RequestParam String writer_id) {
		
		List<Member> VisitorList = blogService.selectMonNeigborVisitorList(writer_id);

		JSONObject json = new JSONObject();
		JSONArray jarr = new JSONArray();

		for (Member m : VisitorList) {

			JSONObject job = new JSONObject();

			job.put("member_id", m.getMember_id());

			jarr.add(job);
		}
		json.put("list", jarr);

		return json.toJSONString();
	}
	@RequestMapping(value = "/selectMonNeiList.do", produces = { "application/json" }, method = RequestMethod.GET)
 		@ResponseBody
			public String selectMonNeiList(String writer_id) {
				
				List<Member> MonNeiList = blogService.selectMonNeiList(writer_id);
		
				JSONObject json = new JSONObject();
				JSONArray jarr = new JSONArray();
		
				for (Member m : MonNeiList) {
		
					JSONObject job = new JSONObject();
		
					job.put("member_id", m.getMember_id());
		
					jarr.add(job);
				}
				json.put("list", jarr);
		
				return json.toJSONString();
			}
	
	@RequestMapping(value = "bsetting.do", method = RequestMethod.POST)
	public ModelAndView blogSetting(MultipartHttpServletRequest/*HttpServletRequest*/ req,
			ModelAndView mv, HttpSession session) {
		// 왜 HttpServletRequest 사용하면 
		// new MultipartRequest 부분에서 Corrupt form data: premature ending 발생하는가 

		String writer_id = ((Member) session.getAttribute("loginUser")).getMember_id();
		
		int maxSize = 1024 * 1024 * 10;
		
		MultipartRequest multiRequest;
		MultipartFile file = req.getFile("file");
		
		InputStream in = null;
		FileOutputStream fos = null;
		File renameFile = null;
		String  renameFileName = null;
		String originalFileName = blogService.selectBlog(writer_id).getBackground();
		String[] originalFileNameSplit;
		File copy, existFile;

		long current = System.currentTimeMillis();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		
		String root = req.getSession().getServletContext().getRealPath("/");

		if (file.isEmpty()) {
			if (originalFileName != null) {
				existFile = new File(
						"/Users/jiseung/git/gonggan2/gonggan/src/main/webapp/backgroundImages/"
						+ originalFileName);
				if(existFile.exists())
					if(existFile.delete()) {
		                System.out.println("파일삭제 성공");
		                renameFileName = "";
					}
			}
		}
		
		else {
		
			if (originalFileName != null) {
				existFile = new File(
						"/Users/jiseung/git/gonggan2/gonggan/src/main/webapp/backgroundImages/"
						+ originalFileName);
				if(existFile.exists())
					if(existFile.delete()) {
		                System.out.println("파일삭제 성공");
		                renameFileName = "";
					}
			}
			
			System.out.println("root : " + root);
			String[] roots = root.split("\\\\");
			String marger="";
	  
			for(int i=0 ; i<roots.length-3; i++)
				marger += roots[i] + "\\";
		         
		    marger = "/Users/jiseung/git/gonggan2/gonggan/"; 
			System.out.println("marger : " + marger);
			String savePath = marger + "src/main/webapp/backgroundImages/";
			System.out.println("savepath : " + savePath);
			
			originalFileName = file.getOriginalFilename();

			try {
				
				if (req.getParameter("bgmode").equals("img")) {
					
					if (req.getParameter("bgchanged").equals("Y")) {
					
						copy = new File(
								
								"/Users/jiseung/git/gonggan2/gonggan/src/main/webapp/backgroundImages/"
										+ "bg" + sdf.format(new Date(current))
										+ ("." + (originalFileNameSplit =
										originalFileName.split("\\."))[originalFileNameSplit.length-1]));
						
						file.transferTo(copy);
					
						renameFileName = copy.getName();
						
						if (blogService.blogSetting_background(new Blog(req.getParameter("blogTitle"),
								writer_id,
								req.getParameter("blogComment"), renameFileName,
								req.getParameter("color"),
								req.getParameter("background_color"),
								req.getParameter("contents_color"),
								req.getParameter("languages"),
								req.getParameter("blogOpenYn"),
								req.getParameter("diaryOpenYn"),
								req.getParameter("placeOpenYn"),
								req.getParameter("reviewOpenYn"),
								req.getParameter("musicOpenYn"),
								req.getParameter("movieOpenYn"),
								req.getParameter("newsOpenYn"),
								req.getParameter("hideComment"))) > 0)
							System.out.println("성공");
					}
				}
				else if (req.getParameter("bgmode").equals("color"))
					if (blogService.blogSetting_color(new Blog(req.getParameter("blogTitle"),
							writer_id,
							req.getParameter("blogComment"), renameFileName,
							req.getParameter("color"),
							req.getParameter("background_color"),
							req.getParameter("contents_color"),
							req.getParameter("languages"),
							req.getParameter("blogOpenYn"),
							req.getParameter("diaryOpenYn"),
							req.getParameter("placeOpenYn"),
							req.getParameter("reviewOpenYn"),
							req.getParameter("musicOpenYn"),
							req.getParameter("movieOpenYn"),
							req.getParameter("newsOpenYn"),
							req.getParameter("hideComment"))) > 0)
						System.out.println("성공");
				
				/*
				multiRequest  = new MultipartRequest(
						req, savePath, maxSize, "UTF-8", new DefaultFileRenamePolicy());
				originalFileName = multiRequest.getFilesystemName("file");
				
				System.out.println("originalFileName : " + originalFileName);
				
				if (originalFileName != null)
					renameFileName = "bbg" + sdf.format(new Date(current))
							+ ("." + (originalFileNameSplit = originalFileName.split("\\."))[originalFileNameSplit.length-1]);

				int input = 0;
				int cnt = 0;
				byte[] data = new byte[1024];
	            
				in = req.getInputStream();
				fos = new FileOutputStream(renameFile = new File(savePath + renameFileName));
				while((input = in.read(data)) != -1) {
					fos.write(data, 0, input);
					cnt += input;
					fos.flush();
				}
				
				
				System.out.println(multiRequest.getParameter("background_color"));
				System.out.println(renameFileName);
				System.out.println(multiRequest.getParameter("bgmode"));
	            
				if(multiRequest.getParameter("bgmode").equals("color")){
					blogService.blogSetting_color(
							new Blog(multiRequest.getParameter("blogTitle"),
									((Member) session.getAttribute("loginUser")).getMember_id(),
									multiRequest.getParameter("blogComment"),
									renameFileName,
									multiRequest.getParameter("color"),
									multiRequest.getParameter("background_color"),
									multiRequest.getParameter("contents_color"),
									multiRequest.getParameter("languages"),
									multiRequest.getParameter("blogOpenYn"),
									multiRequest.getParameter("diaryOpenYn"),
									multiRequest.getParameter("placeOpenYn"),
									multiRequest.getParameter("reviewOpenYn"),
									multiRequest.getParameter("musicOpenYn"),
									multiRequest.getParameter("movieOpenYn"),
									multiRequest.getParameter("newsOpenYn")));
				}
				else {
					blogService.blogSetting_background(
							new Blog(multiRequest.getParameter("blogTitle"),
									writer_id,
									multiRequest.getParameter("blogComment"),
									renameFileName,
									multiRequest.getParameter("color"),
									multiRequest.getParameter("background_color"),
									multiRequest.getParameter("contents_color"),
									multiRequest.getParameter("languages"),
									multiRequest.getParameter("blogOpenYn"),
									multiRequest.getParameter("diaryOpenYn"),
									multiRequest.getParameter("placeOpenYn"),
									multiRequest.getParameter("reviewOpenYn"),
									multiRequest.getParameter("musicOpenYn"),
									multiRequest.getParameter("movieOpenYn"),
									multiRequest.getParameter("newsOpenYn")));
					System.out.println("asdftest");
				}
				*/
				/*if (blogService.blogSetting(
					new Blog(multiRequest.getParameter("blogTitle"),
					((Member) session.getAttribute("loginUser")).getMember_id(),
	                 multiRequest.getParameter("blogComment"), renameFileName,
	                 multiRequest.getParameter("color"),
	                 multiRequest.getParameter("background_color"),
	                        multiRequest.getParameter("contents_color"),
	                        multiRequest.getParameter("languages"),
	                        multiRequest.getParameter("blogOpenYn"),
	                        multiRequest.getParameter("diaryOpenYn"),
	                        multiRequest.getParameter("placeOpenYn"),
	                        multiRequest.getParameter("reviewOpenYn"),
	                        multiRequest.getParameter("musicOpenYn"),
	                        multiRequest.getParameter("movieOpenYn"),
	                        multiRequest.getParameter("newsOpenYn"))) > 0)
	               
	               System.out.println("성공");
				 */
				
				//in.close();
				//fos.close();
	            
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
			
		mv.setViewName("redirect:controll.do?writer_id=" + writer_id);
		return mv;
		
	}
}
