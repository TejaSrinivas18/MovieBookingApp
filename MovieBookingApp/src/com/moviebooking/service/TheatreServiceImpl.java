package com.moviebooking.service;

import com.moviebooking.bean.Theatre;
import com.moviebooking.dao.ITheatreDao;
import com.moviebooking.dao.TheatreDaoImpl;
import com.moviebooking.exception.MovieBookingException;

public class TheatreServiceImpl implements ITheatreService{

	@Override
	public void addTheatre(Theatre theatre) throws MovieBookingException {
		ITheatreDao theatreDao = null;
		theatreDao = new TheatreDaoImpl();
		theatreDao.addTheatre(theatre);
		
	}
}