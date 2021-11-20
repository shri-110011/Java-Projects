package parallelMethods;

import org.testng.annotations.Test;

public class ParallelMethodsExample {

	@Test
	public void testCase1() {
		System.out.println("Running test case one with Thread id :" +Thread.currentThread().getId());
	}
	
	@Test
	public void testCase2() {
		System.out.println("Running test case two with Thread id :" +Thread.currentThread().getId());
	}
	
	@Test
	public void testCase3() {
		System.out.println("Running test case three with Thread id :" +Thread.currentThread().getId());
	}
	
	@Test
	public void testCase4() {
		System.out.println("Running test case four with Thread id :" +Thread.currentThread().getId());
	}
	
}
