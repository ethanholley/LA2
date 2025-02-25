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

}