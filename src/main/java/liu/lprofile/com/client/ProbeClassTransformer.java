package liu.lprofile.com.client;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;

import org.objectweb.asm.ClassAdapter;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;

import liu.lprofile.com.transform.ProbeClassAdapter;
import liu.lprofile.com.util.ConfigInfo;

public class ProbeClassTransformer implements ClassFileTransformer {

	@Override
	public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined,
			ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {
		String packagestr = ConfigInfo.getPropertie("packages");
		if (packagestr != null) {
			String[] packages = packagestr.split(";");
			for (String string : packages) {
				if (className.startsWith(string)) {
					ClassReader classReader = new ClassReader(classfileBuffer);
					ClassWriter classWriter = new ClassWriter(ClassWriter.COMPUTE_MAXS);
					ClassAdapter classAdapter = new ProbeClassAdapter(classWriter, className);
					classReader.accept(classAdapter, 0);
					return classWriter.toByteArray();
				}
			}
			return classfileBuffer;
		} else {
			return classfileBuffer;
		}

	}

}
