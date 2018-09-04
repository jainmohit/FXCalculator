package com.anz.fxcalculator.parser;

import org.junit.Test;

public class TestFileConfigParser {

	@Test
	public void testGetFileConfig() throws Exception {
		FileConfigParser configParser = new FileConfigParser();
		configParser.setConfigFileName("ConversionRate.txt");
		configParser.getConfigFile();
	}

	//@Test(expected = ApplicationException.class)
	public void testFakeGetFileConfig() throws Exception {
		FileConfigParser configParser = new FileConfigParser();
		configParser.setConfigFileName("fake.txt");
		configParser.getConfigFile();
	}
}
