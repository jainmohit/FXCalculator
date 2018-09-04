package com.anz.fxcalculator.parser;

import com.anz.fxcalculator.exceptions.CommandLineParserException;
import com.anz.fxcalculator.vo.InputType;

/**
 * Parser to parse the command line argument passed during the FX conversion
 * every time as per the specified formt like < ccy1 > < amount1 > in < ccy2 >.
 * This parser is supplied with the console argument passed as an array.
 * 
 * 
 * @author Mohit Jain
 *
 */

public class CommandLineParser {

	/**
	 * Restricting the object creation of the class by making the private
	 * constructor
	 */
	private CommandLineParser() {
		// do nothing
	}

	/**
	 * This method is going to parser the arguments (< ccy1 > < amount1 > in <
	 * ccy2 >) <br>
	 * for checking the following condition:
	 * 
	 * <li>Input parameter should consist of 4 parameters with 3rd position as
	 * filler "in".</li>
	 * <li>< amount1 > provided should be valid positive decimal
	 * number.</li><br>
	 * <br>
	 * 
	 * Will be returning true or false as an output as per conditions.<br>
	 * <br>
	 * 
	 * @param args
	 * @return boolean
	 * @throws Exception
	 */
	public static boolean parseArguments(String[] args) throws Exception {

		if (args.length != 4 || !(("in").equalsIgnoreCase(args[Integer.valueOf(InputType.FILLER.getCode())]))) {
			throw new CommandLineParserException(
					"Invalid Inputs.\n"+ "Enter in the format as : " + "<ccy1>" + "<amount1> in <ccy2>");
		}

		if (!isNumeric(args[Integer.valueOf(InputType.AMOUNT.getCode())])) {
			throw new CommandLineParserException("Amount to be converted is not correct");
		}

		return true;

	}

	/**
	 * Method to verify the string is positive decimal number which can be
	 * parsed as double or float
	 * 
	 * @param amount
	 * @return boolean
	 */
	public static boolean isNumeric(String amount) {
		return amount != null && !amount.isEmpty() && amount.matches("[+]?\\d*\\.?\\d+");
	}

}
