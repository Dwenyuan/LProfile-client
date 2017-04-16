package com.liu.LProfile.client;

import org.junit.Test;

import com.liu.lprofile.communication.Commit;
import com.liu.lprofile.entity.MessageBucket;

public class TestBucket {

	
	@Test
	public void testMessageBucket() throws InterruptedException {
//		System.out.println("hello world");
		byte[] bytes = "hello world".getBytes();
		MessageBucket.getInstance().pushMessage(bytes, bytes.length);
		Thread thread = new Thread(new Commit());
		thread.setDaemon(false);
		thread.start();
	}
	
	public static void main(String[] args) throws InterruptedException {
		Thread thread = new Thread(new Commit());
		thread.setDaemon(false);
		thread.start();
		byte[] bytes = "hello world".getBytes();
		MessageBucket.getInstance().pushMessage(bytes, bytes.length);
	}
}
