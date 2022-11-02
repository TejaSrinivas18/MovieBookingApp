package com.moviebooking.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.moviebooking.bean.Theatre;
import com.moviebooking.exception.MovieBookingException;

public class TheatreDaoImpl implements ITheatreDao {
    Logger logger = Logger.getRootLogger();

	public TheatreDaoImpl() {
		PropertyConfigurator.configure("resources//log4j.properties");
	}
	
	 private String jdbcUrl = "jdbc:oracle:thin:@localhost:1521:xe";
	 private String userName = "hr";
	 private String password = "Teja9191@";

	 private static final String INSERT_THEATRE = "INSERT INTO theatre VALUES(?,?,?)";
	
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
	public void addTheatre(Theatre theatre) throws MovieBookingException {
		try (Connection con = getConnection();
	            PreparedStatement preparedStatement = con.prepareStatement(INSERT_THEATRE)) {
	            preparedStatement.setString(1, theatre.gettId());
	            preparedStatement.setString(2, theatre.gettName());
	            preparedStatement.setString(3, theatre.getTCity());	 
	            
	            preparedStatement.executeUpdate();
	        } catch (SQLException e) {
	            //e.printStackTrace();
	            logger.error("Exception Occured :: " + e);
	            //System.err.println("Exception Occured :: " + e);
	            throw new MovieBookingException("Exception Occured :: " + e);
	        }
		
	}

}
