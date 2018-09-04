package com.anz.fxcalculator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.anz.fxcalculator.vo.CalculatorResult;
import com.anz.fxcalculator.vo.CalculatorResultCode;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:beans-config.xml")
public class TestFXCalculator {
	@Autowired
	FXCalculator fxCalculator;

	@Test
	public void TestAUDToUSDConversion() {
		CalculatorResult result = fxCalculator.convertCurrency("AUD", "USD", 200.00f);
		assertEquals((Float)167.42001f, result.getResult());
		assertEquals((Integer)2, result.getPrecision());
		assertEquals(CalculatorResultCode.SUCCESS, result.getResultCode());
	}

	@Test
	public void TestAUDToDKKConversion() {
		CalculatorResult result = fxCalculator.convertCurrency("AUD", "DKK", 200.00f);
		assertEquals((Float)1011.52136f, result.getResult());
		assertEquals((Integer)2, result.getPrecision());
		assertEquals(CalculatorResultCode.SUCCESS, result.getResultCode());
	}

	@Test
	public void TestAUDToNOCConversion() {
		CalculatorResult result = fxCalculator.convertCurrency("AUD", "NOC", 200.00f);

		assertNull(result.getResult());
		assertNull(result.getPrecision());
		assertEquals(CalculatorResultCode.CONVERSION_RATE_NOT_FOUND, result.getResultCode());
	}
}
