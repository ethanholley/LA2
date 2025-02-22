package test;

import model.Rating;
import model.Song;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class testSong {

	Song song = new Song("DNA", "Kendrick Lamar", "Damn.");
	private Rating rating = null;

	public testSong() {
		testCopyConstructor();
		testGetTitle();
		testGetArtist();
		testGetAlbum();
		testGetRating();
		testSetRating();
		testSetFavorite();
		testToString();
	}

	@Test
	private void testCopyConstructor() {

		Song copySong = new Song(song);

		assertTrue(song.getTitle() == copySong.getTitle());
		assertTrue(song.getArtist() == copySong.getArtist());
		assertTrue(song.getAlbum() == copySong.getAlbum());
		assertTrue(song.getRating() == copySong.getRating());
		assertTrue(song.toString().equals(copySong.toString()));

		assertFalse(song == copySong);
	}

	@Test
	private void testGetTitle() {
		assertEquals("DNA", song.getTitle());
	}

	@Test
	private void testGetArtist() {
		assertEquals("Kendrick Lamar", song.getArtist());
	}

	@Test
	private void testGetAlbum() {
		String album = song.getAlbum();
		assertEquals("Damn.", album);
	}

	@Test
	private void testGetRating() {

		rating = rating.ZERO;
		Rating other = null;
		assertTrue(song.getRating().isEquals(other.ZERO));
	}

	@Test
	private void testSetRating() {
		rating = rating.ZERO;

		Rating other = null;
		song.setRating(other.ZERO);

		assertTrue(song.getRating().isEquals(other.ZERO));
	}

	@Test
	private void testSetFavorite() {

		Song copySong = new Song(song);
		assertFalse(copySong.isFavorite());
		copySong.setFavorite();
		assertTrue(copySong.isFavorite() == true);

	}

	@Test
	private void testToString() {
		song.toString().equals("DNA Kendrick Lamar");
	}

}
