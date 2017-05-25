package liu.lprofile.com.communication;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

import liu.lprofile.com.entity.MessageBucket;
import liu.lprofile.com.util.ConfigInfo;

public class Commit implements Runnable {

	private MessageBucket instance;

	private static String HOST = ConfigInfo.getPropertie("ip");
	private static int PORT = Integer.parseInt(ConfigInfo.getPropertie("port"));

	private Socket socket;

	public Commit() {
//		HOST = ConfigInfo.getPropertie("ip");
//		PORT = Integer.parseInt(ConfigInfo.getPropertie("port"));
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
//			outputStream.flush();
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
