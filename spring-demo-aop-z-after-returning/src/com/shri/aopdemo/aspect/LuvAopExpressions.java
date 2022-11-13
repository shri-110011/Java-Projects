package com.shri.aopdemo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class LuvAopExpressions {
	
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
	
	// create pointcut for getter methods
	@Pointcut("execution(* com.shri.aopdemo.dao.*.get*())")
	public void getter() {}
	
	// create pointcut for setter methods
	@Pointcut("execution(* com.shri.aopdemo.dao.*.set*(*))")
	public void setter() {}
	
	// create pointcut: include package 'com.shri.aopdemo.dao' and exclude getter and setter methods
	@Pointcut("forDAOPackage() && !(getter() || setter())")
	public void forDAOPackageNoGetterSetter() {}

}
