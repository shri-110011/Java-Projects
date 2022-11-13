package com.shri.aopdemo.aspect;

import java.util.List;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.shri.aopdemo.Account;

@Component
/* Note the range of value for the @Order is -Integer.min to Integer.max and the aspect having the 
 * lowest value for @Order will have the highest priority.
 */
@Order(2)
@Aspect
public class MyDemoLoggingAspect {
	
	@Before("com.shri.aopdemo.aspect.LuvAopExpressions.forDAOPackageNoGetterSetter()")
	/* Note: In AOP Join point is a point during the execution of a program, such as the execution of a method 
	 * or the handling of an exception. In Spring AOP, a join point always represents a method execution.
	 * 
	 * And when we use the JoinPoint object as a parameter in the method associated with the advice
	 * then this JoinPoint object will contain the meta information about the target object's methods that 
	 * match on these pointcut expressions.
	 * 
	 */
	public void addAccount(JoinPoint theJoinPoint) {
		System.out.println("\n===>>> Executing @Before advice on method");
		
		// display the method signature
		MethodSignature methodSig = (MethodSignature)theJoinPoint.getSignature();
		
		System.out.println("Method: "+methodSig);
		
		
		// display the method arguments
		
		// get args
		Object args[] = theJoinPoint.getArgs();
		
		// loop through args
		for(Object tempArg: args) {
			System.out.println(tempArg);
			
			if(tempArg instanceof Account) {
				// downcast and print Account specific stuff
				Account theAccount = (Account)tempArg;
				System.out.println("account name:"+theAccount.getName());
				System.out.println("account level:"+theAccount.getLevel());
			}
		}
	}
	
	@AfterReturning(
			pointcut = "execution(* com.shri.aopdemo.dao.AccountDAO.findAccounts(..))",
			returning = "result"
			)
	public void afterReturningFindAccountsAdvice(JoinPoint theJoinPoint, List<Account> result) {
		
		// print out which method we are advising on
		String method = theJoinPoint.getSignature().toShortString();
		System.out.println("\n====>>> Executing @AfterReturing on method: "+method);
		
		// print out the results of the method call
		System.out.println("\n====>>> result is: "+result);
		
		// let's post process the data ... let's modify it :-)
		
//		result.forEach(account-> account.setName(account.getName().toUpperCase()));
		
		// convert account names to uppercase
		covertAccountNamesToUpperCase(result);
		
		System.out.println("\n====>>> result is: "+result);
	}

	private void covertAccountNamesToUpperCase(List<Account> result) {
		
		// loop throught he accounts
		for(Account account: result) {
			
			// get the uppercase version of the name
			String theUpperName = account.getName().toUpperCase();
			
			// update the name of the account
			account.setName(theUpperName);
		}
		
	}
	
	@AfterThrowing(
		pointcut = "execution(* com.shri.aopdemo.dao.AccountDAO.findAccounts(..))",
		throwing = "theExc"
	)
	public void afterThrowingFindAccountsAdvice(JoinPoint theJoinPoint, Throwable theExc) {
		
		// print out which method we are advising on
		String method = theJoinPoint.getSignature().toShortString();
		System.out.println("\n=====>>> Executing @AfterThrowing on method: "+method);
		
		// log the exception
		System.out.println("\n=====>>> The exception is: "+theExc);
	}
	
}
