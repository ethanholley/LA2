package model;

import java.util.ArrayList;

public class MusicStore {
	ArrayList<Song> allSongsList;
	ArrayList<Album> allAlbumsList;

	public MusicStore() {
		this.allSongsList = new ArrayList<Song>();
		this.allAlbumsList = new ArrayList<Album>();
	}

	// COPY CONSTRUCTOR
	public MusicStore(MusicStore copy) {
		this.allAlbumsList = copy.allAlbumsList;
		this.allSongsList = copy.allSongsList;
	}

	public void addSong(ArrayList<Song> allSongs) {
		for (Song song : allSongs) {
			allSongsList.add(song);
		}
	}

	public void addAlbum(String albumName, String artist, ArrayList<Song> songList) {
		allAlbumsList.add(new Album(albumName, artist, songList));
	}

	public Song searchSongbyTitle(String title) {
		for (Song song : allSongsList) {
			if (song.getTitle().equals(title)) {
				return new Song(song); // return song object
			}
		}
		return null;
	}

	public Song searchSongbyArtist(String artist, String title) {
		for (Song song : allSongsList) {
			if (song.getArtist() == artist && song.getTitle().equals(title)) {
				return new Song(song);
			}
		}
		return null;
	}

	public Album searchAlbumTitle(String albumTitle) {
		for (Album album : allAlbumsList) {
			if (album.getAlbumName() == albumTitle) {
				ArrayList<Song> copySongs = album.getSongList(); // get a copy list of album's songs
				return new Album(album, copySongs); // return copy of the album
			}
		}
		return null;
	}

	public Album searchAlbumArtist(String artist, String albumName) {
		for (Album album : allAlbumsList) {
			if (album.getArtist() == artist && album.getAlbumName().equals(albumName)) {
				ArrayList<Song> copySongs = album.getSongList(); // get a copy list of album's songs
				return new Album(album, copySongs); // return copy of the album
			}
		}
		return null;
	}

}