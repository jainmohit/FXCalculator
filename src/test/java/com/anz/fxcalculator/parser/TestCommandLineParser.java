package com.anz.fxcalculator.parser;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.anz.fxcalculator.exceptions.CommandLineParserException;

public class TestCommandLineParser {

	String CORRECT_ARGUMENTS = "AUD 100.00 in USD";
	String WRONG_FILLER_ARGUMENTS = "AUD 100.00 abc USD";
	String INCORRECT_NUMBER_OF_ARGUMENTS = "AUD 100.00 USD";
	String INCORRECT_AMOUNT_AS_ARGUMENT = "AUD 100.0B in USD";
	String AMOUNT = "22.26";
	String WRONG_AMOUNT = "23.2B";

	@Test
	public void testParseArguments() throws Exception {
		CommandLineParser.parseArguments(CORRECT_ARGUMENTS.split(" "));
	}

	@Test
	public void testIsNumeric() {
		assertEquals(CommandLineParser.isNumeric(AMOUNT), true);
	}

	@Test(expected = CommandLineParserException.class)
	public void testWrongFillerParseArguments() throws Exception {
		CommandLineParser.parseArguments(WRONG_FILLER_ARGUMENTS.split(" "));
	}

	@Test(expected = CommandLineParserException.class)
	public void testWrongSizeOfParseArguments() throws Exception {
		CommandLineParser.parseArguments(INCORRECT_NUMBER_OF_ARGUMENTS.split(" "));
	}

	@Test(expected = CommandLineParserException.class)
	public void testWrongAmountInParseArguments() throws Exception {
		CommandLineParser.parseArguments(INCORRECT_AMOUNT_AS_ARGUMENT.split(" "));
	}

	@Test
	public void testFailIsNumeric() {
		assertEquals(CommandLineParser.isNumeric(WRONG_AMOUNT), false);
	}

}
