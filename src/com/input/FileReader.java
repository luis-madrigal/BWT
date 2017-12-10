package com.input;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class FileReader {

	public static String readFromFile(String fName) throws IOException {
		InputStream is = new FileInputStream(fName);
        BufferedReader buf = new BufferedReader(new InputStreamReader(is));
        
        String line = buf.readLine();
        StringBuilder sb = new StringBuilder();
        
        while(line != null){
            sb.append(line);
            line = buf.readLine();
        }
        System.out.println("done");
        
        return sb.toString();
	}
}
