package com.example.appinventiv.controller;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.appinventiv.DTO.MovieDTO;
import com.example.appinventiv.DTO.MovieDeleteDTO;
import com.example.appinventiv.response.MultipleObjectResponse;
import com.example.appinventiv.response.Response;
import com.example.appinventiv.response.SingleObjectResponse;
import com.example.appinventiv.service.ICrudService;

@RestController
@RequestMapping("/api/*")
public class CrudController {
	
	@Autowired
	ICrudService crudService;
	
	
	@GetMapping(value = "ping")
	public String ping() {
		return "ping api on Crud Controller called";
	}
	
	
	@PostMapping("create")
	@ResponseStatus(HttpStatus.CREATED)
	public Response createMovie(@Valid @RequestBody MovieDTO movieDto) {
		return crudService.createMovie(movieDto);
	}
	
	@GetMapping("getMovie/{id}")
	public SingleObjectResponse getMovieById(@NotEmpty @PathVariable("id") Long id) {
		return crudService.getMovieById(id);
	}
	
	@GetMapping("getMovies")
	public MultipleObjectResponse getMovies() {
		return crudService.getMovies();
	}
	
	@PutMapping("update")
	public Response updateMovieById(@Valid @RequestBody MovieDeleteDTO movie) {
		return crudService.updateMovieById(movie);
	}
	
	@DeleteMapping("deleteById/{id}")
	public Response deleteMovieById(@NotEmpty @PathVariable("id") Long id) {
		return crudService.deleteMovieById(id);
	}
}
