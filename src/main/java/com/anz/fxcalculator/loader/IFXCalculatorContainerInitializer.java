package com.anz.fxcalculator.loader;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import com.anz.fxcalculator.container.ICurrencyConversionRateContainer;
import com.anz.fxcalculator.container.ICurrencyCrossingContainer;
import com.anz.fxcalculator.container.ICurrencyDecimalPrecisionContainer;
import com.anz.fxcalculator.exceptions.ApplicationException;

/**
 * Base container interface for the FX currency calculator container used to<br>
 * initialize the three container one for user Currency pair, one for
 * decimal<br>
 * precision and one for holding all the currency pair derived from the user<br>
 * provided currency pair.
 * 
 * 
 * @author Mohit Jain
 * @version 1.0
 */
public interface IFXCalculatorContainerInitializer {

	/**
	 * Definition provided for initializing the user defined Currency pair rate
	 * container.
	 * 
	 * @param conversionRateContainer
	 * @throws ApplicationException
	 * @throws UnsupportedEncodingException
	 * @throws IOException
	 */
	public void initializeConversionRateContainer(ICurrencyConversionRateContainer conversionRateContainer)
			throws ApplicationException, UnsupportedEncodingException, IOException;

	/**
	 * This method will provided the initializing the cross-via matrix container
	 * derived from the provided currency pair and set of currencies.
	 * 
	 * @param crossingContainer
	 * @param conversionRateContainer
	 * @throws ApplicationException
	 * @throws UnsupportedEncodingException
	 * @throws IOException
	 */
	public void initializeCrossingContainer(ICurrencyCrossingContainer crossingContainer,
			ICurrencyConversionRateContainer conversionRateContainer)
			throws ApplicationException, UnsupportedEncodingException, IOException;

	/**
	 * This container method will initialize the decimal precision container.
	 * 
	 * @param precisionContainer
	 * @throws ApplicationException
	 * @throws UnsupportedEncodingException
	 * @throws IOException
	 */
	public void initializeDecimalPresionContainer(ICurrencyDecimalPrecisionContainer precisionContainer)
			throws ApplicationException, UnsupportedEncodingException, IOException;
}
