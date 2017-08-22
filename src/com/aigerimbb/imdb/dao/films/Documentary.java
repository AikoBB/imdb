package com.aigerimbb.imdb.dao.films;
import java.util.ArrayList;


public class Documentary extends Films {
private String releaseDate;//saving for release date of documentary film
public ArrayList<UserIdRate> uir=new ArrayList<UserIdRate>();//this line for saving rates
    
   public Documentary(String id, String title, String language,String directorIds, String length, String country, String castIds,String releaseDate) {
		super(id, title, language, directorIds, length, country, castIds);
		this.releaseDate=releaseDate;
	}
	public String getReleaseDate() {
		return releaseDate;
	}
	public void setReleaseDate(String releaseDate) {
		this.releaseDate = releaseDate;
	}
	
	

}
