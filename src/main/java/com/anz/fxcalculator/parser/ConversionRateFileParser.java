package com.anz.fxcalculator.parser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.apache.log4j.Logger;

import com.anz.fxcalculator.container.CurrencyConversionRateContainer;
import com.anz.fxcalculator.container.ICurrencyConversionRateContainer;
import com.anz.fxcalculator.exceptions.ApplicationException;
import com.anz.fxcalculator.util.FXCalculatorUtil;

/**
 * Currency pair File Parser is used to parse the {@code precision.file} value
 * <br>
 * provided in the {@code application.properties} and store the same in the <br>
 * {@link CurrencyConversionRateContainer} which is implementing the
 * interface<br>
 * {@link ICurrencyConversionRateContainer} if it is able to parse the input<br>
 * using the {@link FXCalculatorUtil} class {@code isValidCurrencyPairFormat}
 * method.
 * 
 * 
 * @author Mohit Jain
 * @version 1.0
 */
public class ConversionRateFileParser extends FileConfigParser implements IConversionRateParser {

	/**
	 * logger initialized for logging the warning while parsing the currency
	 * pair provided.
	 */
	private Logger LOGGER = Logger.getLogger(ConversionRateFileParser.class);

	/**
	 * Counter for storing the number of successful line processed from the
	 * currency pair file.
	 */
	private int COUNTER = 0;

	/**
	 * Method will ready the currency pair file line by line and parse the same
	 * and store it into the {@link CurrencyConversionRateContainer} which is
	 * implementing the {@link ICurrencyConversionRateContainer} interface. This
	 * method will also check if there are no valid currency pair available in
	 * the file and in that case it will through the
	 * {@link ApplicationException} with the customized error message.
	 * 
	 */
	public void parseConfig(ICurrencyConversionRateContainer conversionRateContainer)
			throws ApplicationException, IOException {

		File file = getConfigFile();
		BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
		String readLine = "";
		LOGGER.warn("****************" + file.getName() + " Issues start *********************");
		while ((readLine = bufferedReader.readLine()) != null) {
			if (!readLine.isEmpty() && FXCalculatorUtil.isValidCurrencyPairFormat(readLine)) {
				String[] splits = readLine.split("=");
				conversionRateContainer.addConversionRate(splits[0].substring(0, 3), splits[0].substring(3, 6),
						new Float(splits[1]));
				COUNTER++;
			} else {
				LOGGER.warn("Invalid Currency Pair : " + readLine);
			}
		}

		LOGGER.warn("****************" + file.getName() + " Issues end *********************");

		if (COUNTER == 0) {
			bufferedReader.close();
			throw new ApplicationException("Currency Pair file " + file.getName() + " is not properly formatted.");
		}
		bufferedReader.close();
	}
}
