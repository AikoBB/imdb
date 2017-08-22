package com.aigerimbb.imdb.dao.person;
import java.util.ArrayList;


public class User extends People {
public ArrayList <RatingsOfUser> rates=new ArrayList<RatingsOfUser>();//for saving rates of user
	
public User(String id,String name, String surname ,String country)
	{
		super(id,name,surname,country);
	}
	
}
