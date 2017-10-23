package dao.film;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import dao.person.Person;

public class TVSeries extends Film {
	private String genres;
	private ArrayList<Person> writerIds;
	private Date startDate;
	private Date endDate;
	private int seasons;
	private int episodes;
	
	public static class Builder extends FilmBuilder<TVSeries>{

		private String genres;
		private Date startDate;
		private Date endDate;
		private ArrayList<Person> writers;
		private int seasons;
		private int episodes;
		
		public Builder(int id, String title) {
			super(id, title);
		}
		
		public Builder genres(String genres){
			this.genres = genres;
			return this;
		}
		
		public Builder startDate(Date startDate){
			this.startDate = startDate;
			return this;
		}
		
		public Builder endDate(Date endDate){
			this.endDate = endDate;
			return this;
		}
		
		public Builder writers(ArrayList<Person> writers){
			this.writers = writers;
			return this;
		}
		
		public Builder seasons(int seasons){
			this.seasons = seasons;
			return this;
		}
		
		public Builder episodes(int episodes){
			this.episodes = episodes;
			return this;
		}

		@Override
		public TVSeries build() {
			return new TVSeries(this);
		}
		
	}
	
	private TVSeries(Builder builder){
		super(builder.id, builder.title, builder.language, builder.length, builder.country, builder.directorIds, builder.castIds);
		this.writerIds = builder.writers;
		this.genres = builder.genres;
		this.startDate = builder.startDate;
		this.endDate = builder.endDate;
		this.episodes = builder.episodes;
		this.seasons = builder.seasons;
	}

	public String getGenres() {
		return genres;
	}

	public void setGenres(String genres) {
		this.genres = genres;
	}

	public ArrayList<Person> getWriters() {
		return writerIds;
	}

	public void setWriters(ArrayList<Person> writerIds) {
		this.writerIds = writerIds;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public int getSeasons() {
		return seasons;
	}

	public void setSeasons(int seasons) {
		this.seasons = seasons;
	}

	public int getEpisodes() {
		return episodes;
	}

	public void setEpisodes(int episodes) {
		this.episodes = episodes;
	}
	
	public String filmDetails(){
		return this.getTitle()+"(" + getYear(getStartDate()) + "-"+getYear(getEndDate())+")\n"+
				getSeasons()+" seasons and "+getEpisodes()+" episodes";		
	}
	
	private String getYear(Date d){
		SimpleDateFormat df = new SimpleDateFormat("yyyy");
		return df.format(d);
	}

}
