package model;

// SONG CLASS
public class Song {
	private String title;
	private String artist;
	private Rating rating;
	private String album;
	private boolean isFavorite;
	
	// Regular Constructor
	public Song(String title, String artist, String album) {
		this.title = title;
		this.artist = artist;
		this.album = album;
		this.rating = rating.ZERO;
		this.isFavorite = false;
	}
	
	
	// COPY CONSTRUCTOR
	public Song(Song other) {
		this.title = other.title;
		this.artist = other.artist;
		this.album = other.album;
		this.rating = other.rating;
		this.isFavorite = other.isFavorite;
	}
	
	public String getTitle() {
		return this.title;
	}
	
	public String getArtist() {
		return this.artist;
	}
	
	public String getAlbum() {
		return this.album;
	}
	
	public Rating getRating() {
		return rating;
	}
	
	public void setRating(Rating rating) {
		this.rating = rating;
	}
	
	public void setFavorite() {
		isFavorite = true;
	}
	
	@Override
	public String toString() {

		return title + " " + artist;

	}
	
	public boolean isFavorite() {
		return this.isFavorite;
	}

}