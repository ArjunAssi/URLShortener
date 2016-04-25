package com.url.shortener.test;

import com.shortener.url.main.URLShortener;

/**
 * Manual Test class for API testing
 * 
 * @author leonidas
 * @date 22nd April
 */
public class Test {

	public static void main(String[] args) {

		// INITIALIZE THE TEST PARAMETERS
		String originalLongURL = "http://www.java2novice.com/java_constructor_examples/singleton/";
		String inputFile = "/home/leonidas/Desktop/inputURLS.txt";
		String outputFile = "/home/leonidas/Desktop/outputURLS.txt";

		// INSTANTIATE THE API OBJECT
		URLShortener shortener = URLShortener.getInstance();

		// TESTING GENERATION OF SHORT URL USING ONE LONG URL
		String shortURL = shortener.generateShortURL(originalLongURL);
		System.out.println(shortURL);

		// TESTING FETCHING OF ORIGINAL URL FORM SHORT URL GENERATED
		String generatedlongURL = shortener.getLongURL(shortURL);
		System.out.println(generatedlongURL);

		if (generatedlongURL.equals(originalLongURL)) {
			System.out.println("TEST PASSED");
		} else {
			System.out.println("TEST FAILED");
		}

		// TESTING GENERATION OF SHORT URL USING input file
		shortener.generateShortURL(inputFile, outputFile);
	}
}
