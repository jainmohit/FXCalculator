package com.anz.fxcalculator.vo;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TestCalculatorResult {

	Float RESULT = 100.0f;
	Integer PRECISION = 2;

	@Test
	public void testCalculatorResultWithResultCode() {

		CalculatorResult calculatorResultSuccess = new CalculatorResult(CalculatorResultCode.SUCCESS);
		assertEquals(CalculatorResultCode.SUCCESS, calculatorResultSuccess.getResultCode());
		CalculatorResult calculatorResultNotFound = new CalculatorResult(
				CalculatorResultCode.CONVERSION_RATE_NOT_FOUND);
		assertEquals(CalculatorResultCode.CONVERSION_RATE_NOT_FOUND, calculatorResultNotFound.getResultCode());
	}

	@Test
	public void testCalculatorResultWithAllParameters() {

		CalculatorResult calculatorResult = new CalculatorResult(RESULT, PRECISION, CalculatorResultCode.SUCCESS);
		assertEquals(CalculatorResultCode.SUCCESS, calculatorResult.getResultCode());
		assertEquals(RESULT, calculatorResult.getResult());
		assertEquals(PRECISION, calculatorResult.getPrecision());
	}

}
