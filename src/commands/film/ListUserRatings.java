package commands.film;

import commands.Command;
import commands.WriteFile;
import service.FilmService;

public class ListUserRatings extends Command{

	public ListUserRatings(FilmService filmService, WriteFile wf, String [] command) {
		super(filmService, wf, command);
	}

	@Override
	public void execute() {
		super.execute();
		String result = ((FilmService)service).listUserRatings(Integer.parseInt(command[2]));
		wf.writeResultLn(result);
	}

}
