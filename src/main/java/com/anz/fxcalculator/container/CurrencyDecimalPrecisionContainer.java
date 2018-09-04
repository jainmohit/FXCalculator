package com.anz.fxcalculator.container;

import java.util.HashMap;
import java.util.Map;

/**
 * This container will hold the currency decimal precision list in a {@link Map}
 * of all the decimal precision provided by the user.
 * 
 * @author Mohit Jain
 * @version 1.0
 */
public class CurrencyDecimalPrecisionContainer implements ICurrencyDecimalPrecisionContainer {

	/**
	 * Default precision to be set in case no precision is provided by the user
	 * for the term currency provided in the arguments.
	 */
	public static final Integer DEFAULT_PRECISION = 2;

	/**
	 * Map to hold the currency precision pair.
	 */
	private Map<String, Integer> currencyDecimalPrecisionData;

	/**
	 * Method implementation of adding the currency pair in the map initialized.
	 */
	public void addCurrencyPrecision(String currency, Integer precision) {
		if (null == currencyDecimalPrecisionData) {
			currencyDecimalPrecisionData = new HashMap<String, Integer>();
		}

		currencyDecimalPrecisionData.put(currency, precision);
	}

	/**
	 * Method implementation returning the decimal precision for the particular
	 * currency.
	 */
	public Integer getCurrencyPrecision(String currency) {
		return ((null == currencyDecimalPrecisionData) ? DEFAULT_PRECISION
				: currencyDecimalPrecisionData.get(currency));
	}
}
