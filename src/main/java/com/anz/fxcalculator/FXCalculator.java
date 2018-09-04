package com.anz.fxcalculator;

import java.io.IOException;

import com.anz.fxcalculator.container.ICurrencyConversionRateContainer;
import com.anz.fxcalculator.container.ICurrencyCrossingContainer;
import com.anz.fxcalculator.container.ICurrencyDecimalPrecisionContainer;
import com.anz.fxcalculator.exceptions.ApplicationException;
import com.anz.fxcalculator.loader.IFXCalculatorContainerInitializer;
import com.anz.fxcalculator.vo.CalculatorResult;
import com.anz.fxcalculator.vo.CalculatorResultCode;
import com.anz.fxcalculator.vo.CrossViaOptions;

/**
 * This is actual implementation of complete FXCalculator which is responsible
 * to converting the two currency pair from base to term using the currency
 * cross via matrix container.
 * 
 * @author Mohit Jain
 * @version 1.0
 */
public class FXCalculator {
	private ICurrencyConversionRateContainer rateContainer;
	private ICurrencyCrossingContainer crossingContainer;
	private ICurrencyDecimalPrecisionContainer precisionContainer;
	private IFXCalculatorContainerInitializer containerInitializer;

	/**
	 * Initialize the Conversion Rate container, Decimal precision container and
	 * cross via matrix container.
	 * 
	 * @throws ApplicationException
	 * @throws IOException
	 */
	public void initializeContainers() throws ApplicationException, IOException {
		containerInitializer.initializeConversionRateContainer(getRateContainer());
		containerInitializer.initializeDecimalPresionContainer(getPrecisionContainer());
		containerInitializer.initializeCrossingContainer(getCrossingContainer(), getRateContainer());
	}

	/**
	 * This method will find the hops using the cross via currency container to
	 * find the relation between them and return the same.
	 * 
	 * @param sourceCurrency
	 * @param destinationCurrency
	 * @param conversionValue
	 * @return
	 */
	public CalculatorResult convertCurrency(String sourceCurrency, String destinationCurrency, Float conversionValue) {

		String crossViaValue = getCrossingContainer().getCrossingData(sourceCurrency, destinationCurrency);

		if (null == crossViaValue || crossViaValue.length() < 1) {
			return new CalculatorResult(CalculatorResultCode.CONVERSION_RATE_NOT_FOUND);

		} else if (CrossViaOptions.DIRECT.getCode().equals(crossViaValue)) {
			return handleDirectConversion(sourceCurrency, destinationCurrency, conversionValue);

		} else if (CrossViaOptions.INVERSE.getCode().equals(crossViaValue)) {
			return handleInverseConversion(sourceCurrency, destinationCurrency, conversionValue);

		} else if (CrossViaOptions.UNITY.getCode().equals(crossViaValue)) {
			return handleUnityConversion(destinationCurrency, conversionValue);

		} else {
			return handleCrossViaConversion(sourceCurrency, destinationCurrency, conversionValue, crossViaValue);
		}
	}

	/**
	 * Based on the cross-via route required based on the finding by
	 * convertCurrency method this will find the ratio of src currency to
	 * cross-via currency if available direct or inverse.If there is not direct
	 * and inverse relation between the src currency and cross-via currency it
	 * will again send it back to convertCurrency to find the next hop
	 * 
	 * @param sourceCurrency
	 * @param destinationCurrency
	 * @param conversionValue
	 * @param crossViaValue
	 * @return
	 */
	private CalculatorResult handleCrossViaConversion(String sourceCurrency, String destinationCurrency,
			Float conversionValue, String crossViaValue) {

		// Recursive Call
		CalculatorResult resultPhase1 = convertCurrency(sourceCurrency, crossViaValue, conversionValue);
		return (CalculatorResultCode.SUCCESS.equals(resultPhase1.getResultCode()))
				? convertCurrency(crossViaValue, destinationCurrency, resultPhase1.getResult()) : resultPhase1;
	}

	/**
	 * Method used to convert the unity conversion if the source currency and
	 * the destination currency are same.
	 * 
	 * @param destinationCurrency
	 * @param conversionValue
	 * @return
	 */
	private CalculatorResult handleUnityConversion(String destinationCurrency, Float conversionValue) {
		return new CalculatorResult(conversionValue, getPrecisionContainer().getCurrencyPrecision(destinationCurrency),
				CalculatorResultCode.SUCCESS);
	}

	/**
	 * Method to convert and handle the inverse relation between the source
	 * currency and the destination.
	 * 
	 * @param sourceCurrency
	 * @param destinationCurrency
	 * @param conversionValue
	 * @return
	 */
	private CalculatorResult handleInverseConversion(String sourceCurrency, String destinationCurrency,
			Float conversionValue) {
		Float ConversionRate = getRateContainer().getConversionRate(destinationCurrency, sourceCurrency);
		if (null == ConversionRate) {
			return new CalculatorResult(CalculatorResultCode.CONVERSION_RATE_NOT_FOUND);
		}

		Float convertedValue = (1 / ConversionRate) * conversionValue;
		return new CalculatorResult(convertedValue, getPrecisionContainer().getCurrencyPrecision(destinationCurrency),
				CalculatorResultCode.SUCCESS);
	}

	/**
	 * Method to convert and handle the direct currency pair conversion.
	 * 
	 * @param sourceCurrency
	 * @param destinationCurrency
	 * @param conversionValue
	 * @return
	 */
	private CalculatorResult handleDirectConversion(String sourceCurrency, String destinationCurrency,
			Float conversionValue) {

		Float ConversionRate = getRateContainer().getConversionRate(sourceCurrency, destinationCurrency);
		if (null == ConversionRate) {
			return new CalculatorResult(CalculatorResultCode.CONVERSION_RATE_NOT_FOUND);
		}

		Float convertedValue = ConversionRate * conversionValue;
		return new CalculatorResult(convertedValue, getPrecisionContainer().getCurrencyPrecision(destinationCurrency),
				CalculatorResultCode.SUCCESS);
	}

	/**
	 * 
	 * @return
	 */
	public ICurrencyConversionRateContainer getRateContainer() {
		return rateContainer;
	}

	/**
	 * 
	 * @param rateContainer
	 */
	public void setRateContainer(ICurrencyConversionRateContainer rateContainer) {
		this.rateContainer = rateContainer;
	}

	/**
	 * 
	 * @return
	 */
	public ICurrencyCrossingContainer getCrossingContainer() {
		return crossingContainer;
	}

	/**
	 * 
	 * @param crossingContainer
	 */
	public void setCrossingContainer(ICurrencyCrossingContainer crossingContainer) {
		this.crossingContainer = crossingContainer;
	}

	/**
	 * 
	 * @return
	 */
	public ICurrencyDecimalPrecisionContainer getPrecisionContainer() {
		return precisionContainer;
	}

	/**
	 * 
	 * @param precisionContainer
	 */
	public void setPrecisionContainer(ICurrencyDecimalPrecisionContainer precisionContainer) {
		this.precisionContainer = precisionContainer;
	}

	/**
	 * 
	 * @return
	 */
	public IFXCalculatorContainerInitializer getContainerInitializer() {
		return containerInitializer;
	}

	/**
	 * 
	 * @param containerInitializer
	 */
	public void setContainerInitializer(IFXCalculatorContainerInitializer containerInitializer) {
		this.containerInitializer = containerInitializer;
	}
}
