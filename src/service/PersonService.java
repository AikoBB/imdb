package service;

import java.util.ArrayList;
import java.util.HashMap;

import dao.person.Person;
import dao.person.User;
import enums.PersonType;

public interface PersonService extends Service{

	public HashMap<PersonType, ArrayList<Person>> getArtistsByCountry(String country);
	
	public User getUserById(int id);
}
