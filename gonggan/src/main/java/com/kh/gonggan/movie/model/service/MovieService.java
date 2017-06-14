package com.kh.gonggan.movie.model.service;

import java.util.List;

import com.kh.gonggan.movie.model.vo.Movie;
import com.kh.gonggan.news.model.vo.News;

public interface MovieService {

	List<Movie> searchMovie();
	List<Movie> selectAll(int rownum, int rownum2);
	List<Movie> selectAll_index2();
	List<Movie> selectAll_myhome(String writer_id);
	Movie movieDetail(int postId);
}
