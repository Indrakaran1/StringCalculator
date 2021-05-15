package com.project.calculator;

public class Calculator {
	private final static String customDelimiterRegex = "//(.*)\n(.*)";
	private final static String newLine = "\n";
	private final static String defaultDelimiter = ",";
	
	public static int add(String input) {
		if (input.isBlank())
			return 0;
		else if (input.length() == 1)
			return Integer.parseInt(input);
		
		String delimiter = defaultDelimiter;
		if (input.matches(customDelimiterRegex)) {
			delimiter = Character.toString(input.charAt(2));
			input = input.substring(4);
		}
		String[] numberSeperated = input.split(delimiter + "|" + newLine);
		checkForNegativeNumbers(numberSeperated);
		int result = getSum(numberSeperated);
		return result;
	}

	public static int getSum(String[] numbers) {
		int result = 0;
		for (String number : numbers)
			result += Integer.parseInt(number);
		return result;
	}
	
	public static void checkForNegativeNumbers(String[] numbers) {
		String negativeNumbers = "";
		for (String number : numbers) {
			if ((int) Integer.parseInt(number) < 0) {
				if (negativeNumbers.isBlank())
					negativeNumbers += number;
				else
					negativeNumbers += defaultDelimiter + number;
			}
		}
		if (!negativeNumbers.isBlank())
			throw new IllegalArgumentException("negatives not allowed " + negativeNumbers);
	}
}
