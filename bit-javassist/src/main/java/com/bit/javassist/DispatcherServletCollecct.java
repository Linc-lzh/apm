package com.bit.javassist;


import javax.servlet.http.HttpServletRequest;

public class DispatcherServletCollecct {
	public static void begin(Object params[]) {
		HttpServletRequest request = (HttpServletRequest) params[0];
		System.out.println("远程地址是："+request.getRequestURI());
	}
	
}
