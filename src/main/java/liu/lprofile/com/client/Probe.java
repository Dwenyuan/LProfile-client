package liu.lprofile.com.client;

import java.io.IOException;
import java.io.InputStream;
import java.lang.instrument.Instrumentation;

import liu.lprofile.com.communication.Commit;
import liu.lprofile.com.util.ConfigInfo;

/**
 * Hello world!
 *
 */
public class Probe {
	public static void premain(String args, Instrumentation inst) throws IOException {
		InputStream inputStream = ClassLoader.getSystemResourceAsStream("init.properties");
		ConfigInfo.load(inputStream);
		Thread thread = new Thread(new Commit());
		thread.setDaemon(true);
		thread.start();
		inst.addTransformer(new ProbeClassTransformer());
	}
}
