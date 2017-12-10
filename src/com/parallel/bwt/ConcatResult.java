package com.parallel.bwt;

import java.util.concurrent.Callable;

public class ConcatResult implements Callable<String> {

    private String[] rotations;
    private int col;

    public ConcatResult(String[] rotations, int col){
        this.rotations = rotations;
        this.col = col;
    }

    @Override
    public String call() throws Exception {
        String lastColResult = "";
        for(int i = 0; i<rotations.length; i++)
            lastColResult = lastColResult.concat(Character.toString(rotations[i].charAt(col)));
        return lastColResult;
    }
}
