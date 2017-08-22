package com.aigerimbb.imdb.dao.person;

//in people.txt file there will be actresses and for this we have created this class
public class Actress extends Performer{
	public Actress(String id, String name, String surname, String country) {
		super(id, name, surname, country);//this line passes actress' data to superclass
		
	}
}
