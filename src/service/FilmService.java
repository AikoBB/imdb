package service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

import dao.film.FeatureFilm;
import dao.film.Film;

public interface FilmService extends Service{
	
	public String rateFilm(int userID, int filmID, int rating);
	
	public String editRateFilm(int userID, int filmID, int rating);
	
	public String removeRateFilm(int userID, int filmID);
	
	public String listUserRatings(int userID);
	
	public String viewFilm(int filmID);
	
	public ArrayList<Film> listSeries();
	
	public ArrayList<Film> listFilmByCountry(String country);
	
	public ArrayList<Film> listFilmByDate(Date date, boolean before);
	
	public Map<Integer, ArrayList<Film>> listFilmByRate();
	
	public String addFeatureFilm(FeatureFilm film);

}
