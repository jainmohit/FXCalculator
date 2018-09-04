package com.anz.fxcalculator.vo;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class TestInputType {

	@Test
	public void testInputType() {
		assertTrue(Integer.parseInt(InputType.BASE.getCode()) == 0);
		assertTrue(Integer.parseInt(InputType.AMOUNT.getCode()) == 1);
		assertTrue(Integer.parseInt(InputType.FILLER.getCode()) == 2);
		assertTrue(Integer.parseInt(InputType.TERMS.getCode()) == 3);
	}
}
