package commands;

import java.util.List;
import java.util.Map;

import dao.factory.DaoFactory;
import dao.person.Person;
import service.FilmService;
import service.PersonService;

public class ReadCommandsFile extends ReadFile {

	private List<Command> commands;
	private WriteFile wf;
	private PersonService personService;
	private FilmService filmService;
	private Map<Integer, Person> people;
	
	public ReadCommandsFile(List<Command> commands, WriteFile wf, PersonService personService, FilmService filmService, Map<Integer, Person> people) {
		this.commands = commands;
		this.wf = wf;
		this.personService =personService;
		this.filmService = filmService;
		this.people = people;
	}
	
	public void processData(String[] data) {
		if(data.length > 0){
			Command c = DaoFactory.createCommands(data, wf, personService, filmService, people);
			commands.add(c);
		}
	}

}
