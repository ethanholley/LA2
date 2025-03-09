
package test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import model.Album;
import model.Genre;
import model.MusicStore;
import model.Song;
import model.ParseFile;
import model.Rating;

public class testMusicStore {

	ParseFile pf = new ParseFile("/Users/chancekrueger/Desktop/albums");
	MusicStore ms = pf.getMusicStore();
	Album albumDamn = kendricksAlbum();

	@Test
	void testCopyConstructor() {

		MusicStore copyMS = new MusicStore(ms);
		assertFalse(copyMS == ms); // Not aliases
	}

	@Test
	void testAddSong() {

		Song song1 = new Song("IDGAF", "Drake", "For All the Dogs", Genre.ALTERNATIVE);
		ArrayList<Song> songList = new ArrayList<Song>();
		songList.add(song1);

		ms.addSong(songList);

		ArrayList<Song> song2 = ms.searchSongbyArtist("Drake".toLowerCase());

		assertTrue(song1.equals(song2.get(0)));
		assertFalse(song1 == song2.get(0)); // Checks to make sure songs aren't aliases
	}

	@Test
	void testAddAlbum() {
		Album kendrickAlbum = kendricksAlbum();

		Album kensAlbum = kendricksAlbum();

		ms.addSong(kensAlbum.getSongList());

		ms.addAlbum("Damn.", "Kendrick Lamar", kensAlbum.getSongList());
		ArrayList<Album> copyKen = ms.searchAlbumArtist("Kendrick Lamar".toLowerCase());

		assertTrue(copyKen.get(0).equals(kendrickAlbum));
	}

	@Test
	void testSearchSongbyTitle() {
		ArrayList<Song> song = ms.searchSongbyTitle("Chasing Pavements".toLowerCase());
		Song adeleSong = new Song("Chasing Pavements", "Adele", "19", Genre.POP);

		assertTrue(song.get(0).equals(adeleSong));

		ArrayList<Song> falseSong = ms.searchSongbyTitle("Not a Song!".toLowerCase());
		assertTrue(falseSong.size() == 0);
	}

	@Test
	void testSearchSongbyArtist() {
		Album kensAlbum = kendricksAlbum();

		ms.addSong(kensAlbum.getSongList());

		ms.addAlbum("Damn.", "Kendrick Lamar", kensAlbum.getSongList());
		ArrayList<Song> dnaSong = ms.searchSongbyArtist("Kendrick Lamar".toLowerCase());

		Song copyDNA = new Song("DNA.", "Kendrick Lamar", "Damn.", Genre.ALTERNATIVE);

		assertTrue(dnaSong.contains(copyDNA));

		assertTrue(ms.searchSongbyArtist("Kendrick Lamar.").size() == 0);

	}

	@Test
	void testSearchAlbumTitle() {
		Album kensAlbum = kendricksAlbum();

		ms.addSong(kensAlbum.getSongList());

		ms.addAlbum("Damn.", "Kendrick Lamar", kensAlbum.getSongList());
		Album albumCopy = ms.searchAlbumTitle("Damn.".toLowerCase());

		assertTrue(albumDamn.equals(albumCopy));

		Album albumNotReal = ms.searchAlbumTitle("Damn");
		assertTrue(albumNotReal == null);

	}

	@Test
	void testSearchAlbumArtist() {

		Album kensAlbum = kendricksAlbum();

		ms.addSong(kensAlbum.getSongList());

		ms.addAlbum("Damn.", "Kendrick Lamar", kensAlbum.getSongList());

		ArrayList<Album> albumCopy = ms.searchAlbumArtist("Kendrick Lamar".toLowerCase());

		assertTrue(albumDamn.getAlbumName().toLowerCase().equals(albumCopy.get(0).getAlbumName().toLowerCase()));

		ArrayList<Album> albumNoAlbum = ms.searchAlbumArtist("Drake".toLowerCase());

		assertTrue(albumNoAlbum.size() == 0);

	}

	@Test
	void testGetSongsMusicStore() {
		ArrayList<Song> songList = ms.getSongsMusicStore();
		assertTrue(songList.size() == 163);
	}

	@Test
	void testGetAlbumsMusicStore() {
		ArrayList<Album> songList = ms.getAlbumsMusicStore();
		assertTrue(songList.size() == 15);
	}

	@Test
	void testSetRatingOfSong() {
		Album ken = kendricksAlbum();
		ms.addSong(ken.getSongList());
		ms.setRatingOfSong(ken.getArtist().toLowerCase(), "DNA.".toLowerCase(), Rating.FOUR);

		ArrayList<Song> songKen = ms.searchSongbyTitle("DNA.".toLowerCase());

		assertTrue(songKen.get(0).getRating() == Rating.FOUR);
	}

	@Test
	void testSetFavoriteOfSong() {

		Album ken = kendricksAlbum();
		ms.addSong(ken.getSongList());
		ms.setFavoriteOfSong(ken.getArtist().toLowerCase(), "Feel.".toLowerCase());

		ArrayList<Song> songKen = ms.searchSongbyTitle("Feel.".toLowerCase());

		assertTrue(songKen.size() == 1);
		assertTrue(songKen.get(0).isFavorite());

	}

	private Album kendricksAlbum() {

		ArrayList<Song> kenSongList = new ArrayList<Song>();
		kenSongList.add(new Song("Feel.", "Kendrick Lamar", "Damn.", Genre.ALTERNATIVE));
		kenSongList.add(new Song("Pride.", "Kendrick Lamar", "Damn.", Genre.ALTERNATIVE));
		kenSongList.add(new Song("DNA.", "Kendrick Lamar", "Damn.", Genre.ALTERNATIVE));

		return new Album("Damn.", "Kendrick Lamar", kenSongList);
	}
}
