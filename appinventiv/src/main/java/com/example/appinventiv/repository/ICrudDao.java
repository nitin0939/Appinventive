package com.example.appinventiv.repository;

import java.util.List;

import com.example.appinventiv.model.Movie;

public interface ICrudDao {
	public Long createMovie(Movie movie);
	Movie getById(Long id);
	String deleteById(Movie movie);
	String updateMovie(Movie movie);
	List<Movie> getMovies();
}	
