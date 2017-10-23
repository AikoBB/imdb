package commands.film;

import commands.Command;
import commands.WriteFile;
import service.FilmService;

public class EditRate extends Command{

	public EditRate(FilmService filmService, WriteFile wf, String [] command) {
		super(filmService, wf, command);
	}

	@Override
	public void execute() {
		super.execute();
		String result = ((FilmService)service).editRateFilm(Integer.parseInt(command[2]), Integer.parseInt(command[3]), Integer.parseInt(command[4]));
		wf.writeResultLn(result);
	}

}
