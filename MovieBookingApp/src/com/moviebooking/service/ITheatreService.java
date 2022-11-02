package com.moviebooking.service;

import com.moviebooking.bean.Theatre;
import com.moviebooking.exception.MovieBookingException;

public interface ITheatreService {

	void addTheatre(Theatre theatre) throws MovieBookingException;  
    
}