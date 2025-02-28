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
		this.allSongsList = new ArrayList<>();
		this.allAlbumsList = new ArrayList<>();

		// Deep copy all songs (assuming Song has a copy constructor)
		for (Song song : copy.allSongsList) {
			this.allSongsList.add(new Song(song));
		}

		// Deep copy all albums (assuming Album has a copy constructor)
		for (Album album : copy.allAlbumsList) {
			this.allAlbumsList.add(new Album(album));
		}
	}

	// returns a copy of all the songs in the music store
	public ArrayList<Song> getSongsMusicStore() {
		ArrayList<Song> copySongs = new ArrayList<Song>();
		for (Song song : allSongsList) {
			copySongs.add(new Song(song));
		}
		return copySongs;
	}

	// returns a copy of all the albums in the music store
	public ArrayList<Album> getAlbumsMusicStore() {
		ArrayList<Album> copyAlbums = new ArrayList<Album>();
		for (Album album : allAlbumsList) {
			copyAlbums.add(new Album(album));
		}
		return copyAlbums;
	}

	// adds a copy of song to the music Songs list
	public void addSong(ArrayList<Song> allSongs) {
		for (Song song : allSongs) {
			allSongsList.add(new Song(song));
		}
	}

	// creates new album object and adds to allAlbums list
	public void addAlbum(String albumName, String artist, ArrayList<Song> songList) {
		allAlbumsList.add(new Album(albumName, artist, songList));
	}

	// iterates through song list and returns a list of all the songs with that
	// title
	public ArrayList<Song> searchSongbyTitle(String title) {
		ArrayList<Song> allSongsWithTitle = new ArrayList<Song>();
		for (Song song : allSongsList) {
			if (song.getTitle().toLowerCase().equals(title)) {
				allSongsWithTitle.add(new Song(song)); // add song object
			}
		}
		return allSongsWithTitle;
	}

	// iterates through song list and returns a list of the songs by that artist
	public ArrayList<Song> searchSongbyArtist(String artist) {
		ArrayList<Song> allSongsByArtist = new ArrayList<Song>();
		for (Song song : allSongsList) {
			if (song.getArtist().toLowerCase() == artist) {
				allSongsByArtist.add(new Song(song));
			}
		}
		return allSongsByArtist;
	}

	// iterates through albums, returns a deep copy of the album object that matches
	// the title
	public Album searchAlbumTitle(String albumTitle) {
		for (Album album : allAlbumsList) {
			if (album.getAlbumName().toLowerCase() == albumTitle) {
				return new Album(album); // return copy of the album
			}
		}
		return null;
	}

	// iterates through albums, returns a list of all the albums by the artist
	public ArrayList<Album> searchAlbumArtist(String artist, String albumName) {
		ArrayList<Album> allAlbumsByArtist = new ArrayList<Album>();
		for (Album album : allAlbumsList) {
			if (album.getArtist().toLowerCase() == artist) {
				allAlbumsByArtist.add(new Album(album)); // add copy of the album
			}
		}
		return allAlbumsByArtist;
	}

	// SET RATING
	public void setRatingOfSong(String Artist, String Song, Rating rating) {
		for (Song song : this.allSongsList) {
			if (song.getArtist().toLowerCase().equals(Artist) && song.getTitle().toLowerCase().equals(Song)) {
				song.setRating(rating);
				break;
			}
		}
	}

	// SET FAV
	public void setFavoriteOfSong(String Artist, String Song) {
		for (Song song : this.allSongsList) {
			if (song.getArtist().toLowerCase().equals(Artist) && song.getTitle().toLowerCase().equals(Song)) {
				song.setFavorite();
				break;
			}
		}
	}

}