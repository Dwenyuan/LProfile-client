package com.liu.lprofile.communication;

import com.liu.lprofile.entity.MessageBucket;

public class Commit implements Runnable {

	public Commit() {
		
	}
	
	@Override
	public void run() {
		while (true) {
			try {
				byte[] takeMessage = MessageBucket.getInstance().takeMessage();
				byte[] message = new byte[takeMessage.length-4];
				for (int i = 0; i < message.length; i++) {
					message[i] = takeMessage[i+4];
				}
				System.out.println(new String(message));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}
	}

}
