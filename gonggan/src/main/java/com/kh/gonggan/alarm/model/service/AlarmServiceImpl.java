package com.kh.gonggan.alarm.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.gonggan.alarm.model.dao.AlarmDao;

@Service("alarmService")
public class AlarmServiceImpl implements AlarmService {

	@Autowired
	AlarmDao alarmDao;
	
	public int checkAlarm(String loginUser) {
		return alarmDao.checkAlarm(loginUser);
	}
}
