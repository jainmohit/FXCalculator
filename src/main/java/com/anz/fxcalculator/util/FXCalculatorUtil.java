package com.anz.fxcalculator.util;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.anz.fxcalculator.vo.CalculatorResult;
import com.anz.fxcalculator.vo.CalculatorResultCode;

/**
 * Utility class to check the format of the file input provided for currency
 * pair and the precision.
 * 
 * @author Mohit Jain
 * @version 1.0
 */
public class FXCalculatorUtil {

	/**
	 * pattern for recognizing the each row of the currency pair file.
	 */
	private static String CURRENCY_PAIR_PATTERN = "[a-zA-z]{6}\\=[0-9]*\\.[0-9]*";

	/**
	 * pattern for recognizing the each row of the decimal precision file.
	 */
	private static String DECIMAL_PRECISION_PAIR_PATTERN = "[a-zA-z]{3}\\=[0-9]*\\s.*";

	/**
	 * This method will return true or false base on each row of the currency
	 * pair file is matching the pattern or not.
	 * 
	 * @param currencyPair
	 * @return
	 */
	public static boolean isValidCurrencyPairFormat(String currencyPair) {
		Pattern p = Pattern.compile(CURRENCY_PAIR_PATTERN);
		Matcher m = p.matcher(currencyPair);
		return m.matches();
	}

	/**
	 * This method will return true or false base on each row of the decimal
	 * precision file is matching the pattern or not.
	 * 
	 * @param decimalPrecision
	 * @return
	 */
	public static boolean isValidDecimalPrecisionFormat(String decimalPrecision) {
		Pattern p = Pattern.compile(DECIMAL_PRECISION_PAIR_PATTERN);
		Matcher m = p.matcher(decimalPrecision);
		return m.matches();
	}

	/**
	 * Method for returning the result by formating it to the given decimal
	 * place as per the decimal precision value for the destination currency in
	 * case if it is not there then by default will be precision to 2 decimal
	 * places.
	 * 
	 * @param result
	 * @return
	 */
	public static NumberFormat determineResultFormatter(CalculatorResult result) {
		if (CalculatorResultCode.SUCCESS.equals(result.getResultCode())) {
			String floatFormatCode = "#0";
			if (result.getPrecision() > 0) {
				floatFormatCode += ".0";
			}
			for (int i = 1; i < result.getPrecision(); i++) {
				floatFormatCode += "0";
			}
			return new DecimalFormat(floatFormatCode);
		}
		return null;
	}

}
