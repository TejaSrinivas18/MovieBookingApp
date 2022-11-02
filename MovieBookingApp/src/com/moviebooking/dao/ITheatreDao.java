package com.moviebooking.dao;

import com.moviebooking.bean.Theatre;
import com.moviebooking.exception.MovieBookingException;

public interface ITheatreDao {

	void addTheatre(Theatre theatre) throws MovieBookingException;

}
