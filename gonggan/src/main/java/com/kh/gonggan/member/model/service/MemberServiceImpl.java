package com.kh.gonggan.member.model.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.gonggan.comment.model.vo.Comment;
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
	public int insertMember(Member member){
		 return memberDao.insertMember(member);
	}//회원등록
	
	@Override
	public Member selectMember(String memberId){
		return memberDao.selectMember(memberId);
	}//회원 정보 상세 조회
	
	@Override
	public int deleteMember(String memberId) {
		return memberDao.deleteMember(memberId);
	}//회원 삭제
	
	@Override
	public int updateMember(Member mem) {
		return memberDao.updateMember(mem);
	}//회원 수정
	
	@Override
	public int updateProfile(Member mem) {
		return memberDao.updateProfile(mem);
	}
	
	@Override
	public String selectIdByEmail(String email) {
		return memberDao.selectIdbyEmail(email);
	}
	@Override
	public String getPw(Map<String, Object> paramMap){
		return memberDao.getPw(paramMap);
	}
	@Override
	public int requestNeig(String memberId){
		return memberDao.requestNeig(memberId);
	}//이웃신청
	
	@Override
	public int acceptNeigh(String memberId, String memberId2) {
		return memberDao.acceptNeigh(memberId, memberId2);
	}
	
	@Override
	public int rejectNeigh(String memberId, String memberId2) {
		return memberDao.rejectNeigh(memberId, memberId2);
	}
	
	@Override
	public List<Member> checkNeig(String memberId){
		return memberDao.checkNeig(memberId);
	}
	
	@Override
	public List<Member> neigList(){
		return memberDao.neigList();
	}//이웃 신청 목록 조회 여기서 하는게 맞는건지 잘모르겟어여,,,ㅠㅠ
	
	@Override
	public int naverConnect(Member mem){
		return memberDao.naverConnect(mem);
	}//네이버연동
	
	@Override
	public int kakaoConnect(Member mem){
		return memberDao.kakaoConnect(mem);
	}//카카오연동\
	
	@Override
	public int facebookConnect(Member mem){
		return memberDao.facebookConnect(mem);
	}
	
	@Override
	public Member naverIdCheck(Member mem){
		System.out.println("service.naverIdCheck");
		return memberDao.naverIdCheck(mem);
	}//네이버아이디중복체크
	
	public Member kakaoIdCheck(Member mem){
		System.out.println("service.kakaoIdCheck");
		return memberDao.kakaoIdCheck(mem);
	}//카카오아이디중복체크
	
	public Member facebookIdCheck(Member mem){
		System.out.println("service.facebookIdCheck");
		return memberDao.facebookIdCheck(mem);
	}
	
	@Override
	public int neigDelete(String memberId, String memberId2){
		return memberDao.neigDelete(memberId, memberId2);
	}
	@Override
	public Member joinIdCheck (String member_id) {
		return memberDao.joinIdCheck(member_id);
	}
	@Override
	public Member joinEmailCheck (String email) {
		return memberDao.joinEmailCheck(email);
	}
}
