package com.liu.lprofile.client;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;

import org.objectweb.asm.ClassAdapter;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;

import com.liu.lprofile.transform.ProbeClassAdapter;

public class ProbeClassTransformer implements ClassFileTransformer {

	@Override
	public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined,
			ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {

		ClassReader classReader = new ClassReader(classfileBuffer);
		ClassWriter classWriter = new ClassWriter(ClassWriter.COMPUTE_MAXS);
		ClassAdapter classAdapter = new ProbeClassAdapter(classWriter, className);
		classReader.accept(classAdapter, 0);
		return classWriter.toByteArray();
	}

}
