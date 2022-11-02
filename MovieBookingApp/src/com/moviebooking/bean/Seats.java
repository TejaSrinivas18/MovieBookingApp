package com.moviebooking.bean;

public class Seats {
   // private String sId;
    private String seatType;
    private String seatNo;
    private String booked;
    private int price;
    private String mId;
    private String tId;
    private String startsAt;
    
	public Seats( String seatType, String seatNo, String booked, int price, String mId, String tId,
			String startsAt) {
		super();
		//this.sId = sId;
		this.seatType = seatType;
		this.seatNo = seatNo;
		this.booked = booked;
		this.price = price;
		this.mId = mId;
		this.tId = tId;
		this.startsAt = startsAt;
	}
	
	public Seats(String seatType, String seatNo, String booked, String mId, String tId, String startsAt) {
		super();
		this.seatType = seatType;
		this.seatNo = seatNo;
		this.booked = booked;
		this.mId = mId;
		this.tId = tId;
		this.startsAt = startsAt;
	}



	public Seats(String mId, String tId, String startsAt) {
		super();
		this.mId = mId;
		this.tId = tId;
		this.startsAt = startsAt;
	}

	public Seats() {
		super();
	}

//	public String getsId() {
//		return sId;
//	}
//
//	public void setsId(String sId) {
//		this.sId = sId;
//	}

	public String getSeatType() {
		return seatType;
	}

	public void setSeatType(String seatType) {
		this.seatType = seatType;
	}

	public String getSeatNo() {
		return seatNo;
	}

	public void setSeatNo(String seatNo) {
		this.seatNo = seatNo;
	}

	public String getBooked() {
		return booked;
	}

	public void setBooked(String booked) {
		this.booked = booked;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getmId() {
		return mId;
	}

	public void setmId(String mId) {
		this.mId = mId;
	}

	public String gettId() {
		return tId;
	}

	public void settId(String tId) {
		this.tId = tId;
	}

	public String getStartsAt() {
		return startsAt;
	}

	public void setStartsAt(String startsAt) {
		this.startsAt = startsAt;
	}

	@Override
	public String toString() {
		return "Seats [seatType=" + seatType + ", seatNo=" + seatNo + ", booked=" + booked + ", price=" + price
				+ ", mId=" + mId + ", tId=" + tId + ", startsAt=" + startsAt + "]";
	}
}
