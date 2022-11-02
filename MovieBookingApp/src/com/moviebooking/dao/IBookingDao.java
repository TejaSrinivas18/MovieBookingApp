package com.moviebooking.dao;

import com.moviebooking.bean.Booking;
import com.moviebooking.exception.MovieBookingException;

public interface IBookingDao {

	void bookSeats(Booking booking);

}
