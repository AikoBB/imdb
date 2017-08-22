package com.aigerimbb.imdb.dao.films;
import java.util.ArrayList;


public class FeatureFilm extends Films{
	private String genre, releaseDate,writers,budjet;//every feature film has own genre, release date, writers and of course budjet
	public ArrayList<UserIdRate> uir=new ArrayList<UserIdRate>();
public FeatureFilm(String id, String title, String language,
			String directorIds, String length, String country, String castIds,String genre,String releaseDate,String writers,String budjet) {
		super(id, title, language, directorIds, length, country, castIds);
		this.budjet=budjet;
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

public String getBudjet() {
	return budjet;
}

public void setBudjet(String budjet) {
	this.budjet = budjet;
}


}
