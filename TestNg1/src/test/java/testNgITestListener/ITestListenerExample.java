package testNgITestListener;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class ITestListenerExample implements ITestListener {
	
	public void onStart(ITestContext context) {
		System.out.println("In onStart method of ITestListener, Test name is: "+context.getName());
	}

	public void onTestStart(ITestResult result) {
		System.out.println("In onTestStart method of ITestListener, Method name is: "+result.getMethod().getMethodName());
	}

	public void onTestSuccess(ITestResult result) {
		System.out.println("In onTestSuccess method of ITestListener, Method name is: "+result.getMethod().getMethodName());
	}
	
	public void onTestFailure(ITestResult result) {
		System.out.println("In onTestFailure method of ITestListener, Method name is: "+result.getMethod().getMethodName());
		
	}

	public void onTestSkipped(ITestResult result) {
		System.out.println("In onTestSkipped method of ITestListener, Method name is: "+result.getMethod().getMethodName());
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		System.out.println("In onTestFailedButWithinSuccessPercentage method of ITestListener, Method name is: "+result.getMethod().getMethodName());
	}

	public void onTestFailedWithTimeout(ITestResult result) {
		System.out.println("In onTestFailedWithTimeout method of ITestListener, Method name is: "+result.getMethod().getMethodName());
	}

	public void onFinish(ITestContext context) {
		System.out.println("In onFinish method of ITestListener, Test name is: "+context.getName());
	}

}
