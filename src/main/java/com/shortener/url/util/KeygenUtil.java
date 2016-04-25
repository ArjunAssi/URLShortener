package com.shortener.url.util;

/**
 * This class deals with encoding and decoding for the URL for shortening
 * 
 * @author leonidas
 * @date 22st April
 */
public enum KeygenUtil {

	// Instance to get access to functions
	INSTANCE;

	// For the base conversion
	public static final String LITERALS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ123456789-_";
	public static final int BASE = LITERALS.length();

	// Function to convert the ID from redis to a mapped short string
	public String convertToShortURL(int num) {
		StringBuilder stringBuilder = new StringBuilder();
		while (num > 0) {
			stringBuilder.insert(0, LITERALS.charAt(num % BASE));
			num = num / BASE;
		}
		return stringBuilder.toString();
	}

	// Function to generate the ID back from the short URL
	public int generateIDFromShortURL(String shortURL) {
		int num = 0;
		for (int i = 0; i < shortURL.length(); i++) {
			num = num * BASE + LITERALS.indexOf(shortURL.charAt(i));
		}
		return num;
	}

	public static void main(String[] args) {
		
		System.out.println(KeygenUtil.INSTANCE.convertToShortURL(1));
		System.out.println(KeygenUtil.INSTANCE.generateIDFromShortURL("1"));
	}
}
