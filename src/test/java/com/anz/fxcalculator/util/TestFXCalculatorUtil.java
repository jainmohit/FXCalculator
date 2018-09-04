package com.anz.fxcalculator.util;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class TestFXCalculatorUtil {

	private final String CORRECT_INPUT_CURRENCY_PAIR = "AUDCAD=2.34";
	private final String WRONG_INPUT_CURRENCY_PAIR = "AUDCAD=2.3V4";
	private final String CORRECT_INPUT_CURRENCY_PRECISION = "AUD=2 decimal places";
	private final String WRONG_INPUT_CURRENCY_PRECISION = "AU=2 decimal places";

	@Test
	public void testCurrencyPair() {
		assertTrue(FXCalculatorUtil.isValidCurrencyPairFormat(CORRECT_INPUT_CURRENCY_PAIR));
		assertFalse(FXCalculatorUtil.isValidCurrencyPairFormat(WRONG_INPUT_CURRENCY_PAIR));
	}

	@Test
	public void testCurrencyPrecision() {
		assertTrue(FXCalculatorUtil.isValidDecimalPrecisionFormat(CORRECT_INPUT_CURRENCY_PRECISION));
		assertFalse(FXCalculatorUtil.isValidDecimalPrecisionFormat(WRONG_INPUT_CURRENCY_PRECISION));
	}

}
