package com.moviebooking.service;

import java.util.List;
import com.moviebooking.bean.Screen;
import com.moviebooking.exception.MovieBookingException;

public interface IScreenService {

	void addScreen(Screen screen) throws MovieBookingException;

	List<Screen> getScreenInfo() throws MovieBookingException;

}
