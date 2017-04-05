package com.liu.lprofile.aop;

public class Consuming {

	public static long start;
	public static long end;
	public static long consuming;

	public static void before() {
		start = System.currentTimeMillis();
	}

	public static void after() {
		end = System.currentTimeMillis();
		consuming = end - start;
		System.out.println(consuming);
	}
}
