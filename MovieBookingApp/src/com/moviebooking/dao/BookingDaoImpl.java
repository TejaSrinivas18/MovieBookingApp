package com.moviebooking.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.moviebooking.bean.Booking;
import com.moviebooking.exception.BookingException;
import com.moviebooking.exception.MovieBookingException;

public class BookingDaoImpl implements IBookingDao {
    Logger logger = Logger.getRootLogger();

	public BookingDaoImpl() {
		PropertyConfigurator.configure("resources//log4j.properties");
	}
	
	 private String jdbcUrl = "jdbc:oracle:thin:@localhost:1521:xe";
	 private String userName = "hr";
	 private String password = "Teja9191@";

	 private static final String BOOKING_DETAILS = "INSERT INTO booking VALUES(?,?,?,?,?,?,?,?)";
	
	 private Connection getConnection() {
	        Connection con = null;
	        try {
	            con = DriverManager.getConnection(jdbcUrl, userName, password);
	        } catch (SQLException e) {
	            //e.printStackTrace();
	            logger.error("Exception Occured :: " + e);
	            System.err.println("Exception Occured :: " + e);
	        }
	        return con;
     }
	 
	@Override
	public void bookSeats(Booking booking)  {
		try (Connection con = getConnection();
	            PreparedStatement preparedStatement = con.prepareStatement(BOOKING_DETAILS)) {
			    
			    preparedStatement.setString(1, booking.getBookingId());
	            preparedStatement.setString(2, booking.getUserName());
	            preparedStatement.setString(3, booking.getmId());
	            preparedStatement.setString(4, booking.gettId());	 
	            preparedStatement.setInt(5, booking.getSeatsBooked());
	            preparedStatement.setString(6, booking.getSeatNumbers());
	            preparedStatement.setInt(7, booking.getTotalPrice());
	            preparedStatement.setString(8, booking.getPaymentId());
	            
	            preparedStatement.executeUpdate();
	        } catch (SQLException e) {
	           // e.printStackTrace();
	            logger.error("Exception Occured :: " + e);
	            //System.err.println("Exception Occured :: " + e);
	            throw new BookingException("Exception Occured :: " + e);
	        }	
	}

}
