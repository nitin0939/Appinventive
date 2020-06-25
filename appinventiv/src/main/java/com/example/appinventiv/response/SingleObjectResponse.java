package com.example.appinventiv.response;

import com.example.appinventiv.DTO.MovieDTO;
import com.example.appinventiv.base.ServiceResponse;

public class SingleObjectResponse extends ServiceResponse{
	
	private MovieDTO movie;

	public MovieDTO getMovie() {
		return movie;
	}

	public void setMovie(MovieDTO movie) {
		this.movie = movie;
	}

	public SingleObjectResponse(MovieDTO movie) {
		super();
		this.movie = movie;
	}

	public SingleObjectResponse() {
		super();
	}
	
	
}
