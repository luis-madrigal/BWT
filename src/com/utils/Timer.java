package com.utils;

public class Timer {
	
	private long startTime;
	
	public void start() {
		startTime = System.nanoTime();
	}

	public long end() {
		return System.nanoTime() - startTime;
	}
	
}
