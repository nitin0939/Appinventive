package com.example.appinventiv.service;

import com.example.appinventiv.DTO.MovieDTO;
import com.example.appinventiv.DTO.MovieDeleteDTO;
import com.example.appinventiv.model.Movie;
import com.example.appinventiv.repository.ICrudDao;
import com.example.appinventiv.response.MultipleObjectResponse;
import com.example.appinventiv.response.Response;
import com.example.appinventiv.response.SingleObjectResponse;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpStatus;
import org.json.JSONArray;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
@SuppressWarnings("deprecation")
public class CrudServiceTest {
    @Mock
    private ICrudDao crudDao;

    private static MovieDTO movieTO;

    @InjectMocks
    private CrudService crudService=new CrudService();

    @Test
    public void testCreateMovie(){
        Movie movie = new Movie();
        MovieDTO movieDTO=null;
        Mockito.when(crudDao.createMovie(movie)).thenReturn(Mockito.anyLong());
        Response response = crudService.createMovie(movieDTO);
        Assert.assertTrue(response.isStatus());
        Assert.assertTrue(response.getStatusCode().equalsIgnoreCase(HttpStatus.SC_CREATED+""));
    }

    @Test
    @Ignore
    public void testCreateMovieCaseException(){
        Movie movie = null;
        ObjectMapper objectMapper = null;
        String json = null;
        MovieDTO movieDTOObject = null;
        TypeReference<Movie> mapType = new TypeReference<Movie>() {};
        Mockito.when(crudDao.createMovie(movie)).thenReturn(null);
        Response response = crudService.createMovie(movieDTOObject);
        Assert.assertTrue(!response.isStatus());
        Assert.assertTrue(response.getStatusCode().equalsIgnoreCase(HttpStatus.SC_EXPECTATION_FAILED+""));
        Assert.assertTrue("Exception Occured".equalsIgnoreCase(response.getMessage()));

    }

    @Test
    public void testGetMovieByIdMovieNoFound(){
        ObjectMapper mapper=new ObjectMapper();
        TypeReference<MovieDTO> mapType = new TypeReference<MovieDTO>() {};
        MovieDTO movieDTO=null;
        Movie movie=null;
        Mockito.when(crudDao.getById(1l)).thenReturn(movie);
        SingleObjectResponse response=crudService.getMovieById(Mockito.anyLong());
        Assert.assertTrue("Movie not found".equalsIgnoreCase(response.getMessage()));
        Assert.assertTrue(response.getStatusCode().equalsIgnoreCase(HttpStatus.SC_NOT_FOUND+""));
        Assert.assertTrue(!response.isStatus());
    }

    @Test
    public void testGetMovieByIdMovieSuccess(){
        ObjectMapper mapper=new ObjectMapper();
        TypeReference<MovieDTO> mapType = new TypeReference<MovieDTO>() {};
        MovieDTO movieDTO=null;
        String json = null;
        Movie movie=new Movie();
        movie.setId(1l);
        movie.setTitle("action");
        Mockito.when(crudDao.getById(1l)).thenReturn(movie);
        SingleObjectResponse response=crudService.getMovieById(1l);
        Assert.assertTrue("success".equalsIgnoreCase(response.getMessage()));
        Assert.assertTrue(response.getStatusCode().equalsIgnoreCase(HttpStatus.SC_OK+""));
        Assert.assertTrue(response.isStatus());
    }

    @Test
    public void testGetMoviesNotFound(){
        ObjectMapper objectMapper = new ObjectMapper();
        TypeReference<List<MovieDTO>> mapType = new TypeReference<List<MovieDTO>>() {};
        List<MovieDTO> movieListDto=null;
        String json = null;
        JSONArray jsonArray;
        List<Movie> moviesList=null;
        Mockito.when(crudDao.getMovies()).thenReturn(moviesList);
        MultipleObjectResponse response=crudService.getMovies();
        Assert.assertTrue("No Movies Found in Database".equalsIgnoreCase(response.getMessage()));
        Assert.assertTrue(response.getStatusCode().equalsIgnoreCase(HttpStatus.SC_NOT_FOUND+""));
        Assert.assertTrue(!response.isStatus());
    }

    @Test
    public void testGetMoviesSuccess(){
        ObjectMapper objectMapper = new ObjectMapper();
        TypeReference<List<MovieDTO>> mapType = new TypeReference<List<MovieDTO>>() {};
        List<MovieDTO> movieListDto=null;
        Movie movie1= new Movie();
        movie1.setId(1l);
        movie1.setCategory("Action");
        movie1.setTitle("PK");
        Movie movie2= new Movie();
        movie2.setId(2l);
        movie2.setCategory("Action");
        movie2.setTitle("Krrish");
        String json = null;
        JSONArray jsonArray;
        List<Movie> moviesList=new ArrayList<>();
        moviesList.add(movie1);
        moviesList.add(movie2);
        Mockito.when(crudDao.getMovies()).thenReturn(moviesList);
        MultipleObjectResponse response=crudService.getMovies();
        Assert.assertTrue("toal Movies found in Database : 2".equalsIgnoreCase(response.getMessage()));
        Assert.assertTrue(response.getStatusCode().equalsIgnoreCase(HttpStatus.SC_OK+""));
        Assert.assertTrue(response.isStatus());
    }

    @Test
    public void testUpdateMovieCaseMoviesNotFound(){
        Response response=new Response();
        ObjectMapper objectMapper = new ObjectMapper();
        TypeReference<Movie> mapType = new TypeReference<Movie>() {};
        MovieDeleteDTO movieDeleteDTO=null;
        Movie movie=null;
        String json = null;
        String result=null;
        Mockito.when(crudDao.updateMovie(Mockito.any())).thenReturn(Mockito.isNull());
        response = crudService.updateMovieById(movieDeleteDTO);
        Assert.assertTrue("movie not found in DataBase".equalsIgnoreCase(response.getMessage()));
        Assert.assertTrue(response.getStatusCode().equalsIgnoreCase(HttpStatus.SC_NOT_FOUND+""));
        Assert.assertTrue(!response.isStatus());
    }

    @Test
    public void testUpdateMovieCaseSuccess(){
        Response response=new Response();
        ObjectMapper objectMapper = new ObjectMapper();
        TypeReference<Movie> mapType = new TypeReference<Movie>() {};
        MovieDeleteDTO movieDeleteDTO=new MovieDeleteDTO();
        movieDeleteDTO.setId(1l);
        Movie movie=null;
        String json = null;
        String result="success";
        Mockito.when(crudDao.updateMovie(Mockito.any())).thenReturn(result);
        response = crudService.updateMovieById(movieDeleteDTO);
        Assert.assertTrue("movie successfully updated with id : 1".equalsIgnoreCase(response.getMessage()));
        Assert.assertTrue(response.getStatusCode().equalsIgnoreCase(HttpStatus.SC_ACCEPTED+""));
        Assert.assertTrue(response.isStatus());
    }

    @Test
    public void testDeleteMovieByIdSuccess(){
        String result="success";
        Mockito.when(crudDao.deleteById(Mockito.any())).thenReturn(result);

        Response response=crudService.deleteMovieById(Mockito.anyLong());

        Assert.assertTrue("Movie deleted with id : 0".equalsIgnoreCase(response.getMessage()));
        Assert.assertTrue(response.getStatusCode().equalsIgnoreCase(HttpStatus.SC_OK+""));
        Assert.assertTrue(response.isStatus());
    }

    @Test
    public void testDeleteMovieByIdNotFound(){
        Movie movie=new Movie();
        movie.setId(1l);
        String result=null;
        Mockito.when(crudDao.deleteById(movie)).thenReturn(result);
        Response response=crudService.deleteMovieById(1l);
        Assert.assertTrue("Movie not found with id : 1".equalsIgnoreCase(response.getMessage()));
        Assert.assertTrue(response.getStatusCode().equalsIgnoreCase(HttpStatus.SC_NOT_FOUND+""));
        Assert.assertTrue(!response.isStatus());
    }

    @BeforeClass
    public static void setup(){
        movieTO = new MovieDTO();
        movieTO.setId(1l);
        movieTO.setCategory("action");
        movieTO.setStarRating(4.5);
        movieTO.setTitle("PK");
    }
}
