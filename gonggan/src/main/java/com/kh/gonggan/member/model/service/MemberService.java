package com.kh.gonggan.member.model.service;

import java.util.List;
import java.util.Map;

import com.kh.gonggan.member.model.vo.Member;

public interface MemberService {

	Member loginCheck(Member vo);

	List<Member> memberList();

	int insertMember(Member member);

	Member selectMember(String memberId);

	int deleteMember(String memberId);

	int updateMember(Member mem);
	
	int updateProfile(Member mem);

	String selectIdByEmail(String email);
	/*
	 * Member selectPw(String memberId, String email);
	 */

	String getPw(Map<String, Object> paramMap);

	int requestNeig(String memberId);

	int acceptNeigh(String memberId, String memberId2);

	int rejectNeigh(String memberId, String memberId2);

	List<Member> checkNeig(String memberId);

	List<Member> neigList();

	int naverConnect(Member mem);

	int kakaoConnect(Member mem);

	Member naverIdCheck(Member member);

	Member kakaoIdCheck(Member member);

	Member facebookIdCheck(Member member);

	int facebookConnect(Member member);
	
	int neigDelete(String memberId, String memberId2);

	Member joinIdCheck(String member_id);

	Member joinEmailCheck(String email);

	
}
