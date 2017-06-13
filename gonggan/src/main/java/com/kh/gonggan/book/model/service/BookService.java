package com.kh.gonggan.book.model.service;

import java.util.List;

import com.kh.gonggan.book.model.vo.Book;

public interface BookService {
	List<Book> selectAll_index2();
	Book bookDetail(int postId);
}
