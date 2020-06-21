package com.example.appinventiv.repository;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.appinventiv.model.Movie;

@Repository
public class CrudDao implements ICrudDao{
	
	@Autowired
	private SessionFactory entityManagerFactory;
	
	@Override
	public Long createMovie(Movie movie){
		Session session = entityManagerFactory.getCurrentSession();
		Transaction tx = null;
		Long id=null;
		try {
			tx = session.beginTransaction();
			id = (Long)session.save(movie);
			tx.commit();
		} catch (Exception e) {
			if (tx!=null) tx.rollback();
			e.printStackTrace();
		}finally {
			session.close(); 
		}
		
		return id;
	}
	
	@Override
	public Movie getById(Long id){
		Session session = entityManagerFactory.getCurrentSession();
		Transaction tx = null;
		Movie movie =null;
		try {
			tx = session.beginTransaction();
			movie = session.get(Movie.class, id);
			tx.commit();
		} catch (Exception e) {
			if (tx!=null) tx.rollback();
			e.printStackTrace();
		}finally {
			session.close(); 
		}
		return movie;
	}
	
	@Override
	public String deleteById(Movie movie){
		Session session = entityManagerFactory.getCurrentSession();
		Transaction tx = null;
		String result="fail";
		try {
			tx = session.beginTransaction();
			session.delete(movie);
			tx.commit();
			result="success";
		} catch (Exception e) {
			if (tx!=null) tx.rollback();
			e.printStackTrace();
		}finally {
			session.close(); 
		}
		return result;
	}
	
	@Override
	public String updateMovie(Movie movie){
		Session session = entityManagerFactory.getCurrentSession();
		Transaction tx = null;
		String result="fail";
		try {
			tx = session.beginTransaction();
			session.update(movie);
			tx.commit();
			result="success";
		} catch (Exception e) {
			if (tx!=null) tx.rollback();
			e.printStackTrace();
		}finally {
			session.close(); 
		}
		return result;
	}
	
	
	@Override
	public List<Movie> getMovies(){

		Session session = entityManagerFactory.getCurrentSession();
		Transaction tx = null;
		List<Movie> moviesList=null;;
		try {
			tx = session.beginTransaction();
			moviesList = session.createCriteria(Movie.class).list();
			tx.commit();
		} catch (Exception e) {
			if (tx!=null) tx.rollback();
			e.printStackTrace();
		}finally {
			session.close(); 
		}
		return moviesList;
	}
}
