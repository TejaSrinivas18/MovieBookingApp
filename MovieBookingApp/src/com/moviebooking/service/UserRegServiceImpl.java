
package com.moviebooking.service;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.moviebooking.bean.User;
import com.moviebooking.dao.IUserRegDao;
import com.moviebooking.dao.UserRegDaoImpl;
import com.moviebooking.exception.MovieBookingException;

public class UserRegServiceImpl implements IUserRegService {
	Logger logger = Logger.getRootLogger();
	
	public UserRegServiceImpl() {
		PropertyConfigurator.configure("resources//log4j.properties");
	}
	
    @Override
    public void createUser(User user) throws MovieBookingException {
    	validateUser(user);
    	
    	//if(flag) {
    	    encryptPassword(user);
            IUserRegDao userRegDao = null;
            userRegDao = new UserRegDaoImpl();
            userRegDao.createUser(user);
    	//}
    }

    private void validateUser(User user) throws MovieBookingException {
    	//boolean flag= true;
    	List<String> validationErrors = new ArrayList<String>();

		//Validating user name
		if(!(isValidName(user.getuName()))) {
			validationErrors.add("\n User Name Should Be In Alphabets and minimum 5 characters and maximum 15 characters ! \n");
		}
		//Validating password
		if(!(isValidPassword(user.getPass()))){
			validationErrors.add("\n Password Should Be Greater Than 7 Characters and Atmost 15 Characters "
					+ "and Atleast One Upper Case and Atleast One Lower Case and Atleast One Digit "
					+ "and Atleast One Special Character \n");
		}
		//Validating email
		if(!(isValidEmail(user.getMailId()))){ //let pattern = /^[^ ]+@[^ ]+\.[a-z]{2,3}$/
			validationErrors.add("\n email should contain alphabets and digits and end word must be @gmail.com is mandatory \n");
		}
		//Validating Mobile Number
		if(!(isValidMobileNumber(user.getMobileNo()))){
			validationErrors.add("\n Phone Number Should be in 10 digit and it supports indian numbers only \n" );
		}
		//Validating role
		if(!(isValidRole(user.getRole()))){
			validationErrors.add("\n Role Should be either admin or normal \n" );
		}
		
		//try {
		    if(!validationErrors.isEmpty())
		    	//flag = false;
			    throw new MovieBookingException("Validation Errors :: " + validationErrors);
//		}catch(Exception e) {
//			logger.error("Validation Errors :: "+ validationErrors); 
//			System.err.println("Validation Errors :: "+ validationErrors);
//		}
//		return flag;
	}

	private boolean isValidRole(String role) {
    	String roleId ="^[A-Za-z]{5,6}$";
		Pattern roleP = Pattern.compile(roleId);
		Matcher roleM = roleP.matcher(role); 
		
		if (roleM.find()) {
			return true;
		    //throw new RuntimeException("Invalid role"); 
		}
		return false;
	}

	private boolean isValidMobileNumber(String mobileNo) {
		String id2 ="^[9876]\\d{9}$";
		Pattern p2 = Pattern.compile(id2);
		Matcher m2 = p2.matcher(mobileNo); 
		
		if (m2.find()) {
			return true;
		    //throw new RuntimeException("Invalid Mobile Number"); 
		}
		return false;
	}

	private boolean isValidEmail(String mailId) {
		String id1 ="[a-z0-9]+@gmail.com";
		Pattern p1 = Pattern.compile(id1);
		Matcher m1 = p1.matcher(mailId); 
		
		if (m1.find()) {
			return true;
		   //throw new RuntimeException("Invalid email"); 
		}
		return false;
	}

	private boolean isValidPassword(String pass) {
		String id ="^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,15}$";
		Pattern p = Pattern.compile(id);
		Matcher m = p.matcher(pass); 
		
		if (m.find()) {
			return true;
		   //throw new RuntimeException("Invalid Password"); 
		}
		return false;
	}

	private boolean isValidName(String getuName) {
		String userId ="^[A-Za-z]{5,15}$";
		Pattern userP = Pattern.compile(userId);
		Matcher userM = userP.matcher(getuName); 
		
		if (userM.find()) {
			return true;
		  // throw new RuntimeException("Invalid UserName"); 
		}
		return false;
	}

	private void encryptPassword(User user) {
    	if (user.getPass() != null) {
    		Base64.Encoder encoder = Base64.getMimeEncoder();  
            String eStr = encoder.encodeToString(user.getPass().getBytes()); 
            user.setPass(eStr);
            //System.out.println("Encoded MIME message: "+eStr);  
    	}
	}

	@Override
    public User getByUserName(String username) throws MovieBookingException {
        IUserRegDao userRegDao = null;
        userRegDao = new UserRegDaoImpl();
        User user = userRegDao.getByUserName(username);
        
        if (user != null) {
            Base64.Decoder decoder = Base64.getMimeDecoder(); 
            String dStr = new String(decoder.decode(user.getPass()));  
            user.setPass(dStr);  
        }
        
        return user;
    }
}

//Base64.Decoder decoder = Base64.getMimeDecoder();  
// Decoding MIME encoded message  
//String dStr = new String(decoder.decode(eStr));  
