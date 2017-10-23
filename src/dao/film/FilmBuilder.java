package dao.film;

import java.util.ArrayList;

import dao.person.Person;

public abstract class FilmBuilder<T> {
	protected int id; 
	protected String title, language, country;
	protected int length;
	protected ArrayList<Person> directorIds;
	protected ArrayList<Person> castIds;

	public FilmBuilder(int id, String title) {
		this.id = id;
		this.title = title;
	}
	
	@SuppressWarnings("rawtypes")
	public FilmBuilder language(String language){
		this.language = language;
		return this;
	}
	
	@SuppressWarnings("rawtypes")
	public FilmBuilder length(int length){
		this.length = length;
		return this;
	}
	
	@SuppressWarnings("rawtypes")
	public FilmBuilder country(String country){
		this.country = country;
		return this;
	}
	
	@SuppressWarnings("rawtypes")
	public FilmBuilder directors(ArrayList<Person> ids){
		this.directorIds = ids;
		return this;
	}
	
	@SuppressWarnings("rawtypes")
	public FilmBuilder casts(ArrayList<Person> ids){
		this.castIds = ids;
		return this;
	}
	
	public abstract T build();
}
