package com.aigerimbb.imdb.dao.films;
//this class used in every film's subclasses for saving rates of users
public class UserIdRate {
private String userId;//for which user's rate
private int rate;//rate of user
public UserIdRate(String id, int rate)
{
	userId=id;
	this.rate=rate;
}
public String getUserId() {
	return userId;
}
public void setUserId(String userId) {
	this.userId = userId;
}
public int getRate() {
	return rate;
}
public void setRate(int rate) {
	this.rate = rate;
}
}
