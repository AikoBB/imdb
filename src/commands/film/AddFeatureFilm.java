package commands.film;

import java.util.Arrays;
import java.util.Map;

import commands.Command;
import commands.WriteFile;
import dao.factory.DaoFactory;
import dao.film.FeatureFilm;
import dao.person.Person;
import service.FilmService;

public class AddFeatureFilm extends Command {
	

	private Map<Integer, Person> people;
	public AddFeatureFilm(FilmService service, WriteFile wf, String [] command, Map<Integer, Person> people) {
		super(service, wf, command);
		this.people = people;
	}

	@Override
	public void execute() {
		super.execute();
		try {
			String [] data = Arrays.copyOfRange(command, 1, command.length);
			data[0]="FeatureFilm:";
			FeatureFilm film = (FeatureFilm) DaoFactory.createFilm(data, people);
			wf.writeResult(((FilmService)service).addFeatureFilm(film));
		} catch (Exception e) {
			System.out.println("Parse Exception >>" + String.join(" ", command));
			System.exit(0);
		} 
	}
}
