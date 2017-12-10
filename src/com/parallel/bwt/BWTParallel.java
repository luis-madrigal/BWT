package com.parallel.bwt;

import com.parallel.sorting.MergeParallel;
import com.utils.Timer;
import com.utils.Utils;

import java.util.concurrent.RecursiveTask;

public class BWTParallel {

    public final int PARTITION_SIZE = 100000;

    private int origStrCol;

    public void process(String input){
        String transform = this.transform(input);
        System.out.println("TRANSFORM: " +transform);
//        System.out.println("INVERSE TRANSFORM: " +this.inverseTransform(transform, origStrCol));
    }

    public String transform(String input) {
        String[] rotations = rotate(input);
        System.out.println("------ROTATIONS-----");
        Utils.printStrArr(rotations);
        System.out.println("--------------------");

//        Timer.start();
        MergeParallel.sort(rotations);
//        System.out.println("Speed: "+Timer.end());
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

//
//    private String[] rotate(String input){
//        int len = input.length();
//        String[] rotations = new String[len];
//
//        return rotations;
//    }
//
//    private void rotateFork(String[] rotations, int lowBound, int highBound){
//        String rotatedStr = rotations[lowBound-1];
//        for(int i = lowBound; i < highBound; i++) {
//            rotatedStr = Utils.shiftRight(rotatedStr);
//            rotations[i] = rotatedStr;
//        }
//    }


//    private String getStrFromCol(String[] sortedRotations, int col) {
//        String output = "";
//        for (String string : sortedRotations) {
//            output = output.concat(Character.toString(string.charAt(col)));
//        }
//
//        return output;
//    }



    private String getStrFromCol(String[] sortedRotations, int col){
        return  getStrFromCol_Parallel(sortedRotations,0,sortedRotations.length-1,col);
    }

    private String getStrFromCol_Parallel(String[] sortedRotations, int lowBound, int hiBound, int col){
        String concatedString = "";
        if(hiBound - lowBound > PARTITION_SIZE){
            RecursiveTask<String> task = new RecursiveTask<String>() {
                @Override
                protected String compute() {
                    int newHiBound;
                    if(hiBound%2 == 0)
                        newHiBound = hiBound-(hiBound/2)-1;
                    else
                        newHiBound = hiBound-(hiBound/2);
                    return getStrFromCol_Parallel(sortedRotations,lowBound, newHiBound,col);
                }
            };
            task.fork();
            int newLoBound;
            if(hiBound%2 == 0)
                newLoBound = hiBound-(hiBound/2);
            else
                newLoBound = hiBound-(hiBound/2) + 1;

            concatedString = getStrFromCol_Parallel(sortedRotations,newLoBound,hiBound,col) + task.join();
        }
        else {
            for(int i = lowBound; i <= hiBound; i++)
                concatedString.concat(Character.toString(sortedRotations[i].charAt(col)));
        }

        return concatedString;
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
            MergeParallel.sort(inverseTransforms);
        }

        return inverseTransforms[origStrCol];
    }
}
