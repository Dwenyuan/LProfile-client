package com.liu.lprofile.client;

import java.lang.instrument.Instrumentation;

/**
 * Hello world!
 *
 */
public class Probe {
	public static void premain(String[] args,Instrumentation inst) {
		inst.addTransformer(new ProbeClassTransformer());
	}
}