package com.anz.fxcalculator.parser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.apache.log4j.Logger;

import com.anz.fxcalculator.container.CurrencyDecimalPrecisionContainer;
import com.anz.fxcalculator.container.ICurrencyDecimalPrecisionContainer;
import com.anz.fxcalculator.exceptions.ApplicationException;
import com.anz.fxcalculator.util.FXCalculatorUtil;

/**
 * Decimal Precision File Parser is used to parse the {@code precision.file}
 * value <br>
 * provided in the {@code application.properties} and store the same in the <br>
 * {@link CurrencyDecimalPrecisionContainer} which is implementing the interface<br>
 * {@link ICurrencyDecimalPrecisionContainer} if it is able to parse the
 * input<br>
 * using the {@link FXCalculatorUtil} class
 * {@code isValidDecimalPrecisionFormat} method.
 * 
 * 
 * 
 * @author Mohit Jain
 * @version 1.0
 */
public class DecimalPrecisionFileParser extends FileConfigParser implements IDecimalPrecisionParser {

	/**
	 * Logger initialized for the class for storing the issues while parsing the
	 * decimal precision file.
	 */
	private Logger LOGGER = Logger.getLogger(DecimalPrecisionFileParser.class.getName());

	/**
	 * Method parsing the provided decimal precision file line by line and
	 * storing the same in the class implementing the method
	 * <tt>addCurrencyPrecision </tt> provided by
	 * {@link ICurrencyDecimalPrecisionContainer} interface.
	 */
	public void parseConfig(ICurrencyDecimalPrecisionContainer precisionContainer)
			throws ApplicationException, IOException {

		File file = getConfigFile();
		BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
		String readLine = "";
		LOGGER.warn("****************" + file.getName() + " Issues start *********************");
		while ((readLine = bufferedReader.readLine()) != null) {
			if (!readLine.isEmpty() && FXCalculatorUtil.isValidDecimalPrecisionFormat(readLine)) {
				String[] splits = readLine.split("=");
				String[] deciCount = splits[1].split(" ");
				precisionContainer.addCurrencyPrecision(splits[0], Integer.parseInt(deciCount[0]));
			} else {
				LOGGER.warn("Invalid Decimal Precision : " + readLine);
			}
		}
		LOGGER.warn("****************" + file.getName() + " Issues end *********************");
		bufferedReader.close();
	}
}
