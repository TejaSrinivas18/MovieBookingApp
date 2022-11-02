package com.moviebooking.ui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.google.zxing.WriterException;
import com.moviebooking.bean.Movie;
import com.moviebooking.bean.Screen;
import com.moviebooking.bean.Seats;
import com.moviebooking.bean.Theatre;
import com.moviebooking.bean.User;
import com.moviebooking.exception.MovieBookingException;
import com.moviebooking.service.BookingServiceImpl;
import com.moviebooking.service.IBookingService;
import com.moviebooking.service.IMovieService;
import com.moviebooking.service.IScreenService;
import com.moviebooking.service.ISeatsService;
import com.moviebooking.service.ITheatreService;
import com.moviebooking.service.IUserRegService;
import com.moviebooking.service.MovieServiceImpl;
import com.moviebooking.service.ScreenServiceImpl;
import com.moviebooking.service.SeatsServiceImpl;
import com.moviebooking.service.TheatreServiceImpl;
import com.moviebooking.service.UserRegServiceImpl;
import com.moviebooking.util.ScreenUtil;

public class AdminUI {
    static Scanner sc = new Scanner(System.in);
    static Logger logger = Logger.getRootLogger();

    public static void main(String[] args)  {
    	PropertyConfigurator.configure("resources//log4j.properties");
    	
        System.out.println("Welcome to movie booking application");
        while(true) {
            System.out.println("CHOOSE OPTIONS : 1.signup 2.login");
            int ch = sc.nextInt();
            switch (ch) {
                case 1:
                    signup();
                    break;
                case 2:
                	try {
                        login();
                	}catch(Exception e) {
                		logger.error("Exception Occured :: "+e);
                    	System.err.println("Exception Occured :: "+e);
                	}
                    break;
                case 3:
                    System.exit(0);
                    break;
                default:
                    System.out.println("invalid choice");
                    break;
            }
        }// while (true);
    }

    private static void signup() {
    	
        User user = inputFromUser();
        IUserRegService userRegService = null;
        userRegService = new UserRegServiceImpl();
        
        try {
        	 // throw new RuntimeException("exception raised");
             userRegService.createUser(user);
             logger.info("user registration completed");
             System.out.println("user registration completed");
        }catch(Exception e) {
        	logger.error("Exception Occured :: user registration failed"+e);
        	System.err.println("Exception Occured :: user registration failed" +e);
        }
    }

    private static User inputFromUser() {
//    	System.out.println("enter user id");
//    	String userId = sc.next();
    	
    	System.out.println("enter user name");
    	String userName = sc.next();
    	
//    	String userId ="^[A-Za-z]{4,15}$";
//		Pattern userP = Pattern.compile(userId);
//		Matcher userM = userP.matcher(userName); 
//		
//		if (!userM.find()) {
//		   throw new RuntimeException("Invalid UserName"); 
//		}
    	
    	System.out.println("enter password");//Teja9191@
    	String password = sc.next();

//    	String id ="^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,15}$";
//		Pattern p = Pattern.compile(id);
//		Matcher m = p.matcher(password); 
//		
//		if (!m.find()) {
//		   throw new RuntimeException("Invalid Password"); 
//		}
    	
    	System.out.println("enter email"); //"^(.+)@(\\S+)$"; //"^[A-Za-z0-9+_.-]+@(.+)$";
    	String email = sc.next();          // "^[a-zA-Z0-9_+&*-]+(?:\\."+"[a-zA-Z0-9_+&*-]+)*@" + "(?:[a-zA-Z0-9-]+\\.)+[a-z" + "A-Z]{2,7}$";
 	
//    	String id1 ="^[A-Za-z0-9+_.-]+@(.+)$";
//		Pattern p1 = Pattern.compile(id1);
//		Matcher m1 = p1.matcher(email); 
//		
//		if (!m1.find()) {
//		   throw new RuntimeException("Invalid email"); 
//		}
    	
    	System.out.println("enter mobile number");
    	String mobileNo = sc.next();
    	
//    	String id2 ="^[9876]\\d{9}$";
//		Pattern p2 = Pattern.compile(id2);
//		Matcher m2 = p2.matcher(mobileNo); 
//		
//		if (!m2.find()) {
//		   throw new RuntimeException("Invalid Mobile Number"); 
//		}
    	
    	System.out.println("enter role type : admin or normal");
    	String role = sc.next();
    	
//    	String roleId ="^[A-Za-z]\\w{4,5}$";
//		Pattern roleP = Pattern.compile(roleId);
//		Matcher roleM = roleP.matcher(role); 
//		
//		if (!roleM.find()) {
//		   throw new RuntimeException("Invalid role"); 
//		}
    	
//    	if(!(role.equalsIgnoreCase("admin") || role.equalsIgnoreCase("normal"))) {
//    		throw new RuntimeException("role should be either admin or normal");
//    	}
    	
    	User user = new User(userName,password,email,mobileNo,role);
		return user;
	}

	private static void login() throws MovieBookingException {
        System.out.println("enter username and password to login to application");
        
        System.out.println("enter username");
        String username = sc.next();
        
        System.out.println("enter password");
        String password = sc.next();
        
        IUserRegService userRegService = null;
        userRegService = new UserRegServiceImpl();
        
        User user = userRegService.getByUserName(username);
             if(user == null ) {
        	     throw new MovieBookingException("User not found in database");
             }
       
        if (username != null && password != null && user != null && user.getuName().equalsIgnoreCase(username) && user.getPass().equalsIgnoreCase(password)) {
            System.out.println("login success");

            if (user.getRole().equalsIgnoreCase("admin")) {
                System.out.println("Admin can do following actions : 1.add theatre 2.add movie 3.add screen 4.add seats 5.book ticket");

                while(true) {
                    System.out.println("CHOOSE OPTIONS : 1.add theatre 2.add movie 3.add screen 4.add seats 5.book ticket 6.exit");
                    int ch = sc.nextInt();
                    switch (ch) {
                        case 1:
                        	addTheatre();
                            break;
                        case 2:
                            addMovie();
                            break;
                        case 3:
                            addScreen();
                            break;
                        case 4:
                            addSeats();
                            break;
                        case 5:
                            bookTicket();
                            break;
                        case 6:
                            System.exit(0);
                            break;
                        default:
                            System.out.println("invalid choice");
                            break;
                    }

                } //while (true);

            } else if (user.getRole().equalsIgnoreCase("normal")) {
            	System.out.println("User can do following actions : 1.bookticket 2.getAvailabilityAsScreen");
            	
                while(true) {
                    System.out.println("CHOOSE OPTIONS : 1.book ticket 2.getAvailabilityAsScreen");
                    int ch = sc.nextInt();
                    switch (ch) {
                        case 1:
                        	try {
                                bookTicket();
                        	}catch(Exception e) {
                        		logger.error("Exception Occured :: "+e);
                            	System.err.println("Exception Occured :: "+e);
                        	}
                            break;
                        case 2:
                        	getAvailabilityAsScreen();
                        	break;
                        case 3:
                            System.exit(0);
                            break;
                        default:
                            System.out.println("invalid choice");
                            break;
                    }
                } //while (true);
            }
            
        }else {
        	System.out.println("login failed");
        }

    }

	private static void addSeats() {
//    	System.out.println("enter seat id");
//    	String seatId = sc.next();
    	
//    	System.out.println("enter seat type");
//    	String seatType = sc.next();
//    	
//    	System.out.println("enter seat no");
//    	String seatNo = sc.next();
//    	
//    	System.out.println("enter seat booked");
//    	String booked = sc.next();
//    	
//    	System.out.println("enter seat price");
//    	int seatPrice = sc.nextInt();
    	
    	System.out.println("enter movie id ");
    	String movieId = sc.next();
    	
    	System.out.println("enter theatre id");
    	String theatreId = sc.next();
    	
    	System.out.println("enter start time ");
    	String startsAt = sc.next();
    	
        Seats seats = new Seats(movieId,theatreId,startsAt);
        
        ISeatsService seatsService = null;
        seatsService = new SeatsServiceImpl();
        
        try {
        	seatsService.addSeats(seats);       
            logger.info("seats details added successfully");	
            System.out.println("seats details added successfully");
        }catch(Exception e) {
        	logger.error("Exception Occured :: seats details are not added"+e);
        	System.err.println("Exception Occured :: seats details are not added"+e);
        }
        
	}

	private static void addScreen() {
		System.out.println("enter screen start time");
    	String startsAt = sc.next();
    	
    	System.out.println("enter screen end time");
    	String endsAt = sc.next();
    	
    	System.out.println("enter movie id");
    	String movieId = sc.next();
    	
    	System.out.println("enter theatre id");
    	String theatreId = sc.next();
    	
        Screen screen = new Screen(startsAt,endsAt,movieId,theatreId);
        
        IScreenService screenService = null;
        screenService = new ScreenServiceImpl();
        
        try {
        	screenService.addScreen(screen);      
        	logger.info("screen details added successfully");	
            System.out.println("screen details added successfully");
        }catch(Exception e) {
        	logger.error("Exception Occured :: Screen details are not added"+e);
        	System.err.println("Exception Occured :: Screen details are not added"+e);
        }
		
	}

    private static void addMovie() {
    	System.out.println("enter movie id");
    	String movieId = sc.next();
    	
    	System.out.println("enter movie name");
    	String movieName = sc.next();
    	
    	System.out.println("enter movie language");
    	String language = sc.next();
    	
    	System.out.println("enter movie duration");
    	String duration = sc.next();
    	
    	System.out.println("enter movie director");
    	String director = sc.next();
    	
    	System.out.println("enter theatre id");
    	String theatreId = sc.next();
    	
        Movie movie = new Movie(movieId,movieName,language,duration,director,theatreId);
        
        IMovieService movieService = null;
        movieService = new MovieServiceImpl();
       // movieService.addMovie(movie);	
        
        try {
        	movieService.addMovie(movie);     
        	logger.info("movie details added successfully");	
            System.out.println("movie details added successfully");
        }catch(Exception e) {
        	logger.error("Exception Occured :: movie details are not added"+e);
        	System.err.println("Exception Occured :: movie details are not added"+e);
        }
        
    }

    private static void addTheatre() {
    	System.out.println("enter theatre id");
    	String theatreId = sc.next();
    	
    	System.out.println("enter theatre name");
    	String theatreName = sc.next();
    	
    	System.out.println("enter theatre city");
    	String theatreCity = sc.next();
    	
        Theatre theatre = new Theatre(theatreId,theatreName,theatreCity);
        
        ITheatreService theatreService = null;
        theatreService = new TheatreServiceImpl();
        
        try {
              theatreService.addTheatre(theatre); 
              logger.info("theatre details added successfully");	
              System.out.println("theatre details added successfully");
        }catch(Exception e) {
        	logger.error("Exception Occured:: theatre deatils are not added"+e);
        	System.err.println("Exception Occured:: theatre deatils are not added"+e);
        }
        
    }
    
    private static void getAvailabilityAsScreen() {
    	IScreenService iScreenService = new ScreenServiceImpl();
	    List<Screen> listScreen = null;
	    
		try {
			listScreen = iScreenService.getScreenInfo();
		} catch (MovieBookingException e) {
			
			//e1.printStackTrace();
			logger.error("Exception Occured:: screen info not displayed"+e);
        	System.err.println("Exception Occured:: screen info not displayed"+e);
		}
	    
	    ScreenUtil screenUtil = new ScreenUtil();
	    List<String> movies = screenUtil.getMovies(listScreen);
	    
    	System.out.println("u can select one of these movie id: " + movies);
    	String mId = sc.next();
    	
    	List<String> theatres = screenUtil.getTheatres(listScreen);
    	
    	System.out.println("u can select one of these theatre id: " + theatres);
    	String tId = sc.next();
    	
    	List<String> timings = screenUtil.getStartTime(listScreen);
    	
    	System.out.println("u can select one of these timing: " + timings);
    	String startsAt = sc.next();
    	
    	IBookingService bookService = null;
        bookService = new BookingServiceImpl();
//      List<Seats> availableSeats=bookService.getAvailabiltyAsScreen(mId,tId,startsAt);
    	
    	try {
    		List<Seats> availableSeats =bookService.getAvailabiltyAsScreen(mId,tId,startsAt);
            logger.info("Available Seats ::" + availableSeats);	
            System.out.println("Available Seats ::" + availableSeats);
        }catch(Exception e) {
      	    logger.error("Exception Occured:: Available Seats are not displayed" +e);
      	    System.err.println("Exception Occured:: Available Seats are not displayed"+e);
      }
    	
	}
    
    private static void bookTicket() throws MovieBookingException {
    	boolean flag = true;
    	List<Seats> sList = new ArrayList<>();
    	String username = null;
    	int count = 0;
    	
    	do {
    		count++;
    		
    	    System.out.println("enter seat type");
    	    String seatType = sc.next();
    	
    	    System.out.println("enter seat no");
    	    String seatNo = sc.next(); 	
    	
    	    System.out.println("status booked");
    	    String booked = sc.next();
    	
//    		System.out.println("enter price");
//    		int price = sc.nextInt();
    	    
    	    IScreenService iScreenService = new ScreenServiceImpl();
    	    List<Screen> listScreen = iScreenService.getScreenInfo();
    	    
    	    ScreenUtil screenUtil = new ScreenUtil();
    	    List<String> movies = screenUtil.getMovies(listScreen);
    	    
    	    System.out.println("u can select one of these movie id: " + movies);
    	    String mId = sc.next();
    	    
    	    List<String> theatres = screenUtil.getTheatres(listScreen);
    	
    	    System.out.println("u can select one of these theatre id: " + theatres);
    	    String tId = sc.next();
    	    
    	    List<String> timings = screenUtil.getStartTime(listScreen);
    	
    	    System.out.println("u can select one of these timing: " + timings);
    	    String startsAt = sc.next();
    	
    	    System.out.println("Enter user name");
    	    username = sc.next();
    	   	
    	    Seats seats = new Seats(seatType,seatNo,booked,mId,tId,startsAt);
    	    //seats.setPrimaryKey(key);
    	    //List<Seats> sList = new ArrayList<>();
    	    sList.add(seats);
        
            System.out.println("if u want to book another ticket : enter yes otherwise no");
            String option = sc.next();
        
            if (option.equals("no")) {
        	    flag = false;
            }
            
            if (count >= 6) {
            	 throw new RuntimeException("u can book maximum 5 tickets only");
            }
            
        
    	}while(flag);
        
    	IBookingService bookService = null;
        bookService = new BookingServiceImpl();
        bookService.bookSeats(sList,username);
       
    }
}
