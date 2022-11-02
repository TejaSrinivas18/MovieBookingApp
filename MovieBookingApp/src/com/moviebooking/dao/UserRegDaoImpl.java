package com.moviebooking.dao;

import java.sql.*;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.moviebooking.bean.User;
import com.moviebooking.exception.MovieBookingException;

public class UserRegDaoImpl implements IUserRegDao {
    Logger logger = Logger.getRootLogger();

	public UserRegDaoImpl() {
		PropertyConfigurator.configure("resources//log4j.properties");
	}
	
    private String jdbcUrl = "jdbc:oracle:thin:@localhost:1521:xe";
    private String userName = "hr";
    private String password = "Teja9191@";

    private static final String INSERT_USERS = "INSERT INTO users VALUES(?,?,?,?,?)";
    
    private static final String SELECT_USER_BY_NAME = "select * from users where user_name =?";
    
/*    private static final String SELECT_ALL_BIKES = "select * from bikes";
    private static final String DELETE_BIKES_SQL = "delete from bikes where id = ?:";
    private static final String UPDATE_BIKES_SQL = "update bikes set id=?,name=?,color=?,speed=?,price=? where id=?:";
    */
    
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
    public void createUser(User user) throws MovieBookingException {
        try (Connection con = getConnection();
             PreparedStatement preparedStatement = con.prepareStatement(INSERT_USERS)) {
            //preparedStatement.setString(1, user.getuId());
            preparedStatement.setString(1, user.getuName());
            preparedStatement.setString(2, user.getPass());
            //preparedStatement.setString(4, user.getDob());
            preparedStatement.setString(3, user.getMailId());
            preparedStatement.setString(4, user.getMobileNo());
            preparedStatement.setString(5, user.getRole());
            
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
           // e.printStackTrace();
            logger.error("Exception Occured :: " + e);
            //System.err.println("Exception Occured :: " + e);
            throw new MovieBookingException("Exception Occured :: " + e);
        }

    }

    @Override
    public User getByUserName(String username) throws MovieBookingException {
        User user = null;
        try (Connection con = getConnection();
             PreparedStatement preparedStatement = con.prepareStatement(SELECT_USER_BY_NAME)) {
        	
        	preparedStatement.setString(1, username);
        	
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                String name = rs.getString("user_name");
                String password = rs.getString("password");
                String email = rs.getString("email");
                String mobileNo = rs.getString("mobile_no");
                String role = rs.getString("role");

                user = new User(name, password, email, mobileNo, role);
            }

        } catch (SQLException e) {
            //e.printStackTrace();
            logger.error("Exception Occured :: " + e);
            //System.err.println("Exception Occured :: " + e);
            throw new MovieBookingException("Exception Occured :: " + e);
        }
        return user;
    }
}
