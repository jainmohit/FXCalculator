package com.anz.fxcalculator.vo;

/**
 * CrossViaOptions is the relation between the BASE and the TERM currency as it
 * could anyone of the below:
 * 
 * <li>{@code D} = direct feed - eg. the currency pair can be looked up
 * directly.</li>
 * <li>{@code Inv} = inverted - eg. the currency pair can be looked up if base
 * and terms are flipped (and rate = 1/rate)</li>
 * <li>{@code 1:1} = unity - the rate is always 1.</li> <br>
 * <br>
 * 
 * @author Mohit Jain
 * @version 1.0
 */
public enum CrossViaOptions {

	DIRECT("D"), INVERSE("Inv"), UNITY("1:1");

	private String code;

	/**
	 * Constructor for setting the code for different types of entity like
	 * DIRECT.
	 * 
	 * @param code
	 */
	private CrossViaOptions(String code) {
		this.code = code;
	}

	/**
	 * Getter for returning the code for the particular entity like DIRECT.
	 * 
	 * @return code
	 */
	public String getCode() {
		return code;
	}
}