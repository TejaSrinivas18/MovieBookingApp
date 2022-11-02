package com.moviebooking.dao;

import com.moviebooking.bean.Movie;
import com.moviebooking.exception.MovieBookingException;

public interface IMovieDao {

	void addMovie(Movie movie) throws MovieBookingException;

}
