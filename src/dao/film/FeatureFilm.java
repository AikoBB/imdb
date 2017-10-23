package dao.film;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import dao.person.Person;


public class FeatureFilm extends Film{
	private String genres;
	private Date releaseDate;
	private ArrayList<Person> writers;
	private long budget;
	
	public static class Builder extends FilmBuilder<FeatureFilm>{

		private String genres;
		private Date releaseDate;
		private ArrayList<Person> writers;
		private long budget;
		
		public Builder(int id, String title) {
			super(id, title);
			// TODO Auto-generated constructor stub
		}
		
		public Builder genres(String genres){
			this.genres = genres;
			return this;
		}
		
		public Builder releaseDate(Date releaseDate){
			this.releaseDate = releaseDate;
			return this;
		}
		
		public Builder writers(ArrayList<Person> writers){
			this.writers = writers;
			return this;
		}
		
		public Builder budget(long budget){
			this.budget = budget;
			return this;
		}

		@Override
		public FeatureFilm build() {
			return new FeatureFilm(this);
		}
		
	}
	
	private FeatureFilm(Builder builder){
		super(builder.id, builder.title, builder.language, builder.length, builder.country, builder.directorIds, builder.castIds);
		this.budget = builder.budget;
		this.genres = builder.genres;
		this.releaseDate = builder.releaseDate;
		this.writers = builder.writers;
		
	}

	public String getGenres() {
		return genres;
	}

	public void setGenres(String genres) {
		this.genres = genres;
	}

	public Date getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}

	public ArrayList<Person> getWriters() {
		return writers;
	}

	public void setWriterIds(ArrayList<Person> writers) {
		this.writers = writers;
	}

	public long getBudget() {
		return budget;
	}

	public void setBudget(long budget) {
		this.budget = budget;
	}
	
	public String filmDetails(){
		return "Film title: "+ this.getTitle()+" ("+getYear(this.getReleaseDate()) + ")\n"+
				getLength() + " min\n"+
				"Language: " + getLanguage();		
	}
	
	private String getYear(Date d){
		SimpleDateFormat df = new SimpleDateFormat("yyyy");
		return df.format(d);
	}
}
