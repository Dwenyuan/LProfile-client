package liu.lprofile.com.aop;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import liu.lprofile.com.entity.MessageBucket;

public class Consuming {

	public static long start;
	public static long end;
	public static long consuming;
	private static ObjectMapper mapper = new ObjectMapper();

	public static void before() {
		start = System.currentTimeMillis();
	}

	public static void after() {
		end = System.currentTimeMillis();
		consuming = end - start;
		System.out.println(consuming);
	}

	public static void getStracks(long time, StackTraceElement[] stackTraceElements) throws JsonProcessingException {
		String stacks = mapper.writeValueAsString(stackTraceElements);
		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append("{time:");
		stringBuffer.append(time);
		stringBuffer.append(",stacks:");
		stringBuffer.append(stacks);
		stringBuffer.append("}");
		String result = stringBuffer.toString();
		MessageBucket.getInstance().pushMessage(result.getBytes(), result.length());
	}
}
