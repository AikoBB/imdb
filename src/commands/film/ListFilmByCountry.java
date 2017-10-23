package commands.film;

import java.util.ArrayList;

import commands.Command;
import commands.WriteFile;
import dao.film.Film;
import service.FilmService;

public class ListFilmByCountry extends Command{

	ArrayList<Film> result;
	public ListFilmByCountry(FilmService filmService, WriteFile wf, String [] command) {
		super(filmService, wf, command);
	}

	@Override
	public void execute() {
		super.execute();
		String country = command[4];
		wf.writeResults(((FilmService)service).listFilmByCountry(country));
	}

}
