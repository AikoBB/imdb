package service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import dao.film.Documentary;
import dao.film.FeatureFilm;
import dao.film.Film;
import dao.film.ShortFilm;
import dao.film.TVSeries;
import dao.person.Person;
import dao.person.User;

public class FilmServiceImpl implements FilmService{
	
	private Map <Integer, Film>films;
	private Map<Integer, Person> users;
	
	public FilmServiceImpl(Map<Integer, Film>films, Map<Integer, Person> users) {
		this.films = films;
		this.users = users;
	}

	public String rateFilm(int userID, int filmID, int rating) {
		
		if(users.get(userID) == null || ! (users.get(userID) instanceof User) || films.get(filmID) == null){
			return  "Command Failed\n"+
					"User ID: " + userID +"\n"+
					"Film ID: "+filmID;
		}
		
		User user = (User) users.get(userID);
		Film film = films.get(filmID);
		if(film.getRatings().containsKey(user)){
			return "This film was earlier rated";
		}
		
		film.getRatings().put(user, rating);
		return  "Film rated successfully\n"+
				"Film type: "+film.getClass().getSimpleName()+"\n"+
			    "Film title: "+film.getTitle();
		
	}
	

	public String editRateFilm(int userID, int filmID, int rating){
		Film film = films.get(filmID);
		Person person = users.get(userID);
		if(person == null || ! (person instanceof User) || film == null || !film.getRatings().containsKey(person)){
			return  "Command Failed\n"+
					"User ID: " + userID +"\n"+
					"Film ID: "+filmID;
		}
		
		film.getRatings().put((User) person, rating);
		return "New ratings done successfully\n" +
				"Film title: " + film.getTitle()+
				"Your rating: "+ film.getRatings().get(person);
	}
	
	public String removeRateFilm(int userID, int filmID){
		Film film = films.get(filmID);
		Person person = users.get(userID);
		if(person == null || ! (person instanceof User) || film == null || !film.getRatings().containsKey(person)){
			return  "Command Failed\n"+
					"User ID: " + userID +"\n"+
					"Film ID: "+filmID;
		}
		
		film.getRatings().remove(person);
		return "Your film rating was removed successfully\n" +
				"Film title: " + film.getTitle();
	}
	
	public String listUserRatings(int userID){
		Person person = users.get(userID);
		if(person == null || ! (person instanceof User))
			return  "Command Failed\n"+
					"User ID: " + userID;
		String result = "";
		for(Film film : films.values()){
			if(film.getRatings().containsKey(person)){
				result += film.getTitle() + ": "+ film.getRatings().get(person)+"\n";
			}
		}
		
		if(result.length()>0)
			return result;
		return "There is not any ratings so far\n";
	}
	
	public String viewFilm(int filmID ){
		Film film = films.get(filmID);
		if(film == null)
			return "Command Failed\n"+
					"Film ID: "+filmID;

		String result = "";
		if(film instanceof TVSeries){
			result += ((TVSeries) film).filmDetails() +"\n";
			result += ((TVSeries) film).getGenres() + "\n";
			result += getArtists(((TVSeries) film).getWriters(), "Writers") + "\n";
		}
		else if(film instanceof Documentary){
			result += film.getTitle()+" ("+getYear(((Documentary)film).getReleaseDate()) + ")\n";
		}
		else if(film instanceof FeatureFilm){
			result += film.getTitle()+" ("+getYear(((FeatureFilm)film).getReleaseDate()) + ")\n";
			result += ((FeatureFilm) film).getGenres() + "\n";
			result += getArtists(((FeatureFilm) film).getWriters(), "Writers") + "\n";
		}
		else if(film instanceof ShortFilm){
			result += film.getTitle()+" ("+getYear(((ShortFilm)film).getReleaseDate()) + ")\n";
			result += ((ShortFilm) film).getGenres() + "\n";
			result += getArtists(((ShortFilm) film).getWriters(), "Writers") + "\n";
		}
		

		result += getArtists(film.getDirectors(), "Directors") + "\n";
		result += getArtists (film.getCasts(), "Stars") + "\n";
		result += "Ratings: " + film.getRating() + "/10" +  " from " + film.getRatings().size() + " users";
		return result;
	}
	
	private static String getArtists(ArrayList<Person> people, String type){
		String result = type + ": ";
		boolean isFirst = true;
		for(Person p : people){
			if(isFirst){
				result += p.getName() + " " + p.getSurname();
				isFirst = false;
			}else
				result += ", " + p.getName() + " " + p.getSurname();
		}
		return result;
	}
	private static String getYear(Date d){
		SimpleDateFormat df = new SimpleDateFormat("yyyy");
		return df.format(d);
	}

	@Override
	public ArrayList<Film> listSeries() {
		ArrayList<Film> list = new ArrayList<>();
		films.entrySet()
			 .stream()
			 .filter(film -> film.getValue() instanceof TVSeries)
			 .forEach(film -> {list.add(film.getValue());});
		return list;
	}

	@Override
	public ArrayList<Film> listFilmByCountry(String country) {
		ArrayList<Film> list = new ArrayList<>();
		films.entrySet()
			 .stream()
			 .filter(film -> country.equalsIgnoreCase(film.getValue().getCountry()))
			 .forEach(film -> {list.add(film.getValue());});
		return list;
	}

	@Override
	public ArrayList<Film> listFilmByDate(Date date, boolean before) {
		ArrayList<Film> list = new ArrayList<>();
		films.entrySet()
			 .stream()
			 .filter(film ->(film.getValue() instanceof FeatureFilm) &&
					 (!before ? date.before(((FeatureFilm)film.getValue()).getReleaseDate()) 
							 : date.after(((FeatureFilm)film.getValue()).getReleaseDate())))
			 .forEach(film -> {list.add(film.getValue());});
		return list;
	}
	
	public Map<Integer, ArrayList<Film>> listFilmByRate(){
		Map<Integer, ArrayList<Film>> result = new HashMap<Integer, ArrayList<Film>>(); 
		result.put(0,new ArrayList<>());
		result.put(1,new ArrayList<>());
		result.put(2, new ArrayList<>());
		result.put(3, new ArrayList<>());
		
		for(Film film : films.values()){
			if(film instanceof FeatureFilm){
				result.get(0).add(film);
			}
			else if(film instanceof ShortFilm){
				result.get(1).add(film);
			}
			else if(film instanceof Documentary){
				result.get(2).add(film);
			}
			else{
				result.get(3).add(film);
			}
		}
		return result;
	}

	@Override
	public String addFeatureFilm(FeatureFilm film) {
		String result = "";
		if(films.get(film.getId())==null){
			films.put(film.getId(), film);
			result += "FeatureFilm added successfully\n";
		}else
			result += "Command Failed\n";
		result += "Film ID: "+film.getId()+"\n"+
				  "Film title: "+film.getTitle();
		return result;
	}

}
