package com.anz.fxcalculator.exceptions;

/**
 * Custom exception to parse the input arguments being passed every time<br>
 * like < ccy1 > < amount1 > in < ccy2 >
 * 
 * @author Mohit Jain
 * @version 1.0
 */
public class CommandLineParserException extends Exception {

	/**
	 * serial version id
	 */
	private static final long serialVersionUID = 6573047219805948374L;

	/**
	 * Constructor with the specific message
	 * 
	 * @param message
	 */
	public CommandLineParserException(String message) {
		super(message);
	}

}
