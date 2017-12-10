package com.utils;

public class Utils {
	
	public static String shiftRight(String str) {
		return str.charAt(str.length() - 1) + str.substring(0, str.length() - 1);
	}

	public static void printRotations(String[] rotations) {
		for (String string : rotations) {
			System.out.println(string);
		}
	}
}
