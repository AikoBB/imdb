package com.aigerimbb.imdb.dao.person;

public class Director extends Artist{
private String agent;//in directors' data there is information about where they work

public Director(String id, String name, String surname, String country,String agent) {
	super(id, name, surname, country);
	this.agent=agent;
	
}
public String getAgent() {
	return agent;
}

public void setAgent(String agent) {
	this.agent = agent;
}
}
