package com.moviebooking.service;

import com.moviebooking.bean.Seats;
import com.moviebooking.exception.MovieBookingException;

public interface ISeatsService {

	void addSeats(Seats seats) throws MovieBookingException;
}