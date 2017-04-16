package com.liu.LProfile.client;

import org.junit.Test;

import com.liu.lprofile.util.ZIPUtil;

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
}
