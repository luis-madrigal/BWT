package com.utils;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Utils {

	public static String readFile(String path, Charset encoding)
			throws IOException
	{
		byte[] encoded = Files.readAllBytes(Paths.get(path));
		return new String(encoded, encoding);
	}
	
	public static String shiftRight(String str) {
		return str.charAt(str.length() - 1) + str.substring(0, str.length() - 1);
	}

	public static void printStrArr(String[] rotations) {
		for (String string : rotations) {
			System.out.println(string);
		}
	}

	public static List<String[]> partitionString(String[] toPartition, int partitionSize){
		List<String[]> partitions = new LinkedList<>();
		for (int i = 0; i < toPartition.length; i += partitionSize) {
			partitions.add(Arrays.copyOfRange(toPartition,i,
					Math.min(i + partitionSize, toPartition.length)));
		}
		return partitions;
	}
	
	public static int getStringFromArr(String[] arr, String str) {
		for(int i = 0; i < arr.length; i++) {
			if(arr[i].equals(str))
				return i;
		}
		return -1;
	}


}
