package com.bit.javassist;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.CtNewMethod;
import javassist.NotFoundException;

public class BitMonitor {
	public static void main(String[] args) throws NotFoundException,
			CannotCompileException {
		ClassPool pool = new ClassPool(true);
		CtClass targetClass = pool.get("com.bit.javassist.BitStringUtil");
		
		CtMethod addStringMethod = targetClass.getDeclaredMethod("addString");
		CtMethod agentMethod = CtNewMethod.copy(addStringMethod,
				  "addString$agent", targetClass, null);
		targetClass.addMethod(agentMethod);

		String agentSrc = "{" 
				+ "long begin = System.nanoTime();"
				+ "Object result=addString$agent($$);" 
				+ "long end = System.nanoTime();"
				+ "System.out.println(end-begin);" 
				+ "return result;"
				+ "}";
		addStringMethod.setBody(agentSrc);

		targetClass.toClass();
		BitStringUtil util = new BitStringUtil();
		util.addString(100);
	}
}
