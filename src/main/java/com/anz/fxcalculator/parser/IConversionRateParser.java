package com.anz.fxcalculator.parser;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import com.anz.fxcalculator.container.CurrencyConversionRateContainer;
import com.anz.fxcalculator.container.ICurrencyConversionRateContainer;
import com.anz.fxcalculator.exceptions.ApplicationException;

/**
 * Interface providing with the exposed methods which is implemented by
 * {@link CurrencyConversionRateContainer}
 * 
 * @author Mohit Jain
 * @version 1.0
 */
public interface IConversionRateParser {

	/**
	 * <tt> parseConfig </tt> method will accept the input of class implementing
	 * the {@link ICurrencyConversionRateContainer}
	 * 
	 * @param conversionRateContainer
	 * @throws ApplicationException
	 * @throws UnsupportedEncodingException
	 * @throws IOException
	 */
	public void parseConfig(ICurrencyConversionRateContainer conversionRateContainer)
			throws ApplicationException, UnsupportedEncodingException, IOException;
}
