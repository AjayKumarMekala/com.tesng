package com.testClass;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(ITestListenerClass.class)
public class TestClass extends BaseTest {
		
	@Test(testName="TestGoogle",groups="Sanity")
	public void TestGoogle() {
		
		driver.get("https://www.google.com/");
		extenttest.info("Navigated to URL");
		driver.findElement(By.id("APjFqb")).sendKeys("Tataconsultancyservices",Keys.ENTER);
		extenttest.info("Entered text");
		
	}
	@Test(testName="TestOrangeHRM",groups={"Sanity","Regression"})
	public void TestOrangeHRM() throws InterruptedException {
		
		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
		extenttest.info("Navigated to URL");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		driver.findElement(By.name("username")).sendKeys("Admin");
		driver.findElement(By.name("password")).sendKeys("admin123");
		extenttest.info("Entered username and Password");
		driver.findElement(By.xpath("//button[text()=' Login ']")).click();
		//driver.switchTo().alert().accept();
	}
	@Test(testName="TestHYRTutorials",groups={"Smoke","Regression"})
   public void TestHYRTutorials() {
		
		driver.get("https://www.hyrtutorials.com/");
		extenttest.info("Navigated to URL");
		Actions ACT=new Actions(driver);
		ACT.moveToElement(driver.findElement(By.xpath("//a[text()='Selenium Practice']"))).perform();
		ACT.moveToElement(driver.findElement(By.xpath("//a[text()='XPath Practice']"))).click().perform();
		extenttest.info("Performed Actions");
		
	}
	

}
