package commands.film;

import java.util.ArrayList;

import commands.Command;
import commands.WriteFile;
import dao.film.Film;
import dao.film.TVSeries;
import service.FilmService;

public class ListTvSeries extends Command{

	public ListTvSeries(FilmService filmService, WriteFile wf, String [] command) {
		super(filmService, wf, command);
	}

	@Override
	public void execute() {
		super.execute();
		writeResult(((FilmService)service).listSeries());
	}

	public void writeResult(ArrayList<Film> result) {
		if(result!=null && result.size()>0)
			for (Film film : result) {
				System.out.println(((TVSeries)film).filmDetails());
				System.out.println();
			}
		else
			wf.noResults();
	}

}
