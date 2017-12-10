package com.utils;

public class Utils {
	
	public static String shiftRight(String str) {
		return str.charAt(str.length() - 1) + str.substring(0, str.length() - 1);
	}

	public static void printStrArr(String[] rotations) {
		for (String string : rotations) {
			System.out.println(string);
		}
	}
	
	public static int getStringFromArr(String[] arr, String str) {
		for(int i = 0; i < arr.length; i++) {
			if(arr[i].equals(str))
				return i;
		}
		return -1;
	}
}
