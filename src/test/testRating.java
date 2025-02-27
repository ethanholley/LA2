package test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import model.Rating;

public class testRating {

	Rating rateZero = Rating.ZERO;
	Rating rateOne = Rating.ONE;
	Rating rateTwo = Rating.TWO;
	Rating rateThree = Rating.THREE;
	Rating rateFour = Rating.FOUR;
	Rating rateFive = Rating.FIVE;

	@Test
	void testGetRating() {

		assertTrue(rateZero.getRating() == 0);
		assertTrue(rateOne.getRating() == 1);
		assertTrue(rateTwo.getRating() == 2);
		assertTrue(rateThree.getRating() == 3);
		assertTrue(rateFour.getRating() == 4);
		assertTrue(rateFive.getRating() == 5);

	}

	// STILL NEED 1 MORE BRANCH, I THINK SINCE ITS AN ENUM WE CAN'T GET IT
	@Test
	void testIsEquals() {
		assertFalse(rateZero.isEquals(null));
		assertTrue(rateOne.isEquals(rateOne));

		Rating rateTwo = null;
		rateTwo = rateTwo.TWO;

		assertTrue(this.rateTwo.isEquals(rateTwo)); // -> wont give the other half of branch for
													// return this.getRating() == other.getRating()
		assertFalse(rateThree.isEquals(rateOne));

	}

}