package com.anz.fxcalculator.vo;

/**
 * 
 * Class defined to represent the four arguments present in the input<br>
 * like "<ccy1> <amount1> in <ccy2>". So in this case ccy1 is BASE currency,
 * "in" is a kind of filler<br>
 * to separate the Amount from the ccy2 which is Terms(destination) currency.
 * 
 * @author Mohit Jain
 * @version 1.0
 * 
 */
public enum InputType {

	BASE("0"),

	AMOUNT("1"),

	FILLER("2"),

	TERMS("3");

	private String code;

	/**
	 * Constructor for code assigned to the particular input type.
	 * 
	 * @param code
	 */
	private InputType(String code) {
		this.code = code;
	}

	/**
	 * Returns the code assigned to the particular input type.
	 * 
	 * @return code
	 */
	public String getCode() {
		return code;
	}
}
