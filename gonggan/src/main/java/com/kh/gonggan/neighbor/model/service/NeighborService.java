package com.kh.gonggan.neighbor.model.service;

import java.util.List;

import com.kh.gonggan.member.model.vo.Member;
import com.kh.gonggan.neighbor.model.vo.Neighbor;

public interface NeighborService {
	List<Member> selectNeighborList(String loginUser);

	List<Neighbor> NeighborList();
	List<Member> neighborSearch(String member_id, String member_id2);
}
