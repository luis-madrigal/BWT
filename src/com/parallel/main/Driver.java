package com.parallel.main;

import com.input.FileReader;
import com.parallel.bwt.BWTParallel;
import com.standard.bwt.BWTStandard;
import com.utils.Timer;

import java.io.IOException;

public class Driver {
    public static void main(String[] args) {
    	Timer t = new Timer();
        t.start();
        try {
			new BWTParallel().process(FileReader.readFromFile("input.txt"));
		} catch (IOException e) {
			e.printStackTrace();
		}
        System.out.println("Execution time for parallel algorithm: " + t.end());
    }
}