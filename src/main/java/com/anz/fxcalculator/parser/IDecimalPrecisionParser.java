package com.anz.fxcalculator.parser;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import com.anz.fxcalculator.container.CurrencyDecimalPrecisionContainer;
import com.anz.fxcalculator.container.ICurrencyDecimalPrecisionContainer;
import com.anz.fxcalculator.exceptions.ApplicationException;

/**
 * Interface providing with the exposed methods which is implemented by
 * {@link CurrencyDecimalPrecisionContainer}.
 * 
 * @author Mohit Jain
 * @version 1.0
 */
public interface IDecimalPrecisionParser {

	public void parseConfig(ICurrencyDecimalPrecisionContainer precisionContainer)
			throws ApplicationException, UnsupportedEncodingException, FileNotFoundException, IOException;
}
