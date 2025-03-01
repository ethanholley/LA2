
package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import model.Playlist;

public class testPlaylist {

	Playlist pl = new Playlist("Test");

	@Test
	void testGetPlaylistName() {
		assertEquals("Test", pl.getPlaylistName());

	}

	@Test
	void testAddSongToPlaylist() {
		Playlist copy = new Playlist(pl);
		copy.addSongToPlaylist("IDK", "ANOTHER", "SONGS");
		copy.addSongToPlaylist("IDK", "ANOTHER", "SONGS"); // shoudn't add to list
		assertTrue(copy.getUserSongList().size() == 1);
	}

	@Test
	void testRemoveSongFromPlaylist() {
		Playlist copy = new Playlist("Testing");
		copy.addSongToPlaylist("Lacrimosa", "Mozart", "1770");
		copy.addSongToPlaylist("Mamma Mia", "Mozart", "1770");
		copy.removeSongFromPlaylist("Lacrimosa", "Mozart");

		copy.removeSongFromPlaylist("Doesn't Exist", "Mozart");

		assertTrue(copy.getUserSongList().size() == 1);
	}
	
	@Test
	void testCopyConstructor() {
		pl.addSongToPlaylist("Hello", "World", "My");
		Playlist plCopy = new Playlist(pl);
		assertTrue(plCopy.getUserSongList().size() == pl.getUserSongList().size());
	}

}
