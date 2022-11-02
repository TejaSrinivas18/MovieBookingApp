package com.moviebooking.dao;

import java.util.List;

import com.moviebooking.bean.Seats;
import com.moviebooking.exception.MovieBookingException;

public interface ISeatsDao {

	void addSeats(Seats seats) throws MovieBookingException;

	List<Seats> getAvailabilitySeats(String mId, String tId, String startsAt) throws MovieBookingException;

	Seats findBySeat(String seatNo);

	void update(String booked, String seatNo);

}
