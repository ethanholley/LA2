package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PlayTracker {
	private ArrayList<Song> recentlyPlayed;
	private Map<Song, Integer> playCounts;
	
	public PlayTracker() {
        this.recentlyPlayed = new ArrayList<>();
        this.playCounts = new HashMap<>();
    }
	
	public void playSong(Song song) {
		
		// set count to 1 or increment song count if already in HashMap
		if (playCounts.containsKey(song)) {
		    playCounts.put(song, playCounts.get(song) + 1);
		} else {
		    playCounts.put(song, 1);
		}
		
		// Update recently played list
        recentlyPlayed.remove(song);  // Remove if already in list
        recentlyPlayed.add(0, song); // Add to front to make most recent
        
        if (recentlyPlayed.size() > 10) {
            recentlyPlayed.remove(10); // remove the oldest song
        }
        
	}
	
	// return copy of recently played songs
	public ArrayList<Song> getRecentlyPlayed() {
        return new ArrayList<>(recentlyPlayed);
    }
	
	public Map<Song, Integer> getPlayCounts() {
	    return new HashMap<>(playCounts); // Return a copy of the playCounts map
	}
	
	public ArrayList<Song> getMostPlayed() {
		ArrayList<Song> sortedSongs = new ArrayList<>(playCounts.keySet()); // create array of played songs
		
		sortedSongs.sort((a, b) -> playCounts.get(b) - playCounts.get(a)); // sort songs by play count (descending)
		
		// return first 10 songs from playCounts
		return new ArrayList<>(sortedSongs.subList(0, Math.min(sortedSongs.size(), 10)));
	}
	
}
