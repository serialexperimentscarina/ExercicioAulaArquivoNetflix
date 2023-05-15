package model;

public class Serie {

	public String major_genre;
	public String title;
	public String subgenre;
	public int premiere_year;
	public String episodes;
	public String status;
	public int imdb_rating;
	
	public Serie() {
		super();
	}
	
	@Override
	public String toString() {
		return (major_genre + ";" + title + ";" + subgenre + ";" + premiere_year + ";" + episodes + ";" + status + ";" + imdb_rating + System.getProperty("line.separator"));
	}
	
	@Override
	public int hashCode() {
		return ((imdb_rating - 1) / 15);
	}
	
}
