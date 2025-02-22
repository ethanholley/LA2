package model;

import java.util.ArrayList;

// MUSIC STORE COMMENT

public class MusicStore {
	ArrayList<Song> allSongsList;
	ArrayList<Album> allAlbumsList;
	
	public MusicStore() {
		this.allSongsList = new ArrayList<Song>();
		this.allAlbumsList = new ArrayList<Album>();
	}
	
	@Override
	public boolean equals(Object o) {
		if (o == null)
			return false;
		else if (o == this)
			return true;
		else if (o.getClass() != getClass())
			return false;
		else {
			return this.allSongsList == ((MusicStore) o).allSongsList && this.allAlbumsList == ((MusicStore) o).allAlbumsList;
		}
	}

	public void addSong(ArrayList<Song> allSongs) {
		for(Song song: allSongs) {
			allSongsList.add(song);
		}
	}
	
	public void addAlbum(Album album) {
		allAlbumsList.add(album);
	}
	
	public String searchSongbyTitle(String title) {
		for (Song song: allSongsList) {
			if (song.getTitle() == title) {
				return song.getTitle() + ", By: " + song.getArtist() + ", Album: " + song.getAlbum();
			}
		}
		return null;
	}
	
	public String searchSongbyArtist(String artist) {
		for (Song song: allSongsList) {
			if (song.getArtist() == artist) {
				return song.getTitle() + ", By: " + song.getArtist() + ", Album: " + song.getAlbum();
			}
		}
		return null;
	}
	
	public Album searchAlbumTitle(String albumTitle) {
		for(Album album: allAlbumsList) {
			if (album.getAlbumName() == albumTitle) {
				ArrayList<Song> copySongs = album.getSongList(); // get a copy list of album's songs
				return new Album(album, copySongs); // return copy of the album	
			}
		}
		return null;
	}
	
	public Album searchAlbumArtist(String artist) {
		for(Album album: allAlbumsList) {
			if (album.getArtist() == artist) {
				ArrayList<Song> copySongs = album.getSongList(); // get a copy list of album's songs
				return new Album(album, copySongs); // return copy of the album	
			}
		}
		return null;
	}

}