package com.liu.lprofile.transform;

import org.objectweb.asm.ClassAdapter;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.commons.AnalyzerAdapter;
import org.objectweb.asm.commons.LocalVariablesSorter;

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
		if ("<init>".equals(name) || "<clinit>".equals(name)) {
			return super.visitMethod(access, name, desc, signature, exceptions);
		}
//		MethodVisitor vm = cv.visitMethod(access, name, desc, signature, exceptions);
		MethodVisitor vm = super.visitMethod(access, name, desc, signature, exceptions);
		
		ProbeMethodAdapter probeMethodAdapter = new ProbeMethodAdapter(vm, className, name);
		
		probeMethodAdapter.analyzerAdapter = new AnalyzerAdapter(className, access, name, desc, probeMethodAdapter);
		probeMethodAdapter.localVariablesSorter = new LocalVariablesSorter(access, desc, probeMethodAdapter.analyzerAdapter);
//		return new ProbeMethodAdapter(vm, className, name, localVariablesSorter);
		return probeMethodAdapter.localVariablesSorter;
	}

	@Override
	public void visitEnd() {
		cv.visitField(Opcodes.ACC_PUBLIC + Opcodes.ACC_STATIC, "record_timer", "J", null, null);
		super.visitEnd();
	}

}
