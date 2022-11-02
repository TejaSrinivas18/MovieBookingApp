package com.moviebooking.bean;

public class Theatre {
    private String tId;
    private String tName;
    private String tCity;
    
	public Theatre() {
		super();
	}

	public Theatre(String tId, String tName, String tCity) {
		super();
		this.tId = tId;
		this.tName = tName;
		this.tCity = tCity;
	}

	public String gettId() {
		return tId;
	}

	public void settId(String tId) {
		this.tId = tId;
	}

	public String gettName() {
		return tName;
	}

	public void settName(String tName) {
		this.tName = tName;
	}

	public String getTCity() {
		return tCity;
	}

	public void setTcity(String tCity) {
		this.tCity = tCity;
	}

	@Override
	public String toString() {
		return "Theatre [tId=" + tId + ", tName=" + tName + ", tCity=" + tCity + "]";
	}
    
}

