package com.basicsstrong.reflection;

public class GettingClassObject {

	public static void main(String[] args) throws Exception {
		
		//forName
		Class<?> clss = Class.forName("java.lang.String");
		Class<?> clss2 = Class.forName("java.lang.String");
		
		System.out.println(clss==clss2);
		
		//ClassName.class
		Class<?> clss3 = int.class;
		Class<?> clss4 = String.class;
		
		//obj.getClass()
		MyClass m = new MyClass();
		Class<? extends MyClass> clss5 =  m.getClass();
		
		Class<?> superClass = clss5.getSuperclass();
		
		Class<?>[] interfaces = clss5.getInterfaces();
		
		System.out.println(clss5.getName());

	}

}
