package com.moviebooking.bean;

public class Notification {
    private String id;	
	private String bookingId;		
	private String receiverEmail;	
	private String receiverMobileNo;	
	private String receiverType;	
	private String senderEmail;
	private String status;
	
	public Notification() {
		super();
	}

	public Notification(String id, String bookingId, String receiverEmail, String receiverMobileNo, String receiverType,
			String senderEmail, String status) {
		super();
		this.id = id;
		this.bookingId = bookingId;
		this.receiverEmail = receiverEmail;
		this.receiverMobileNo = receiverMobileNo;
		this.receiverType = receiverType;
		this.senderEmail = senderEmail;
		this.status = status;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getBookingId() {
		return bookingId;
	}

	public void setBookingId(String bookingId) {
		this.bookingId = bookingId;
	}

	public String getReceiverEmail() {
		return receiverEmail;
	}

	public void setReceiverEmail(String receiverEmail) {
		this.receiverEmail = receiverEmail;
	}

	public String getReceiverMobileNo() {
		return receiverMobileNo;
	}

	public void setReceiverMobileNo(String receiverMobileNo) {
		this.receiverMobileNo = receiverMobileNo;
	}

	public String getReceiverType() {
		return receiverType;
	}

	public void setReceiverType(String receiverType) {
		this.receiverType = receiverType;
	}

	public String getSenderEmail() {
		return senderEmail;
	}

	public void setSenderEmail(String senderEmail) {
		this.senderEmail = senderEmail;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Notification [id=" + id + ", bookingId=" + bookingId + ", receiverEmail=" + receiverEmail
				+ ", receiverMobileNo=" + receiverMobileNo + ", receiverType=" + receiverType + ", senderEmail="
				+ senderEmail + ", status=" + status + "]";
	}	
	
}
