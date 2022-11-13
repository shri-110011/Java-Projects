package com.shri.aopdemo.dao;

import java.util.ArrayList;
import java.util.List;

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
	
	public List<Account> findAccounts() {
		List<Account> myAccounts = new ArrayList<>();
		
		// create sample accounts
		Account temp1 = new Account("John", "Silver");
		Account temp2 = new Account("Madhu", "Platinum");
		Account temp3 = new Account("Luca", "Gold");
		
		
		// add them to our account list
		myAccounts.add(temp1);
		myAccounts.add(temp2);
		myAccounts.add(temp3);
		
		return myAccounts;
	}
}
