package com.anz.fxcalculator.container;

import java.util.Set;

/**
 * Interface for currency pair along with the conversion rate provided by the
 * user in a file.
 * 
 * @author Mohit Jain
 * @version 1.0
 */
public interface ICurrencyConversionRateContainer {

	/**
	 * Interface method for adding the initial list of currency pair with the
	 * conversion rate.
	 * 
	 * @param sourceCurrency
	 * @param destinationCurrency
	 * @param conversionRate
	 */
	public void addConversionRate(String sourceCurrency, String destinationCurrency, Float conversionRate);

	/**
	 * Interface method for getting the conversion rate for the given initial
	 * currency pair.
	 * 
	 * @param sourceCurrency
	 * @param destinationCurrency
	 * @return
	 */
	public Float getConversionRate(String sourceCurrency, String destinationCurrency);

	/**
	 * This interface method will hold all the unique currencies available for
	 * conversion.
	 * 
	 * @return
	 */
	public Set<String> getAllCurrencies();

	/**
	 * This interface method will return the set of all the distinct currency which
	 * can traversed from a given source currency.
	 * 
	 * @param currency
	 * @return
	 */
	public Set<String> getAllMatches(String currency);
}
