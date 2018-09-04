package com.anz.fxcalculator.container;

import java.util.HashMap;
import java.util.Map;

/**
 * Container to hold all the relation between all the distinct currency pair
 * which could be direct, unity, inverse or cross-via any other currency.
 * 
 * @author Mohit Jain
 * @version 1.0
 */
public class CurrencyCrossingContainer implements ICurrencyCrossingContainer {

	/**
	 * map of all the currency pair possible from the given set of currency pair
	 * with conversion rate.
	 */
	private Map<String, Map<String, String>> currencyCrossingDataMatrix;

	/**
	 * This method will keep on adding all the unique set to map
	 * currencyCrossingDataMatrix which can be traced directly, unity, inverse
	 * or cross-via currency.
	 */
	public void addCrossingData(String sourceCurrency, String destinationCurrency, String crossingViaValue) {

		if (null == currencyCrossingDataMatrix) {
			currencyCrossingDataMatrix = new HashMap<String, Map<String, String>>();
		}

		Map<String, String> currencyDestinations = currencyCrossingDataMatrix.get(sourceCurrency);
		if (null == currencyDestinations) {
			Map<String, String> newCurrencyDestinations = new HashMap<String, String>();
			newCurrencyDestinations.put(destinationCurrency, crossingViaValue);
			currencyCrossingDataMatrix.put(sourceCurrency, newCurrencyDestinations);
		} else {
			currencyDestinations.put(destinationCurrency, crossingViaValue);
		}
	}

	/**
	 * This method will keep return the relation between the unique set
	 * available in the currencyCrossingDataMatrix.
	 * 
	 */
	public String getCrossingData(String sourceCurrency, String destinationCurrency) {

		if (null == currencyCrossingDataMatrix || currencyCrossingDataMatrix.size() < 1) {
			return null;
		}

		Map<String, String> currencyDestinations = currencyCrossingDataMatrix.get(sourceCurrency);
		if (null == currencyDestinations || currencyDestinations.size() < 1) {
			return null;
		}

		return currencyDestinations.get(destinationCurrency);
	}
}
