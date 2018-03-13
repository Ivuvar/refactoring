package rental;

public class RentalPriceCalculator {

	public final double PRICE_UPPER_LIMIT = 1000.00;
	
	public double getRentalPrice(int driverAge, int yearsLicenceOwned, int carClass,
								 boolean hasCausedAccidentsLastYear, boolean isHighSeason) {
		
		if (driverAge < 18) {
			throw new IllegalArgumentException("Driver too young - cannot quote the price");
		}

		if (driverAge <= 21 && carClass > 1) {
			throw new UnsupportedOperationException("Drivers 21 y/o or less can only rent Class 1 vehicles");
		}

		if (yearsLicenceOwned < 1) {
			throw new IllegalArgumentException("Driver must hold driving licence at least for one year. Can not rent a car!");
		}

		double rentalPrice = calculateRentalPrice(driverAge, yearsLicenceOwned, carClass, hasCausedAccidentsLastYear, isHighSeason);

		return rentalPrice > PRICE_UPPER_LIMIT ? PRICE_UPPER_LIMIT : rentalPrice;
	}

	private double calculateRentalPrice(int driverAge, int yearsLicenceOwned, int carClass, boolean hasCausedAccidentsLastYear, boolean isHighSeason) {

		double rentalPrice = driverAge;

		if (carClass >= 4 && driverAge <= 25 && isHighSeason) {
			rentalPrice *= 2;
		}

		if (yearsLicenceOwned < 3) {
			rentalPrice *= 1.3;
		}

		if (hasCausedAccidentsLastYear && driverAge < 30) {
			rentalPrice += 15;
		}

		return rentalPrice;
	}
}