package com.utils;

public class Timer {
	
	private long startTime;
	
	public void start() {
		startTime = System.currentTimeMillis();
	}

	public long end() {
		return System.currentTimeMillis() - startTime;
	}
	
}
