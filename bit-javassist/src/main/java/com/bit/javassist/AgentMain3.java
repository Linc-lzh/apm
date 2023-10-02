package com.bit.javassist;

import java.lang.instrument.Instrumentation;

public class AgentMain3 {
	public static void main(String[] args) {
		System.out.println("hello main2111");
	}
	
		// 预处理方法
	public static void premain(String args, Instrumentation inst) {
		System.out.println("hello premain");
	}
	
}
