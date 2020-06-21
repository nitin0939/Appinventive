package com.example.appinventiv.response;

import java.util.List;

import com.example.appinventiv.DTO.MovieDTO;
import com.example.appinventiv.base.ServiceResponse;

public class MultipleObjectResponse extends ServiceResponse{
	
	private List<MovieDTO> moviesList;

	public List<MovieDTO> getMoviesList() {
		return moviesList;
	}

	public void setMoviesList(List<MovieDTO> moviesList) {
		this.moviesList = moviesList;
	}
	
}
