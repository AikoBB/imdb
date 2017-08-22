package com.aigerimbb.imdb.dao.films;
import java.util.ArrayList;


public class ShortFilm extends Films {
	private String genre;//short film has own data as a genre, release date and writers
	private String releaseDate;
	private String writers;
	public ArrayList<UserIdRate> uir=new ArrayList<UserIdRate>();//for saving rates
	

	public ShortFilm(String id, String title, String language,String directorIds, String length, String country, String castIds,String genre,String releaseDate,String writers) {
		super(id, title, language, directorIds, length, country, castIds);//passing data to super class
		this.genre=genre;
		this.releaseDate=releaseDate;
		this.writers=writers;
	}
	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}
	public String getReleaseDate() {
		return releaseDate;
	}
	public void setReleaseDate(String releaseDate) {
		this.releaseDate = releaseDate;
	}
	public String getWriters() {
		return writers;
	}
	public void setWriters(String writers) {
		this.writers = writers;
	}
	

}
