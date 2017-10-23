package commands;
import java.util.Map;

import dao.factory.DaoFactory;
import dao.film.Film;
import dao.person.Person;

public class ReadFilmFile extends ReadFile {
	private Map<Integer, Film> films;
	private Map<Integer, Person> people;
	
	public ReadFilmFile(Map<Integer, Film> films,  Map<Integer, Person> people) {
		this.films = films;
		this.people = people;
	}

	public void processData(String [] data) {
		if(data.length > 0){
			Film f;
			try {
				f = DaoFactory.createFilm(data, people);
				films.put(f.getId(),f);
			} catch (Exception e) {
				System.out.println("Parse Exception!!!");
				System.exit(0);
			} 
		}
		
	}


}
