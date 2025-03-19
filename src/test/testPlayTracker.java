package test;

import model.Genre;
import model.PlayTracker;
import model.Song;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Map;


public class testPlayTracker {

	@Test
    public void testPlaySongUpdatesPlayCount() {
		PlayTracker playTracker = new PlayTracker();
		
		Song song1 = new Song("luther", "Kendrick Lamar", "GNX", Genre.SINGER_SONGWRITER);
		Song song2 = new Song("Feel No Ways", "Drake", "Views", Genre.SINGER_SONGWRITER);
		Song song3 = new Song("Sunflower", "Post Malone", "Hollywoods Bleeding", Genre.ALTERNATIVE);
		
		playTracker.playSong(song1);
		playTracker.playSong(song2);
		playTracker.playSong(song3);
		playTracker.playSong(song1);
		
		Map<Song, Integer> playCounts = playTracker.getPlayCounts(); // Assuming you have a method to get the playCounts
		
		// Validate the play counts of each song
        assertEquals(2, playCounts.get(song1));
        assertEquals(1, playCounts.get(song2));
        assertEquals(1, playCounts.get(song3));
	}
	
	@Test
	public void testRecentlyPlayedSongs() {
		PlayTracker playTracker = new PlayTracker();
		Song song1 = new Song("luther", "Kendrick Lamar", "GNX", Genre.SINGER_SONGWRITER);
		Song song2 = new Song("Feel No Ways", "Drake", "Views", Genre.SINGER_SONGWRITER);
		Song song3 = new Song("Sunflower", "Post Malone", "Hollywoods Bleeding", Genre.ALTERNATIVE);
		
		playTracker.playSong(song1);
        playTracker.playSong(song2);
        playTracker.playSong(song3);
        
        ArrayList<Song> recentlyPlayed = playTracker.getRecentlyPlayed();
        
        assertEquals(song3, recentlyPlayed.get(0));
        assertEquals(song2, recentlyPlayed.get(1));
        assertEquals(song1, recentlyPlayed.get(2));
    }
	
	@Test
    public void testMostPlayedSongs() {
        PlayTracker playTracker = new PlayTracker();
        
        Song song1 = new Song("luther", "Kendrick Lamar", "GNX", Genre.SINGER_SONGWRITER);
        Song song2 = new Song("Feel No Ways", "Drake", "Views", Genre.SINGER_SONGWRITER);
        Song song3 = new Song("Sunflower", "Post Malone", "Hollywoods Bleeding", Genre.ALTERNATIVE);
        
        // Play songs multiple times
        playTracker.playSong(song1); // 1 play
        playTracker.playSong(song1); // 2 plays
        playTracker.playSong(song2); // 1 play
        playTracker.playSong(song3); // 1 play
        playTracker.playSong(song3); // 2 plays
        playTracker.playSong(song3); // 3 plays
        
        // Get most played songs (expecting them in descending order of play count)
        ArrayList<Song> mostPlayed = playTracker.getMostPlayed();
        
        assertEquals(song3, mostPlayed.get(0));
        assertEquals(song1, mostPlayed.get(1));
        assertEquals(song2, mostPlayed.get(2));
        
	}
	
	@Test
    public void testRecentlyPlayedListSize() {
        PlayTracker playTracker = new PlayTracker();
        
        for (int i = 1; i <= 15; i++) {
            playTracker.playSong(new Song("Song #" + i, "Artist #" + i, "Album #" + i, Genre.SINGER_SONGWRITER));
        }
        ArrayList<Song> recentlyPlayed = playTracker.getRecentlyPlayed();
        
        assertEquals(10, recentlyPlayed.size());
        
        assertEquals("Song #15", recentlyPlayed.get(0).getTitle());
        
	}
}

