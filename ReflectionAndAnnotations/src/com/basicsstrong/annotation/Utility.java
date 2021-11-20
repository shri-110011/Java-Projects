package com.basicsstrong.annotation;

@MostUsed
public class Utility {

	public void doStuff() {
		System.out.println("Doing something.");
	}
	
	@MostUsed("Python")
	public void doStuff(String arg) {
		System.out.println("Operating on: "+arg);
	}
	
	public void doStuff(int i) {
		System.out.println("Operating on: "+i);
	}
	
}

class SubUtility extends Utility {
	
}
