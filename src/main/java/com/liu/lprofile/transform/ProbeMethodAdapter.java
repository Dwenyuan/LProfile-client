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
public class ProbeMethodAdapter extends MethodAdapter implements Opcodes{

	private String className = "";
	private String methodName = "";

	public ProbeMethodAdapter(MethodVisitor mv, String className, String methodName) {
		super(mv);
		this.className = className;
		this.methodName = methodName;
	}

	@Override
	public void visitCode() {
		this.visitMethodInsn(INVOKESTATIC, "com/liu/lprofile/aop/Consuming", "before", "()V");
		super.visitCode();
	}

	@Override
	public void visitInsn(int opcode) {
		if (opcode >= IRETURN && opcode <= RETURN) {
			this.visitMethodInsn(INVOKESTATIC, "com/liu/lprofile/aop/Consuming", "after", "()V");
		}
		super.visitInsn(opcode);
	}

	@Override
	public void visitMethodInsn(int opcode, String owner, String name, String desc) {
		super.visitMethodInsn(opcode, owner, name, desc);
	}

	@Override
	public void visitLdcInsn(Object cst) {
		super.visitLdcInsn(cst);
	}

}
