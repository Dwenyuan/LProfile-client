package com.liu.LProfile.client;

import org.junit.Before;
import org.junit.Test;

import liu.lprofile.com.communication.Commit;
import liu.lprofile.com.entity.MessageBucket;
import liu.lprofile.com.util.ConfigInfo;
import liu.lprofile.com.util.ZIPUtil;



public class TestBucket {

	@Before
	public void readConfig(){
		ConfigInfo.load(ClassLoader.getSystemResourceAsStream("init.properties"));
	}
	
	public void testMessageBucket() throws InterruptedException {
//		System.out.println("hello world");
		byte[] bytes = "hello world".getBytes();
		MessageBucket.getInstance().pushMessage(bytes, bytes.length);
		Thread thread = new Thread(new Commit());
		thread.setDaemon(false);
		thread.start();
	}
	
	@Test
	public void start() throws InterruptedException {
		Thread thread = new Thread(new Commit());
		thread.setDaemon(false);
		thread.start();
		byte[] bytes = "hello world".getBytes();
		byte[] encoder = ZIPUtil.encoder(bytes);
		while(true){
			byte[] lens = ZIPUtil.intToByteArray(12);
			byte[] buffer = new byte[12+4];
			byte[] l1 = new byte[2];
			byte[] l2 = new byte[2];
			l1[0] =lens[0];
			l1[1] =lens[1];
			l2[0] =lens[2];
			l2[1] =lens[3];
			MessageBucket.getInstance().pushMessage(l1, bytes.length);
			Thread.sleep(2000);
			MessageBucket.getInstance().pushMessage(l2, bytes.length);
			Thread.sleep(2000);
			MessageBucket.getInstance().pushMessage(bytes, bytes.length);
			Thread.sleep(2000);
			MessageBucket.getInstance().pushMessage("a".getBytes(), bytes.length);
		}
	}
}
