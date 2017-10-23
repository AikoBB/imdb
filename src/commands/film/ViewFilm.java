package commands.film;

import commands.Command;
import commands.WriteFile;
import service.FilmService;

public class ViewFilm extends Command{

	public ViewFilm(FilmService filmService, WriteFile wf, String [] command) {
		super(filmService, wf, command);
	}

	@Override
	public void execute() {
		super.execute();
		String result = ((FilmService)service).viewFilm(Integer.parseInt(command[1]));
		wf.writeResultLn(result);
	}

}
