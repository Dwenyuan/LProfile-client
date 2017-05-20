package liu.lprofile.com.client;

import java.io.IOException;
import java.io.InputStream;
import java.lang.instrument.Instrumentation;

import liu.lprofile.com.util.ConfigInfo;

/**
 * Hello world!
 *
 */
public class Probe {
	public static void premain(String args, Instrumentation inst) throws IOException {
		InputStream inputStream = ClassLoader.getSystemResourceAsStream("init.propreties");
		ConfigInfo.load(inputStream);
		inst.addTransformer(new ProbeClassTransformer());
	}
}
