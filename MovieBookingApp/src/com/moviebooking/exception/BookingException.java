package com.moviebooking.exception;

public class BookingException extends RuntimeException{
	private static final long serialVersionUID = 1L;

	public BookingException(String errorMsg) {
		super(errorMsg);
	}
}
