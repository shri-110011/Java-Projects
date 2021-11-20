package com.basicsstrong.reflection;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class ModifierInfo {

	public static void main(String[] args) throws Exception {
		
		Entity e = new Entity(10, "id");
		Class<?> clss = e.getClass();
		int modifiersInt = clss.getModifiers();
//		int i = modifiersInt & Modifier.PUBLIC;
//		System.out.println(i);
		
		boolean isPublicClass = Modifier.isPublic(modifiersInt);
//		System.out.println(isPublicClass);
		System.out.println(Modifier.toString(modifiersInt));
		
		Method method = clss.getMethod("getVal");
		int methodModifiers = method.getModifiers();
//		int j = methodModifiers & Modifier.PUBLIC;
//		System.out.println(j);
		
		boolean isPubMethod = Modifier.isPublic(methodModifiers);
//		System.out.println(isPubMethod);
		System.out.println(Modifier.toString(methodModifiers));

	}

}
