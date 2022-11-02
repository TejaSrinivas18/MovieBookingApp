package com.moviebooking.exception;

public class MovieBookingException extends Exception {
	private static final long serialVersionUID = 1L;
	
	public MovieBookingException(String errorMsg) {
		super(errorMsg);
	}
}
