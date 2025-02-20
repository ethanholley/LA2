package model;

import java.util.ArrayList;

public class MusicStore {
	ArrayList<Song> songList = new ArrayList<Song>();
	ArrayList<Album> albumList = new ArrayList<Album>();

	@Override
	public boolean equals(Object o) {
		if (o == null)
			return false;
		else if (o == this)
			return true;
		else if (o.getClass() != getClass())
			return false;
		else {
			return this.songList == ((MusicStore) o).songList && this.albumList == ((MusicStore) o).albumList;
		}
	}
}
