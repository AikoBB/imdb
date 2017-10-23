package commands;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dao.film.Film;
import dao.person.Person;
import service.FilmService;
import service.FilmServiceImpl;
import service.PersonService;
import service.PersonServiceImpl;

public class Main {
	
	public static void main(String [] args){
		
		if(args.length == 3){
			Map<Integer, Person> people =new HashMap<Integer, Person>();
			Map<Integer, Film> films =new HashMap<Integer, Film>();
			List<Command> commands = new ArrayList<>();
			
			WriteFile wf = WriteFile.getInstance();
			/*** reading files ***/
			ReadFile file = new ReadPeopleFile(people);
			file.read(args[0]);
			file = new ReadFilmFile(films, people);
			file.read(args[1]);
			PersonService personService = new PersonServiceImpl(people);
			FilmService filmService = new FilmServiceImpl(films, people);
			file = new ReadCommandsFile(commands, wf, personService,filmService, people);
			file.read(args[2]);
			
			commands.stream().filter(command -> null != command).forEach(Command::execute);
			
		}else{
			System.out.println("Have to pass four arguments!!!");
		}
	}

}
