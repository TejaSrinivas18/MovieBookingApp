package com.moviebooking.service;

import com.moviebooking.bean.Movie;
import com.moviebooking.dao.IMovieDao;
import com.moviebooking.dao.MovieDaoImpl;
import com.moviebooking.exception.MovieBookingException;

public class MovieServiceImpl implements IMovieService{

	@Override
	public void addMovie(Movie movie) throws MovieBookingException {
		IMovieDao movieDao = null;
		movieDao = new MovieDaoImpl();
		movieDao.addMovie(movie);
	}
}
