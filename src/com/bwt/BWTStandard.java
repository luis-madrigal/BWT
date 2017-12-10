package com.bwt;

import com.sorting.MergeStandard;
import com.utils.Utils;

public class BWTStandard {
	
	private int outputCol;
	
	public String process(String input) {
		outputCol = input.length() - 1;
		
		String[] rotations = rotate(input);
		System.out.println("------ROTATIONS-----");
		Utils.printRotations(rotations);
		System.out.println("--------------------");

		MergeStandard.sort(rotations);
		System.out.println("--SORTED ROTATIONS--");
		Utils.printRotations(rotations);
		System.out.println("--------------------");

		System.out.println("-------OUTPUT-------");
		System.out.println(this.getOutput(rotations));
		System.out.println("--------------------");
		return "asd";
	}
	
	private String[] rotate(String input) {
		int len = input.length();
		String[] rotations = new String[len];
		String rotatedStr = input;
		
		rotations[0] = input;
		
		for(int i = 1; i < len; i++) {
			rotatedStr = Utils.shiftRight(rotatedStr);
			rotations[i] = rotatedStr;
		}
		
		return rotations;
	}
	
	private String getOutput(String[] sortedRotations) {
		String output = "";
		for (String string : sortedRotations) {
			output = output.concat(Character.toString(string.charAt(outputCol)));
		}
		
		return output;
	}
}
