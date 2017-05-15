package com.kh.gonggan.member.model.service;

import java.util.List;

import com.kh.gonggan.member.model.vo.Member;

public interface MemberService {
	
	Member loginCheck(Member vo);

	List<Member> memberList();

	int insertMember(Member mem);

	Member selectMember(String memberId);

	int deleteMember(String memberId);

	int updateMember(Member mem);

	Member selectId(String email);

	Member selectPw(String memberId, String email);

}
