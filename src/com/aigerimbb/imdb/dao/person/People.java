package com.aigerimbb.imdb.dao.person;
//this is super class
//which saves commonly data of users,artist,performers
public class People {
	private String name, surname, country , id;
	public People(String id,String name, String surname ,String country)
	{
		this.id=id;
		this.name=name;
		this.surname=surname;
		this.country=country;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
