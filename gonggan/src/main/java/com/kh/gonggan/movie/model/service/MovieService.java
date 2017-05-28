package com.kh.gonggan.movie.model.service;

import java.util.List;

import com.kh.gonggan.movie.model.vo.Movie;

public interface MovieService {

	List<Movie> searchMovie();
	
	List<Movie> selectAll_index2();

}
