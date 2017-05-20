package liu.lprofile.com.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigInfo {
	
	public static Properties properties = new Properties();
	
	private ConfigInfo() {
		super();
	}

	public static void load(InputStream inStream) {
		try {
			properties.load(inStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String getPropertie(String key) {
		return properties.getProperty(key);
	}
}
