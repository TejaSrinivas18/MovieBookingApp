package com.moviebooking.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.moviebooking.bean.Notification;
import com.moviebooking.exception.MovieBookingException;

public class NotificationDaoImpl implements INotificationDao {
    Logger logger = Logger.getRootLogger();

	public NotificationDaoImpl() {
		PropertyConfigurator.configure("resources//log4j.properties");
	}
	
	private String jdbcUrl = "jdbc:oracle:thin:@localhost:1521:xe";
    private String userName = "hr";
    private String password = "Teja9191@";

    private static final String INSERT_NOTIFICATION = "INSERT INTO notification VALUES(?,?,?,?,?,?,?)";
    
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
	public void createNotification(Notification tobeSendNotification) throws MovieBookingException {
		try (Connection con = getConnection();
	             PreparedStatement preparedStatement = con.prepareStatement(INSERT_NOTIFICATION)) {
	            
			    preparedStatement.setString(1, tobeSendNotification.getId());
	            preparedStatement.setString(2, tobeSendNotification.getBookingId());
	            preparedStatement.setString(3, tobeSendNotification.getReceiverEmail());
	            preparedStatement.setString(4, tobeSendNotification.getReceiverMobileNo());
	            preparedStatement.setString(5, tobeSendNotification.getReceiverType());
	            preparedStatement.setString(6, tobeSendNotification.getSenderEmail());
	            preparedStatement.setString(7, tobeSendNotification.getStatus());
	            
	            preparedStatement.executeUpdate();
	        } catch (SQLException e) {
	            //e.printStackTrace();
	            logger.error("Exception Occured :: " + e);
	            //System.err.println("Exception Occured :: " + e);
	            throw new MovieBookingException("Exception Occured :: " + e);
	        }		
	}

}
