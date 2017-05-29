package com.kh.gonggan.neighbor.model.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kh.gonggan.neighbor.model.vo.Neighbor;

@Repository ("neighborDao")
public class NeighborDao {
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	public NeighborDao(){}
	
	public List<Neighbor> selectNeighborList(String loggedinUser){
		System.out.println("neighbordao");
		return (List<Neighbor>) sqlSession.selectList("neighbormapper.neighborBlogList", loggedinUser);
	}
	
}
