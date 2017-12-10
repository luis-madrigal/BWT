package com.standard.bwt;

import com.standard.sorting.MergeStandard;
import com.utils.Timer;
import com.utils.Utils;

public class BWTStandard {
	
	private int origStrCol;
	
	public void process(String input) {
		String transform = this.transform(input);
		System.out.println("TRANSFORM: " +transform);
		System.out.println("INVERSE TRANSFORM: " +this.inverseTransform(transform, origStrCol));
	}
	
	public String transform(String input) {
		String[] rotations = rotate(input);
		System.out.println("------ROTATIONS-----");
		Utils.printStrArr(rotations);
		System.out.println("--------------------");

		Timer.start();
		MergeStandard.sort(rotations);
		System.out.println("Speed: "+Timer.end());
		System.out.println("--SORTED ROTATIONS--");
		Utils.printStrArr(rotations);
		System.out.println("--------------------");
		
		origStrCol = Utils.getStringFromArr(rotations, input);
		
		return this.getStrFromCol(rotations, input.length()-1);
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
	
	private String getStrFromCol(String[] sortedRotations, int col) {
		String output = "";
		for (String string : sortedRotations) {
			output = output.concat(Character.toString(string.charAt(col)));
		}
		
		return output;
	}
	
	public String inverseTransform(String input, int origStrCol) {
		int len = input.length();
		String[] inverseTransforms = new String[len];
		
		for(int i = 0; i < len; i++) {
			inverseTransforms[i] = "";
		}
		
		for(int i = 0; i < len; i++) {
			for(int j = 0; j < len; j++) {
				inverseTransforms[j] = Character.toString(input.charAt(j)) + inverseTransforms[j];
			}
			MergeStandard.sort(inverseTransforms);
		}
				
		return inverseTransforms[origStrCol];
	}
}
