package com.anz.fxcalculator.container;

/**
 * Interface provided with the basic functionality of adding and getting the
 * currency precision.
 * 
 * @author Mohit Jain
 * @version 1.0
 */
public interface ICurrencyDecimalPrecisionContainer {

	/**
	 * This method is responsible for storing the pair of currency and
	 * precision.
	 * 
	 * @param currency
	 * @param precision
	 */
	public void addCurrencyPrecision(String currency, Integer precision);

	/**
	 * this method will accept the currency and in return will return the
	 * precision required for the term currency.
	 * 
	 * @param currency
	 * @return Integer that contains precision
	 */
	public Integer getCurrencyPrecision(String currency);
}
