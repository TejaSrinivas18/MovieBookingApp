package com.moviebooking.service;

import com.moviebooking.bean.Movie;
import com.moviebooking.exception.MovieBookingException;

public interface IMovieService {

	void addMovie(Movie movie) throws MovieBookingException;
}