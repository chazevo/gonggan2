package com.kh.gonggan.alarm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kh.gonggan.alarm.model.service.AlarmService;

@Controller
public class AlarmController {
	@Autowired
	private AlarmService alarmService;
	
	@RequestMapping(value = "/checkalarm.do", method = RequestMethod.GET)
		@ResponseBody
	public int checkAlarm(@RequestParam String loginUser) {
		
		return alarmService.checkAlarm(loginUser);
	}
}
