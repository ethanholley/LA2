package model;

public enum Genre {
	POP, ALTERNATIVE, TRADITIONALCOUNTRY, LATIN, ROCK, SINGERSONGWRITER;

	Genre() {
		// TODO Auto-generated constructor stub

	}

	Genre fromString(String genre) {
		// TODO Auto-generated method stub
		return null;
	}

	public Genre getGenre() {
		return this.getGenre();
	}

	public boolean isEquals(Genre other) {
		if (other == null)
			return false;
		else if (other == this)
			return true;
		else
			return this.getGenre() == other.getGenre();
	}
}
