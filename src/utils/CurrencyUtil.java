package utils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import delegations.calculation.model.Diet;

public class CurrencyUtil {

	private CurrencyUtil() {
	}

	public static String findCrrencyCode(String countryCode) {
		switch (countryCode) {
		case "AT":
		case "DE":
			return "EUR";
		case "PL":
			return "PLN";
		case "CZ":
			return "CZK";
		default:
			return "PLN";
		}
		// nie byly przewidziane w zadaniu
	}

	public static boolean isPossibleToHande(String countryCode) {
		return "AT".equals(countryCode) || "DE".equals(countryCode) || "PL".equals(countryCode)
				|| "CZ".equals(countryCode);
	}

	public static List<Diet> createDietCache() {
		List<Diet> diets = new ArrayList<>();
		diets.add(new Diet("AT", "EUR", new BigDecimal("52")));
		diets.add(new Diet("CZ", "EUR", new BigDecimal("41")));
		diets.add(new Diet("DE", "EUR", new BigDecimal("49")));
		diets.add(new Diet("PL", "PLN", new BigDecimal("30")));
		return diets;
	}

}
