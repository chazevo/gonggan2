package com.kh.gonggan.place.model.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kh.gonggan.place.model.vo.Place;

@Repository("placeDao")
public class PlaceDao {
	@Autowired
	private SqlSessionTemplate sqlSession;
	public PlaceDao(){}
	
	public List<Place> searchFree() {
		//System.out.println("dao: selectLogin run...");
		//return (List<Movie>) sqlSession.selectOne("moviemapper.loginSelect");
		return null;
	}
	
	public List<Place> selectAll(int rownum, int rownum2) {
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("rownum", rownum);
		map.put("rownum2", rownum2);
		return (List<Place>) sqlSession.selectList("placemapper.placelist");
	}
	
	public List<Place> selectAll_index2(){
		return (List<Place>) sqlSession.selectList("placemapper.placelist_index2");
	}
	
	public List<Place> selectAll_myhome(String writer_id) {
		return (List<Place>) sqlSession.selectList(
				"placemapper.placelist_myhome", writer_id);
	}

	public Place newsDetail(int postId) {
		return (Place) sqlSession.selectOne("placemapper.placedetail", postId);
	}
}
