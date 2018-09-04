package com.anz.fxcalculator.vo;

/**
 * {@code CalculatorResult} class will hold three values as : <br>
 * <br>
 * 
 * {@code result} which holds the result amount in float. <br>
 * <br>
 * {@code precision} which will hold the position of decimal places to be set to
 * the destination currency.<br>
 * <br>
 * {@code resultCode} will hold the status of success and failure as per the
 * enum class {@link CalculatorResultCode}.<br>
 * <br>
 * 
 * 
 * @author Mohit Jain
 * @version 1.0
 *
 */
public class CalculatorResult {

	private Float result;
	private Integer precision;
	private CalculatorResultCode resultCode;

	/**
	 * Constructor which accepts the {@link CalculatorResultCode} as an input
	 * parameter.
	 * 
	 * @param resultCode
	 */
	public CalculatorResult(CalculatorResultCode resultCode) {
		this.resultCode = resultCode;
	}

	/**
	 * Constructor which accepts three parameters result, precision and
	 * {@link CalculatorResultCode} as an input.
	 * 
	 * @param result
	 * @param precision
	 * @param resultCode
	 */
	public CalculatorResult(Float result, Integer precision, CalculatorResultCode resultCode) {
		this.result = result;
		this.precision = precision;
		this.resultCode = resultCode;
	}

	/**
	 * Getter for result
	 * 
	 * @return result Float
	 */
	public Float getResult() {
		return result;
	}

	/**
	 * Getter for Precision
	 * 
	 * @return precision Integer
	 */
	public Integer getPrecision() {
		return precision;
	}

	/**
	 * Getter for ResultCode
	 * 
	 * @return resultCode CalculatorResultCode
	 */
	public CalculatorResultCode getResultCode() {
		return resultCode;
	}
}