package model;

public enum Genre {
	ALTERNATIVE("Alternative"), POP("Pop"), SINGER_SONGWRITER("Singer/Songwriter"),
	TRADITIONAL_COUNTRY("Traditional Country"), ROCK("Rock"), LATIN("Latin");

	private final String displayName;

	Genre(String displayName) { // constructor
		this.displayName = displayName;
	}

	public String getDisplayName() { // getter
		return displayName;
	}

	public static Genre fromString(String input) {
		if (input == null) {
			return null;
		}
		for (Genre genre : Genre.values()) {
			if (genre.getDisplayName().equalsIgnoreCase(input)) { // Compare with displayName
				return genre;
			}
		}
		return null; // Return null if no match is found
	}

	public boolean isEquals(Genre other) {
		if (other == null)
			return false;
		else if (other == this)
			return true;
		else
			return this.getDisplayName() == other.getDisplayName();
	}
}
