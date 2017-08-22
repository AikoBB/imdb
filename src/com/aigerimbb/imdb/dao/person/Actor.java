package com.aigerimbb.imdb.dao.person;

//in people.txt file there will be actors' data and for saving them we created this class
public class Actor extends Performer {
	private String height;// in actors' data there is information about their
							// height

	public Actor(String id, String name, String surname, String country, String height) {
		super(id, name, surname, country);// passing data to superclass
		this.height = height;

	}

	public String getHeight() {
		return height;
	}

	public void setHeight(String height) {
		this.height = height;
	}
}
