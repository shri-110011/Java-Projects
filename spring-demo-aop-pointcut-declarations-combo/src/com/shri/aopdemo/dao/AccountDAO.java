package com.shri.aopdemo.dao;

import org.springframework.stereotype.Component;

import com.shri.aopdemo.Account;

@Component
public class AccountDAO {
	
	private String name;
	private String serviceCode;

//	public void addAccount() {
//		System.out.println(getClass()+": Doing my DB work: Adding an account");
//	}
	
//	public void addAccount(Account theAccount) {
//		System.out.println(getClass()+": Inside addAccount(Account a): Doing my DB work: Adding an account");
//	}
	
//	public void addAccount(Account theAccount, boolean vipFlag) {
//		System.out.println(getClass()+": Inside addAccount(Account a, boolean b): Doing my DB work: Adding an account");
//	}

	public void addAccount(Account theAccount, boolean vipFlag, String accountNumber) {
		System.out.println(getClass()+": Inside addAccount(Account a, boolean b, String c): Doing my DB work: Adding an account");
	}
	
	public boolean doWork() {
		System.out.println(getClass()+": Inside doWork(): Doing my DB work");
		return false;
	}
	
	public String getName() {
		System.out.println(getClass()+": Inside getName()");
		return name;
	}

	public void setName(String name) {
		System.out.println(getClass()+": Inside setName()");
		this.name = name;
	}

	public String getServiceCode() {
		System.out.println(getClass()+": Inside getServiceCode()");
		return serviceCode;
	}

	public void setServiceCode(String serviceCode) {
		System.out.println(getClass()+": Inside setServiceCode()");
		this.serviceCode = serviceCode;
	}
}
