package test;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import model.Album;
import model.MusicStore;
import model.Song;
import model.ParseFile;

class testMusicStore {

	ParseFile pf = new ParseFile("/Users/chancekrueger/Desktop/albums");
	MusicStore ms = pf.getMusicStore();
	Album albumDamn = kendricksAlbum();

	public testMusicStore() {
		testCopyConstructor();
		testAddSong();
		testAddAlbum();
		testSearchSongbyTitle();
		testSearchSongbyArtist();
		testSearchAlbumTitle();
		testSearchAlbumArtist();

	}

	@Test
	private void testCopyConstructor() {

		MusicStore copyMS = new MusicStore(ms);
		assertFalse(copyMS == ms); // Not aliases
	}

	@Test
	private void testAddSong() {

		Song song1 = new Song("IDGAF", "Drake", "For All the Dogs");
		ArrayList<Song> songList = new ArrayList<Song>();
		songList.add(song1);

		ms.addSong(songList);

		Song song2 = ms.searchSongbyArtist("Drake", "IDGAF");

		assertTrue(song1.equals(song2));
		assertFalse(song1 == song2); // Checks to make sure songs aren't aliases
	}

	@Test
	private void testAddAlbum() {
		Album kendrickAlbum = kendricksAlbum();

		Album kensAlbum = kendricksAlbum();

		ms.addSong(kensAlbum.getSongList());

		ms.addAlbum("Damn.", "Kendrick Lamar", kensAlbum.getSongList());
		Album copyKen = ms.searchAlbumArtist("Kendrick Lamar", "Damn.");

		assertTrue(copyKen.equals(kendrickAlbum));
	}

	@Test
	private void testSearchSongbyTitle() {
		Song song = ms.searchSongbyTitle("Chasing Pavements");
		Song adeleSong = new Song("Chasing Pavements", "Adele", "19");

		assertTrue(song.equals(adeleSong));

		Song falseSong = ms.searchSongbyTitle("Not a Song!");
		assertTrue(falseSong == null);
	}

	@Test
	private void testSearchSongbyArtist() {
		Album kensAlbum = kendricksAlbum();

		ms.addSong(kensAlbum.getSongList());

		ms.addAlbum("Damn.", "Kendrick Lamar", kensAlbum.getSongList());
		Song dnaSong = ms.searchSongbyArtist("Kendrick Lamar", "DNA.");

		Song copyDNA = new Song("DNA.", "Kendrick Lamar", "Damn.");

		assertTrue(dnaSong.equals(copyDNA));

		assertTrue(ms.searchSongbyArtist("Kendrick Lamar", "DNA") == null);
		assertTrue(ms.searchSongbyArtist("Kendrick Lamar.", "DNA.") == null);

	}

	@Test
	private void testSearchAlbumTitle() {
		Album kensAlbum = kendricksAlbum();

		ms.addSong(kensAlbum.getSongList());

		ms.addAlbum("Damn.", "Kendrick Lamar", kensAlbum.getSongList());
		Album albumCopy = ms.searchAlbumTitle("Damn.");

		assertTrue(albumDamn.equals(albumCopy));

		Album albumNotReal = ms.searchAlbumTitle("Damn");
		assertTrue(albumNotReal == null);

	}

	@Test
	private void testSearchAlbumArtist() {
		
		Album kensAlbum = kendricksAlbum();

		ms.addSong(kensAlbum.getSongList());

		ms.addAlbum("Damn.", "Kendrick Lamar", kensAlbum.getSongList());

		Album albumCopy = ms.searchAlbumArtist("Kendrick Lamar", "Damn.");
		
		assertTrue(albumDamn.equals(albumCopy));

		Album albumNoName = ms.searchAlbumArtist("Kendrick Lamar", "Damn");
		Album albumNoAlbum = ms.searchAlbumArtist("Drake", "Damn.");

		assertTrue(albumNoName == null);
		assertTrue(albumNoAlbum == null);

	}

	private Album kendricksAlbum() {

		ArrayList<Song> kenSongList = new ArrayList<Song>();
		kenSongList.add(new Song("Feel.", "Kendrick Lamar", "Damn."));
		kenSongList.add(new Song("Pride.", "Kendrick Lamar", "Damn."));
		kenSongList.add(new Song("DNA.", "Kendrick Lamar", "Damn."));

		ms.addSong(kenSongList);

		return new Album("Damn.", "Kendrick Lamar", kenSongList);
	}

}
