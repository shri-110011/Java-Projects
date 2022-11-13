package com.shri.aopdemo;

import java.util.List;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.shri.aopdemo.dao.AccountDAO;

public class AfterFinallyDemoApp {

	public static void main(String[] args) {
		
		// read the spring config java class
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DemoConfig.class);
		
		// get the AccountDAO bean from spring container
		AccountDAO accountDAO = context.getBean(AccountDAO.class);
		
		// call method to find the accounts
		List<Account> accounts = null;
		try {
			boolean tripWire = false;
			accounts = accountDAO.findAccounts(tripWire);
		}
		catch(Exception exc) {
			System.out.println("\n\n Main Program ... caught exception: "+exc);
		}
		
		// display the accounts
		System.out.println("\n Main Program: AfterFinallyDemoApp");
		System.out.println("----");
		
		System.out.println(accounts);
		
		System.out.println("\n");
		
		// close the context
		context.close();

	}

}
