package com.aigerimbb.imdb.dao.person;
//we create this class for saving ratings
public class RatingsOfUser  {
private int rate;//rate of user
private String filmId;//and rate for which film
public RatingsOfUser(int rate,String filmId)
{
	this.rate=rate;
	this.filmId=filmId;
}
public int getRate() {
	return rate;
}
public void setRate(int rate) {
	
	this.rate = rate;
}
public String getFilmId() {
	return filmId;
}
public void setFilmId(String filmId) {
	this.filmId = filmId;
}
}
