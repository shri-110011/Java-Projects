package com.shri.aopdemo;

import java.util.List;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.shri.aopdemo.dao.AccountDAO;

public class MainDemoApp {

	public static void main(String[] args) {
		
		// read the spring config java class
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DemoConfig.class);
		
		// get the AccountDAO bean from spring container
		AccountDAO accountDAO = context.getBean(AccountDAO.class);
		
		// get the MembershipDAO bean from spring container
//		MembershipDAO membershipDAO = context.getBean(MembershipDAO.class);
		
		// call the business method
//		accountDAO.addAccount();
		
		// do it again
//		System.out.println("\n let's call it again!\n");
		
		// call the business method again
//		accountDAO.addAccount();
//		membershipDAO.addAccount();
//		membershipDAO.addMembership();
//		membershipDAO.addSillyMember();
//		membershipDAO.goToSleep();
		
//		Account myAccount = new Account();
		/* The following getter setter for Account is for the JoinPoint logging method argument example 
		 */
//		myAccount.setName("Madhu");
//		myAccount.setLevel("Platinum");
//		accountDAO.addAccount(myAccount);
//		accountDAO.addAccount(myAccount, true);
//		accountDAO.addAccount(myAccount, true, "");
//		accountDAO.doWork();
		
		// call the AccountDAO getter and setter methods 
//		accountDAO.setName("foobar");
//		accountDAO.setServiceCode("silver");
		
//		String name = accountDAO.getName();
//		String serviceCode = accountDAO.getServiceCode();
		
		List<Account> accounts = accountDAO.findAccounts(true);
		System.out.println(accounts);
		
		
		// close the context
		context.close();

	}

}
