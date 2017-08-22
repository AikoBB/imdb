package com.aigerimbb.imdb.dao.person;

public class Writer extends Artist {
private String style;

public Writer(String id, String name, String surname, String country,String style) {
	super(id, name, surname, country);
	this.style=style;
	
}
public String getStyle() {
	return style;
}

public void setStyle(String style) {
	this.style = style;
}
}
