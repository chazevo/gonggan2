package com.kh.gonggan.member.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.gonggan.member.model.dao.MemberDao;
import com.kh.gonggan.member.model.vo.Member;

@Service("memberService")
public class MemberServiceImpl implements MemberService{
	//현재 클래스를 스프링에서 관리하는 service bean으로 등록
	@Autowired
	MemberDao memberDao; //객체를 스프링에서 생성하여 주입시킴
	
	@Override
	public Member loginCheck(Member vo) {
		System.out.println("service : loginCheck run...");
		return  memberDao.loginCheck(vo);
		
	}//로그인
	
	@Override
	public List<Member> memberList(){
		return memberDao.memberList();
	}//전체 회원 조회
	
	@Override
	public int insertMember(Member mem){
		 return memberDao.insertMember(mem);
	}//회원등록
	
	@Override
	public Member selectMember(String memberId){
		return memberDao.selectMember(memberId);
	}//회원 정보 상세 조회
	
	@Override
	public int deleteMember(String memberId){
		return memberDao.deleteMember(memberId);
	}//회원 삭제
	
	@Override
	public int updateMember(Member mem){
		return memberDao.updateMember(mem);
	}//회원 수정
	
	@Override
	public Member selectId(String email){
		return memberDao.selectId(email);
	}//아이디 찾기
	
	@Override
	public Member selectPw(String memberId, String email){
		return memberDao.selectPw(memberId, email);
	}
	

}
