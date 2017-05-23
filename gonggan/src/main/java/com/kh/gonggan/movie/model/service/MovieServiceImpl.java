package com.kh.gonggan.movie.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.gonggan.movie.model.dao.MovieDao;
import com.kh.gonggan.movie.model.vo.Movie;

@Service("movieService")
public class MovieServiceImpl implements MovieService{
	@Autowired
	MovieDao movieDao; //객체를 스프링에서 생성하여 주입시킴
	
	@Override
	public List<Movie> searchMovie() {
		System.out.println("service : loginCheck run...");
		return  movieDao.searchMovie();
		
	}
}
