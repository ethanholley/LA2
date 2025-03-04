package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import model.Album;
import model.Song;

public class testAlbum {

	private Album album = new Album("Damn.", "Kendrick Lamar", kendricksAlbum().getSongList());

	@Test
	void testGetArtist() {
		assertEquals(album.getArtist(), "Kendrick Lamar");
	}

	@Test
	void testGetAlbumName() {
		assertEquals(album.getAlbumName(), "Damn.");
	}

	@Test
	void testAddSong() {
		Song song = new Song("Love", "Kendrick Lamar", "Damn.", "Rap");
		album.addSong(song);

		ArrayList<Song> songList = album.getSongList();

		assertTrue(songList.contains(song));
	}

	@SuppressWarnings("unlikely-arg-type")
	@Test
	void testEquals() {
		assertFalse(album.equals(null));
		assertTrue(album.equals(album));
		assertFalse(album.equals(new Song("HELLO", "WORLD", "IDC", "Rap")));

		Album copy = new Album(album.getAlbumName(), album.getArtist(), album.getSongList());

		assertTrue(album.equals(copy));

		Song song = new Song("Love", "Kendrick Lamar", "Damn.", "Rap");
		album.addSong(song);

		copy.getSongList().remove(0);
		ArrayList<Song> songList = copy.getSongList();

		assertFalse(album.equals(new Album("Damn", "Kendrick Lamar", copy.getSongList())));
		assertFalse(album.equals(new Album("Damn.", "Kendrick", copy.getSongList())));
		assertFalse(album.equals(new Album("Damn.", "Kendrick Lamar", songList)));
	}

	private Album kendricksAlbum() {

		ArrayList<Song> kenSongList = new ArrayList<Song>();
		kenSongList.add(new Song("Feel.", "Kendrick Lamar", "Damn.", "Rap"));
		kenSongList.add(new Song("Pride.", "Kendrick Lamar", "Damn.", "Rap"));
		kenSongList.add(new Song("DNA.", "Kendrick Lamar", "Damn.", "Rap"));

		return new Album("Damn.", "Kendrick Lamar", kenSongList);
	}

//	public static void main(String[] args) {
//		testAlbum al = new testAlbum();
//	}
}
