package com.standard.main;

import com.standard.bwt.BWTStandard;
import com.utils.Timer;

public class Driver {
	
	public static void main(String[] args) {
		Timer.start();
		new BWTStandard().process("BANANA");
		System.out.println("Execution time for standard algorithm: " + Timer.end() + " ms");
	}

}
