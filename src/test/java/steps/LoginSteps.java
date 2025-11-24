package steps;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import hooks.HooksNotExtendable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LoginSteps extends HooksNotExtendable {


	
	@Given("Launch the application")
	public void launchTheApplication() {
	  
	   driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
	    System.out.println("Launched successfully");
	}
	@And("Enter {string} and {string}")
	public void enterUserameAndPassword(String userName,String Password) throws InterruptedException {
		Thread.sleep(3000);
		driver.findElement(By.name("username")).sendKeys(userName);
		driver.findElement(By.name("password")).sendKeys(Password);
		System.out.println("Entered username and password successfully");
	}
	@When("User click on login button")
	public void userClickkOnLoginButton() {
		driver.findElement(By.xpath("//button[text()=' Login ']")).click();
		System.out.println("clicked Submit button successfully");
	    
	}
	
	@Then("Login should be successfull")
	public void loginShouldBeSuccessfull() {
		driver.quit();
		System.out.println("Login Should Be Successfull");
	    
	}
	
	@Then("Login should not be successfull")
	public void loginShouldNotBeSuccessfull() throws InterruptedException {
		Thread.sleep(3000);
		String str=driver.findElement(By.xpath("//div[contains(@class,'oxd-alert-content ')]//p[contains(@class,'oxd-text')]")).getText();
		Assert.assertEquals(str, "Invalid credential");
		
		System.out.println("Login Should not be Successfull");
	}
}
