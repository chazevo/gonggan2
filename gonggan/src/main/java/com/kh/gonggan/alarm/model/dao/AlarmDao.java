package com.kh.gonggan.alarm.model.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("alarmDao")
public class AlarmDao {
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	public AlarmDao(){}
	

	public int checkAlarm(String loginUser) {
		int res = 0;
		if ((int) sqlSession.selectOne("commentmapper.acheck", loginUser) > 0)
			res = (int) sqlSession.selectOne("goodmapper.acheck", loginUser);
		return res;
	}
	
}
