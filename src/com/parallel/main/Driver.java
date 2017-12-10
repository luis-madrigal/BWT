package com.parallel.main;

import com.parallel.bwt.BWTParallel;
import com.utils.Timer;

public class Driver {
    public static void main(String[] args) {
//        Timer.start();
        new BWTParallel().process("BANANA");
//        System.out.println("Execution time for standard algorithm: " + Timer.end());
    }
}