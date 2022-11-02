package com.moviebooking.bean;

//import java.util.List;

public class Movie {

    private String mId;
    private String mName;
    private String lang;
    private String duration;
    private String director;
    private String tId;
    
//    private List<Theatre> theatre;
//    
//    public Movie(int mId, String mName, String lang, String duration, List<Theatre> theatre) {
//        this.mId = mId;
//        this.mName = mName;
//        this.lang = lang;
//        this.duration = duration;
//        
//        this.theatre = theatre;
//    }
    
    public Movie(String mId, String mName, String lang, String duration,String director,String tId) {
        this.mId = mId;
        this.mName = mName;
        this.lang = lang;
        this.duration = duration;
        this.director = director;
        this.tId = tId;
    }

    public Movie() {
    }

    public String getmId() {
        return mId;
    }

    public void setmId(String mId) {
        this.mId = mId;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }
    
    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }
    
    public String getTId() {
        return tId;
    }

    public void setTId(String tId) {
        this.tId = tId;
    }

	@Override
	public String toString() {
		return "Movie [mId=" + mId + ", mName=" + mName + ", lang=" + lang + ", duration=" + duration + ", director="
				+ director + ", tId=" + tId + "]";
	}

//    public List<Theatre> getTheatre() {
//        return theatre;
//    }
//
//    public void setTheatre(List<Theatre> theatre) {
//        this.theatre = theatre;
//    }

	
    
}
