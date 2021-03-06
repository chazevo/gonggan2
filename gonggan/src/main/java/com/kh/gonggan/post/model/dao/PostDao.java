package com.kh.gonggan.post.model.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kh.gonggan.post.model.vo.Post;

@Repository("postDao")
public class PostDao {
	@Autowired
	private SqlSessionTemplate sqlSession;
	public PostDao(){}
	
	public List<Post> selectCalendarAll(String writer_id, int year, int month) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("writer_id", writer_id);
		map.put("year", year + "");
		map.put("month", month + "");
		
		return  (List<Post>) sqlSession.selectList("postmapper.calplist", map);
	}
	
	public List<Post> selectUserAll(String writer_id, int rownum, int rownum2) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("writer_id", writer_id);
		map.put("rownum", rownum + "");
		map.put("rownum2", rownum2 + "");
		return (List<Post>) sqlSession.selectList("postmapper.userplist", map);
	}

	public List<Post> selectLikeNpost(int rownum, int rownum2, String writer_id) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("rownum", rownum + "");
		map.put("rownum2", rownum2 + "");
		map.put("writer_id", writer_id);
		return (List<Post>) sqlSession.selectList("postmapper.nplikelist", map);
	}
	
	public List<Post> selectLikeCategoryPost(int rownum, int rownum2, String category) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("rownum", rownum + "");
		map.put("rownum2", rownum2 + "");
		map.put("category", category);
		return (List<Post>) sqlSession.selectList("postmapper.categoryplikelist", map);
	}
	
	public List<Post> selectSearchLikeList(int rownum, int rownum2,
			String keyword, int option) {
		List<Post> plist = new ArrayList<Post>();
		Map<String, String> map = new HashMap<String, String>();
		map.put("rownum", rownum + "");
		map.put("rownum2", rownum2 + "");
		map.put("keyword", keyword);
		if (option == 0)
			plist = (List<Post>) sqlSession.selectList("postmapper.pcontentSearchLikelist", map);
		else if (option == 1)
			plist = (List<Post>) sqlSession.selectList("postmapper.pwriterSearchLikelist", map);
		return plist;
		
	}
	public List<Post> selectUserFree(String writer_id, int rownum, int rownum2) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("writer_id", writer_id);
		map.put("rownum", rownum + "");
		map.put("rownum2", rownum2 + "");
		return (List<Post>) sqlSession.selectList("postmapper.userfree", map);
	}
	
	public List<Post> selectUserMusic(String writer_id, int rownum, int rownum2) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("writer_id", writer_id);
		map.put("rownum", rownum + "");
		map.put("rownum2", rownum2 + "");
		return (List<Post>) sqlSession.selectList("postmapper.usermusic", map);
	}
	
	public List<Post> selectUserMovie(String writer_id, int rownum, int rownum2) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("writer_id", writer_id);
		map.put("rownum", rownum + "");
		map.put("rownum2", rownum2 + "");
		return (List<Post>) sqlSession.selectList("postmapper.usermovie", map);
	}

	public List<Post> selectUserReview(String writer_id, int rownum, int rownum2) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("writer_id", writer_id);
		map.put("rownum", rownum + "");
		map.put("rownum2", rownum2 + "");
		return (List<Post>) sqlSession.selectList("postmapper.userreview", map);
	}

	public List<Post> selectUserBook(String writer_id, int rownum, int rownum2) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("writer_id", writer_id);
		map.put("rownum", rownum + "");
		map.put("rownum2", rownum2 + "");
		return (List<Post>) sqlSession.selectList("postmapper.userbook", map);
	}

	public List<Post> selectUserPlace(String writer_id, int rownum, int rownum2) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("writer_id", writer_id);
		map.put("rownum", rownum + "");
		map.put("rownum2", rownum2 + "");
		return (List<Post>) sqlSession.selectList("postmapper.userplace", map);
	}

	public List<Post> selectUserNews(String writer_id, int rownum, int rownum2) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("writer_id", writer_id);
		map.put("rownum", rownum + "");
		map.put("rownum2", rownum2 + "");
		return (List<Post>) sqlSession.selectList("postmapper.usernews", map);
	}

	public List<Post> selectUserDiary(String writer_id, int rownum, int rownum2) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("writer_id", writer_id);
		map.put("rownum", rownum + "");
		map.put("rownum2", rownum2 + "");
		return (List<Post>) sqlSession.selectList("postmapper.userdiary", map);
	}

	public Post postDetail(int post_id) {
		return (Post) sqlSession.selectOne("postmapper.pdetail", post_id);
	}
	
	public int postContentSearchMaxRnum(String keyword) {
		return (int) sqlSession.selectOne("postmapper.pcontentsearchmax", keyword);
	}
	
	public int postWriterSearchMaxRnum(String keyword) {
		Object result = null;
		int integerResult = -1;
		
		if ((result = sqlSession.selectOne("postmapper.pwritersearchmax", keyword)) == null)
			integerResult = 0;
		else integerResult = (Integer)result;
		
		return integerResult;
	}
	
	public List<Post> postContentSearch(String keyword, int rownum, int rownum2) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("keyword", keyword);
		map.put("rownum", rownum + "");
		map.put("rownum2", rownum2 + "");
		return (List<Post>) sqlSession.selectList("postmapper.pcontentsearch", map);
	}
	
	public List<Post> postWriterSearch(String keyword, int rownum, int rownum2) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("keyword", keyword);
		map.put("rownum", rownum + "");
		map.put("rownum2", rownum2 + "");
		return (List<Post>) sqlSession.selectList("postmapper.pwritersearch", map);
	}

	public String selectPostWriter(int post_id) {
		
		return (String) sqlSession.selectOne("postmapper.selectpostwriter", post_id);
	}

	public List<Post> selectAll(int rownum, int rownum2) {
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("rownum", rownum);
		map.put("rownum2", rownum2);
		return (List<Post>) sqlSession.selectList("postmapper.plist", map);
	}

	public List<Post> selectLikeAll(int rownum, int rownum2) {
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("rownum", rownum);
		map.put("rownum2", rownum2);
		return (List<Post>) sqlSession.selectList("postmapper.plikelist", map);
	}
	
	public List<Post> selectAll_index2() {
	      return (List<Post>) sqlSession.selectList("postmapper.plist_index2");
	}

	public List<Post> selectAll_myhome(String writer_id) {
	      return (List<Post>) sqlSession.selectList(
	    		  "postmapper.plist_myhome", writer_id);
	}
	
	public List<Post> selectNeighborAll_index2(String member_id) {
		return (List<Post>) sqlSession.selectList("postmapper.nplist_index2", member_id);
	}
	
	public List<Post> selectMusic(int rownum, int rownum2) {
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("rownum", rownum);
		map.put("rownum2", rownum2);
		return (List<Post>) sqlSession.selectList("postmapper.musiclist", map);
	}

	public List<Post> selectMovie(int rownum, int rownum2) {
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("rownum", rownum);
		map.put("rownum2", rownum2);
		return (List<Post>) sqlSession.selectList("postmapper.movielist", map);
	}

	public List<Post> selectReview(int rownum, int rownum2) {
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("rownum", rownum);
		map.put("rownum2", rownum2);
		return (List<Post>) sqlSession.selectList("postmapper.reviewlist", map);
	}

	public List<Post> selectDiary(int rownum, int rownum2) {
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("rownum", rownum);
		map.put("rownum2", rownum2);
		return (List<Post>) sqlSession.selectList("postmapper.diarylist", map);
	}

	public List<Post> selectNews(int rownum, int rownum2) {
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("rownum", rownum);
		map.put("rownum2", rownum2);
		return (List<Post>) sqlSession.selectList("postmapper.newslist", map);
	}

	public List<Post> selectBook(int rownum, int rownum2) {
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("rownum", rownum);
		map.put("rownum2", rownum2);
		return (List<Post>) sqlSession.selectList("postmapper.booklist", map);
	}

	public List<Post> selectPlace(int rownum, int rownum2) {
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("rownum", rownum);
		map.put("rownum2", rownum2);
		return (List<Post>) sqlSession.selectList("postmapper.placelist", map);
	}
	
	public List<Post> selectFree(int rownum, int rownum2) {
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("rownum", rownum);
		map.put("rownum2", rownum2);
		return (List<Post>) sqlSession.selectList("postmapper.freelist", map);
	}
	
	public int maxRownum(String category) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("category", category);
		return (int) sqlSession.selectOne("postmapper.maxrnum", map);
	}
	
	public List<Post> likeInOrder(String writer_id) {
	      return (List<Post>) sqlSession.selectList("postmapper.likeinorder", writer_id);
	   }//나의 포스트에 좋아요 순위
	public List<Post> commentInOrder(String writer_id) {
	      return (List<Post>) sqlSession.selectList("postmapper.commentinorder", writer_id);
	   }//나의 포스트에 댓글 순위
	
	public List<Post> selectUserNeighbor(String loginUser, int rownum, int rownum2){
		Map<String, String> map = new HashMap<String, String>();
		map.put("loginUser", loginUser);
		map.put("rownum", rownum + "");
		map.put("rownum2", rownum2 + "");
		System.out.println("DAO :"+map);
		return (List<Post>) sqlSession.selectList("postmapper.neighborpost", map);
	}
	

	public int pinsert(String loginUser, String category, String content,
			String diary_title, String music_title, String movie_title,
			String open, String bg, String music_info, String place_name, String pimg) {
		Map<String, String> map = new HashMap<String, String>();
		int post_id = (int) sqlSession.selectOne("postmapper.postid");
		int pinsert = 0;
		map.put("post_id", post_id+"");
		map.put("loginUser", loginUser);
		map.put("category",category);
		map.put("content",content);
		map.put("diary_title", diary_title);
		map.put("music_title", music_title);
		map.put("movie_title", movie_title);
		map.put("open", open);
		map.put("bg", bg);
		map.put("music_info", music_info);
		map.put("place_name", place_name);
		map.put("pimg", pimg);
		
		System.out.println(open);
		
		if("onlyMe".equals(open)){
			pinsert = sqlSession.insert("postmapper.ponlymeinsert",map);
			System.out.println(pinsert+"onlyme");
			if("news".equals(category)) {
				System.out.println(""+category);
				sqlSession.insert("newsmapper.ninsert",map);
			}
			else if("diary".equals(category)){
				sqlSession.insert("diarymapper.dinsert",map);
			}
			else if("review".equals(category)){
				sqlSession.insert("reviewmapper.rinsert",map);
			}
			else if("book".equals(category)){
				sqlSession.insert("bookmapper.binsert",map);
			}
			else if("movie".equals(category)){
				sqlSession.insert("moviemapper.movieinsert",map);
			}
			else if("music".equals(category)){
				sqlSession.insert("musicmapper.minsert",map);
			}
			else if("place".equals(category)){
				sqlSession.insert("placemapper.pinsert",map);
			}
			else if("free".equals(category)){
	           
				System.out.println(""+category);
				sqlSession.insert("freemapper.finsert",map);
			}
		}else if("public".equals(open)){
			pinsert = sqlSession.insert("postmapper.ppublicinsert",map);
			System.out.println(pinsert+"public");
			if("news".equals(category)) {
				System.out.println(""+category);
				sqlSession.insert("newsmapper.ninsert",map);
			}
			else if("diary".equals(category)){
				sqlSession.insert("diarymapper.dinsert",map);
			}
			else if("review".equals(category)){
				sqlSession.insert("reviewmapper.rinsert",map);
			}
			else if("book".equals(category)){
				sqlSession.insert("bookmapper.binsert",map);
			}
			else if("movie".equals(category)){
				sqlSession.insert("moviemapper.movieinsert",map);
			}
			else if("music".equals(category)){
				sqlSession.insert("musicmapper.minsert",map);
			}
			else if("place".equals(category)){
				sqlSession.insert("placemapper.pinsert",map);
			}
			else if("free".equals(category)){
	           
				System.out.println(""+category);
				sqlSession.insert("freemapper.finsert",map);
			}
		}
		return pinsert;
	}
	
	public int postDelete(int post_id, String loginUser) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("post_id", post_id+"");
		map.put("loginUser", loginUser);
		System.out.println("post_id :" +post_id);
		int delete = sqlSession.delete("postmapper.postdelete",map);
		System.out.println(delete+"mmmm");
		return delete;
	}

	
}

