package com.anz.fxcalculator;

import java.io.Console;
import java.text.NumberFormat;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.anz.fxcalculator.parser.CommandLineParser;
import com.anz.fxcalculator.util.FXCalculatorUtil;
import com.anz.fxcalculator.vo.CalculatorResult;
import com.anz.fxcalculator.vo.CalculatorResultCode;
import com.anz.fxcalculator.vo.InputType;

/**
 * FxCalcApp is setting the correct argument provided for currency
 * conversion<br>
 * and passing it to the {@link FXCalculator} using the method
 * convertCurrency<br>
 * which takes three parameters as BASE Currency or sourceCurrency, Terms or<br>
 * destination currency and Amount i.e. conversionAmount.<br>
 * <br>
 * 
 * The {@code result} will object of {@link CalculatorResult} class which holds
 * 
 * the <br>
 * success and failure of conversion and also the precision<br>
 * that need to applied to the converted amount as the destination currency.<br>
 * <br>
 * 
 * @author Mohit Jain
 * @version 1.0
 */
public class FXCalcApp {
	public static void main(String args[]) {
		ApplicationContext context = new ClassPathXmlApplicationContext("beans-config.xml");
		FXCalculator calculator = (FXCalculator) context.getBean("fxCalculator");

		String EXIT_APPLICATON = "q";
		String input = "";
		Console c = System.console();
		while (!EXIT_APPLICATON.equalsIgnoreCase(input)) {

			System.out.print("%> ");
			input = c.readLine();
			if (!EXIT_APPLICATON.equalsIgnoreCase(input)) {
				try {
					CommandLineParser.parseArguments(input.split(" "));
					String[] inputs = input.split(" ");
					String sourceCurrency = inputs[Integer.valueOf(InputType.BASE.getCode())];
					String destinationCurrency = inputs[Integer.valueOf(InputType.TERMS.getCode())];
					String conversionAmount = inputs[Integer.valueOf(InputType.AMOUNT.getCode())];

					CalculatorResult result = calculator.convertCurrency(sourceCurrency, destinationCurrency,
							new Float(conversionAmount));
					NumberFormat formatter = FXCalculatorUtil.determineResultFormatter(result);

					System.out.println((CalculatorResultCode.CONVERSION_RATE_NOT_FOUND.equals(result.getResultCode()))
							? ("Unable to find rate for " + sourceCurrency + "/" + destinationCurrency)
							: (sourceCurrency + " " + conversionAmount + " = " + destinationCurrency + " "
									+ formatter.format(result.getResult())));
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
			}
		}

	}

}
