package com.kh.gonggan.book.model.service;

import java.util.List;

import com.kh.gonggan.book.model.vo.Book;

public interface BookService {
	List<Book> selectAll_index2();
	List<Book> selectAll_myhome(String writer_id);
	Book bookDetail(int postId);
}
