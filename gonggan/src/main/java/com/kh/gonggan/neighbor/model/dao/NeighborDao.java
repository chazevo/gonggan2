package com.kh.gonggan.neighbor.model.dao;

import java.util.List;

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

	public List<Neighbor> selectNeighborAll() {
		return (List<Neighbor>) sqlSession.selectList("neighbormapper.neighborList");
	}
	
}
