package rental;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class RentalPriceCalculatorTest {

	RentalPriceCalculator priceCalculator;

	@Before
	public void beforeEachTest() {
		priceCalculator = new RentalPriceCalculator();
	}

	@Test(expected = IllegalArgumentException.class)
	public void testDriverTooYoung() {
		priceCalculator.getRentalPrice(17, 0, 1, false, false);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testLicenceNotOwnedForLongEnough() {
		priceCalculator.getRentalPrice(18, 0, 1, false, false);

	}

	@Test(expected = UnsupportedOperationException.class)
	public void test21YearOldDrivingHigherClassCar() {
		priceCalculator.getRentalPrice(21, 1, 2, false, false);

	}

	@Test
	public void testAbsurdParametersDontExceedMaxPrice() {
		Assert.assertEquals(priceCalculator.PRICE_UPPER_LIMIT, priceCalculator.getRentalPrice(10000, 1000, 50, true, true), 100);
	}

	@Test(expected = UnsupportedOperationException.class)
	public void test18YearOldCanOnlyRentClassOneCar() {
		priceCalculator.getRentalPrice(18, 1, 2, false, false);
	}

	@Test
	public void testLowestPriceIsDriverAge() {
		int driverAge = 35;
		double rentalPrice = priceCalculator.getRentalPrice(driverAge, 15, 1, false, false);
		assertEquals((double) driverAge, rentalPrice, 0.5);
	}
}
