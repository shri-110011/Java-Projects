package com.basicsstrong.annotation;

import java.util.ArrayList;

class Parent {
	public void m1() {
		System.out.println("m1 Parent implementation");
	}
	
	@Deprecated(since = "5")
	public void m2(int a) {
		System.out.println("m2 Parent implementation, a is: "+a);
	}
}

public class GeneralPurpose extends Parent {
	
	@Override
	public void m1() {
		System.out.println("m1 Child implementation");
	}

	public static void main(String[] args) {
		
		
//		@SuppressWarnings("unused")
		int a = 10;
		
		@SuppressWarnings({ "rawtypes", "unused" })
		ArrayList aList = new ArrayList();
		
		GeneralPurpose gp = new GeneralPurpose();
		gp.m2(a);
		
		@SuppressWarnings({ "deprecation", "unused" })
		Integer i1 = new Integer(1);
		
		MyFunctionalInterface impl = () -> System.out.println("Method Invoked!");
		impl.method();

	}

}

interface MyFunctionalInterface {
	public void method();
}
