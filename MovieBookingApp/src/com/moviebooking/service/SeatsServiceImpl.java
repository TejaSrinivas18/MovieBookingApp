package com.moviebooking.service;

import java.util.HashMap;
import java.util.Map;

import com.moviebooking.bean.Seats;
import com.moviebooking.dao.ISeatsDao;
import com.moviebooking.dao.SeatsDaoImpl;
import com.moviebooking.exception.MovieBookingException;

public class SeatsServiceImpl implements ISeatsService{
	
	private final int DEFAULT_SEAT_COUNT = 10;
	private final int DEFAULT_SEAT_PRICE = 100;
	
	@Override
	public void addSeats(Seats seats) throws MovieBookingException {
//		List<Seats> list = new ArrayList<>();
//		list.add(seats);
		//validateInput(list);
		
		for (int i = 0; i < DEFAULT_SEAT_COUNT; i++) {
			Seats newMatrix = new Seats();
			
			newMatrix.setBooked("no");
			newMatrix.setPrice(DEFAULT_SEAT_PRICE);
			newMatrix.setSeatType("NORMAL");
			
//			//SeatMatrixPk matrixPk = new SeatMatrixPk();
			
			newMatrix.setmId(seats.getmId());
			newMatrix.setStartsAt(seats.getStartsAt());
			newMatrix.settId(seats.gettId());
			newMatrix.setSeatNo(makeSeatNumber(i));
			//String uniqueID = UUID.randomUUID().toString();
			//newMatrix.setPrimaryKey(seat.);
			ISeatsDao seatsDao= null;
			seatsDao = new SeatsDaoImpl();
			seatsDao.addSeats(newMatrix);

		}
	}
	
	private String makeSeatNumber(int i) {
		StringBuffer sb = new StringBuffer();          //mutable // thread safe
		Map<Integer, Character> map = new HashMap<>();
		
		for (char c = 'A'; c <= 'F'; c++) {
			int key = c - 'A';
			char value = c;
			map.put(key, value);
		}
		
		char ch = map.get(i / 10);
		int index = i % 10;
		sb.append("" + ch + index);
		return sb.toString();
	}

}