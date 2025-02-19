package model;

public class Song {
	private String title;
	private String artist;
	private Rating rating;
	
	public Song(String title, String artist) {
		this.title = title;
		this.artist = artist;
		this.rating = rating.ZERO;
	}
	
	public String getTitle() {
		return this.title;
	}
	
	public String getArtist() {
		return this.artist;
	}
	
	public Rating getRating() {
		return rating;
	}
	
	public void setRating(Rating rating) {
		this.rating = rating;
	}
	
}
