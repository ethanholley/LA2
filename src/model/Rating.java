package model;

public enum Rating {
	ZERO(0), ONE(1), TWO(2), THREE(3), FOUR(4), FIVE(5);

	private final int VALUE;

	Rating(int value) {
		this.VALUE = value;
	}

	public int getRating() {
		return this.VALUE;
	}

	public boolean isEquals(Rating other) {
		if (other == null)
			return false;
		else if (other == this)
			return true;
		else
			return this.getRating() == other.getRating();
	}

}
