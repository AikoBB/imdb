package commands.film;

import commands.Command;
import commands.WriteFile;
import service.FilmService;

public class RemoveRate extends Command{

	public RemoveRate(FilmService filmService, WriteFile wf, String [] command) {
		super(filmService, wf, command);
	}

	@Override
	public void execute() {
		super.execute();
		String result = ((FilmService)service).removeRateFilm( Integer.parseInt(command[2]), Integer.parseInt(command[3]));
		wf.writeResultLn(result);
	}

}
