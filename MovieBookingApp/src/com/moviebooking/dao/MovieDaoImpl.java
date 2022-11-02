package com.moviebooking.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.moviebooking.bean.Movie;
import com.moviebooking.exception.MovieBookingException;

public class MovieDaoImpl implements IMovieDao {
    Logger logger = Logger.getRootLogger();

	public MovieDaoImpl() {
		PropertyConfigurator.configure("resources//log4j.properties");
	}
	
	 private String jdbcUrl = "jdbc:oracle:thin:@localhost:1521:xe";
	 private String userName = "hr";
	 private String password = "Teja9191@";

	 private static final String INSERT_MOVIE = "INSERT INTO movie VALUES(?,?,?,?,?,?)";
	
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
	public void addMovie(Movie movie) throws MovieBookingException {
		try (Connection con = getConnection();
	            PreparedStatement preparedStatement = con.prepareStatement(INSERT_MOVIE)) {
	            preparedStatement.setString(1, movie.getmId());
	            preparedStatement.setString(2, movie.getmName());
	            preparedStatement.setString(3, movie.getLang());	 
	            preparedStatement.setString(4, movie.getDuration());
	            preparedStatement.setString(5, movie.getDirector());
	            preparedStatement.setString(6, movie.getTId());
	            
	            preparedStatement.executeUpdate();
	        } catch (SQLException e) {
	           // e.printStackTrace();
	            logger.error("Exception Occured :: " + e);
	            //System.err.println("Exception Occured :: " + e);
	            throw new MovieBookingException("Exception Occured :: "+e);
	        }
	}

}
