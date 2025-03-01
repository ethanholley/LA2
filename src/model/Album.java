
package model;

import java.util.ArrayList;

public class Album {
	private String album;
	private String artist;
	private ArrayList<Song> songList;

	/*
	 * Regular Constructor
	 */
	public Album(String album, String artist, ArrayList<Song> songList) {
		this.album = album;
		this.artist = artist;
		this.songList = songList;
	}

	/*
	 * COPY CONSRUCTOR
	 */
	public Album(Album other) {
		this.album = other.album;
		this.artist = other.artist;
		this.songList = new ArrayList<Song>();

		for (Song song : other.songList) {
			this.songList.add(new Song(song));
		}
	}

	public String getArtist() {
		return artist;
	}

	public String getAlbumName() {
		return album;
	}

	public void addSong(Song song) {
		songList.add(song);
	}

	/*
	 * returns a copy of songList
	 * 
	 * @return ArrayList of all songs in album
	 */
	public ArrayList<Song> getSongList() {
		ArrayList<Song> copy = new ArrayList<>();
		for (Song song : songList) {
			copy.add(new Song(song));
		}
		return copy;
	}

	public boolean equals(Object o) {
		if (o == null)
			return false;
		else if (o == this)
			return true;
		else if (o.getClass() != getClass())
			return false;
		else {
			return this.album.equals(((Album) o).getAlbumName()) && this.artist.equals(((Album) o).getArtist())
					&& this.songList.equals(((Album) o).getSongList());
		}
	}

}
