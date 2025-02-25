package model;

import java.util.ArrayList;

public class Playlist {
	private ArrayList<Song> userSongList;
	private String playlistName;

	public Playlist(String playlistName) {
		this.playlistName = playlistName;
		this.userSongList = new ArrayList<Song>();
	}

	public String getPlaylistName() {
		return this.playlistName;
	}

	public ArrayList<Song> getUserSongList() {
		ArrayList<Song> copyPlaylist = new ArrayList<>();
		for (Song song : userSongList) {
			copyPlaylist.add(new Song(song)); // adds a song copy to the copied playlist array
		}
		return copyPlaylist;
	}

	public void addSongToPlaylist(Song song) { // adds a song to the playlist
		userSongList.add(song);
	}

	public void removeSongFromPlaylist(String songTitle) { // removes a song from the playlist
		for (Song song : userSongList) {
			if (song.getTitle() == songTitle) {
				userSongList.remove(song);
			}
		}
	}

}