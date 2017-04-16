package com.liu.lprofile.communication;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import com.liu.lprofile.entity.MessageBucket;

public class Commit implements Runnable {

	private MessageBucket instance;

	private static String HOST = "127.0.0.1";
	private static int PORT = 12017;

	private Socket socket;

	public Commit() {
		instance = MessageBucket.getInstance();
		try {
			socket = new Socket(HOST, PORT);
		} catch (IOException e) {
			// TODO 链接地址错误处理
			e.printStackTrace();
		}
	}

	private void sendMessage(byte[] message) {
		OutputStream outputStream = null;
		try {
			outputStream = socket.getOutputStream();
			outputStream.write(message);
			outputStream.flush();
		} catch (IOException e) {
			try {
				outputStream.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		while (true) {
			try {
				byte[] takeMessage = instance.takeMessage();
				sendMessage(takeMessage);
			} catch (InterruptedException e) {
				// TODO 处理拿不到桶中的数据错误
				e.printStackTrace();
			}

		}
	}

}
