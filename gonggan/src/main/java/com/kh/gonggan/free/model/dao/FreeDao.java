package com.kh.gonggan.free.model.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kh.gonggan.free.model.vo.Free;

@Repository("freeDao")
public class FreeDao {
	@Autowired
	private SqlSessionTemplate sqlSession;
	public FreeDao(){}
	
	public List<Free> searchFree() {
		//System.out.println("dao: selectLogin run...");
		//return (List<Movie>) sqlSession.selectOne("moviemapper.loginSelect");
		return null;
	}
	
	public List<Free> selectAll(int rownum, int rownum2) {
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("rownum", rownum);
		map.put("rownum2", rownum2);
		return (List<Free>) sqlSession.selectList("freemapper.freelist");
	}
	
	public List<Free> selectAll_index2(){
		return (List<Free>) sqlSession.selectList("freemapper.freelist_index2");
	}
	
	public List<Free> selectAll_myhome(String writer_id) {
		return (List<Free>) sqlSession.selectList(
				"freemapper.freelist_myhome", writer_id);
	}

	public Free freeDetail(int postId) {
		return (Free) sqlSession.selectOne("freemapper.freedetail", postId);
	}
}