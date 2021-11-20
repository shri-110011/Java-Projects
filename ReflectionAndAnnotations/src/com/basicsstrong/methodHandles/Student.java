package com.basicsstrong.methodHandles;

public class Student {

	private static int noOfStudents;
	private String name, course;
	
	public Student() {
		noOfStudents++;
	}

	public Student(String name, String course) {
		noOfStudents++;
		this.name = name;
		this.course = course;
	}

	public static int getNoOfStudents() {
		return noOfStudents;
	}

	public static void setNoOfStudents(int noOfStudents) {
		Student.noOfStudents = noOfStudents;
	}

	public String getName() {
		System.out.println("Inside getName()");
		return name;
	}

	public void setName(String name) {
		System.out.println("Inside setName()");
		this.name = name;
	}

	public String getCourse() {
		return course;
	}

	public void setCourse(String course) {
		this.course = course;
	}
	
	public void test() {
		System.out.println("Inside test()");
	}

	@Override
	public String toString() {
		return "Student [name=" + name + ", course=" + course + "]";
	}
	
}
