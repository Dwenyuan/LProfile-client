package com.liu.lprofile.aop;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

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

	public static void getStracks(long time, StackTraceElement[] stackTraceElements) throws JsonProcessingException {
		System.out.println("================================");
		ObjectMapper mapper = new ObjectMapper();
		String stacks = mapper.writeValueAsString(stackTraceElements);
		System.out.println(time + ":" + stacks);
		// for (StackTraceElement stackTraceElement : stackTraceElements) {
		// System.out.println(stackTraceElement.getClassName() + "." +
		// stackTraceElement.getMethodName());
		// }
	}
}
