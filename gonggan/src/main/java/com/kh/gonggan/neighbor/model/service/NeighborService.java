package com.kh.gonggan.neighbor.model.service;

import java.util.List;

import com.kh.gonggan.member.model.vo.Member;
import com.kh.gonggan.neighbor.model.vo.Neighbor;

public interface NeighborService {
	List<Member> selectNeighborList(String loginUser);

	List<Member> neighborSearch(String member_id, String member_id2);
	List<Neighbor> neigList(String loginUser, String writer_id);
	int requestNeig(String member_id1, String member_id2);
}
