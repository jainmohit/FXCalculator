package com.anz.fxcalculator.vo;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class TestCrossViaOptions {

	
	@Test
	public void testCrossViaOptions() {
		assertTrue(CrossViaOptions.DIRECT.getCode().equalsIgnoreCase("D"));
		assertTrue(CrossViaOptions.INVERSE.getCode().equalsIgnoreCase("Inv"));
		assertTrue(CrossViaOptions.UNITY.getCode().equalsIgnoreCase("1:1"));
	}
	
	@Test
	public void testNegativeCrossViaOptions() {
		assertFalse(CrossViaOptions.DIRECT.getCode().equalsIgnoreCase("E"));
		assertFalse(CrossViaOptions.INVERSE.getCode().equalsIgnoreCase("X"));
		assertFalse(CrossViaOptions.UNITY.getCode().equalsIgnoreCase("Z"));
	}
}
