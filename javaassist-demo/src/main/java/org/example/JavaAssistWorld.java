package org.example;

import javassist.*;

public class JavaAssistWorld {
    public static void main(String[] args) throws NotFoundException, CannotCompileException, InstantiationException, IllegalAccessException {
        ClassPool pool = new ClassPool(true);
        // 插入类路径，通过类路径去搜索我们要的类
        pool.insertClassPath(new LoaderClassPath(JavaAssistWorld.class
                .getClassLoader()));

        // 构建一个新的CtClass对象
        CtClass targetClass = pool.makeClass("com.luban.hello");
        // 实现一个接口
        targetClass.addInterface(pool.get(IHello.class.getName()));
        CtClass returnType = pool.get(void.class.getName());
        String mname = "sayHello";
        CtClass[] parameters = new CtClass[]{pool.get(String.class.getName())};
        CtMethod method = new CtMethod(returnType, mname, parameters, targetClass);


        String src = "{"
                + "System.out.println($1);"
                + "int i=System.nanoTime();"
                + "System.out.println(i);"
                + "System.out.println($args.toString());"
//				+ "System.out.println($r+\"\");"
//				+ "System.out.println($type.toString())"
//				+ "System.out.println($class.toString())"
                + "System.out.println(\"hello \"+$1);"
                + "}";
        method.setBody(src);
        targetClass.addMethod(method);

        Class cla = targetClass.toClass();
        IHello hello = (IHello) cla.newInstance();
        hello.sayHello("小灰灰, 小可爱");
    }
}
