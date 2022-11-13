package com.shri.aopdemo.dao;

import org.springframework.stereotype.Component;

@Component
public class MembershipDAO {

//	public void addAccount() {
//		System.out.println(getClass()+": Doing my DB work: Adding an account");
//	}
	
//	public void addMembership() {
//		System.out.println(getClass()+": Doing my DB work: Adding membership");
//	}
	
	public boolean addSillyMember() {
		System.out.println(getClass()+": Doing my DB work: Adding silly member");
		
		return false;
	}
	
	public void goToSleep() {
		System.out.println(getClass()+": I'm going to sleep now");
	}
	
}
