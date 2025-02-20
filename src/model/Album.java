package model;

import java.util.ArrayList;

public class Album {

	private String album;
	private String artist;
	private ArrayList<Song> songList = new ArrayList<Song>();

	public Album(String album, String artist, ArrayList<Song> songList) {
		this.album = album;
		this.artist = artist;
		this.songList = songList;
	}

	public String getAlbum() {
		return album;
	}

	public String getArtist() {
		return artist;
	}

	public ArrayList<Song> getSongList() {
		return songList;
	}
}
