package com.moviebooking.service;

import java.util.List;

import com.moviebooking.bean.Screen;
import com.moviebooking.dao.IScreenDao;
//import com.moviebooking.dao.List;
import com.moviebooking.dao.ScreenDaoImpl;
import com.moviebooking.exception.MovieBookingException;

public class ScreenServiceImpl implements IScreenService {

	@Override
	public void addScreen(Screen screen) throws MovieBookingException {
		IScreenDao screenDao = null;
		screenDao = new ScreenDaoImpl();
		screenDao.addScreen(screen);
		
	}

	@Override
	public List<Screen> getScreenInfo() throws MovieBookingException {
		IScreenDao iScreenDao = null;
		iScreenDao = new ScreenDaoImpl();
		List<Screen> listScreen = iScreenDao.getScreenInfo();
		return listScreen;
	}
}
