package dao.film;
import java.util.ArrayList;
import java.util.Date;

import dao.person.Person;

public class ShortFilm extends Film {
	private String genres;
	private Date releaseDate;
	private ArrayList<Person> writers;
	
	public static class Builder extends FilmBuilder<ShortFilm>{

		private String genres;
		private Date releaseDate;
		private ArrayList<Person> writers;
		
		public Builder(int id, String title) {
			super(id, title);
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

		@Override
		public ShortFilm build() {
			return new ShortFilm(this);
		}
		
	}
	
	private ShortFilm(Builder builder){
		super(builder.id, builder.title, builder.language, builder.length, builder.country, builder.directorIds, builder.castIds);
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
	
	
}
