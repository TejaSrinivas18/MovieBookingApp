package com.moviebooking.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.moviebooking.bean.Payment;
import com.moviebooking.exception.BookingException;
import com.moviebooking.exception.MovieBookingException;

public class PaymentDaoImpl implements IPaymentDao {
    Logger logger = Logger.getRootLogger();

	public PaymentDaoImpl() {
		PropertyConfigurator.configure("resources//log4j.properties");
	}
	
	private String jdbcUrl = "jdbc:oracle:thin:@localhost:1521:xe";
    private String userName = "hr";
    private String password = "Teja9191@";

    private static final String INSERT_PAYMENT = "INSERT INTO payment VALUES(?,?,?,?,?)";
    
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
	public void createPayment(Payment payment) {
		try (Connection con = getConnection();
	             PreparedStatement preparedStatement = con.prepareStatement(INSERT_PAYMENT)) {
	            
	            preparedStatement.setString(1, payment.getPaymentId());
	            preparedStatement.setInt(2, payment.getAmountToBePaid());
	            preparedStatement.setString(3, payment.getStatus());
	            preparedStatement.setString(4, payment.getPaymentType());
	            preparedStatement.setString(5, payment.getCardNo());
	            //preparedStatement.setString(6, payment.getBookingId());
	            
	            preparedStatement.executeUpdate();
	        } catch (SQLException e) {
	            //e.printStackTrace();
	            logger.error("Exception Occured :: " + e);
	           // System.err.println("Exception Occured :: " + e);
	            throw new BookingException("Exception Occured :: " + e);
	        }
		
	}

}
