package model;

import java.util.ArrayList;

public class LibraryModel {
	private ArrayList<Song> songLibrary;
	private ArrayList<Album> albumLibrary;
	private ArrayList<Playlist> playlistLibrary;

	public LibraryModel() {
		this.songLibrary = new ArrayList<Song>();
		this.albumLibrary = new ArrayList<Album>();
		this.playlistLibrary = new ArrayList<Playlist>();

	}

	/*
	 * creates a playlist object for the user
	 */
	public void createPlayList(String playlistName) {
		Playlist userPlaylist = new Playlist(playlistName);
		playlistLibrary.add(userPlaylist); // add playlist to playlistLibrary
	}

	/*
	 * return a deep copy of the list of playlists and their songs using copy
	 * constructor
	 */
	public ArrayList<Playlist> getAllPlayList() {
		ArrayList<Playlist> copyPlaylists = new ArrayList<Playlist>();
		for (Playlist playlist : playlistLibrary) {
			copyPlaylists.add(new Playlist(playlist));
		}
		return copyPlaylists;
	}

	/*
	 * gets a list of song titles from the library, added to the list as a string
	 */
	public ArrayList<String> getSongTitles() {
		ArrayList<String> songTitles = new ArrayList<String>();
		for (Song song : songLibrary) {
			songTitles.add(song.getTitle() + " By: " + song.getArtist() + ", Album: " + song.getAlbum());
		}
		return songTitles;

	}

	/*
	 * gets a deep copy of the list of the albums from the library
	 */
	public ArrayList<Album> getAlbumList() {
		ArrayList<Album> albumList = new ArrayList<Album>();
		for (Album album : albumLibrary) {
			albumList.add(new Album(album));
		}
		return albumList;
	}

	/*
	 * gets a list of the favorite songs from the songLibrary
	 */
	public ArrayList<Song> getFavoriteSongs() {
		ArrayList<Song> allFavoriteSongs = new ArrayList<Song>();
		for (Song song : songLibrary) {
			if (song.isFavorite()) {
				allFavoriteSongs.add(new Song(song));
			}
		}
		return allFavoriteSongs;
	}

	/*
	 * returns a string array list of all the artists in the music library
	 */
	public ArrayList<String> getArtists() {
		ArrayList<String> artistList = new ArrayList<>();
		for (Song song : songLibrary) {
			if (!artistList.contains(song.getArtist())) {
				artistList.add(song.getArtist()); // add artist if not in list yet
			}
		}
		return artistList;
	}

	/*
	 * iterates through song list and returns a list of all the songs with same
	 * title
	 */
	public ArrayList<Song> searchSongbyTitle(String title) {
		ArrayList<Song> songsWithTitle = new ArrayList<Song>();
		for (Song song : songLibrary) {
			if (song.getTitle().toLowerCase().equals(title)) {
				songsWithTitle.add(new Song(song));
			}
		}
		return songsWithTitle;
	}

	/*
	 * iterates through song list and returns a copy of song that matches title and
	 * artist
	 */
	public ArrayList<Song> searchSongbyArtist(String artist) {
		ArrayList<Song> allArtistSongs = new ArrayList<Song>();
		for (Song song : songLibrary) {
			if (song.getArtist().toLowerCase().equals(artist)) {
				allArtistSongs.add(new Song(song));
			}
		}
		return allArtistSongs;
	}

	/*
	 * iterates through albums, returns a deep copy of the album object that matches
	 * the title
	 */
	public Album searchAlbumTitle(String albumTitle) {
		for (Album album : albumLibrary) {
			if (album.getAlbumName().toLowerCase().equals(albumTitle)) {
				return new Album(album); // return copy of the album
			}
		}
		return null;
	}

	/*
	 * iterates through albums, returns a deep copy of all the album objects that
	 * matches with the artist
	 */
	public ArrayList<Album> searchAlbumArtist(String artist) {
		ArrayList<Album> allArtistAlbums = new ArrayList<Album>();
		for (Album album : albumLibrary) {
			if (album.getArtist().toLowerCase().equals(artist)) {
				allArtistAlbums.add(new Album(album)); // return copy of the album
			}
		}
		return allArtistAlbums;
	}

	/*
	 * iterates through playlists, returns a deep copy of the playlist object that
	 * matches with name
	 */
	public Playlist searchPlaylistByName(String playlistName) {
		for (Playlist playlist : playlistLibrary) {
			if (playlist.getPlaylistName().toLowerCase().equals(playlistName)) {
				return new Playlist(playlist);
			}
		}
		return null;
	}

	/*
	 * adds a song to the Song list library
	 */
	public boolean addSongToLibrary(MusicStore ms, String title, String artist) {
		for (Song song : ms.getSongsMusicStore()) { // iterate over songs in music store that are available
			if (song.getTitle().toLowerCase().equals(title) && song.getArtist().toLowerCase().equals(artist)) {
				if (!songLibrary.contains(song)) {
					songLibrary.add(new Song(song)); // add song to library if not in list yet
				}
				return true;
			}
		}
		// song was not found in music store, cannot be added to library
		return false;
	}

	/*
	 * adds an album to the Album list library
	 */
	public boolean addAlbumToLibrary(MusicStore ms, String albumName, String artist) {
		for (Album album : ms.getAlbumsMusicStore()) { // iterate over albums in music store that are available
			if (album.getAlbumName().toLowerCase().equals(albumName)
					&& album.getArtist().toLowerCase().equals(artist)) {
				for (Song song : album.getSongList()) { // add all songs from album to songLibrary
					if (!songLibrary.contains(song)) {
						songLibrary.add(song);
					}
				}
				albumLibrary.add(new Album(album));
				return true;
			}
		}
		// album was not found in music store, cannot be added to library
		return false;
	}

	/*
	 * Add's song from a playlist given by the user
	 */
	public void addSongToPlaylist(String playlistName, String songTitle, String artist, String albumTitle,
			String genre) {
		for (Playlist playlist : playlistLibrary) {
			if (playlist.getPlaylistName().equals(playlistName)) {
				if (!playlist.getUserSongList().contains(new Song(songTitle, artist, albumTitle, genre))) {
					playlist.addSongToPlaylist(songTitle, artist, albumTitle, genre);
				}
				if (!songLibrary.contains(new Song(songTitle, artist, albumTitle, genre))) {
					songLibrary.add(new Song(songTitle, artist, albumTitle, genre)); // add song to library if not in
																						// list yet
				}
			}
		}
	}

	/*
	 * Remove's song from a playlist given by the user
	 */
	public void removeSongPlaylist(String playlistName, String songTitle, String artist) {
		for (Playlist playlist : playlistLibrary) {
			if (playlist.getPlaylistName().toLowerCase().equals(playlistName.toLowerCase())) {
				playlist.removeSongFromPlaylist(songTitle, artist);
			}
		}
	}
}