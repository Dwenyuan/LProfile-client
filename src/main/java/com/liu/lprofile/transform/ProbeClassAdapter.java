package com.liu.lprofile.transform;

import org.objectweb.asm.ClassAdapter;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.MethodVisitor;

/**
 * 处理类一级的字节码
 * 
 * @author liu
 *
 */
public class ProbeClassAdapter extends ClassAdapter {

	private String className = "";

	public ProbeClassAdapter(ClassVisitor cv, String className) {
		super(cv);
		this.className = className;
	}

	@Override
	public FieldVisitor visitField(int access, String name, String desc, String signature, Object value) {
		return super.visitField(access, name, desc, signature, value);
	}

	@Override
	public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
		if ("<clinit>".equals(name)) {
			return super.visitMethod(access, name, desc, signature, exceptions);
		}
		return new ProbeMethodAdapter(super.visitMethod(access, name, desc, signature, exceptions), className, name);
	}

}
