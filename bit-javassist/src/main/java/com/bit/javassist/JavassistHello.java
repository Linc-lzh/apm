package com.bit.javassist;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.NotFoundException;

public class JavassistHello {

	/**
	 * 生成一个简单类
	 * 
	 * @param args
	 * @throws CannotCompileException
	 * @throws NotFoundException
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 */
	public static void main(String[] args) throws CannotCompileException,
			NotFoundException, InstantiationException, IllegalAccessException {
		ClassPool pool = new ClassPool(true);
		CtClass helloClass = pool.makeClass("com.bit.Hello");
		helloClass.addInterface(pool.get(IHello.class.getName()));
		CtClass returnType = pool.get(void.class.getName());
		String methodName = "sayHello";
		CtClass[] params = new CtClass[] { pool.get(String.class.getName()) };
		CtMethod method = new CtMethod(returnType, methodName, params,
				helloClass);
		String src = "{" 
				+ "System.out.println(\"hello:\"+$1);" 
				+ "}" 
				+ "";
		method.setBody(src);
		helloClass.addMethod(method);
		IHello cla = (IHello) helloClass.toClass().newInstance();
		cla.sayHello("han mei mei");
	}

	public static interface IHello {
		public void sayHello(String name);
	}
}
