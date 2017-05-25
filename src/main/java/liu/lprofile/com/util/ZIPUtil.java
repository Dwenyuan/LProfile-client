package liu.lprofile.com.util;

import java.util.Arrays;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

/**
 * TODO 现决定 格式为[]
 * 
 * @author liu
 *
 */
public class ZIPUtil {

	private static Deflater deflater = new Deflater(Deflater.BEST_SPEED);
	private static Inflater inflater = new Inflater();

	public static byte[] encoder(byte[] src) {
		byte[] result = new byte[2048];
		int len = 0;
		try {
			deflater.setInput(src);
			deflater.finish();
			deflater.deflate(result);
			len = deflater.getTotalOut();
			deflater.reset();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Arrays.copyOf(result, len);
	}

	public static byte[] decoder(byte[] src) {
		byte[] result = new byte[2048];
		try {
			inflater.setInput(src);
			inflater.inflate(result);
			inflater.reset();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 长度用4个byte表示
	 * 
	 * @param b
	 * @return
	 */
	public static int byteArrayToInt(byte[] b) {
		return b[3] & 0xFF | (b[2] & 0xFF) << 8 | (b[1] & 0xFF) << 16 | (b[0] & 0xFF) << 24;
	}

	/**
	 * 将整数转换为byte数组表示
	 * @param a
	 * @return
	 */
	public static byte[] intToByteArray(int a) {
		return new byte[] { (byte) ((a >> 24) & 0xFF), (byte) ((a >> 16) & 0xFF), (byte) ((a >> 8) & 0xFF),
				(byte) (a & 0xFF) };
	}

}
