package com.kh.gonggan.neighbor.model.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kh.gonggan.member.model.vo.Member;
import com.kh.gonggan.neighbor.model.vo.Neighbor;

@Repository ("neighborDao")
public class NeighborDao {
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	public NeighborDao(){}
	
	public List<Member> selectNeighborList(String loginUser){
		System.out.println("neighbordao");
		return (List<Member>) sqlSession.selectList("membermapper.neighborBlogList", loginUser);
	}

	public int requestNeig(String member_id1, String member_id2) {
	      HashMap<String, String> map = new HashMap<String, String>();
	      map.put("member_id1", member_id1);
	      map.put("member_id2", member_id2);
	      return sqlSession.insert("neighbormapper.nrequest", map);
	   }
	   public List<Neighbor> neigList(String loginUser, String writer_id) {
	      Map<String, String> map = new HashMap<String, String>();
	      map.put("loginUser", loginUser);
	      map.put("writer_id", writer_id);
	      return (List<Neighbor>) sqlSession.selectList("neighbormapper.neigList",map);
	   }
	
	public List<Member> neighborSearch(String member_id, String member_id2) {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("member_id", member_id);
		map.put("member_id2", member_id2);
		return (List<Member>) sqlSession.selectList("membermapper.nsearch", map);
		
	}

	public String neighYn(String loginUser, String writer_id) {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("loginUser", loginUser);
		map.put("writer_id", writer_id);
		return (sqlSession.selectOne(
				"neighbormapper.neighyn", map) == null ?
				"N" : "Y");
	}
}
