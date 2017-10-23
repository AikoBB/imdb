package commands.film;

import commands.Command;
import commands.WriteFile;
import service.FilmService;

public class RateFilm extends Command {

	public RateFilm(FilmService filmService, WriteFile wf, String [] command) {
		super(filmService, wf, command);
	}

	@Override
	public void execute() {
		super.execute();
		String result = ((FilmService)service).rateFilm(Integer.parseInt(command[1]), Integer.parseInt(command[2]), Integer.parseInt(command[3]));
		wf.writeResultLn(result);
	}
}
