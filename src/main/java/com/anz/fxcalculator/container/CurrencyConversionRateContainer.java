package com.anz.fxcalculator.container;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 
 * Implementation for currency pair along with the conversion rate provided by
 * the user in a file
 * 
 * @author Mohit Jain
 * @version 1.0
 */
public class CurrencyConversionRateContainer implements ICurrencyConversionRateContainer {

	/**
	 * Holds the value of the currency pair along with rates.
	 */
	private Map<String, Float> conversionRateData;

	/**
	 * Map containing distinct currency mapping to the set of Values that can be
	 * traversed directly from the same.
	 */
	private Map<String, Set<String>> currencyMatchingData;

	/**
	 * 
	 */
	public void addConversionRate(String sourceCurrency, String destinationCurrency, Float conversionRate) {

		updateConversionRateData(sourceCurrency, destinationCurrency, conversionRate);
		updateCurrencyMatchRoutes(sourceCurrency, destinationCurrency);
	}

	/**
	 * Update the conversion rate for all the different currency pair.
	 * 
	 * @param sourceCurrency
	 * @param destinationCurrency
	 * @param conversionRate
	 */
	private void updateConversionRateData(String sourceCurrency, String destinationCurrency, Float conversionRate) {
		if (null == conversionRateData) {
			conversionRateData = new HashMap<String, Float>();
		}
		conversionRateData.put(sourceCurrency + destinationCurrency, conversionRate);

		if (null == currencyMatchingData) {
			currencyMatchingData = new HashMap<String, Set<String>>();
		}
	}

	/**
	 * Update the map of all unique currencies holds the set of all the distinct
	 * currency that can be traced from the same.
	 * 
	 * @param sourceCurrency
	 * @param destinationCurrency
	 */
	private void updateCurrencyMatchRoutes(String sourceCurrency, String destinationCurrency) {
		Set<String> existingMatchSrc = currencyMatchingData.get(sourceCurrency);
		if (null == existingMatchSrc) {
			existingMatchSrc = new HashSet<String>();
			existingMatchSrc.add(destinationCurrency);
			currencyMatchingData.put(sourceCurrency, existingMatchSrc);
		} else {
			existingMatchSrc.add(destinationCurrency);
		}

		Set<String> existingMatchDest = currencyMatchingData.get(destinationCurrency);
		if (null == existingMatchDest) {
			existingMatchDest = new HashSet<String>();
			existingMatchDest.add(sourceCurrency);
			currencyMatchingData.put(destinationCurrency, existingMatchDest);
		} else {
			existingMatchDest.add(sourceCurrency);
		}
	}

	/**
	 * return the conversion rate for a give currency pair.
	 * 
	 * @return Float
	 */
	public Float getConversionRate(String sourceCurrency, String destinationCurrency) {
		return ((null == conversionRateData) ? null : conversionRateData.get(sourceCurrency + destinationCurrency));
	}

	/**
	 * Method implementation for getting all the unique currencies.
	 */
	public Set<String> getAllCurrencies() {
		return currencyMatchingData.keySet();
	}

	/**
	 * This interface method will return the set of all the distinct currency
	 * which can traversed from a given source currency.
	 */
	public Set<String> getAllMatches(String currency) {
		return currencyMatchingData.get(currency);
	}
}