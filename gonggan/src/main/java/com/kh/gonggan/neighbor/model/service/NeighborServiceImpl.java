package com.kh.gonggan.neighbor.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.gonggan.member.model.vo.Member;
import com.kh.gonggan.neighbor.model.dao.NeighborDao;
import com.kh.gonggan.neighbor.model.vo.Neighbor;

@Service("neighborService")
public class NeighborServiceImpl implements NeighborService{
	@Autowired
	NeighborDao neighborDao;

	@Override
	public List<Member> selectNeighborList(String loginUser) {
		return neighborDao.selectNeighborList(loginUser); 
	}
	
	@Override
	public int requestNeig(String member_id1, String member_id2){
		return neighborDao.requestNeig(member_id1, member_id2);
	}
	   
	@Override
	public List<Neighbor> neigList(String loginUser, String writer_id){
		return neighborDao.neigList(loginUser,writer_id);
	}
	
	@Override
	public List<Member> neighborSearch(String member_id, String member_id2) {
		return neighborDao.neighborSearch(member_id, member_id2);
	}
	@Override
	public String neighYn(String loginUser, String writer_id) {
		return neighborDao.neighYn(loginUser, writer_id);
	}
}
