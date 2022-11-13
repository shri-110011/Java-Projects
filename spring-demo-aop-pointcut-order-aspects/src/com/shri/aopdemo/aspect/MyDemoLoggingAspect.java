package com.shri.aopdemo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
/* Note the range of value for the @Order is -Integer.min to Integer.max and the aspect having the 
 * lowest value for @Order will have the highest priority.
 */
@Order(2)
@Aspect
public class MyDemoLoggingAspect {
	
	@Before("com.shri.aopdemo.aspect.LuvAopExpressions.forDAOPackageNoGetterSetter()")
	public void addAccount() {
		System.out.println("\n===>>> Executing @Before advice on method");
	}
	
}
