package com.moviebooking.util;

import java.util.List;
import java.util.stream.Collectors;

import com.moviebooking.bean.Screen;

public class ScreenUtil {

	public List<String> getMovies(List<Screen> listScreen) {
		List<String> movies = listScreen.stream().map(Screen :: getmId).collect(Collectors.toList());
		return movies;
	}

	public List<String> getTheatres(List<Screen> listScreen) {
		List<String> theatres = listScreen.stream().map(Screen :: gettId).collect(Collectors.toList());
		return theatres;
	}

	public List<String> getStartTime(List<Screen> listScreen) {
		List<String> timings = listScreen.stream().map(Screen :: getStartsAt).collect(Collectors.toList());
		return timings;
	}

}
