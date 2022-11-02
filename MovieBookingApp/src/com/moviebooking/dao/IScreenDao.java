package com.moviebooking.dao;

import java.util.List;

import com.moviebooking.bean.Screen;
import com.moviebooking.exception.MovieBookingException;

public interface IScreenDao {

	void addScreen(Screen screen) throws MovieBookingException;

	List<Screen> getScreenInfo() throws MovieBookingException;

}
