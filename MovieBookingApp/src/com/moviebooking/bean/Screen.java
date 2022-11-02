package com.moviebooking.bean;

public class Screen {
	private String startsAt;
    private String endsAt;
    private String mId;
    private String tId;
    
	public Screen(String startsAt, String endsAt, String mId, String tId) {
		super();
		this.startsAt = startsAt;
		this.endsAt = endsAt;
		this.mId = mId;
		this.tId = tId;
	}

	public Screen() {
		super();
	}

	public String getStartsAt() {
		return startsAt;
	}

	public void setStartsAt(String startsAt) {
		this.startsAt = startsAt;
	}

	public String getEndsAt() {
		return endsAt;
	}

	public void setEndsAt(String endsAt) {
		this.endsAt = endsAt;
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

	@Override
	public String toString() {
		return "Screen [startsAt=" + startsAt + ", endsAt=" + endsAt + ", mId=" + mId + ", tId=" + tId + "]";
	}
    
    
}
