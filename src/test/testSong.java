
package test;

import model.Genre;
import model.Rating;
import model.Song;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class testSong {

	Song song = new Song("DNA", "Kendrick Lamar", "Damn.", Genre.ALTERNATIVE);
	private Rating rating = null;

	@Test
	void testCopyConstructor() {

		Song copySong = new Song(song);

		assertTrue(song.getTitle() == copySong.getTitle());
		assertTrue(song.getArtist() == copySong.getArtist());
		assertTrue(song.getAlbum() == copySong.getAlbum());
		assertTrue(song.getRating() == copySong.getRating());
		assertTrue(song.toString().equals(copySong.toString()));

		assertFalse(song == copySong);
	}

	@Test
	void testGetTitle() {
		assertEquals("DNA", song.getTitle());
	}

	@Test
	void testGetArtist() {
		assertEquals("Kendrick Lamar", song.getArtist());
	}

	@Test
	void testGetAlbum() {
		String album = song.getAlbum();
		assertEquals("Damn.", album);
	}

	@Test
	void testGetRating() {

		rating = rating.ZERO;
		Rating other = null;
		assertTrue(song.getRating().isEquals(other.ZERO));
	}

	@Test
	void testSetRating() {
		rating = rating.ZERO;

		Rating other = null;
		song.setRating(other.ZERO);

		assertTrue(song.getRating().isEquals(other.ZERO));
	}

	@Test
	void testSetFavorite() {

		Song song = new Song("IDGAF", "Drake", "Boys", Genre.ALTERNATIVE);
		assertFalse(song.isFavorite());
		song.setFavorite();
		assertTrue(song.isFavorite() == true);

	}

	@Test
	void testToString() {
		song.toString().equals("DNA Kendrick Lamar");
	}

	@Test
	void testCheckRating() {
		Song song = new Song("IDGAF", "Drake", "Boys", Genre.ALTERNATIVE);
		song.setRating(Rating.FIVE);
		song.checkRating();

		assertTrue(song.getRating().equals(Rating.FIVE));
	}

	@Test
	void testEquals() {
		// Song song = new Song("DNA", "Kendrick Lamar", "Damn.");
		Song songCopyTitle = new Song("DNA", "Hello", "World", Genre.ALTERNATIVE);
		Song songCopyArtist = new Song("DNA", "Kendrick Lamar", "World", Genre.ALTERNATIVE);
		Song songCopyAlbumn = new Song("DNA", "Kendrick Lamar", "Damn.", Genre.ALTERNATIVE);
		Song songCopyGenre = new Song("DNA", "Kendrick Lamar", "Damn.", Genre.ALTERNATIVE);

		assertFalse(song.equals(null));
		assertTrue(song.equals(song));
		assertFalse(song.equals(Rating.FIVE));

		songCopyAlbumn.setRating(rating.FOUR);
		assertFalse(song.equals(songCopyAlbumn));

		song.setRating(rating.ZERO);
		songCopyAlbumn.setRating(rating.FIVE);
		assertFalse(song.equals(songCopyAlbumn));

		assertFalse(song.equals(songCopyAlbumn));
		assertFalse(song.equals(songCopyArtist));
		assertFalse(song.equals(songCopyTitle));

		song.setRating(rating.FIVE);
		songCopyGenre.setRating(rating.FIVE);

		assertTrue(song.equals(songCopyGenre));

	}
	
	@Test
	void testGetGenre() {
		assertTrue(this.song.getGenre().isEquals(Genre.ALTERNATIVE));
	}

}
