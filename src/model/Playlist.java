package model;

import java.util.ArrayList;

public class Playlist {
	private ArrayList<Song> userSongList;
	private String playlistName;

	/*
	 * Regular Constructor
	 */
	public Playlist(String playlistName) {
		this.playlistName = playlistName;
		this.userSongList = new ArrayList<Song>();
	}

	/*
	 * COPY CONSTRUCTOR
	 */
	public Playlist(Playlist other) {
		this.playlistName = other.playlistName;
		this.userSongList = new ArrayList<Song>();

		for (Song song : other.userSongList) {
			this.userSongList.add(new Song(song));
		}
	}

	public String getPlaylistName() {
		return this.playlistName;
	}

	/*
	 * gets a deep copy of the playlist, with all the copied songs.
	 * 
	 * @return ArrayList of Songs in playlist
	 */
	public ArrayList<Song> getUserSongList() {
		ArrayList<Song> copyPlaylist = new ArrayList<>();
		for (Song song : userSongList) {
			copyPlaylist.add(new Song(song));
		}
		return copyPlaylist;
	}

	/*
	 * adds a song to the playlist, avoids escape reference by passing in string
	 * arguments
	 */
	public void addSongToPlaylist(String title, String artist, String album, String genre) {
		if (!userSongList.contains(new Song(title, artist, album, genre))) {
			userSongList.add(new Song(title, artist, album, genre));
		}
	}

	/*
	 * removes a song from the playlist
	 */
	public void removeSongFromPlaylist(String songTitle, String artist) {
		for (Song song : userSongList) {
			if (song.getTitle().equals(songTitle)) {
				userSongList.remove(song);
				break;
			}
		}
	}

}