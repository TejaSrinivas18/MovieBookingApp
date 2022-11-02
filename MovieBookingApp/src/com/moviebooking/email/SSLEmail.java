package com.moviebooking.email;

import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.moviebooking.bean.Booking;
import com.moviebooking.bean.Notification;

public class SSLEmail {
    static Logger logger = Logger.getRootLogger();
	
	public SSLEmail() {
		PropertyConfigurator.configure("resources//log4j.properties");
	}
	/**
	   Outgoing Mail (SMTP) Server
	   requires TLS or SSL: smtp.gmail.com (use authentication)
	   Use Authentication: Yes
	   Port for SSL: 465
	 */
	public static void notify(Notification notification,Booking booking) {
		final String fromEmail = notification.getSenderEmail(); //requires valid gmail id
		final String password = "pertxhdryirxhwzk"; // correct password for gmail id
		final String toEmail = notification.getReceiverEmail(); // can be any email id 
		
		logger.info("SSLEmail Start");
		System.out.println("SSLEmail Start");
		
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com"); //SMTP Host
		props.put("mail.smtp.socketFactory.port", "465"); //SSL Port
		props.put("mail.smtp.socketFactory.class",
				"javax.net.ssl.SSLSocketFactory"); //SSL Factory Class
		props.put("mail.smtp.auth", "true"); //Enabling SMTP Authentication
		props.put("mail.smtp.port", "465"); //SMTP Port
		
		Authenticator auth = new Authenticator() {
			//override the getPasswordAuthentication method
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(fromEmail, password);
			}
		};
		
		Session session = Session.getDefaultInstance(props, auth);
		logger.info("Session created");
		System.out.println("Session created");
		    
		String bookingId = booking.getBookingId();
		String movieId = booking.getmId();
		String seatNos = booking.getSeatNumbers();
		String theatreId = booking.gettId();
		int totalPrice = booking.getTotalPrice();
		int noOfSeatsBooked = booking.getSeatsBooked();

	        EmailUtil.sendEmail(session, toEmail,"Movie Ticket Booking Details", "Booking ID: "+ bookingId+"\n "+"Movie ID: "+movieId+"\n "+"Seat Nos: "+seatNos+"\n "+"Theatre ID: "+theatreId+"\n "+"Total Amount: "+totalPrice+"\n "+"No Of Seats Booked: "+noOfSeatsBooked);

	       // EmailUtil.sendAttachmentEmail(session, toEmail,"SSLEmail Testing Subject with Attachment", "SSLEmail Testing Body with Attachment");

	       // EmailUtil.sendImageEmail(session, toEmail,"SSLEmail Testing Subject with Image", "SSLEmail Testing Body with Image");
           //bookingid+" "+movieid+" "+seatnos+" "+theatreid+" "+totprice+" "+noofseatsbook;
	}

}