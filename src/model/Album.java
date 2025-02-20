package model;

import java.util.ArrayList;

public class Album {
	private String artist;
	private ArrayList<Song> songList = new ArrayList<Song>();

	public Album(String artist, ArrayList<Song> songList) {
		this.artist = artist;
		this.songList = songList;
	}

	// test
	public String getArtist() {
		return artist;
	}

	public ArrayList<Song> getSongList() {
		return songList;
	}

	// hello
}
