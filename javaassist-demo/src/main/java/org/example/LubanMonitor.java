package org.example;

import javassist.*;

public class LubanMonitor {
    public static void main(String[] args) throws NotFoundException, CannotCompileException {
        ClassPool pool = new ClassPool(true);
//		String targetClassName="com.bit.javassist.BitStringUtil";
        String targetClassName = BitStringUtil.class.getName();
        CtClass targetClass = pool.get(targetClassName);
        CtMethod method = targetClass.getDeclaredMethod("addString");
        CtMethod agentMethod = CtNewMethod.copy(method, method.getName() + "$agent", targetClass, null);
        targetClass.addMethod(agentMethod);
        String src = "{"
                + "long begin = System.nanoTime();"
                + "Object result=" + method.getName() + "$agent($$);"
                + "long end = System.nanoTime();"
                + "System.out.println(end-begin);"
                + "return ($r)result;"
                + "}";
        method.setBody(src);
        // 载入至当前ClassLoader
        //targetClass.toClass();
        BitStringUtil util = new BitStringUtil();
        String ret = util.addString(1000);
//		Thread.currentThread().getContextClassLoader();
    }
}
