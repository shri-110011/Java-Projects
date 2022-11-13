package com.shri.aopdemo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
/* Note the range of value for the @Order is -Integer.min to Integer.max and the aspect having the 
 * lowest value for @Order will have the highest priority.
 */
@Order(-1)
public class MyCloudLogAsyncAspect {
	
	@Before("com.shri.aopdemo.aspect.LuvAopExpressions.forDAOPackageNoGetterSetter()")
	public void logToCloudAsync() {
		System.out.println("\n===>>> Logging to cloud in async fashion");
	}

}
