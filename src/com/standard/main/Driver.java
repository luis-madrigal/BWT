package com.standard.main;

import java.io.IOException;

import com.input.FileReader;
import com.standard.bwt.BWTStandard;
import com.utils.Timer;

public class Driver {

	public static final String filename = "dna1mil.txt";

	public static void main(String[] args) {
		Timer.start();
		try {
			new BWTStandard().process(FileReader.readFromFile("dna100k.txt"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Execution time for standard algorithm: " + Timer.end() + " ms");
	}

}
