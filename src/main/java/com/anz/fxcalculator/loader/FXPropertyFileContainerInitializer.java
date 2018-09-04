package com.anz.fxcalculator.loader;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import com.anz.fxcalculator.container.ICurrencyConversionRateContainer;
import com.anz.fxcalculator.container.ICurrencyCrossingContainer;
import com.anz.fxcalculator.container.ICurrencyDecimalPrecisionContainer;
import com.anz.fxcalculator.exceptions.ApplicationException;
import com.anz.fxcalculator.parser.IConversionRateParser;
import com.anz.fxcalculator.parser.IDecimalPrecisionParser;
import com.anz.fxcalculator.vo.CrossViaOptions;

/**
 * 
 * Implementation of the {@link IFXCalculatorContainerInitializer} interface
 * for<br>
 * the FX currency calculator container used to<br>
 * initialize the three container one for user Currency pair, one for
 * decimal<br>
 * precision and one for holding all the currency pair derived from the user<br>
 * provided currency pair.
 * 
 * @author Mohit Jain
 * @version 1.0
 * 
 */
public class FXPropertyFileContainerInitializer implements IFXCalculatorContainerInitializer {

	/**
	 * Reference of the conversion rate parser
	 */
	private IConversionRateParser conversionRateParser;

	/**
	 * Reference of the decimal precision parser
	 */
	private IDecimalPrecisionParser decimalPrecisionParser;

	/**
	 * Initializing the user defined Currency pair rate container by parsing the
	 * currency pair file provided by user.
	 */
	public void initializeConversionRateContainer(ICurrencyConversionRateContainer conversionRateContainer)
			throws ApplicationException, IOException {

		getConversionRateParser().parseConfig(conversionRateContainer);
	}

	/**
	 * Initializing the cross-via matrix container derived from the provided
	 * currency pair and set of currencies. This method will trace down the
	 * different currency pair that can go via any other currency or directly or
	 * inverse or same by using the different currencies ad currency pair
	 * provided to us.
	 */
	public void initializeCrossingContainer(ICurrencyCrossingContainer crossingContainer,
			ICurrencyConversionRateContainer conversionRateContainer) throws ApplicationException, IOException {

		/* getCrossingMatrixParser().parseConfig(crossingContainer); */

		Set<String> allCurrencies = conversionRateContainer.getAllCurrencies();
		String[] curreniesArraySource = allCurrencies.toArray(new String[allCurrencies.size()]);
		String[] curreniesArrayDestination = allCurrencies.toArray(new String[allCurrencies.size()]);

		for (String currSrc : curreniesArraySource) {
			for (String currDes : curreniesArrayDestination) {
				if (currDes.equals(currSrc)) {
					crossingContainer.addCrossingData(currSrc, currDes, CrossViaOptions.UNITY.getCode());
				} else if (null != conversionRateContainer.getConversionRate(currSrc, currDes)) {
					crossingContainer.addCrossingData(currSrc, currDes, CrossViaOptions.DIRECT.getCode());
				} else if (null != conversionRateContainer.getConversionRate(currDes, currSrc)) {
					crossingContainer.addCrossingData(currSrc, currDes, CrossViaOptions.INVERSE.getCode());
				} else {
					String viaCode = findLinkCurrency(currSrc, currDes, new HashSet<String>(), conversionRateContainer);
					if (null == viaCode) {
						// throw new ApplicationException("Crossing-Via not
						// found [" + currSrc + "-->" + currDes + "]");
					} else {
						crossingContainer.addCrossingData(currSrc, currDes, viaCode);
					}
				}
			}
		}
	}

	/**
	 * Method responsible for finding the different hops for the different pair
	 * of currencies recursively.
	 * 
	 * @param currSrc
	 * @param currDes
	 * @param routeTaken
	 * @param conversionRateContainer
	 * @return
	 */
	private String findLinkCurrency(String currSrc, String currDes, Set<String> routeTaken,
			ICurrencyConversionRateContainer conversionRateContainer) {

		Set<String> matches = conversionRateContainer.getAllMatches(currSrc);

		if (matches.contains(currDes)) {
			return currSrc;
		}

		for (String eachMatch : matches) {
			routeTaken.add(currSrc);

			if (!isRouteAlreadyTaken(eachMatch, routeTaken)) {

				String viaCode = findLinkCurrency(eachMatch, currDes, routeTaken, conversionRateContainer);
				if (null != viaCode) {
					return eachMatch;
				}
			}
			routeTaken.remove(currSrc);
		}
		return null;
	}

	/**
	 * Method used to check to keep track of all the hops in between and
	 * breaking and coming out of the same currency route if it has already been
	 * taken to avoid the infinite loop.
	 * 
	 * @param eachMatch
	 * @param routeTaken
	 * @return
	 */
	private boolean isRouteAlreadyTaken(String eachMatch, Set<String> routeTaken) {
		return routeTaken.contains(eachMatch);
	}

	/**
	 * This container method will initialize the decimal precision container.
	 */
	public void initializeDecimalPresionContainer(ICurrencyDecimalPrecisionContainer precisionContainer)
			throws ApplicationException, IOException {

		getDecimalPrecisionParser().parseConfig(precisionContainer);
	}

	/**
	 * Getter for the conversion rate parser.
	 * 
	 * @return
	 */
	public IConversionRateParser getConversionRateParser() {
		return conversionRateParser;
	}

	/**
	 * Setter for the conversion rate parser.
	 * 
	 * @param conversionRateParser
	 */
	public void setConversionRateParser(IConversionRateParser conversionRateParser) {
		this.conversionRateParser = conversionRateParser;
	}

	/**
	 * Getter for the decimal precision parser.
	 * 
	 * @return
	 */
	public IDecimalPrecisionParser getDecimalPrecisionParser() {
		return decimalPrecisionParser;
	}

	/**
	 * Setter for the decimal precision parser.
	 * 
	 * @param decimalPrecisionParser
	 */
	public void setDecimalPrecisionParser(IDecimalPrecisionParser decimalPrecisionParser) {
		this.decimalPrecisionParser = decimalPrecisionParser;
	}
}
