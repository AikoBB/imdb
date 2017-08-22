package com.aigerimbb.imdb.dao.films;
//this class has many subclasses
//in this class we have created commonly used states

public  class Films {
private String id, title, language,  length, country;
private String directorIds;
private String castIds;


public Films(String id,String title,String language,String directorIds,String length,String country,String castIds)
{
	this.id=id;
	this.castIds=castIds;
	this.country=country;
	this.language=language;
	this.title=title;
	this.directorIds=directorIds;
	this.length=length;
}
public String getId() {
	return id;
}
public void setId(String id) {
	this.id = id;
}
public String getTitle() {
	return title;
}
public void setTitle(String title) {
	this.title = title;
}
public String getLanguage() {
	return language;
}
public void setLanguage(String language) {
	this.language = language;
}
public String getLength() {
	return length;
}
public void setLength(String length) {
	this.length = length;
}
public String getCountry() {
	return country;
}
public void setCountry(String country) {
	this.country = country;
}
public String getDirectorIds() {
	return directorIds;
}
public void setDirectorIds(String directorIds) {
	this.directorIds = directorIds;
}
public String getCastIds() {
	return castIds;
}
public void setCastIds(String castIds) {
	this.castIds = castIds;
}




}
