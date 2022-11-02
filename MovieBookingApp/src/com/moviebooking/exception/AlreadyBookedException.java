package com.moviebooking.exception;

public class AlreadyBookedException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public AlreadyBookedException(String errorMsg) {
		super(errorMsg);
	}
}
