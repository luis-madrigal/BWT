package com.standard.bwt;

import com.standard.sorting.BubbleStandard;
import com.standard.sorting.MergeStandard;
import com.utils.Timer;
import com.utils.Utils;

public class BWTStandard {
	
	private int origStrCol;
	private Timer t;
	
	public BWTStandard() {
		t = new Timer();
	}
	
	public void process(String input) {
		String transform = this.transform(input);
		System.out.println("INDEX RETRIEVAL: " +t.end());
		
		System.out.println("TRANSFORMED STRING: " +transform);
		System.out.println("INVERSE TRANSFORM STRING: " +this.inverseTransform(transform, origStrCol));
	}
	
	public String transform(String input) {
		t.start();
		String[] rotations = rotate(input);
		System.out.println("INPUT ROTATION: " +t.end());
//		System.out.println("------ROTATIONS-----");
//		Utils.printStrArr(rotations);
//		System.out.println("--------------------");

		t.start();
//		MergeStandard.sort(rotations);
		BubbleStandard.sort(rotations);
		System.out.println("ROTATION SORTING: " +t.end());
//		System.out.println("Execution time for parallel algorithm: " + Timer.end());
//		System.out.println("Speed: "+Timer.end());
//		System.out.println("--SORTED ROTATIONS--");
//		Utils.printStrArr(rotations);
//		System.out.println("--------------------");
		
		t.start();
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
		
		t.start();
		for(int i = 0; i < len; i++) {
			for(int j = 0; j < len; j++) {
				inverseTransforms[j] = Character.toString(input.charAt(j)) + inverseTransforms[j];
			}
			MergeStandard.sort(inverseTransforms);
		}
		System.out.println("INVERSE TRANSFORMATION: " +t.end());
				
		return inverseTransforms[origStrCol];
	}
}
