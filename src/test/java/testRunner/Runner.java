package testRunner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
		features = {"src/test/java/features"},
		glue= {"steps","hooks"},
		monochrome=false,
		//tags = "@smoke or @sanity",
        //plugin={"pretty","html:CucumberReports/reports.html"}
		plugin= "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:" 
        
		)
public class Runner extends AbstractTestNGCucumberTests {

}
