package commands;

import java.util.Map;

import dao.factory.DaoFactory;
import dao.person.Person;

public class ReadPeopleFile extends ReadFile {
	private Map<Integer, Person> people;
	
	public ReadPeopleFile(Map<Integer, Person> people) {
		this.people = people;
	}

	public void processData(String [] data) {
		if(data.length > 0){
			Person p = DaoFactory.createPerson(data);
			people.put(p.getId(),p);
		}
		
	}

}
