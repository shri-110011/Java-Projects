package com.basicsstrong.methodHandles;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodHandles.Lookup;
import java.lang.invoke.MethodType;
import java.lang.invoke.VarHandle;

public class MethodHandlesDemo {

	public static void main(String[] args) throws Throwable {
		
		Lookup lookUp = MethodHandles.lookup();
		
		Class<?> clss = lookUp.findClass(Student.class.getName());
		
		Student s = new Student();
		s.setCourse("Java");
		
		MethodType methodType = MethodType.methodType(String.class);
		
		MethodHandle getCourseHandle = lookUp.findVirtual(clss, "getCourse", methodType);
		
		System.out.println(getCourseHandle.invoke(s));
		
		MethodType type = MethodType.methodType(void.class);
		MethodHandle noArgsHandle = lookUp.findConstructor(clss, type);
		Student s1 = (Student)noArgsHandle.invoke();
		s1.setCourse("Scala");
		s1.setName("A.Shrikant");
		System.out.println(s1.toString());
		
		//Use findVirtual() to get MethodHandle for an instance method
		MethodType methodType2 = MethodType.methodType(void.class, String.class);
		MethodHandle setNameHandle = lookUp.findVirtual(clss, "setName", methodType2);
		setNameHandle.invoke(s1, "S.Arjun");
		System.out.println(s1.toString());
		
		MethodType type1 = MethodType.methodType(void.class, String.class, String.class);
		MethodHandle paraCons = lookUp.findConstructor(clss, type1);
		Student siddharth = (Student)paraCons.invoke("A.Sddharth", "Python");
		System.out.println(siddharth.toString());
		
		//Use findStatic() to get MethodHandle for a static method
		MethodType methodType3 = MethodType.methodType(void.class, int.class);
		MethodHandle setNoOfStudentsHandle = lookUp.findStatic(clss, "setNoOfStudents", methodType3);
		setNoOfStudentsHandle.invoke(15);
		System.out.println(Student.getNoOfStudents());
		
		Lookup privateLookup = MethodHandles.privateLookupIn(clss, lookUp);
		
		MethodHandle findGetter = privateLookup.findGetter(clss, "name", String.class);
		MethodHandle findSetter = privateLookup.findSetter(clss, "name", String.class);
		String str = (String)findGetter.invoke(s1);
		System.out.println(str);
		findSetter.invoke(s1, "Varun");
		System.out.println(s1.toString());
		
		//VarHandles
		
		VarHandle courseVarHandle = privateLookup.findVarHandle(clss, "course", String.class);
		String val = (String)courseVarHandle.get(s1);
		System.out.println(val);
		courseVarHandle.set(s1, "Kotlin");
		System.out.println(s1.toString());
	}

}
