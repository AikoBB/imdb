package commands.film;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Map;

import commands.Command;
import commands.WriteFile;
import dao.film.Documentary;
import dao.film.FeatureFilm;
import dao.film.Film;
import dao.film.ShortFilm;
import dao.film.TVSeries;
import service.FilmService;

public class ListFilmsByRating extends Command{

	public ListFilmsByRating(FilmService filmService, WriteFile wf, String [] command) {
		super(filmService, wf, command);
	}

	@Override
	public void execute() {
		super.execute();
		Map<Integer, ArrayList<Film>> result = ((FilmService)service).listFilmByRate();
		wf.writeResult("FeatureFilm:");
		writeResults(result.get(0));
		
		wf.writeResult("ShortFilm:");
		writeResults(result.get(1));
		
		wf.writeResult("Documentary:");
		writeResults(result.get(2));
		
		wf.writeResult("TVSeries:");
		writeResults(result.get(3));
	}
	
	public void writeResults(ArrayList<Film> obj){
		if(obj.size()>0){
			Collections.sort(obj, new RateComparator());
			for (Film film : obj) {
				String title;
				float rate = film.getRating();
				if(film instanceof FeatureFilm){
					title = film.getTitle()+" ("+getYear(((FeatureFilm)film).getReleaseDate()) + ") " +
							"Ratings: " + rate + "/10" +  " from " + film.getRatings().size() + " users";
				}
				else if(film instanceof ShortFilm){
					title = film.getTitle()+" ("+getYear(((ShortFilm)film).getReleaseDate()) + ") " +
						"Ratings: " + rate + "/10" +  " from " + film.getRatings().size() + " users";
				}
				else if(film instanceof Documentary){
					title = film.getTitle()+" ("+getYear(((Documentary)film).getReleaseDate()) + ") " +
							"Ratings: " + rate + "/10" +  " from " + film.getRatings().size() + " users";
				}
				else{
					title = film.getTitle()+" ("+getYear(((TVSeries)film).getStartDate()) + "-" + getYear(((TVSeries)film).getEndDate())+ ") " +
							"Ratings: " + rate + "/10" +  " from " + film.getRatings().size() + " users";
				}
				wf.writeResult(title);
			}
		}
		else
			wf.noResults();
		System.out.println();
	}
	
	private static String getYear(Date d){
		SimpleDateFormat df = new SimpleDateFormat("yyyy");
		return df.format(d);
	}
	
	private class RateComparator implements Comparator<Film>{
		@Override
		public int compare(Film o1, Film o2) {
			Float f1 = o1.getRating();
			Float f2 = o2.getRating();
			return f2.compareTo(f1);
		}
	}
}
