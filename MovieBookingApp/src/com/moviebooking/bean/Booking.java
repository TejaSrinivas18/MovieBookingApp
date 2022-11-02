package com.moviebooking.bean;

public class Booking {
	
	private String bookingId;
    private String userName;
    private String tId;
    private String mId;
    private int totalPrice;
    private int seatsBooked;
    private String seatNumbers;
    private String paymentId;
    
	public Booking() {
		super();
	}

	public Booking(String bookingId, String userName, String tId, String mId, int totalPrice, int seatsBooked,
			String seatNumbers, String paymentId) {
		super();
		this.bookingId = bookingId;
		this.userName = userName;
		this.tId = tId;
		this.mId = mId;
		this.totalPrice = totalPrice;
		this.seatsBooked = seatsBooked;
		this.seatNumbers = seatNumbers;
		this.paymentId = paymentId;
	}



	public Booking(String bookingId, String userName, String tId, String mId, String seatNumbers) {
		super();
		this.bookingId = bookingId;
		this.userName = userName;
		this.tId = tId;
		this.mId = mId;
		this.seatNumbers = seatNumbers;
	}

	public String getBookingId() {
		return bookingId;
	}

	public void setBookingId(String bookingId) {
		this.bookingId = bookingId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String gettId() {
		return tId;
	}

	public void settId(String tId) {
		this.tId = tId;
	}

	public String getmId() {
		return mId;
	}

	public void setmId(String mId) {
		this.mId = mId;
	}

	public int getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}

	public int getSeatsBooked() {
		return seatsBooked;
	}

	public void setSeatsBooked(int seatsBooked) {
		this.seatsBooked = seatsBooked;
	}

	public String getSeatNumbers() {
		return seatNumbers;
	}

	public void setSeatNumbers(String seatNumbers) {
		this.seatNumbers = seatNumbers;
	}

	public String getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(String paymentId) {
		this.paymentId = paymentId;
	}

	@Override
	public String toString() {
		return "Booking [bookingId=" + bookingId + ", userName=" + userName + ", tId=" + tId + ", mId=" + mId
				+ ", totalPrice=" + totalPrice + ", seatsBooked=" + seatsBooked + ", seatNumbers=" + seatNumbers
				+ ", paymentId=" + paymentId + "]";
	}
    
}
