package dao.film;
import java.util.Date;


public class Documentary extends Film {
	private Date releaseDate;
	
	public static class Builder extends FilmBuilder<Documentary>{
	
		private Date releaseDate;
		
		public Builder(int id, String title) {
			super(id, title);
		}
		
		public Builder releaseDate(Date releaseDate){
			this.releaseDate = releaseDate;
			return this;
		}
		
		@Override
		public Documentary build() {
			return new Documentary(this);
		}
		
	}
	
	private Documentary(Builder builder){
		super(builder.id, builder.title, builder.language, builder.length, builder.country, builder.directorIds, builder.castIds);
		this.releaseDate = builder.releaseDate;
		
	}
	public Date getReleaseDate() {
		return releaseDate;
	}
	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}
}
