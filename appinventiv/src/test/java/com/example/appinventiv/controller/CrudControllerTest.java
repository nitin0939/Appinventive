package com.example.appinventiv.controller;

import static org.junit.Assert.fail;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.example.appinventiv.DTO.MovieDTO;
import com.example.appinventiv.response.SingleObjectResponse;
import com.example.appinventiv.service.CrudService;

@RunWith(SpringRunner.class)
@WebMvcTest(value = CrudController.class)
public class CrudControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private CrudService crudService;
	
	MovieDTO movieDto=new MovieDTO(1l,"test", "action", 3.5);
	
	SingleObjectResponse singleObjectResponse = new SingleObjectResponse(movieDto);
	
	@Test
	public void testGetMovieById () throws Exception{
		Mockito.when(crudService.getMovieById(Mockito.anyLong())).thenReturn(singleObjectResponse);
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/crud/getMovie/1").accept(MediaType.APPLICATION_JSON);
		
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		System.out.println(result.getResponse());
		String expected = "{test}";
		JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
	}
	
	@Test
	public void test() {
		fail("Not yet implemented"); // TODO
	}

}
