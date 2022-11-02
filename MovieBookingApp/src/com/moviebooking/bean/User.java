package com.moviebooking.bean;

public class User {

    //private String uId;
    private String uName;
    private String pass;
    //private String dob;
    private String mailId;
    private String mobileNo;
    private String role;

	public User(String uName, String pass, String mailId, String mobileNo, String role) {
		super();
		//this.uId = uId;
		this.uName = uName;
		this.pass = pass;
		this.mailId = mailId;
		this.mobileNo = mobileNo;
		this.role = role;
	}

//	public String getuId() {
//		return uId;
//	}
//
//	public void setuId(String uId) {
//		this.uId = uId;
//	}

	public String getuName() {
		return uName;
	}

	public void setuName(String uName) {
		this.uName = uName;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getMailId() {
		return mailId;
	}

	public void setMailId(String mailId) {
		this.mailId = mailId;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "User [uName=" + uName + ", pass=" + pass + ", mailId=" + mailId + ", mobileNo=" + mobileNo + ", role="
				+ role + "]";
	}
    
}
