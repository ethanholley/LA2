
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
	ParseFile pf = new ParseFile("/Users/ethanjholly/Desktop/LA 1/albums");
	MusicStore ms = pf.getMusicStore();

	@Test
	void testCreatePlayList() {
		lib.createPlayList("Test");
		lib.createPlayList("Testing");
		assertTrue(lib.getAllPlayList().size() == 2);
	}

	@Test
	void testGetAllPlayList() {
		lib.createPlayList("Test".toLowerCase());
		lib.createPlayList("Testing".toLowerCase());
		ArrayList<Playlist> plList = lib.getAllPlayList();
		assertTrue(plList.size() == 2);
	}

	@Test
	void testGetSongTitles() {
		lib.addAlbumToLibrary(ms, "21".toLowerCase(), "Adele".toLowerCase());
		ArrayList<String> songList = lib.getSongTitles();
		assertTrue(songList.contains("Rolling in the Deep By: Adele, Album: 21"));
		assertFalse(songList.contains("Rolling in the Deep By: Adele, Album: 19"));
	}

	@Test
	void testGetAlbumList() {

		ArrayList<Album> songList = lib.getAlbumList();
		assertTrue(songList.size() == 0);

		lib.addAlbumToLibrary(ms, "21".toLowerCase(), "Adele".toLowerCase());
		lib.addAlbumToLibrary(ms, "Begin Again".toLowerCase(), "Norah Jones".toLowerCase());

		ArrayList<Album> songList2 = lib.getAlbumList();
		assertTrue(songList2.size() == 2);
	}

	@Test
	void testGetFavoriteSongs() {

		lib.addSongToLibrary(ms, "Rumour Has It".toLowerCase(), "Adele".toLowerCase());
		lib.addSongToLibrary(ms, "Lullaby".toLowerCase(), "OneRepublic".toLowerCase());
		lib.addSongToLibrary(ms, "Lullaby".toLowerCase(), "Leonard Cohen".toLowerCase());
		lib.addSongToLibrary(ms, "After Party".toLowerCase(), "Ozomatli".toLowerCase());
		lib.addSongToLibrary(ms, "Fire".toLowerCase(), "The Heavy".toLowerCase());

		ms.setRatingOfSong("Adele".toLowerCase(), "Rumour Has It".toLowerCase(), Rating.FIVE);
		ms.setRatingOfSong("OneRepublic".toLowerCase(), "Lullaby".toLowerCase(), Rating.FIVE);
		ms.setRatingOfSong("Leonard Cohen".toLowerCase(), "Lullaby".toLowerCase(), Rating.FOUR);
		ms.setRatingOfSong("Ozomatli".toLowerCase(), "After Party".toLowerCase(), Rating.FIVE);
		ms.setRatingOfSong("The Heavy".toLowerCase(), "Fire".toLowerCase(), Rating.FIVE);

		lib.addSongToLibrary(ms, "Rumour Has It".toLowerCase(), "Adele".toLowerCase());
		lib.addSongToLibrary(ms, "Lullaby".toLowerCase(), "OneRepublic".toLowerCase());
		lib.addSongToLibrary(ms, "Lullaby".toLowerCase(), "Leonard Cohen".toLowerCase());
		lib.addSongToLibrary(ms, "After Party".toLowerCase(), "Ozomatli".toLowerCase());
		lib.addSongToLibrary(ms, "Fire".toLowerCase(), "The Heavy".toLowerCase());

		ArrayList<Song> songListFav = lib.getFavoriteSongs();

		assertTrue(songListFav.size() == 4);

	}

	@Test
	void testGetArtists() {

		lib.addAlbumToLibrary(ms, "21".toLowerCase(), "Adele".toLowerCase());
		lib.addAlbumToLibrary(ms, "Begin Again".toLowerCase(), "Norah Jones".toLowerCase());
		lib.addAlbumToLibrary(ms, "Boys & Girls".toLowerCase(), "Alabama Shakes".toLowerCase());

		ArrayList<String> artists = lib.getArtists();

		assertTrue(artists.contains("Adele"));
		assertTrue(artists.contains("Norah Jones"));
		assertTrue(artists.contains("Alabama Shakes"));
		assertFalse(artists.contains("Kendrick Lamar"));
	}

	// FIND ALBUMS WITH DUPS< WANT TO MAKE SURE IT WORKS WITH DUPS
	@Test
	void testSearchSongbyTitle() {

		lib.addAlbumToLibrary(ms, "Waking Up".toLowerCase(), "OneRepublic".toLowerCase()); // Contains duplicate
		lib.addAlbumToLibrary(ms, "Old Ideas".toLowerCase(), "Leonard Cohen".toLowerCase()); // Contains duplicate
		lib.addAlbumToLibrary(ms, "Boys & Girls".toLowerCase(), "Alabama Shakes".toLowerCase());

		ArrayList<Song> songList = lib.searchSongbyTitle("Hang Loose".toLowerCase());

		assertTrue(songList.size() == 1);

		songList = lib.searchSongbyTitle("Lullaby".toLowerCase());
		assertTrue(songList.size() == 2);

	}

	@Test
	void testSearchSongbyArtist() {

		lib.addAlbumToLibrary(ms, "19".toLowerCase(), "Adele".toLowerCase());
		lib.addAlbumToLibrary(ms, "21".toLowerCase(), "Adele".toLowerCase());
		lib.addAlbumToLibrary(ms, "Begin Again".toLowerCase(), "Norah Jones".toLowerCase());

		ArrayList<Song> song = lib.searchSongbyArtist("Adele".toLowerCase());

		assertTrue(song.size() == 24);
	}

	@Test
	void testSearchAlbumTitle() {

		lib.addAlbumToLibrary(ms, "Waking Up".toLowerCase(), "OneRepublic".toLowerCase());
		lib.addAlbumToLibrary(ms, "Old Ideas".toLowerCase(), "Leonard Cohen".toLowerCase());
		lib.addAlbumToLibrary(ms, "Boys & Girls".toLowerCase(), "Alabama Shakes".toLowerCase());

		Album album = lib.searchAlbumTitle("Old Ideas".toLowerCase());

		assertTrue(album.getAlbumName().equals("Old Ideas"));
		assertTrue(album.getArtist().equals("Leonard Cohen"));

		Album albumNull = lib.searchAlbumTitle("WHAT THE".toLowerCase());
		assertTrue(albumNull == null);

	}

	@Test
	void testSearchAlbumArtist() {

		// WHY IS THIS A ARRAY INSTEAD OF A ALBUM OBJECT? -> Can only have one artist
		// and album that match together

		lib.addAlbumToLibrary(ms, "19".toLowerCase(), "Adele".toLowerCase());
		lib.addAlbumToLibrary(ms, "21".toLowerCase(), "Adele".toLowerCase());
		lib.addAlbumToLibrary(ms, "Begin Again".toLowerCase(), "Norah Jones".toLowerCase());

		ArrayList<Album> album = lib.searchAlbumArtist("Adele".toLowerCase());

		assertTrue(album.size() == 2);
		assertTrue(album.get(0).getAlbumName().equals("19"));
		assertTrue(album.get(1).getAlbumName().equals("21"));
		assertTrue(album.get(0).getArtist().equals("Adele"));

	}

	@Test
	void testSearchPlaylistByName() {

		lib.createPlayList("Test".toLowerCase());
		lib.createPlayList("Testing".toLowerCase());
		assertTrue(lib.getAllPlayList().size() == 2);

		lib.addSongToPlaylist("Testing".toLowerCase(), "Rumour Has It".toLowerCase(), "Adele".toLowerCase(),
				"21".toLowerCase());

		Playlist plTest = lib.searchPlaylistByName("Testing".toLowerCase());

		assertTrue(plTest.getPlaylistName().equals("Testing".toLowerCase()));

		assertTrue(plTest.getUserSongList().get(0).getTitle().equals("Rumour Has It".toLowerCase()));

		Playlist plTestNull = lib.searchPlaylistByName("DOESN'T EXIT".toLowerCase());
		assertTrue(plTestNull == null);

	}

	@Test
	void testAddSongToLibrary() {
		assertTrue(lib.addSongToLibrary(ms, "Lullaby".toLowerCase(), "OneRepublic".toLowerCase()));
		assertTrue(lib.addSongToLibrary(ms, "Lullaby".toLowerCase(), "OneRepublic".toLowerCase()));
		assertFalse(lib.addSongToLibrary(ms, "Lullaby".toLowerCase(), "OneRepublic!".toLowerCase()));
	}

	@Test
	void testAddAlbumToLibrary() {
		assertTrue(lib.addAlbumToLibrary(ms, "19", "Adele".toLowerCase()));
		assertTrue(lib.addAlbumToLibrary(ms, "21", "Adele".toLowerCase()));
		assertTrue(lib.addAlbumToLibrary(ms, "Begin Again".toLowerCase(), "Norah Jones".toLowerCase()));
		assertTrue(lib.addAlbumToLibrary(ms, "19", "Adele".toLowerCase()));
		assertFalse(lib.addAlbumToLibrary(ms, "19", "21 Savage".toLowerCase()));
	}

	@Test // FIX THIS
	void testAddSongToPlaylist() {

		lib.createPlayList("TEST".toLowerCase());
		assertTrue(lib.searchPlaylistByName("TEST".toLowerCase()).getUserSongList().size() == 0);

		lib.addSongToPlaylist("TEST".toLowerCase(), "IDGAF".toLowerCase(), "Drake".toLowerCase(),
				"For All the Dogs".toLowerCase());
		lib.addSongToPlaylist("TEST".toLowerCase(), "IDGAF".toLowerCase(), "Drake".toLowerCase(),
				"For All the Dogs".toLowerCase());

		assertTrue(lib.searchPlaylistByName("TEST".toLowerCase()).getUserSongList().size() == 1);
		assertTrue(lib.searchPlaylistByName("TEST".toLowerCase()).getUserSongList().get(0).getTitle()
				.equals("IDGAF".toLowerCase()));

	}

	@Test
	void testRemoveSongPlaylist() {

		lib.createPlayList("TESTER".toLowerCase());
		lib.createPlayList("TEST".toLowerCase());
		assertTrue(lib.searchPlaylistByName("TEST".toLowerCase()).getUserSongList().size() == 0);

		lib.addSongToPlaylist("TEST".toLowerCase(), "IDGAF".toLowerCase(), "Drake".toLowerCase(),
				"For All the Dogs".toLowerCase());
		lib.addSongToPlaylist("TEST".toLowerCase(), "DNA.".toLowerCase(), "Kendrick Lamar".toLowerCase(),
				"Damn.".toLowerCase());
		lib.addSongToPlaylist("TEST".toLowerCase(), "LOVE.".toLowerCase(), "Kendrick Lamar".toLowerCase(),
				"Damn.".toLowerCase());

		assertTrue(lib.searchPlaylistByName("TEST".toLowerCase()).getUserSongList().size() == 3);

		lib.removeSongPlaylist("TEST".toLowerCase(), "LOVE.".toLowerCase(), "Kendrick Lamar".toLowerCase());
		assertTrue(lib.searchPlaylistByName("TEST".toLowerCase()).getUserSongList().size() == 2);

	}

}
