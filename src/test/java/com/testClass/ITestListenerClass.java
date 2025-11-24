package com.testClass;

import java.io.IOException;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;



public class ITestListenerClass implements ITestListener {

	@Override
	public void onTestStart(ITestResult result) {
	System.out.println("onTestStart");	
	System.out.println(result.getMethod().getMethodName());
	System.out.println(result.getStatus());
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		System.out.println("onTestSuccess");
		System.out.println(result.getMethod().getMethodName());
		System.out.println(result.getStatus());
	}

	@Override
	public void onTestFailure(ITestResult result) {
		System.out.println("onTestFailure");
		System.out.println(result.getMethod().getMethodName());
		System.out.println(result.getStatus());
		try {
			BaseTest.screenShot(result.getTestContext().getName()+"_"+result.getMethod().getMethodName()+".png");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		System.out.println("onTestSkipped");
		System.out.println(result.getMethod().getMethodName());
		System.out.println(result.getStatus());
	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
		System.out.println("onTestFailedWithTimeout");	
		System.out.println(result.getMethod().getMethodName());
		System.out.println(result.getStatus());
	}

	@Override
	public void onStart(ITestContext context) {
		System.out.println("onStart");	
		context.getAllTestMethods().getClass();
		context.getPassedTests().getAllResults().size();
	}

	@Override
	public void onFinish(ITestContext context) {
		System.out.println("onFinish");	
	}
	

}
