package com.moviebooking.service;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.google.zxing.WriterException;
import com.moviebooking.bean.Booking;
import com.moviebooking.bean.Notification;
//import com.moviebooking.bean.NotificationType;
import com.moviebooking.bean.Payment;
import com.moviebooking.bean.Seats;
import com.moviebooking.bean.User;
import com.moviebooking.dao.BookingDaoImpl;
import com.moviebooking.dao.IBookingDao;
import com.moviebooking.dao.INotificationDao;
import com.moviebooking.dao.IPaymentDao;
import com.moviebooking.dao.ISeatsDao;
import com.moviebooking.dao.IUserRegDao;
import com.moviebooking.dao.NotificationDaoImpl;
import com.moviebooking.dao.PaymentDaoImpl;
import com.moviebooking.dao.SeatsDaoImpl;
import com.moviebooking.dao.UserRegDaoImpl;
import com.moviebooking.email.SSLEmail;
import com.moviebooking.exception.AlreadyBookedException;
import com.moviebooking.exception.MovieBookingException;

public class BookingServiceImpl implements IBookingService {
	Logger logger = Logger.getRootLogger();
	
	IUserRegDao userRegDao = new UserRegDaoImpl();
	
	public BookingServiceImpl() {
		PropertyConfigurator.configure("resources//log4j.properties");
	}

	@Override
	public List<Seats> getAvailabiltyAsScreen(String mId, String tId, String startsAt) throws MovieBookingException {
		ISeatsDao seatsDao = null;
		seatsDao = new SeatsDaoImpl();
		List<Seats> availableseats = seatsDao.getAvailabilitySeats(mId,tId,startsAt);
		return availableseats;
	}

	@Override
	public void bookSeats(List<Seats> sList, String username) throws MovieBookingException  {
		validateUserName(username);
		
		ISeatsDao seatsDao = null;
		seatsDao = new SeatsDaoImpl();
		
		StringBuilder sb = new StringBuilder(); //mutable  not thread safe// string is immutable
		
		int totalPrice = 0;
		// update seat matrix first
		for (Seats seat : sList) {
			
			 Seats seatFromDb = seatsDao.findBySeat(seat.getSeatNo());
			 if(seatFromDb == null ) {
				 logger.error("Exception Occured :: Seats not found in database");
				 //System.err.println("Exception Occured :: Seats not found in database");
			     throw new MovieBookingException("Exception Occured :: Seat not found in database");
			 }
			 
							
			//Seats oSeatMatrix = seatFromDb.get(0);
			
			if ((!seatFromDb.getmId().equals(seat.getmId())) || (!seatFromDb.gettId().equals(seat.gettId()))) {
				logger.error("Exception Occured :: Invalid movie id or theatre id");
				//System.err.println("Exception Occured :: Invalid movie id or theatre id");
				 throw new MovieBookingException("Invalid movie id or theatre id");
			}
			
			if (seatFromDb != null && seatFromDb.getBooked().equals("yes")) {
			    logger.error("Exception Occured :: The seat number: " + seat.getSeatNo() + " is already booked");
			    //System.err.println("Exception Occured :: The seat number: " + seat.getSeatNo() + " is already booked");
				throw new AlreadyBookedException("The seat number: " + seat.getSeatNo() + " is already booked");
			}	
			
			seatFromDb.setBooked("yes");
			totalPrice += seatFromDb.getPrice();
			
			
			seatsDao.update(seatFromDb.getBooked(),seatFromDb.getSeatNo());
			sb.append(seat.getSeatNo() + " ,");
		}
		
		String seatNumbers = sb.toString();
		seatNumbers = (seatNumbers.endsWith(",")) ? seatNumbers.substring(0, seatNumbers.length() - 2)
				: seatNumbers;
		
		Seats seat = sList.get(0);
		
        Booking booking = new Booking();
		
		String uniqueID = UUID.randomUUID().toString();
		uniqueID = uniqueID.substring(0, 8);
		
		booking.setBookingId(uniqueID);
		booking.setUserName(username);
		booking.setmId(seat.getmId());
		booking.settId(seat.gettId());
		booking.setSeatsBooked(sList.size());
		booking.setSeatNumbers(seatNumbers);
		booking.setTotalPrice(totalPrice);
		
		addQRCode();
		
//		GenerateQRCode qrCode = new GenerateQRCode();
//		
//		String qrCodeText = "https://www.phonepe.com";
//		String filePath = "JD.png";
//		int size = 125;
//		String fileType = "png";
//		File qrFile = new File(filePath);
//		
//		try {
//			qrCode.createQRImage(qrFile, qrCodeText, size, fileType);
//		} catch (WriterException | IOException e) {
//			//e.printStackTrace();
//			logger.error("Exception Occured :: " + e.toString());
//			System.out.println("Exception Occured :: " + e.toString());
//		}
//		
//		System.out.println("DONE");
		
		// adding payment method to database
		
		Payment payment = new Payment();
		
		String paymentID = UUID.randomUUID().toString();
		paymentID = paymentID.substring(0, 8);
		
		payment.setPaymentId(paymentID);
		payment.setAmountToBePaid(booking.getTotalPrice());
		payment.setStatus("SUCCESS");
		payment.setPaymentType("Debit Card");
		payment.setCardNo("12345678912");
		//payment.setBookingId(booking.getBookingId());
		
		IPaymentDao paymentDao = null;
		paymentDao = new PaymentDaoImpl();

		 paymentDao.createPayment(payment);
		 logger.info("Payment Completed Successfully!!");
		 System.out.println("Payment Completed Successfully!!");
		
		booking.setPaymentId(payment.getPaymentId());
		
		IBookingDao bookingDao = null;
		bookingDao = new BookingDaoImpl();	
		
//		try {
		    bookingDao.bookSeats(booking);
		    logger.info("Ticket Booked Successfully!!");
		    System.out.println("Ticket Booked Successfully!!");
//		}catch(Exception e) {
//			logger.error("Exception Occured :: Ticket not Booked !!");
//		    System.err.println("Exception Occured :: Ticket not Booked !!");
//		}
		
		User user = userRegDao.getByUserName(username);
		sendNotification(booking, user);
			
	}
	
	private void addQRCode() {
        GenerateQRCode qrCode = new GenerateQRCode();
		
		String qrCodeText = "https://www.phonepe.com";
		String filePath = "JD.png";
		int size = 125;
		String fileType = "png";
		File qrFile = new File(filePath);
		
		try {
			qrCode.createQRImage(qrFile, qrCodeText, size, fileType);
		} catch (WriterException | IOException e) {
			//e.printStackTrace();
			logger.error("Exception Occured :: " + e.toString());
			System.err.println("Exception Occured :: " + e.toString());
		}
		
		System.out.println("DONE");
		logger.info("DONE");
	}
	
	private void sendNotification(Booking booking, User user) {
		Notification tobeSendNotification = new Notification();
		
		String notificationID = UUID.randomUUID().toString();
		notificationID = notificationID.substring(0, 8);
		
		tobeSendNotification.setId(notificationID);
		tobeSendNotification.setBookingId(booking.getBookingId());
		tobeSendNotification.setReceiverEmail(user.getMailId());
		tobeSendNotification.setReceiverMobileNo(user.getMobileNo());
		tobeSendNotification.setReceiverType("EMAIL");
		tobeSendNotification.setSenderEmail("tejasrinivas19@gmail.com");
		
		SSLEmail.notify(tobeSendNotification, booking);
		tobeSendNotification.setStatus("SUCCESS");
		
		//adding notification method to database		
		INotificationDao notificationDao = null;
		notificationDao = new NotificationDaoImpl();
		try {
			notificationDao.createNotification(tobeSendNotification);
		} catch (MovieBookingException e) {
			//e.printStackTrace();
			logger.error("Exception Occured :: " + e.toString());
			System.err.println("Exception Occured :: " + e.toString());
		}
		
	}

	private void validateUserName(String username) throws MovieBookingException  {
		User user = userRegDao.getByUserName(username);
		
		    if (user == null) {
		    	logger.error("Invalid username");
		    	//System.err.println("Invalid username");
			    throw new MovieBookingException("Invalid username");
		    }		
	}
}
