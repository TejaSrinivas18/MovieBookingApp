package com.moviebooking.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.moviebooking.bean.Seats;
import com.moviebooking.exception.BookingException;
import com.moviebooking.exception.MovieBookingException;

public class SeatsDaoImpl implements ISeatsDao {
    Logger logger = Logger.getRootLogger();

	public SeatsDaoImpl() {
		PropertyConfigurator.configure("resources//log4j.properties");
	}
	
	private String jdbcUrl = "jdbc:oracle:thin:@localhost:1521:xe";
	private String userName = "hr";
	private String password = "Teja9191@";

	private static final String INSERT_SEATS = "INSERT INTO seats VALUES(?,?,?,?,?,?,?)";
	 
	private static final String AVAILABLE_SEATS = "select * from seats where movie_id = ? and theatre_id=? and starts_at = ? and booked = 'no'";
	 
	private static final String SELECT_SEATS = "select * from seats where seat_no =?";
	
	private static final String UPDATE_BOOKED_STATUS = "update seats set booked =? where seat_no=?";
	
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
	public void addSeats(Seats seats) throws MovieBookingException {
		try (Connection con = getConnection();
	            PreparedStatement preparedStatement = con.prepareStatement(INSERT_SEATS)) {
	           // preparedStatement.setString(1, seats.getsId());
	            preparedStatement.setString(1, seats.getSeatType());
	            preparedStatement.setString(2, seats.getSeatNo());	 
	            preparedStatement.setString(3, seats.getBooked());
	            preparedStatement.setInt(4, seats.getPrice());
	            preparedStatement.setString(5, seats.getmId());
	            preparedStatement.setString(6, seats.gettId());
	            preparedStatement.setString(7, seats.getStartsAt());
	            
	            preparedStatement.executeUpdate();
	        } catch (SQLException e) {
	            //e.printStackTrace();
	            logger.error("Exception Occured :: " + e);
	            //System.err.println("Exception Occured :: " + e);
	            throw new MovieBookingException("Exception Occured :: " + e);
	        }
		
	}

	@Override
	public List<Seats> getAvailabilitySeats(String mId, String tId, String startsAt) throws MovieBookingException {
		List<Seats> lSeats = new ArrayList<>();
		
		try (Connection con = getConnection();
	            PreparedStatement preparedStatement = con.prepareStatement(AVAILABLE_SEATS)) {
          preparedStatement.setString(1,mId);
          preparedStatement.setString(2,tId);
          preparedStatement.setString(3,startsAt);
        	
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                
                String seatType =rs.getString("seat_type");
                String seatNo = rs.getString("seat_no");
                String booked =rs.getString("booked");
                int price =rs.getInt("price");
                String movieId =rs.getString("movie_id");
                String theatreId =rs.getString("theatre_id");
                String scstartsAt =rs.getString("starts_at");
               // String primarykey =rs.getString("primarykey");
                
                Seats seats = new Seats(seatType,seatNo,booked,price,movieId,theatreId,scstartsAt);
                lSeats.add(seats);
            }

        } catch (SQLException e) {
            //e.printStackTrace();
            logger.error("Exception Occured :: " + e);
           // System.err.println("Exception Occured :: " + e);
            throw new MovieBookingException("Exception Occured :: " + e);
        }
        return lSeats;  
	}

	@Override
	public Seats findBySeat(String seatNo) {
		//List<Seats> lSeats = new ArrayList<>();
		Seats seats = null;
		try (Connection con = getConnection();
	            PreparedStatement preparedStatement = con.prepareStatement(SELECT_SEATS)) {
                preparedStatement.setString(1, seatNo);
        	
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                
                String seatType =rs.getString("seat_type");
                String seatno = rs.getString("seat_no");
                String booked =rs.getString("booked");
                int price =rs.getInt("price");
                String movieId =rs.getString("movie_id");
                String theatreId =rs.getString("theatre_id");
                String startsAt =rs.getString("starts_at");
               // String primarykey =rs.getString("primarykey");
                
                seats = new Seats(seatType,seatno,booked,price,movieId,theatreId,startsAt);
               // lSeats.add(seats);
            }

        } catch (SQLException e) {
            //e.printStackTrace();
            logger.error("Exception Occured :: " + e);
            //System.err.println("Exception Occured :: " + e);
            throw new BookingException("Exception Occured :: " + e);
        }
        return seats; 
	}

	@Override
	public void update(String booked, String seatNo) {
		try (Connection con = getConnection();
	            PreparedStatement preparedStatement = con.prepareStatement(UPDATE_BOOKED_STATUS)) {
	            //preparedStatement.setString(1, screen.getScreenId());
	            preparedStatement.setString(1, booked);
	            preparedStatement.setString(2, seatNo);
	     
	            preparedStatement.executeUpdate();
	        } catch (SQLException e) {
	            //e.printStackTrace();
	            logger.error("Exception Occured :: " + e);
	           // System.err.println("Exception Occured :: " + e);
	            throw new BookingException("Exception Occured :: " + e);
	        }
		
	}

}
