package com.kh.gonggan.good.model.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.kh.gonggan.good.model.vo.Good;



@Repository("GoodDao")

public class GoodDao {
	@Autowired
	private SqlSessionTemplate sqlSession;
	public GoodDao(){}
	
	
	public int goodCount(int postId){
		return (int) sqlSession.selectOne("goodmapper.gcount", postId);
	}//good 카운트 세기
	

	public int goodInsert(int postId, String memberId){

        Map<String, String> map = new HashMap<String, String>();
        int alarm_num = (int) sqlSession.selectOne("goodmapper.alarmnum");
        map.put("alarm_num", alarm_num+"");
        map.put("postId", postId+"");
        map.put("memberId",memberId);
		
		if (sqlSession.insert("goodmapper.ginsert", map) < 0){
			System.out.println("alarm 테이블 insert 실패");
		}if(sqlSession.update("goodmapper.ginsert3", map) <0){
		System.out.println(" plist 실패");
		}
		return sqlSession.insert("goodmapper.ginsert2", map);
	} //good 카운트 증가

	public int goodDelete(int postId, String memberId){
        Map<String, String> map = new HashMap<String, String>();
        map.put("postId", postId+"");
        map.put("memberId",memberId);
		sqlSession.update("goodmapper.gdelete3", map);

		return sqlSession.delete("goodmapper.gdelete", map);
	} //good 카운트 감소


	public Object goodCheck(int postId, String memberId) {
		Good gmember = new Good(postId, memberId);
		return sqlSession.selectOne("goodmapper.gcheck", gmember);
	}//로그인 유저가 포스팅 작성자의 게시글 좋아요 했는지 여부


	public List<Good> goodList(int postId) {
		return (List<Good>) sqlSession.selectList("goodmapper.glist", postId);
	}


	public List<Good> goodSearch(String member_id, int post_id) {
		HashMap<String, String> map =new  HashMap<String, String>();
		map.put("member_id", member_id);
		map.put("post_id", post_id+"");
		 return (List<Good>)sqlSession.selectList("goodmapper.gsearch",map);
	}


	public List<Good> checkGoodAlram(String member_id) {
		return (List<Good>)sqlSession.selectList("goodmapper.gcheckalram",member_id);
	}

	public List<Good> goodMyList(String writer_id) {
		return (List<Good>)sqlSession.selectList("goodmapper.goodMyList",writer_id);
	}

}
