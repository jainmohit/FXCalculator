package com.anz.fxcalculator.exceptions;

import org.apache.log4j.Logger;

/**
 * General application exception used to throw exception wile application
 * loading
 * 
 * @author Mohit Jain
 * @version 1.0
 */
public class ApplicationException extends Exception {

	private Logger LOGGER = Logger.getLogger(ApplicationException.class.getName());

	/**
	 * serial version id generated
	 */
	private static final long serialVersionUID = 8571779784681005958L;

	/**
	 * Constructor with the specific message
	 * 
	 * @param message
	 */
	public ApplicationException(String message) {
		super(message);
		System.out.println(message);
		LOGGER.error(message);
		System.exit(1);
	}

}
