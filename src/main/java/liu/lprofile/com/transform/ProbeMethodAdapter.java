package liu.lprofile.com.transform;

import java.util.List;

import org.objectweb.asm.MethodAdapter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;
import org.objectweb.asm.commons.AnalyzerAdapter;
import org.objectweb.asm.commons.LocalVariablesSorter;

/**
 * 处理方法级的字节码
 * 
 * @author liu
 *
 */
public class ProbeMethodAdapter extends MethodAdapter implements Opcodes {

	private String className = "";
	private String methodName = "";
	public AnalyzerAdapter analyzerAdapter;
	public LocalVariablesSorter localVariablesSorter;
	private int localRecordTimer;

	public ProbeMethodAdapter(MethodVisitor mv, String className, String methodName) {
		super(mv);
		this.className = className;
		this.methodName = methodName;
	}

	public ProbeMethodAdapter(MethodVisitor vm, String className, String methodName,
			LocalVariablesSorter localVariablesSorter) {
		super(vm);
		this.className = className;
		this.methodName = methodName;
		this.localVariablesSorter = localVariablesSorter;
	}

	@Override
	public void visitCode() {
		super.visitCode();
		System.out.println(className + "==========");
		mv.visitMethodInsn(INVOKESTATIC, "java/lang/System", "currentTimeMillis", "()J");
		mv.visitFieldInsn(PUTSTATIC, className, "record_timer", "J");

		mv.visitMethodInsn(INVOKESTATIC, "java/lang/System", "currentTimeMillis", "()J");
		localRecordTimer = localVariablesSorter.newLocal(Type.LONG_TYPE);
		mv.visitVarInsn(LSTORE, localRecordTimer);
	}

	@Override
	public void visitInsn(int opcode) {
		if (opcode >= IRETURN && opcode <= RETURN) {
			mv.visitMethodInsn(INVOKESTATIC, "java/lang/System", "currentTimeMillis", "()J");
			mv.visitVarInsn(LLOAD, localRecordTimer);
			mv.visitInsn(LSUB);
			mv.visitVarInsn(LSTORE, localRecordTimer);

			mv.visitFieldInsn(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
			mv.visitVarInsn(LLOAD, localRecordTimer);
			mv.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(J)V");

			mv.visitVarInsn(LLOAD, localRecordTimer);
			mv.visitMethodInsn(INVOKESTATIC, "java/lang/Thread", "currentThread", "()Ljava/lang/Thread;");
			mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/Thread", "getStackTrace", "()[Ljava/lang/StackTraceElement;");
			mv.visitMethodInsn(INVOKESTATIC, "liu/lprofile/com/aop/Consuming", "getStracks",
					"(J[Ljava/lang/StackTraceElement;)V");

		}
		super.visitInsn(opcode);
	}

}
