package com.example.appinventiv.service;

import java.util.List;

import com.example.appinventiv.DTO.MovieDTO;
import com.example.appinventiv.DTO.MovieDeleteDTO;
import com.example.appinventiv.model.Movie;
import com.example.appinventiv.response.MultipleObjectResponse;
import com.example.appinventiv.response.Response;
import com.example.appinventiv.response.SingleObjectResponse;

public interface ICrudService {
	public Response createMovie(MovieDTO movieDto);
	public SingleObjectResponse getMovieById(Long id);
	public MultipleObjectResponse getMovies();
	public Response updateMovieById(MovieDeleteDTO movie);
	public Response deleteMovieById(Long id);
}
