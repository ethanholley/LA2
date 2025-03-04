package model;

public class Song {
	private String title;
	private String artist;
	private Rating rating;
	private String album;
	private Genre genre;
	private boolean isFavorite;

	/*
	 * Regular Constructor
	 */
	public Song(String title, String artist, String album, Genre genre) {
		this.title = title;
		this.artist = artist;
		this.album = album;
		this.rating = rating.ZERO;
		this.genre = genre;
		this.isFavorite = false;
	}

	/*
	 * COPY CONSTRUCTOR
	 */
	public Song(Song other) {
		this.title = other.title;
		this.artist = other.artist;
		this.album = other.album;
		this.rating = other.rating;
		this.genre = other.genre;
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
	
	public Genre getGenre() {
		return this.genre;
	}

	public void setRating(Rating rating) {
		this.rating = rating;
		checkRating();
	}

	public void setFavorite() {
		isFavorite = true;
	}

	public boolean isFavorite() {
		return this.isFavorite;
	}

	/*
	 * checks if the song is rated a five and marks it as a favorite
	 */
	public void checkRating() {
		if (this.rating == Rating.FIVE) {
			setFavorite();
		}
	}

	@Override
	public String toString() {

		return title + " " + artist;

	}

	@Override
	public boolean equals(Object o) {
		if (o == null)
			return false;
		else if (o == this)
			return true;
		else if (o.getClass() != getClass())
			return false;
		else {
			return this.title.equals(((Song) o).title) && this.artist.equals(((Song) o).artist)
					&& this.album.equals(((Song) o).album) && this.rating.equals(((Song) o).rating)
					&& this.isFavorite == ((Song) o).isFavorite;
		}
	}
}