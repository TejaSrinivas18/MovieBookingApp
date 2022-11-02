package com.moviebooking.bean;

public class Payment {
	private String paymentId;
	private int amountToBePaid;
	private String status;
	private String paymentType;
	private String cardNo;
	//private String bookingId;
	
	public Payment() {
		super();
	}

	public Payment(String paymentId, int amountToBePaid, String status, String paymentType, String cardNo
			) {
		super();
		this.paymentId = paymentId;
		this.amountToBePaid = amountToBePaid;
		this.status = status;
		this.paymentType = paymentType;
		this.cardNo = cardNo;
		//this.bookingId = bookingId;
	}

	public String getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(String paymentId) {
		this.paymentId = paymentId;
	}

	public int getAmountToBePaid() {
		return amountToBePaid;
	}

	public void setAmountToBePaid(int amountToBePaid) {
		this.amountToBePaid = amountToBePaid;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}

	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	@Override
	public String toString() {
		return "Payment [paymentId=" + paymentId + ", amountToBePaid=" + amountToBePaid + ", status=" + status
				+ ", paymentType=" + paymentType + ", cardNo=" + cardNo + "]";
	}

//	public String getBookingId() {
//		return bookingId;
//	}
//
//	public void setBookingId(String bookingId) {
//		this.bookingId = bookingId;
//	}


}
