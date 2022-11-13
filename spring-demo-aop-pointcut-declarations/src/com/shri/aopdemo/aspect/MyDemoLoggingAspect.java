package com.shri.aopdemo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MyDemoLoggingAspect {
	
	/* Note: 'forDAOPackage' is the name of the pointcut declaration and this declaration
	 * just holds a reference to the pointcut expression.
	 * 
	 * So pointcut declaration provides the solution to the problem i.e. How to reuse a pointcut
	 * expression in multiple advices?
	 * 
	 * Using pointcut declaration makes it easier to make updates to the pointcut expression since
	 * change is to be done in only one place.
	 * 
	 */
	@Pointcut("execution(* com.shri.aopdemo.dao.*.*(..))")
	public void forDAOPackage() {}
	
	// this is where we add all our advices for logging
	
	// let's start with @Before advice
	
	/* In below pointcut expression we are not using the declaring type pattern so this expression will 
	 * match on 'addAccount' method that is present in any class and has return type 'void' and modifier 
	 * as 'public'. And this modifier pattern is optional since by default it is 'public' in spring-aop.
	 */
//	@Before("execution(public void addAccount())")
	
//	@Before("execution(public void updateAccount())")
	
	/* In below pointcut expression we are being more specific as far as which type's 'addAccount' call to 
	 * be tracked and that method's return type must be 'void' and modifier as 'public'.
	 */
//	@Before("execution(public void com.shri.aopdemo.dao.AccountDAO.addAccount())")
	
	/* In below pointcut expression we use wildcard to match on any method that starts with 'add' and has
	 * return type 'void' and modifier as 'public'.
	 */
//	@Before("execution(public void add*())")
	
//	@Before("execution(public * add*())")
	
//	@Before("execution(* add*(com.shri.aopdemo.Account))")
//	@Before("execution(* add*(com.shri.aopdemo.Account, ..))")
//	@Before("execution(* add*(..))")
//	@Before("execution(* com.shri.aopdemo.dao.*.*(..))")
	@Before("forDAOPackage()")
	
	/* Note: "execution(public void addAccount())" is a pointcut expression. And the method to be executed
	 * when this expression matches on any method can have any name. So this below method can have name other 
	 * than addAccount(). 
	 */
	public void addAccount() {
		System.out.println("\n===>>> Executing @Before advice on method");
	}
	
	// Pointcut declaration being used here
	@Before("forDAOPackage()")
	public void performApiAnalytics() {
		System.out.println("\n===>>> Performing API analytics");
	}
	
}
