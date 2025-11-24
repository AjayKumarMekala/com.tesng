package com.testng;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.testClass.ITestListenerClass;
@Listeners(ITestListenerClass.class)
public class TestClass {
	@Test
	public void Test1() {
        System.out.println("I'm inside Test1 Method");
	}
	@Test
	public void Test2() {
		System.out.println("I'm inside Test1 Method");
		
	}
	@Test
	public void Test3() {
		System.out.println("I'm inside Test1 Method");
		Assert.assertTrue(false);
	}
	@Test(dependsOnMethods="Test3")
	public void Test4() {
		System.out.println("I'm inside Test1 Method");
	}
}
