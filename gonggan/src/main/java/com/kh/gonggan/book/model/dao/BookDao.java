package com.kh.gonggan.book.model.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kh.gonggan.book.model.vo.Book;

@Repository("bookDao")
public class BookDao {
	
	@Autowired
	private SqlSessionTemplate sqlSession;

	public BookDao(){}

	public List<Book> selectAll_index2() {
		return (List<Book>) sqlSession.selectList("bookmapper.booklist_index2");
	}

	public Book bookDetail(int postId) {
		return (Book) sqlSession.selectOne("bookmapper.bookdetail", postId);
	}
}
