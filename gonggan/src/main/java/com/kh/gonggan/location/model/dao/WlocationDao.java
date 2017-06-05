package com.kh.gonggan.location.model.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kh.gonggan.location.model.vo.Wlocation;


@Repository("WlocationDao")

public class WlocationDao {
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	public WlocationDao(){}

	public List<Wlocation> selectWlocationList() {
		return (List<Wlocation>) sqlSession.selectList("wlocationmapper.wloclist");
	}
}
