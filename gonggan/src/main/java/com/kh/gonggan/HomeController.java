package com.kh.gonggan;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "start.do", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! ");
		return "home";
	}
	
	@RequestMapping(value = "index2.do", method = RequestMethod.GET)
	public String index2(Locale locale, Model model) {
		logger.info("Welcome index2! ");
		return "index2";
	}
	
	@RequestMapping(value = "findIdPwd.do", method = RequestMethod.GET)
	public String findIdPwd(Locale locale, Model model) {
		logger.info("Welcome findIdPwd! ");
		return "findIdPwd";
	}
	
	@RequestMapping(value = "controll.do", method = RequestMethod.GET)
	public String controll(Locale locale, Model model) {
		logger.info("Welcome controll! ");
		return "controll";
	}
	
	@RequestMapping(value = "myhome.do", method = RequestMethod.GET)
	public String myhome(Locale locale, Model model) {
		logger.info("Welcome myhome! ");
		return "myhome";
	}
	
	@RequestMapping(value = "mypage.do", method = RequestMethod.GET)
	public String mypage(Locale locale, Model model) {
		logger.info("Welcome mypage! ");
		return "mypage";
	}

	@RequestMapping(value = "uploadform.do", method = RequestMethod.GET)
	public String uploadform(Locale locale, Model model) {
		logger.info("Welcome uploadform! ");
		return "uploadform";
	}

	@RequestMapping(value = "uploadHtml.do", method = RequestMethod.GET)
	public String uploadHtml(Locale locale, Model model) {
		logger.info("Welcome uploadHtml! ");
		return "uploadHtml";
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

}
