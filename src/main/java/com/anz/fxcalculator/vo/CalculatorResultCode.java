package com.anz.fxcalculator.vo;

/**
 * This class represent a set of outcome as a result of the currency
 * conversion<br>
 * which could be success in case f conversion possible and not found in case
 * the<br>
 * conversion is not possible.<br>
 * <br>
 * 
 * @author Mohit Jain
 * @version 1.0
 */
public enum CalculatorResultCode {

	/**
	 * Represent successful scenario for the conversion
	 */
	SUCCESS,

	/**
	 * Represents the conversion if is not successful.
	 */
	CONVERSION_RATE_NOT_FOUND;
}