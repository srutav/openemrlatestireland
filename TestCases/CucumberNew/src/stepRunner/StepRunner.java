package stepRunner;


import java.io.BufferedReader;
import java.io.FileReader;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;



public class StepRunner {
	WebDriver driver;
	@Given("^browser is launched proper and login page is loaded$")
	public void browser_is_launched_proper_and_login_page_is_loaded() throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		//throw new PendingException();
		BufferedReader br=new BufferedReader(new FileReader("/tmp/elb-dns.txt"));
		String url=br.readLine();
		driver=new FirefoxDriver();
		driver.get(url);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
	}

	@When("^We add wrong credentials$")
	public void we_add_wrong_credentials() throws Throwable {
		
		WebElement mainFrame=driver.findElement(By.xpath("//frameset/frame[3]"));

		driver.switchTo().defaultContent();
		driver.switchTo().frame(mainFrame);
		driver.findElement(By.xpath("//form[@name='login_form']/table//div[@class='login-box']//div[@class='table-right']/table//input[@name='authUser']")).sendKeys("admin");
		driver.findElement(By.xpath("//form[@name='login_form']/table//div[@class='login-box']//div[@class='table-right']/table//input[@name='clearPass']")).sendKeys("admin1234");
		driver.findElement(By.xpath("//form[@name='login_form']/table//div[@class='login-box']//div[@class='table-right']/table//input[@value='Login']")).click();
		// Write code here that turns the phrase above into concrete actions
		//throw new PendingException();
	}

	@Then("^We get error$")
	public void we_get_error() throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		//throw new PendingException();
		String title=driver.getTitle();
		if(title.equals("Login")){
			
			System.out.println("We have entered wrong credentials");
			assert.fail("Build failed:Wrong credentails");
		}
	}
}
