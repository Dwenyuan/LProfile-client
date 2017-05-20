package com.liu.LProfile.client;

import java.util.Arrays;

import org.junit.Test;

import liu.lprofile.com.util.ZIPUtil;

public class TestZipUtil {

	@Test
	public void testEncoder(){
		byte[] encoder = ZIPUtil.encoder("hello".getBytes());
		for (byte b : encoder) {
			System.out.format("0x%x,", b);
		}
		byte[] decoder = ZIPUtil.decoder(encoder);
		System.out.println("");
		for (byte b : decoder) {
			System.out.format("0x%x,", b);
		}
		System.out.println(new String(decoder));
	}
	
	@Test
	public void TestCopy(){
		byte[] b = new byte[]{0x1,0x2,0x3,0x4};
		byte[] cs = Arrays.copyOf(b, 2);
		for (byte c : cs) {
			System.out.println(c);
		}
	}
}
