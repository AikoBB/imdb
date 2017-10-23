package service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import dao.person.Actor;
import dao.person.Actress;
import dao.person.Director;
import dao.person.Person;
import dao.person.User;
import dao.person.Writer;
import enums.PersonType;

public class PersonServiceImpl implements PersonService{
	private Map<Integer, Person> people;
	
	public PersonServiceImpl( Map<Integer, Person> people){
		this.people = people;
	}

	public HashMap<PersonType, ArrayList<Person>> getArtistsByCountry(String country) {
		 	HashMap<PersonType, ArrayList<Person>> result = new HashMap<PersonType, ArrayList<Person>>();
	        result.put(PersonType.ACTOR, new ArrayList<Person>());
	        result.put(PersonType.ACTRESS, new ArrayList<Person>());
	        result.put(PersonType.WRITER, new ArrayList<Person>());
	        result.put(PersonType.DIRECTOR, new ArrayList<Person>());
	        people
	        	.entrySet()
	        	.stream()
	        	.filter(person-> country.equalsIgnoreCase(person.getValue().getCountry()))
	        	.forEach(person -> {
	            if (person.getValue() instanceof Writer) {
	                result.get(PersonType.WRITER).add((Writer) person.getValue());
	            }
	            else if (person.getValue() instanceof Director) {
	            	result.get(PersonType.DIRECTOR).add((Director) person.getValue());
	            }
	            else if (person.getValue() instanceof Actor) {
	            	result.get(PersonType.ACTOR).add((Actor) person.getValue());
	            }
	            else if (person.getValue() instanceof Actress) {
	            	result.get(PersonType.ACTRESS).add((Actress) person.getValue());
	            }
	        });
	        
	        return result;
	}
	
	public User getUserById(int id){
		return (User)people.get(id);
	}

}
