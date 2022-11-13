package com.shri.aopdemo.aspect;

import org.aspectj.lang.JoinPoint;
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
	
}
