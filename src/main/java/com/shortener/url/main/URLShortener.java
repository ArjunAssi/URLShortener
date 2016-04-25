package com.shortener.url.main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;

import com.shortener.url.util.KeygenUtil;
import com.shortener.url.util.RedisUtil;

/**
 * API for URL shortening functionality
 * 
 * @author leonidas
 * @date 22st April
 */
public class URLShortener {

	// counter variable to hold the count of keys
	private int counter;
	private static final String APPENDER = "http://shortyURL/";
	private static URLShortener shortener;

	// For singleton access
	public static URLShortener getInstance() {
		if (shortener == null) {
			shortener = new URLShortener();
		}
		return shortener;
	}

	public URLShortener() {
		counter = 1;
		RedisUtil.INSTANCE.createRedisConnection("localhost", 6379);
	}

	// Function to generate a long URL
	public String generateShortURL(String longURL) {

		// generate the short URL for the given long URL
		String shortURL = KeygenUtil.INSTANCE.convertToShortURL(counter);
		RedisUtil.INSTANCE.pushToRedis(counter++, longURL);
		return (APPENDER + shortURL);
	}

	// Function to get the original URL from short URL
	public String getLongURL(String shortURL) {
		
		// Generate the ID from shortURL
		int keygenID = KeygenUtil.INSTANCE.generateIDFromShortURL(shortURL.replace(APPENDER,""));
		String originalURL = RedisUtil.INSTANCE.fetchFromRedis(keygenID);
		return originalURL;
	}

	// Function to generate Short URLs for a file of long URLs
	public void generateShortURL(String input, String output) {
		try {
			// Handle for writing to output file
			File outputFile = new File(output);
			FileOutputStream fileOutputStream = new FileOutputStream(outputFile);
			BufferedWriter bufferedWriter = new BufferedWriter(
					new OutputStreamWriter(fileOutputStream));

			// Handle for readind from file
			BufferedReader bufferedReader = new BufferedReader(new FileReader(
					input));
			String line;
			// iterate over lines and generate the short URLS
			while ((line = bufferedReader.readLine()) != null) {
				bufferedWriter.write(generateShortURL(line));
				bufferedWriter.newLine();

			}

			// Close the buffered reader/writer
			bufferedReader.close();
			bufferedWriter.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
