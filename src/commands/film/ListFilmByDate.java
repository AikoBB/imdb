package commands.film;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import commands.Command;
import commands.WriteFile;
import dao.film.FeatureFilm;
import dao.film.Film;
import service.FilmService;

public class ListFilmByDate extends Command{

	public ListFilmByDate(FilmService filmService, WriteFile wf, String [] command) {
		super(filmService, wf, command);
	}

	@Override
	public void execute() {
		super.execute();
		try {
			Date releaseDate = new SimpleDateFormat( "yyyy" ).parse( command[3] );
			boolean before = "BEFORE".equalsIgnoreCase(command[2]);
			writeResult(((FilmService)service).listFilmByDate(releaseDate, before));
		} catch (ParseException e) {
			System.out.println("Parse Exception >>" + String.join(" ", command));
			System.exit(0);
		}
	}

	public void writeResult(ArrayList<Film> result) {
		if(result!=null && result.size()>0)
			for (Film film : result) {
				System.out.println(((FeatureFilm)film).filmDetails());
				System.out.println();
			}
		else
			wf.noResults();
	}
}
