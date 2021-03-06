package com.kh.gonggan.member.model.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kh.gonggan.alarm.model.vo.Alarm;
import com.kh.gonggan.member.model.vo.Member;

@Repository("memberDao")
public class MemberDao {
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	public MemberDao(){}

	public Member loginCheck(Member loginVo) {
		//System.out.println("dao: selectLogin run...");
		return (Member) sqlSession.selectOne("membermapper.loginSelect", loginVo);
	}
	
	public Member joinIdCheck (String member_id) {
		return (Member) sqlSession.selectOne("membermapper.joinidcheck",member_id);
	}
	public Member joinEmailCheck (String email) {
		return (Member) sqlSession.selectOne("membermapper.joinemailcheck", email);
	}
	
	public int insertMember(Member member){
		return sqlSession.insert("membermapper.minsert", member);
	}//회원 등록
	
	public List<Member> memberList(){
		return (List<Member>) sqlSession.selectList("membermapper.mlist");
	}//회원 전제 조회
	
	public Member selectMember(String memberId){
		return (Member) sqlSession.selectOne("membermapper.selectMember", memberId);
	}//회원 정보 상세 조회
	
	public int deleteMember(String memberId)
	{
		return sqlSession.delete("membermapper.mdelete", memberId);
	}//회원 정보 삭제
	
	public int updateMember(Member mem){
		System.out.println("asdf");
		System.out.println(mem);
		return sqlSession.update("membermapper.mupdate", mem);
	}//회원 정보 수정
	
	public int updateProfile(Member mem) {
		return sqlSession.update("membermapper.profileupdate", mem);
	}
	
	public String selectIdbyEmail(String email){
		return (String) sqlSession.selectOne("membermapper.selectIdbyEmail", email);
	} //아이디 찾기
	
	public int requestNeig(String memberId) {
		// TODO Auto-generated method stub
		return  sqlSession.insert("membermapper.requestNeig", memberId);
	}

	public int acceptNeigh(String memberId, String memberId2) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("memberId", memberId);
		map.put("memberId2", memberId2);
		return sqlSession.update("membermapper.naccept", map);
	}

	public int rejectNeigh(String memberId, String memberId2) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("memberId", memberId);
		map.put("memberId2", memberId2);
		return sqlSession.update("membermapper.nreject", map);
	}
	
	public List<Member> checkNeig(String memberId) {
		// TODO Auto-generated method stub
		return (List<Member>) sqlSession.selectList("membermapper.ncheck", memberId);
	}
	
	public List<Member> neigList() {
		return null;
	}
	
	public String getPw(Map<String, Object> paramMap) {
		// TODO Auto-generated method stub
		
		Member result =  (Member) sqlSession.selectOne(
				"membermapper.selectPw", paramMap);
		String pw;
		
		if (result == null)
			pw = "실패";
		else
			pw = result.getMember_pw();
		
		return pw;
	}
	
	public List<Alarm>  alarmCheck(String writer_id) {
		return  (List<Alarm>) sqlSession.selectList("membermapper.alarmCheck", writer_id);
     }
	
	public Member naverIdCheck(Member mem) {
	      // TODO Auto-generated method stub
	      System.out.println("memberdao");
	      return (Member) sqlSession.selectOne("membermapper.naverIdCheck", mem);
	   }
	   
	   public int naverConnect(Member mem) {
	      return sqlSession.update("membermapper.naverConnect", mem);
	   }

	   public Member kakaoIdCheck(Member mem) {
	      return (Member) sqlSession.selectOne("membermapper.kakaoIdCheck", mem);
	   }

	   public int kakaoConnect(Member mem) {
	      return sqlSession.update("membermapper.kakaoConnect", mem);
	   }

	   public Member facebookIdCheck(Member mem) {
	      return (Member) sqlSession.selectOne("membermapper.facebookIdCheck", mem);
	   }

	   public int facebookConnect(Member mem) {
	      return   sqlSession.update("membermapper.facebookConnect", mem);
	   }
	   
	   public int neigDelete(String memberId, String memberId2) {
		      Map<String, String> map = new HashMap<String, String>();
		      map.put("memberId", memberId);
		      map.put("memberId2", memberId2);
		      return sqlSession.delete("neighbormapper.ndelete", map);
		   }

}
