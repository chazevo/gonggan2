package com.kh.gonggan.book.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.gonggan.book.model.dao.BookDao;
import com.kh.gonggan.book.model.vo.Book;

@Service("bookService")
public class BookServiceImpl implements BookService {

	@Autowired
	BookDao bookDao; // 객체를 스프링에서 생성하여 주입시킴

	@Override
	public List<Book> selectAll_index2() {
		return bookDao.selectAll_index2();
	}

	@Override
	public Book bookDetail(int postId) {
		return bookDao.bookDetail(postId);
	}
	
}
