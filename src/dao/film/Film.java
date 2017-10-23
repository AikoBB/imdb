package dao.film;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import dao.person.Person;
import dao.person.User;

public class Film {
	private int id;
	private String title, language, country;
	private int length;
	private Map<User, Integer> ratings;
	private ArrayList<Person> directors;
	private ArrayList<Person> casts;
	
	
	public Film(int id, String title, String language, int length, String country, ArrayList<Person> directors,
			ArrayList<Person> casts) {
		this.id = id;
		this.title = title;
		this.language = language;
		this.length = length;
		this.country = country;
		this.directors = directors;
		this.casts = casts;
		ratings = new HashMap<User,Integer>();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public int getLength() {
		return length;
	}
	public void setLength(int length) {
		this.length = length;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public ArrayList<Person> getDirectors() {
		return directors;
	}
	public void setDirectors(ArrayList<Person> directors) {
		this.directors = directors;
	}
	public ArrayList<Person> getCasts() {
		return casts;
	}
	public void setCasts(ArrayList<Person> casts) {
		this.casts = casts;
	}
	
	public Map<User, Integer> getRatings() {
		return ratings;
	}
	
	public float getRating(){
		if(ratings.size() == 0)
			return 0.0f;
		
		float result = 0f;
		for(Integer rate :ratings.values()){
			result += rate;
		}
		return result / (float)ratings.size();
	}
	
	@Override
	public boolean equals(Object obj) {
		if(this == obj) return true;
		if(!(obj instanceof Film) || (obj == null)) return false;
		
		Film arg = (Film) obj;
		
		return arg.id == this.id;
	}
	
	@Override
	public int hashCode() {
		int result = 17;
		result = result * 31 + id;
		return result;
	}
	
	/**
	 * Returns film's title, length and language
	 */
	
	public String toString(){
		return "Film title: "+ this.getTitle()+"\n"+
				getLength() + " min\n"+
				"Language: " + getLanguage()+"\n";		
	}

}
