package com.example.appinventiv.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpStatus;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.example.appinventiv.DTO.MovieDTO;
import com.example.appinventiv.response.MultipleObjectResponse;
import com.example.appinventiv.response.SingleObjectResponse;
import com.example.appinventiv.service.CrudService;


@RunWith(SpringRunner.class)
@SpringBootTest
//@WebAppConfiguration
public class CrudControllerTest {

	@Autowired
	private WebApplicationContext context;

	private MockMvc mockMvc;
	
	@MockBean
	private CrudService crudService;
	
	
	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context).build();

	}
	
	//@Ignore
	@Test
	public void testCreateMovie() throws Exception {

		mockMvc.perform(post("/api/create").contentType(MediaType.APPLICATION_JSON)
				.content("{\"title\":\"krrish\",\"category\":\"action\",\"starRating\":\"4.5\"}").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated())
				.andDo(print());
	}
	
	//@Ignore
	@Test
	public void testGetMovieById() throws Exception{
		SingleObjectResponse response = null;
		response = setupMovie();
		
		when(crudService.getMovieById(1l)).thenReturn(response);
		mockMvc.perform(get("/api/getMovie/1").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
					.andExpect(content().contentType(MediaType.APPLICATION_JSON))
					.andExpect(content().contentType(MediaType.APPLICATION_JSON))
					.andExpect(jsonPath("$.status").value(true))
					.andExpect(jsonPath("$.statusCode").value(HttpStatus.SC_OK+""))
					.andExpect(jsonPath("$.message").value("success"))
					.andExpect(jsonPath("$.movie.id").value(1))
					.andExpect(jsonPath("$.movie.title").value("krrish"))
					.andExpect(jsonPath("$.movie.category").value("action"))
					.andExpect(jsonPath("$.movie.starRating").value(4.5))
					.andDo(print());
	}
	
	//@Ignore
	@Test
	public void testUpdateMovie() throws Exception {

		mockMvc.perform(post("/api/create").contentType(MediaType.APPLICATION_JSON)
				.content("{\"title\":\"krrish\",\"category\":\"action\",\"starRating\":\"4.5\"}").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated());
		
		mockMvc.perform(put("/api/update").contentType(MediaType.APPLICATION_JSON)
				.content("{\"id\":\"1\",\"title\":\"krrish\",\"category\":\"action\",\"starRating\":\"4.9\"}").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andDo(print());
	}
	
	//@Ignore
	@Test
	public void testDeleteMovie() throws Exception {
		
		mockMvc.perform(post("/api/create").contentType(MediaType.APPLICATION_JSON)
				.content("{\"title\":\"krrish\",\"category\":\"action\",\"starRating\":\"4.5\"}").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated());

		mockMvc.perform(delete("/api/deleteById/1").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andDo(print());
	}
	
	//@Ignore
	@Test
	public void testGetMovies() throws Exception{
		MultipleObjectResponse response = null;
		response = setUpAListOfMovies();
		
		when(crudService.getMovies()).thenReturn(response);
		mockMvc.perform(get("/api/getMovies").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk()) 
					.andExpect(content().contentType(MediaType.APPLICATION_JSON))
					.andExpect(jsonPath("$.status").value(true))
					.andExpect(jsonPath("$.statusCode").value(HttpStatus.SC_OK+""))
					.andExpect(jsonPath("$.message").value("success"))
					.andExpect(jsonPath("$.moviesList[0].id").value(1))
					.andExpect(jsonPath("$.moviesList[0].title").value("krrish"))
					.andExpect(jsonPath("$.moviesList[0].category").value("action"))
					.andExpect(jsonPath("$.moviesList[0].starRating").value(4.5))
					.andExpect(jsonPath("$.moviesList[1].id").value(2))
					.andExpect(jsonPath("$.moviesList[1].title").value("intersteller"))
					.andExpect(jsonPath("$.moviesList[1].category").value("scifi"))
					.andExpect(jsonPath("$.moviesList[1].starRating").value(4.8))
					.andDo(print());
	}
	
	
	private MultipleObjectResponse setUpAListOfMovies() {
		MultipleObjectResponse Finalresponse=new MultipleObjectResponse();
		List<MovieDTO> movieList = new ArrayList<MovieDTO>();
		MovieDTO movie1=new MovieDTO(1l,"krrish", "action", 4.5);
		MovieDTO movie2=new MovieDTO(2l,"intersteller", "scifi", 4.8);
		movieList.add(movie1);
		movieList.add(movie2);
		Finalresponse.setMoviesList(movieList);
		Finalresponse.setStatus(true);
		Finalresponse.setMessage("success");
		Finalresponse.setStatusCode(HttpStatus.SC_OK+"");
		
		return Finalresponse;
	}
	
	private SingleObjectResponse setupMovie() {
		SingleObjectResponse response = new SingleObjectResponse();
		MovieDTO movie1 = new MovieDTO(1l,"krrish", "action", 4.5);
		response.setMovie(movie1);
		response.setStatus(true);
		response.setMessage("success");
		response.setStatusCode(HttpStatus.SC_OK+"");
		return response;
	}

}
