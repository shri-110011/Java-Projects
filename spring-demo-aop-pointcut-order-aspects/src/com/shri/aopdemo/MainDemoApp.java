package com.shri.aopdemo;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.shri.aopdemo.dao.AccountDAO;
import com.shri.aopdemo.dao.MembershipDAO;

public class MainDemoApp {

	public static void main(String[] args) {
		
		// read the spring config java class
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DemoConfig.class);
		
		// get the AccountDAO bean from spring container
		AccountDAO accountDAO = context.getBean(AccountDAO.class);
		
		// get the MembershipDAO bean from spring container
		MembershipDAO membershipDAO = context.getBean(MembershipDAO.class);
		
		// call the business method
//		accountDAO.addAccount();
		
		// do it again
//		System.out.println("\n let's call it again!\n");
		
		// call the business method again
//		accountDAO.addAccount();
//		membershipDAO.addAccount();
//		membershipDAO.addMembership();
		membershipDAO.addSillyMember();
		membershipDAO.goToSleep();
		
		Account myAccount = new Account();
//		accountDAO.addAccount(myAccount);
//		accountDAO.addAccount(myAccount, true);
		accountDAO.addAccount(myAccount, true, "");
		accountDAO.doWork();
		
		// call the AccountDAO getter and setter methods 
		accountDAO.setName("foobar");
		accountDAO.setServiceCode("silver");
		
		String name = accountDAO.getName();
		String serviceCode = accountDAO.getServiceCode();
		
		
		// close the context
		context.close();

	}

}
