package com.testClass;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;


import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;




public class BaseTest {

	public static WebDriver driver;
	public static String screenshotfoldername;
	public static ExtentReports extentreports;
	public static ExtentTest extenttest;
	

	@Parameters("browserName")
	@BeforeTest
	public void setup(ITestContext context,@Optional("chrome") String browserName) {

		switch(browserName.toLowerCase()){
		case "chrome":
			driver=new ChromeDriver();
			break;
		case "edge":
			driver=new EdgeDriver();
			break;
		case "firefox":
			driver=new FirefoxDriver();
			break;
		default:
			System.out.println("Invalid Browser name");
			break;
		}
		driver.manage().window().maximize();
		
		Capabilities cap=((RemoteWebDriver)driver).getCapabilities();
		String Device=cap.getBrowserName()+"_"+cap.getBrowserVersion().substring(0,cap.getBrowserVersion().indexOf("."));
		String Author=context.getCurrentXmlTest().getParameter("Author");
        extenttest=extentreports.createTest(context.getName());
        extenttest.assignAuthor(Author);
        extenttest.assignDevice(Device);
	}

	@AfterMethod
	public static void screenshot(Method m,ITestResult result) throws IOException, InterruptedException {
		
		if(result.getStatus()==ITestResult.FAILURE) {
			String screenshotpath=null;
			screenshotpath=screenShot(result.getTestContext().getName()+"_"+result.getMethod().getMethodName()+".png");
			extenttest.addScreenCaptureFromPath(screenshotpath);
			extenttest.fail(result.getThrowable());
		}
		else if(result.getStatus()==ITestResult.SUCCESS){
			extenttest.pass(m.getName()+" is Passed");
		}
		

		extenttest.assignCategory(m.getAnnotation(Test.class).groups());
	}
	public static String screenShot(String fileName) throws IOException  {
		if(screenshotfoldername==null) {
			LocalDateTime myDateObj = LocalDateTime.now();
			DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("ddMMyyyy_HHmmss");
            screenshotfoldername = myDateObj.format(myFormatObj);
         }
		TakesScreenshot ts=(TakesScreenshot)driver;
		File src=  ts.getScreenshotAs(OutputType.FILE);
	    String path =  "./Screenshot/" 
                + screenshotfoldername + "/" + fileName;

	    File dest = new File(path);
	    FileUtils.copyFile(src, dest);
		return path;
		
	}

	@BeforeSuite
	public void initializeReport() {
		
        extentreports=new ExtentReports();
        
        ExtentSparkReporter extentsparkreport=new ExtentSparkReporter("Allreports.html");
        extentsparkreport.config().setJs("document.getElementsByClassName('logo')[0].style.display='none';");;
        
        ExtentSparkReporter sparkrepoter_Failed=new ExtentSparkReporter("Failed.html");
		sparkrepoter_Failed.filter().statusFilter().as(new Status[] {Status.FAIL}).apply();
		sparkrepoter_Failed.config().setReportName("Failed Report");
		sparkrepoter_Failed.config().setJs("document.getElementsByClassName('logo')[0].style.display='none';");
		
        extentreports.attachReporter(extentsparkreport,sparkrepoter_Failed);
        extentreports.setSystemInfo("OS", System.getProperty("os.name"));
        extentreports.setSystemInfo("Java Version", System.getProperty("java.version"));
	}

	@AfterSuite
	public void flushReport() throws IOException {
     extentreports.flush();
     Desktop.getDesktop().browse(new File("Allreports.html").toURI());
     Desktop.getDesktop().browse(new File("Failed.html").toURI());
	}


	@AfterTest
	public void tearDown() {
		driver.quit();
	}

}
