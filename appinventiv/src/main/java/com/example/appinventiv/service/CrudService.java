package com.example.appinventiv.service;

import java.io.IOException;
import java.util.List;

import org.apache.http.HttpStatus;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.appinventiv.DTO.MovieDTO;
import com.example.appinventiv.DTO.MovieDeleteDTO;
import com.example.appinventiv.model.Movie;
import com.example.appinventiv.repository.ICrudDao;
import com.example.appinventiv.response.MultipleObjectResponse;
import com.example.appinventiv.response.Response;
import com.example.appinventiv.response.SingleObjectResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

@Service
public class CrudService implements ICrudService {
	
	@Autowired
	private ICrudDao CrudDao;
	
	@Override
	public Response createMovie(MovieDTO movieDto) {
		Response response=new Response();
		ObjectMapper objectMapper = new ObjectMapper();
		String json = new Gson().toJson(movieDto);
		TypeReference<Movie> mapType = new TypeReference<Movie>() {};
		Movie movie = new Movie();
		Long id;
		try {
			movie = objectMapper.readValue(json, mapType);
			id = CrudDao.createMovie(movie);
			response.setStatus(true);
			response.setStatusCode(HttpStatus.SC_CREATED+"");
			response.setMessage("succesfully created with id: "+id);
		} catch (IOException e) {
			response.setStatusCode(HttpStatus.SC_EXPECTATION_FAILED+"");
			response.setMessage("Exception Occured");
			e.printStackTrace();
		}
		return response;
	}

	@Override
	public SingleObjectResponse getMovieById(Long id) {
		SingleObjectResponse response=new SingleObjectResponse();
		ObjectMapper objectMapper = new ObjectMapper();
		TypeReference<MovieDTO> mapType = new TypeReference<MovieDTO>() {};
		MovieDTO movieDTO=null;
		String json = null;
		response.setMessage("Movie not found");
		response.setStatusCode(HttpStatus.SC_NOT_FOUND+"");
		Movie movie=null;
		movie=CrudDao.getById(id);
		if(null != movie){
			json = new Gson().toJson(movie);
			try {
				movieDTO=objectMapper.readValue(json, mapType);
			} catch (JsonProcessingException e) {
				response.setStatusCode(HttpStatus.SC_EXPECTATION_FAILED+"");
				response.setMessage("Exception Occured");
				e.printStackTrace();
			}
			response.setMessage("success");
			response.setStatus(true);
			response.setMovie(movieDTO);
			response.setStatusCode(HttpStatus.SC_OK+"");
		}
		
		return response;
	}

	@Override
	public MultipleObjectResponse getMovies() {
		MultipleObjectResponse response=new MultipleObjectResponse();
		ObjectMapper objectMapper = new ObjectMapper();
		TypeReference<List<MovieDTO>> mapType = new TypeReference<List<MovieDTO>>() {};
		List<MovieDTO> movieListDto=null;
		String json = null;
		JSONArray jsonArray;
		response.setMessage("No Movies Found in Database");
		response.setStatusCode(HttpStatus.SC_NOT_FOUND+"");
		List<Movie> moviesList;
		moviesList = CrudDao.getMovies();
		if(null !=moviesList && !moviesList.isEmpty()){
			try {
				json = new Gson().toJson(moviesList);
				jsonArray = new JSONArray(json);
				movieListDto=objectMapper.readValue(jsonArray.toString(), mapType);
				response.setStatus(true);
				response.setMoviesList(movieListDto);
				response.setMessage("toal Movies found in Database : "+movieListDto.size());
				response.setStatusCode(HttpStatus.SC_OK+"");
			} catch (Exception e) {
				response.setMessage("Exception Occured");
				response.setStatusCode(HttpStatus.SC_EXPECTATION_FAILED+"");
				e.printStackTrace();
			}
			
		}
		
		return response;
	}

	@Override
	public Response updateMovieById(MovieDeleteDTO movieDeleteDTO) {
		Response response=new Response();
		ObjectMapper objectMapper = new ObjectMapper();
		TypeReference<Movie> mapType = new TypeReference<Movie>() {};
		Movie movie=null;
		String json = null;
		String result=null;
		try {
			json = new Gson().toJson(movieDeleteDTO);
			movie=objectMapper.readValue(json, mapType);
			result=CrudDao.updateMovie(movie);
			if(null !=result && "success".equalsIgnoreCase(result)){
				response.setMessage("movie successfully updated with id : "+movie.getId());
				response.setStatus(true);
				response.setStatusCode(HttpStatus.SC_ACCEPTED+"");
			}else{
				response.setMessage("movie not found in DataBase");
				response.setStatusCode(HttpStatus.SC_NOT_FOUND+"");
			}
		} catch (Exception e) {
			response.setMessage("Exception Occured");
			response.setStatusCode(HttpStatus.SC_EXPECTATION_FAILED+"");
			e.printStackTrace();
		}
		return response;
	}

	@Override
	public Response deleteMovieById(Long id) {
		Response response=new Response();
		response.setMessage("Movie not found with id : "+id);
		response.setStatusCode(HttpStatus.SC_NOT_FOUND+"");
		Movie movie=new Movie();
		movie.setId(id);
		String result=null;
		result=CrudDao.deleteById(movie);
		
		if(null != result && "success".equalsIgnoreCase(result)){
			response.setStatus(true);
			response.setMessage("Movie deleted with id : "+id);
			response.setStatusCode(HttpStatus.SC_OK+"");
		}
		return response;
	}
	
}
