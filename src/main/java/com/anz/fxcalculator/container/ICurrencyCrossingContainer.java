package com.anz.fxcalculator.container;

/**
 * Interface defined for the cross-via matrix container.
 * 
 * @author Mohit Jain
 * @version 1.0
 */
public interface ICurrencyCrossingContainer {
	/**
	 * Adding the cross-via currency pair.
	 * 
	 * @param sourceCurrency
	 * @param destinationCurrency
	 * @param crossingViaValue
	 */
	public void addCrossingData(String sourceCurrency, String destinationCurrency, String crossingViaValue);

	/**
	 * Returning the cross-via for the currency pair.
	 * 
	 * @param sourceCurrency
	 * @param destinationCurrency
	 * @return
	 */
	public String getCrossingData(String sourceCurrency, String destinationCurrency);
}
