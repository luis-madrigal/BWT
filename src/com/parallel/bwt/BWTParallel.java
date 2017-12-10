package com.parallel.bwt;

import com.parallel.sorting.MergeParallel;
import com.utils.Timer;
import com.utils.Utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

public class BWTParallel {

    //number of cores the computer has
    public final int N_CORES = 2;
    //change this to adjust partitioning for last column letter retrieval
    public final int PARTITION_SIZE = 400;

    private int origStrCol;

    public void process(String input){
        String transform = this.transform(input);
        System.out.println("TRANSFORM: " +transform);
        System.out.println("INVERSE TRANSFORM: " +this.inverseTransform(transform, origStrCol));
    }

    public String transform(String input) {
        String[] rotations = rotate(input);
//        System.out.println("------ROTATIONS-----");
//        Utils.printStrArr(rotations);
//        System.out.println("--------------------");

//        Timer.start();
        MergeParallel.sort(rotations);
//        System.out.println("Execution time for parallel algorithm: " + Timer.end());
//        System.out.println("Speed: "+Timer.end());
//        System.out.println("--SORTED ROTATIONS--");
//        Utils.printStrArr(rotations);
//        System.out.println("--------------------");

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

    //PARALLEL USING EXECUTOR
//    private String getStrFromCol(String[] sortedRotations, int col){
//
//        List<String[]> partitions = Utils.partitionString(sortedRotations,PARTITION_SIZE);
//
//        ArrayList<ConcatResult> callables = new ArrayList<>();
//        for(String[] partition : partitions)
//            callables.add(new ConcatResult(partition,col));
//
//        try {
////            ExecutorService executor = Executors.newFixedThreadPool(N_CORES);
//            ScheduledExecutorService executor = Executors.newScheduledThreadPool(N_CORES, r -> {
//                Thread thread = Executors.defaultThreadFactory().newThread(r);
//                thread.setDaemon(true);
//                return thread;
//            });
//
//            List<Future<String>> results = executor.invokeAll(callables);
//
//            String transformed = "";
//            for (Future<String> res : results)
//                transformed = transformed.concat(res.get());
//
//            System.out.println("TF: " + transformed);
//            executor.shutdownNow();
//
//            return transformed;
//        }
//        catch(Exception e){
//            e.printStackTrace();
//        }
//
//        return null;
//    }

    //PARALLEL USING FORK/JOIN
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
            concatedString = task.join() + getStrFromCol_Parallel(sortedRotations,newLoBound,hiBound,col);
        }
        else {
            for(int i = lowBound; i <= hiBound; i++)
                concatedString = concatedString.concat(Character.toString(sortedRotations[i].charAt(col)));
        }

        return concatedString;
    }

    //standard implementation
//    private String getStrFromCol(String[] sortedRotations, int col) {
//        String output = "";
//        for (String string : sortedRotations) {
//            output = output.concat(Character.toString(string.charAt(col)));
//        }
//
//        return output;
//    }



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
