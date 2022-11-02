package com.moviebooking.service;

import java.io.IOException;
import java.util.List;

import com.google.zxing.WriterException;
import com.moviebooking.bean.Seats;
import com.moviebooking.exception.MovieBookingException;

public interface IBookingService {

	List<Seats> getAvailabiltyAsScreen(String mId, String tId, String startsAt) throws MovieBookingException;
	
	void bookSeats(List<Seats> sList,String username) throws MovieBookingException;

}
