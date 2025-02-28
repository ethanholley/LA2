
package test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import model.Album;
import model.LibraryModel;
import model.MusicStore;
import model.ParseFile;
import model.Playlist;
import model.Rating;
import model.Song;

public class testLibraryModel {

	LibraryModel lib = new LibraryModel();
	ParseFile pf = new ParseFile("/Users/chancekrueger/Desktop/albums");
	MusicStore ms = pf.getMusicStore();

	@Test
	void testCreatePlayList() {
		lib.createPlayList("Test");
		lib.createPlayList("Testing");
		assertTrue(lib.getAllPlayList().size() == 2);
	}

	@Test
	void testGetAllPlayList() {
		lib.createPlayList("Test");
		lib.createPlayList("Testing");
		ArrayList<Playlist> plList = lib.getAllPlayList();
		assertTrue(plList.size() == 2);
	}

	@Test
	void testGetSongTitles() {
		lib.addAlbumToLibrary(ms, "21", "Adele");
		ArrayList<String> songList = lib.getSongTitles();
		assertTrue(songList.contains("Rolling in the Deep By: Adele, Album: 21"));
		assertFalse(songList.contains("Rolling in the Deep By: Adele, Album: 19"));
	}

	@Test
	void testGetAlbumList() {

		ArrayList<Album> songList = lib.getAlbumList();
		assertTrue(songList.size() == 0);

		lib.addAlbumToLibrary(ms, "21", "Adele");
		lib.addAlbumToLibrary(ms, "Begin Again", "Norah Jones");

		ArrayList<Album> songList2 = lib.getAlbumList();
		assertTrue(songList2.size() == 2);
	}

	// How do I get songs/set to favorite?
	@Test
	void testGetFavoriteSongs() {

		lib.addSongToLibrary(ms, "Rumour Has It", "Adele");
		lib.addSongToLibrary(ms, "Lullaby", "OneRepublic");
		lib.addSongToLibrary(ms, "Lullaby", "Leonard Cohen");
		lib.addSongToLibrary(ms, "After Party", "Ozomatli");
		lib.addSongToLibrary(ms, "Fire", "The Heavy");

		ms.setRatingOfSong("Adele", "Rumour Has It", Rating.FIVE);
		ms.setRatingOfSong("OneRepublic", "Lullaby", Rating.FIVE);
		ms.setRatingOfSong("Leonard Cohen", "Lullaby", Rating.FOUR);
		ms.setRatingOfSong("Ozomatli", "After Party", Rating.FIVE);
		ms.setRatingOfSong("The Heavy", "Fire", Rating.FIVE);

		lib.addSongToLibrary(ms, "Rumour Has It", "Adele");
		lib.addSongToLibrary(ms, "Lullaby", "OneRepublic");
		lib.addSongToLibrary(ms, "Lullaby", "Leonard Cohen");
		lib.addSongToLibrary(ms, "After Party", "Ozomatli");
		lib.addSongToLibrary(ms, "Fire", "The Heavy");

		ArrayList<Song> songListFav = lib.getFavoriteSongs();

		assertTrue(songListFav.size() == 4);

	}

	@Test
	void testGetArtists() {

		lib.addAlbumToLibrary(ms, "21", "Adele");
		lib.addAlbumToLibrary(ms, "Begin Again", "Norah Jones");
		lib.addAlbumToLibrary(ms, "Boys & Girls", "Alabama Shakes");

		ArrayList<String> artists = lib.getArtists();

		assertTrue(artists.contains("Adele"));
		assertTrue(artists.contains("Norah Jones"));
		assertTrue(artists.contains("Alabama Shakes"));
		assertFalse(artists.contains("Kendrick Lamar"));
	}

	// FIND ALBUMS WITH DUPS< WANT TO MAKE SURE IT WORKS WITH DUPS
	@Test
	void testSearchSongbyTitle() {

		lib.addAlbumToLibrary(ms, "Waking Up", "OneRepublic"); // Contains duplicate
		lib.addAlbumToLibrary(ms, "Old Ideas", "Leonard Cohen"); // Contains duplicate
		lib.addAlbumToLibrary(ms, "Boys & Girls", "Alabama Shakes");

		ArrayList<Song> songList = lib.searchSongbyTitle("Hang Loose");

		assertTrue(songList.size() == 1);

		songList = lib.searchSongbyTitle("Lullaby");
		assertTrue(songList.size() == 2);

	}

	@Test
	void testSearchSongbyArtist() {

		lib.addAlbumToLibrary(ms, "19", "Adele");
		lib.addAlbumToLibrary(ms, "21", "Adele");
		lib.addAlbumToLibrary(ms, "Begin Again", "Norah Jones");

		// WHY IS THIS A ARRAY INSTEAD OF A SONG OBJECT?

		lib.searchSongbyArtist("Adele", "Rumour Has It");
		Song song = lib.searchSongbyArtist("Adele", "Rumour Has It").get(0);

		assertTrue(song.getAlbum().equals("21"));
		assertTrue(song.getArtist().equals("Adele"));
		assertTrue(song.getTitle().equals("Rumour Has It"));
	}

	@Test
	void testSearchAlbumTitle() {

		lib.addAlbumToLibrary(ms, "Waking Up", "OneRepublic");
		lib.addAlbumToLibrary(ms, "Old Ideas", "Leonard Cohen");
		lib.addAlbumToLibrary(ms, "Boys & Girls", "Alabama Shakes");

		Album album = lib.searchAlbumTitle("Old Ideas");

		assertTrue(album.getAlbumName().equals("Old Ideas"));
		assertTrue(album.getArtist().equals("Leonard Cohen"));

		Album albumNull = lib.searchAlbumTitle("WHAT THE");
		assertTrue(albumNull == null);

	}

	@Test
	void testSearchAlbumArtist() {

		// WHY IS THIS A ARRAY INSTEAD OF A ALBUM OBJECT? -> Can only have one artist
		// and album that match together

		lib.addAlbumToLibrary(ms, "19", "Adele");
		lib.addAlbumToLibrary(ms, "21", "Adele");
		lib.addAlbumToLibrary(ms, "Begin Again", "Norah Jones");

		ArrayList<Album> album = lib.searchAlbumArtist("Adele");

		assertTrue(album.size() == 2);
		assertTrue(album.get(0).getAlbumName().equals("19 "));
		assertTrue(album.get(1).getAlbumName().equals("21"));
		assertTrue(album.get(0).getArtist().equals("Adele"));

	}

	@Test
	void testSearchPlaylistByName() {

		lib.createPlayList("Test");
		lib.createPlayList("Testing");
		assertTrue(lib.getAllPlayList().size() == 2);

		lib.addSongToPlaylist("Testing", "Rumour Has It", "Adele", "21");

		Playlist plTest = lib.searchPlaylistByName("Testing");

		assertTrue(plTest.getPlaylistName().equals("Testing"));

		assertTrue(plTest.getUserSongList().get(0).getTitle().equals("Rumour Has It"));

		Playlist plTestNull = lib.searchPlaylistByName("DOESN'T EXIT");
		assertTrue(plTestNull == null);

	}

	@Test
	void testAddSongToLibrary() {
		assertTrue(lib.addSongToLibrary(ms, "Lullaby", "OneRepublic"));
		assertTrue(lib.addSongToLibrary(ms, "Lullaby", "OneRepublic"));
		assertFalse(lib.addSongToLibrary(ms, "Lullaby", "OneRepublic!"));
	}

	@Test
	void testAddAlbumToLibrary() {
		assertTrue(lib.addAlbumToLibrary(ms, "19", "Adele"));
		assertTrue(lib.addAlbumToLibrary(ms, "21", "Adele"));
		assertTrue(lib.addAlbumToLibrary(ms, "Begin Again", "Norah Jones"));
		assertTrue(lib.addAlbumToLibrary(ms, "19", "Adele"));
		assertFalse(lib.addAlbumToLibrary(ms, "19", "21 Savage"));
	}

	@Test // FIX THIS
	void testAddSongToPlaylist() {

		lib.createPlayList("TEST");
		assertTrue(lib.searchPlaylistByName("TEST").getUserSongList().size() == 0);

		lib.addSongToPlaylist("TEST", "IDGAF", "Drake", "For All the Dogs");
		lib.addSongToPlaylist("TEST", "IDGAF", "Drake", "For All the Dogs");

		assertTrue(lib.searchPlaylistByName("TEST").getUserSongList().size() == 2);
		assertTrue(lib.searchPlaylistByName("TEST").getUserSongList().get(0).getTitle().equals("IDGAF"));
		
		

	}

	@Test
	void testRemoveSongPlaylist() {

		lib.createPlayList("TESTER");
		lib.createPlayList("TEST");
		assertTrue(lib.searchPlaylistByName("TEST").getUserSongList().size() == 0);

		lib.addSongToPlaylist("TEST", "IDGAF", "Drake", "For All the Dogs");
		lib.addSongToPlaylist("TEST", "DNA.", "Kendrick Lamar", "Damn.");
		lib.addSongToPlaylist("TEST", "LOVE.", "Kendrick Lamar", "Damn.");

		assertTrue(lib.searchPlaylistByName("TEST").getUserSongList().size() == 3);

		lib.removeSongPlaylist("TEST", "LOVE.", "Kendrick Lamar");
		assertTrue(lib.searchPlaylistByName("TEST").getUserSongList().size() == 2);

	}

}
