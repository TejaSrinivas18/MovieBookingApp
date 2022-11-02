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

import com.moviebooking.bean.Screen;
//import com.moviebooking.bean.User;
import com.moviebooking.exception.MovieBookingException;

public class ScreenDaoImpl implements IScreenDao {
    Logger logger = Logger.getRootLogger();

	public ScreenDaoImpl() {
		PropertyConfigurator.configure("resources//log4j.properties");
	}

	private String jdbcUrl = "jdbc:oracle:thin:@localhost:1521:xe";
	private String userName = "hr";
	private String password = "Teja9191@";

	 private static final String INSERT_SCREEN = "INSERT INTO screen VALUES(?,?,?,?)";
	 
	 private static final String SELECT_SCREEN = "SELECT * FROM screen";
	
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
	public void addScreen(Screen screen) throws MovieBookingException {
		try (Connection con = getConnection();
	            PreparedStatement preparedStatement = con.prepareStatement(INSERT_SCREEN)) {
	            preparedStatement.setString(1, screen.getStartsAt());
	            preparedStatement.setString(2, screen.getEndsAt());
	            preparedStatement.setString(3, screen.getmId());	 
	            preparedStatement.setString(4, screen.gettId());
	           	            
	            preparedStatement.executeUpdate();
	        } catch (SQLException e) {
	            //e.printStackTrace();
	            logger.error("Exception Occured :: " + e);
	            //System.err.println("Exception Occured :: " + e);
	            throw new MovieBookingException("Exception Occured :: " + e);
	        }
		
	}

	@Override
	public List<Screen> getScreenInfo() throws MovieBookingException {
		List<Screen> listScreen = new ArrayList<>();
		Screen screen = null;
        try (Connection con = getConnection();
             PreparedStatement preparedStatement = con.prepareStatement(SELECT_SCREEN)) {
        	
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                String startsAt = rs.getString("STARTS_AT");
                String endsAt = rs.getString("ENDS_AT");
                String movieId = rs.getString("MOVIE_ID");
                String theatreId = rs.getString("THEATRE_ID");
                //String role = rs.getString("role");

                screen = new Screen(startsAt, endsAt, movieId, theatreId);
                listScreen.add(screen);
            }

        } catch (SQLException e) {
            //e.printStackTrace();
            logger.error("Exception Occured :: " + e);
            //System.err.println("Exception Occured :: " + e);
            throw new MovieBookingException("Exception Occured :: " + e);
        }
		return listScreen;
	}

}
