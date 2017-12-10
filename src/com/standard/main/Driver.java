package com.standard.main;

import java.io.IOException;

import com.input.FileReader;
import com.standard.bwt.BWTStandard;
import com.utils.Timer;

public class Driver {

	public static final String filename = "dna100k.txt";

	public static void main(String[] args) {
		Timer t = new Timer();
		t.start();
		try {
			new BWTStandard().process(FileReader.readFromFile(filename));
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Execution time for standard algorithm: " + t.end() + " ms");
//		Timer.start();
//		new BWTStandard().process("Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque ipsa quae ab illo inventore veritatis et quasi architecto beatae vitae dicta sunt explicabo. Nemo enim ipsam voluptatem quia voluptas sit aspernatur aut odit aut fugit, sed quia consequuntur magni dolores eos qui ratione voluptatem sequi nesciunt. Neque porro quisquam est, qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit, sed quia non numquam eius modi tempora incidunt ut labore et dolore magnam aliquam quaerat voluptatem. Ut enim ad minima veniam, quis nostrum exercitationem ullam corporis suscipit laboriosam, nisi ut aliquid ex ea commodi consequatur? Quis autem vel eum iure reprehenderit qui in ea voluptate velit esse quam nihil molestiae consequatur, vel illum qui dolorem eum fugiat quo voluptas nulla pariatur?");
//		System.out.println("Execution time for standard algorithm: " + Timer.end()+ " ms");
	}

}
