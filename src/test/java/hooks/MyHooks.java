package hooks;

import java.io.File;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.chrome.ChromeDriver;

import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeStep;
import io.cucumber.java.Scenario;

public class MyHooks extends HooksNotExtendable {
	
	@Before
	public void beforeScenario(Scenario scenario) {
		System.out.println("Before Scenario");
		 driver=new ChromeDriver();
		   driver.manage().window().maximize();
		System.out.println(scenario.getName()+"@@@@");
	}
	
	@After
	public void AfterScenario(Scenario scenario) {
		boolean failed=scenario.isFailed();
		System.out.println(failed+"@@@@");
		if(failed) {
			byte[] src=driver.getScreenshotAs(OutputType.BYTES);
			scenario.attach(src, "image/png", "Failed Screenshot");
			
		}
		driver.quit();
	}
	
	@BeforeStep
	public void beforeStep() {
		
	}
	
	@AfterStep
	public void afterStep() {
		
	}

}
