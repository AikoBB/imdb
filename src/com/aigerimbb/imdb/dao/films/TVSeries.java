package com.aigerimbb.imdb.dao.films;
import java.util.ArrayList;


public class TVSeries extends Films {
	private String genre, writers, startDate, endDate, seasons, episodes;//this data only for tv series
	
	public ArrayList<UserIdRate> uir=new ArrayList<UserIdRate>();//for saving rates

	public String getSeasons() {
		return seasons;
	}

	public void setSeasons(String seasons) {
		this.seasons = seasons;
	}

	

	

	public TVSeries(String id, String title, String language,String directorIds, String length, String country, String castIds,String genre,String writers,String startDate,String endDate, String seasons,String episodes) {
		super(id, title, language, directorIds, length, country, castIds);
		this.genre=genre;
		this.writers=writers;
		this.startDate=startDate;
		this.endDate=endDate;
		this.seasons=seasons;
	    this.episodes=episodes;
		
	}

	
	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getWriters() {
		return writers;
	}

	public void setWriters(String writers) {
		this.writers = writers;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getSeason() {
		return seasons;
	}

	public void setSeason(String seasons) {
		this.seasons = seasons;
	}

	public String getEpisodes() {
		return episodes;
	}

	public void setEpisodes(String episodes) {
		this.episodes = episodes;
	}
	

}
