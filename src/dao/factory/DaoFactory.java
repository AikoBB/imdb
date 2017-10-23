package dao.factory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Map;

import commands.Command;
import commands.WriteFile;
import commands.film.AddFeatureFilm;
import commands.film.EditRate;
import commands.film.ListFilmByCountry;
import commands.film.ListFilmByDate;
import commands.film.ListFilmsByRating;
import commands.film.ListTvSeries;
import commands.film.ListUserRatings;
import commands.film.RateFilm;
import commands.film.RemoveRate;
import commands.film.ViewFilm;
import commands.people.ListArtistsByCountry;
import dao.film.Documentary;
import dao.film.FeatureFilm;
import dao.film.Film;
import dao.film.ShortFilm;
import dao.film.TVSeries;
import dao.person.Actor;
import dao.person.Actress;
import dao.person.Director;
import dao.person.Person;
import dao.person.User;
import dao.person.Writer;
import service.FilmService;
import service.PersonService;

public class DaoFactory {
	
	public static Person createPerson(String[] data) {
		int id = Integer.parseInt(data[1]);
		switch (data[0]) {
			case "Actor:":
				return (Person)new Actor.Builder()
												.height(data[5])
												.id(id)
												.name(data[2])
												.surname(data[3])
												.country(data[4])
												.build();
			case "Actress:":
				return (Person)new Actress.Builder()
													.id(id)
													.name(data[2])
													.surname(data[3])
													.country(data[4])
													.build();
			case "Director:":
				return (Person)new Director.Builder()
													.agent(data[5])
													.id(id).name(data[2])
													.surname(data[3])
													.country(data[4])
													.build();
			case "Writer:":
				return (Person)new Writer.Builder()
													.style(data[5])
													.id(id)
													.name(data[2])
													.surname(data[3])
													.country(data[4])
													.build();
			case "User:":
				return  (Person)new User.Builder()
												.id(id)
												.name(data[2])
												.surname(data[3])
												.country(data[4])
												.build();
			default:
				return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	public static Film createFilm(String [] data, Map<Integer, Person> people) throws NumberFormatException, ParseException{
		switch (data[0]) {
		case "FeatureFilm:":
			return (Film) new FeatureFilm.Builder(Integer.parseInt(data[1]), data[2] )
											.genres(data[8])
											.releaseDate(new SimpleDateFormat( "dd.MM.yyyy" ).parse( data[9] ))
											.writers(getArtistList(data[10], people))
											.budget(Long.parseLong(data[11]))
											.language(data[3])
											.directors(getArtistList(data[4], people))
											.length(Integer.parseInt(data[5]))
											.country(data[6])
											.casts(getArtistList(data[7], people))
											.build();
		case "ShortFilm:":
			return (Film) new ShortFilm.Builder(Integer.parseInt(data[1]), data[2] )
											.genres(data[8])
											.releaseDate(new SimpleDateFormat( "dd.MM.yyyy" ).parse( data[9] ))
											.writers(getArtistList(data[10], people))
											.language(data[3])
											.directors(getArtistList(data[4], people))
											.length(Integer.parseInt(data[5]))
											.country(data[6])
											.casts(getArtistList(data[7], people))
											.build();
		case "TVSeries:":
			return (Film) new TVSeries.Builder(Integer.parseInt(data[1]), data[2] )
											.genres(data[8])
											.writers(getArtistList(data[9], people))
											.startDate(new SimpleDateFormat( "dd.MM.yyyy" ).parse( data[10] ))
											.endDate(new SimpleDateFormat( "dd.MM.yyyy" ).parse( data[11] ))
											.seasons(Integer.parseInt(data[12]))
											.episodes(Integer.parseInt(data[13]))
											.language(data[3])
											.directors(getArtistList(data[4], people))
											.length(Integer.parseInt(data[5]))
											.country(data[6])
											.casts(getArtistList(data[7], people))
											.build();
		case "Documentary:":
			return (Film) new Documentary.Builder(Integer.parseInt(data[1]), data[2] )
											.releaseDate(new SimpleDateFormat( "dd.MM.yyyy" ).parse( data[8] ))
											.language(data[3])
											.directors(getArtistList(data[4], people))
											.length(Integer.parseInt(data[5]))
											.country(data[6])
											.casts(getArtistList(data[7], people))
											.build();
		default:
			return null;
	}
	}
	
	public static Command createCommands(String [] data,WriteFile wf, PersonService personService, FilmService filmService, Map<Integer, Person> people){
		switch(data[0]){
		case "ADD":
			return new AddFeatureFilm(filmService, wf, data, people);
		case "LIST":
			if(data[1].equalsIgnoreCase("ARTISTS"))
				return new ListArtistsByCountry(personService , wf, data);
			if(data[1].equalsIgnoreCase("USER"))
				return new ListUserRatings(filmService , wf, data);
			else if(data[1].equalsIgnoreCase("FILM") && data[2].equalsIgnoreCase("SERIES"))
				return new ListTvSeries(filmService , wf, data);
			else if(data[1].equalsIgnoreCase("FILMS") && data[3].equalsIgnoreCase("COUNTRY"))
				return new ListFilmByCountry(filmService , wf, data);
			else if(data[1].equalsIgnoreCase("FILMS") && data[3].equalsIgnoreCase("RATE"))
				return new ListFilmsByRating(filmService , wf, data);
			else if(data[1].equalsIgnoreCase("FEATUREFILMS"))
				return new ListFilmByDate(filmService , wf, data);
			return null;
		case "RATE":
			return new RateFilm(filmService, wf, data);
		case "EDIT":
			return new EditRate(filmService, wf, data);
		case "REMOVE":
			return new RemoveRate(filmService, wf, data);
		case "VIEWFILM":
			return new ViewFilm(filmService, wf, data);
		default:
			return null;
		}
	}
	
	private static ArrayList<Person> getArtistList(String ids, Map<Integer, Person> people){
		String [] str = ids.split(",");
		ArrayList<Person> result =new ArrayList<Person>();
		for(String id : str){
			result.add(people.get(Integer.parseInt(id)));
		}
		return result;
	}

}
