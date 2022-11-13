package com.shri.aopdemo.aspect;

import java.util.List;
import java.util.logging.Logger;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
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
	
	private static Logger myLogger = Logger.getLogger(MyDemoLoggingAspect.class.getName());
	
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
		myLogger.info("\n===>>> Executing @Before advice on method");
		
		// display the method signature
		MethodSignature methodSig = (MethodSignature)theJoinPoint.getSignature();
		
		myLogger.info("Method: "+methodSig);
		
		
		// display the method arguments
		
		// get args
		Object args[] = theJoinPoint.getArgs();
		
		// loop through args
		for(Object tempArg: args) {
			myLogger.info(tempArg.toString());
			
			if(tempArg instanceof Account) {
				// downcast and print Account specific stuff
				Account theAccount = (Account)tempArg;
				myLogger.info("account name:"+theAccount.getName());
				myLogger.info("account level:"+theAccount.getLevel());
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
		myLogger.info("\n====>>> Executing @AfterReturing on method: "+method);
		
		// print out the results of the method call
		myLogger.info("\n====>>> result is: "+result);
		
		// let's post process the data ... let's modify it :-)
		
//		result.forEach(account-> account.setName(account.getName().toUpperCase()));
		
		// convert account names to uppercase
		covertAccountNamesToUpperCase(result);
		
		myLogger.info("\n====>>> result is: "+result);
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
		myLogger.info("\n=====>>> Executing @AfterThrowing on method: "+method);
		
		// log the exception
		myLogger.info("\n=====>>> The exception is: "+theExc);
		
	}
	
	/* Spring AOP follows the same precedence rules as AspectJ to determine the order of advice execution. 
	 * The highest precedence advice runs first "on the way in" (so, given two pieces of before advice, the 
	 * one with highest precedence runs first). "On the way out" from a join point, the highest precedence 
	 * advice runs last (so, given two pieces of after advice, the one with the highest precedence will run 
	 * second).
	 * 
	 * As of Spring Framework 5.2.7, advice methods defined in the same @Aspect class that need to run at the 
	 * same join point are assigned precedence based on their advice type in the following order, from 
	 * highest to lowest precedence: @Around, @Before, @After, @AfterReturning, @AfterThrowing. Note, 
	 * however, that an @After advice method will effectively be invoked after any @AfterReturning or 
	 * @AfterThrowing advice methods in the same aspect, following AspectJ�s "after finally advice" 
	 * semantics for @After.
	 * 
	 */
	
	@After("execution(* com.shri.aopdemo.dao.AccountDAO.findAccounts(..))")
	public void afterFinallyFindAccountsAdvice(JoinPoint theJoinPoint) {
		
		// print out which method we are advising on
		String method = theJoinPoint.getSignature().toShortString();
		myLogger.info("\n=====>>> Executing @After (finally) on method: "+method);
		
	}
	
	@Around("execution(* com.shri.aopdemo.service.*.getFortune(..))")
	/* theProceedingJoinPoint is just a handle to our target method.
	 * 
	 */
	public Object aroundGetFortune(ProceedingJoinPoint theProceedingJoinPoint) throws Throwable {
		
		// print out which method we are advising on
		String method = theProceedingJoinPoint.getSignature().toShortString();
		myLogger.info("\n=====>>> Executing @Around on method: "+method);
				
		// get the begin timestamp
		long begin = System.currentTimeMillis();
		
		// call the target object method
		Object result = null;
		
		try {
			result = theProceedingJoinPoint.proceed();
		} catch (Throwable e) {
			// log the exception
			myLogger.warning(e.getMessage());
			
//			// give the user a custom message
//			result = "Major accident! But no worries, your private AOP helicopter is on its way!";
			
			/* Here we just log the exception that occurred in the proceeding join point and re-throw it 
			 * to the calling program.
			 */
			throw e;
		}
		
		// get the end timestamp
		long end = System.currentTimeMillis();
		
		// compute the duration and display it
		long duration = end - begin;
		myLogger.info("\n=====>>> Duration: " + duration / 1000.0 + " seconds");
		
		return result;
		
	}
	
}
