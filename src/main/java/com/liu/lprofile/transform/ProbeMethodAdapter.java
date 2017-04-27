package com.liu.lprofile.transform;

import org.objectweb.asm.MethodAdapter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import com.liu.lprofile.aop.Consuming;

/**
 * 处理方法级的字节码
 * 
 * @author liu
 *
 */
public class ProbeMethodAdapter extends MethodAdapter implements Opcodes {

	private String className = "";
	private String methodName = "";

	public ProbeMethodAdapter(MethodVisitor mv, String className, String methodName) {
		super(mv);
		this.className = className;
		this.methodName = methodName;
	}

	@Override
	public void visitCode() {
		System.out.println(className + "==========");
		mv.visitMethodInsn(INVOKESTATIC, "java/lang/System", "currentTimeMillis", "()J");
		mv.visitFieldInsn(PUTSTATIC, className, "record_timer", "J");

		super.visitCode();
	}

	@Override
	public void visitInsn(int opcode) {
		if (opcode >= IRETURN && opcode <= RETURN) {
			mv.visitMethodInsn(INVOKESTATIC, "java/lang/System", "currentTimeMillis", "()J");
			mv.visitFieldInsn(GETSTATIC, "com/example/DemoApplication", "record_timer", "J");
			mv.visitInsn(LSUB);
			mv.visitFieldInsn(PUTSTATIC, "com/example/DemoApplication", "record_timer", "J");

			
			mv.visitFieldInsn(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
			mv.visitFieldInsn(GETSTATIC, className, "record_timer", "J");
			mv.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(J)V");
		}
		super.visitInsn(opcode);
	}

}
