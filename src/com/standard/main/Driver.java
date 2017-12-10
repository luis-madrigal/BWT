package com.standard.main;

import com.standard.bwt.BWTStandard;
import com.utils.Timer;
import com.utils.Utils;
import java.nio.charset.Charset;

public class Driver {

	public static final String filename = "dna1mil.txt";

	public static void main(String[] args) {
		try{
		//Timer.start();
		new BWTStandard().process(Utils.readFile(filename, Charset.defaultCharset()));
		//System.out.println("Execution time for standard algorithm: " + Timer.end());
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
