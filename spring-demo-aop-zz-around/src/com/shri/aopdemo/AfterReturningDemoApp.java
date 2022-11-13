package com.shri.aopdemo;

import java.util.List;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.shri.aopdemo.dao.AccountDAO;

public class AfterReturningDemoApp {

	public static void main(String[] args) {
		
		// read the spring config java class
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DemoConfig.class);
		
		// get the AccountDAO bean from spring container
		AccountDAO accountDAO = context.getBean(AccountDAO.class);
		
		// call method to find the accounts
		List<Account> accounts = accountDAO.findAccounts(false);
		
		// display the accounts
		System.out.println("\n Main Program: AfterReturningDemoApp");
		System.out.println("----");
		
		System.out.println(accounts);
		
		System.out.println("\n");
		
		// close the context
		context.close();

	}

}
