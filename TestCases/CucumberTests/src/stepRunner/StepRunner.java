package stepRunner;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class StepRunner {
	
	WebDriver driver;
	@Given("^browser is launched proper and login is done$")
	public void browser_is_launched_proper_and_login_is_done() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		driver=new FirefoxDriver();
		driver.get("http://firsttest.pzqtn7hcgf.us-west-2.elasticbeanstalk.com/interface/login/login_frame.php?site=default");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
		String title=driver.getTitle();
		Assert.assertEquals(title, "Login");
		WebElement mainFrame=driver.findElement(By.xpath("//frameset/frame[3]"));

		driver.switchTo().defaultContent();
		driver.switchTo().frame(mainFrame);
		//System.out.println(driver.getPageSource());
		//WebElement testElement=driver.findElement(By.xpath("//table"));
		//System.out.println(testElement.getAttribute("width"));
		driver.findElement(By.xpath("//form[@name='login_form']/table//div[@class='login-box']//div[@class='table-right']/table//input[@name='authUser']")).sendKeys("admin");
		driver.findElement(By.xpath("//form[@name='login_form']/table//div[@class='login-box']//div[@class='table-right']/table//input[@name='clearPass']")).sendKeys("admin123");
		driver.findElement(By.xpath("//form[@name='login_form']/table//div[@class='login-box']//div[@class='table-right']/table//input[@value='Login']")).click();
	   
	}

	@Given("^We add patient$")
	public void we_add_patient() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		WebElement titleFrame=driver.findElement(By.xpath("//frameset/frame"));
		//System.out.println(driver.getPageSource());
		//driver.switchTo().defaultContent();

		driver.switchTo().frame(titleFrame);
		driver.findElement(By.xpath("//table//table//td/a[@class='css_button_small']")).click();
	  
	}

	@When("^Insurance Provider is present or we add Insurance provider$")
	public void insurance_Provider_is_present_or_we_add_Insurance_provider() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		driver.switchTo().defaultContent();
		String mainWindow=driver.getWindowHandle();
		//WebElement test =driver.findElement(By.xpath("//*[@id='fsbody']//frame[@name='RTop']"));
		//driver.switchTo().frame(test);
		//System.out.println(driver.getPageSource());
		driver.switchTo().frame(driver.findElement(By.xpath("//frameset/frameset[2]/frame[@name='RTop']")));
		Select select=new Select(driver.findElement(By.xpath("//form/table//td[2]//select")));
		select.selectByVisibleText("Mr.");
		driver.findElement(By.xpath("//form/table//td[2]//input[@name='form_fname']")).sendKeys("Sruthi");
		driver.findElement(By.xpath("//form/table//td[2]//input[@name='form_lname']")).sendKeys("Sreejith");
		//driver.findElement(By.xpath("//table//table//[td1]/input[@name='form_fname']")).sendKeys("Joey");
		select=new Select(driver.findElement(By.xpath(".//*[@id='form_sex']")));
		select.selectByVisibleText("Male");
		driver.findElement(By.xpath(".//*[@id='form_DOB']")).sendKeys("1985/05/05");
		driver.findElement(By.xpath(".//*[@id='form_cb_2']")).click();
		driver.findElement(By.xpath(".//*[@id='form_street']")).sendKeys("98th Street");
		driver.findElement(By.xpath(".//*[@id='form_city']")).sendKeys("Lexington");
		driver.findElement(By.xpath(".//*[@id='form_postal_code']")).sendKeys("40503");
		select=new Select(driver.findElement(By.xpath(".//*[@id='form_state']")));
		select.selectByVisibleText("Kentucky");
		select=new Select(driver.findElement(By.xpath(".//*[@id='form_country_code']")));
		select.selectByVisibleText("USA");
		WebElement element = null;
		try{
		element=driver.findElement(By.xpath("//form/table//input[@name='form_cb_ins']"));
		}
		catch(Exception e){
		}
		if(element.isDisplayed()){
			element.click();
			System.out.println("Inside Insurance Block");
			 select=new Select(driver.findElement(By.xpath("//form/table//div[@id='div_ins']/table//select[@name='i1provider']")));
			List<WebElement> selectOptions=select.getOptions();
			System.out.println(selectOptions.size());
			 if(selectOptions.size()==1){
				 System.out.println("Inside adding Insurer block");
				 driver.findElement(By.xpath("//form/table//div[@id='div_ins']/table//a[@class='iframe medium_modal']/span")).click();
				 //driver.switchTo().defaultContent();
				 WebElement fancyFrame=driver.findElement(By.xpath("//div[@id='fancy_outer']//iframe[@id='fancy_frame']"));
				 driver.switchTo().frame(fancyFrame);
				 System.out.println("Frame Switched");
				driver.findElement(By.xpath("//form//table//input[@name='form_name']")).sendKeys("Life Insurance Corporation of India");
				
					
					
				driver.findElement(By.xpath("//form//table//input[@name='form_city']")).sendKeys("Bangalore");
				driver.findElement(By.xpath("//form//table//input[@name='form_attn']")).sendKeys("LIC");
				driver.findElement(By.xpath("//form//table//input[@name='form_addr1']")).sendKeys("99th Street");
				driver.findElement(By.xpath("//form//table//input[@name='form_addr2']")).sendKeys("Straight Road");
				driver.findElement(By.xpath("//form//table//input[@name='form_state']")).sendKeys("India");
				
				driver.findElement(By.xpath("//form//table//input[@name='form_zip']")).sendKeys("40503");
				driver.findElement(By.xpath("//form//input[@name='form_save']")).click();
				System.out.println("Life Insurance Corporation of India is added");
				driver.switchTo().defaultContent();
				driver.switchTo().frame(driver.findElement(By.xpath("//frameset/frameset[2]/frame[@name='RTop']")));
				select.selectByVisibleText("Life Insurance Corporation of India");
			 }
			 else{
				 select.selectByIndex(1);
			 }
			 
			driver.findElement(By.xpath("//form/table//div[@id='div_ins']/table//table//input[@name='i1plan_name']")).sendKeys("PlanA");
			driver.findElement(By.xpath("//form/table//div[@id='div_ins']/table//table//input[@name='i1effective_date']")).sendKeys("2016/07/07");
			driver.findElement(By.xpath("//form/table//div[@id='div_ins']/table//table//input[@name='i1policy_number']")).sendKeys("ABC12345GFC");
			driver.findElement(By.xpath("//form/table//div[@id='div_ins']/table//input[@name='i1subscriber_fname']")).sendKeys("Sruthi");
			driver.findElement(By.xpath("//form/table//div[@id='div_ins']/table//input[@name='i1subscriber_lname']")).sendKeys("Sreejith");
			driver.findElement(By.xpath("//form/table//div[@id='div_ins']/table//input[@name='i1subscriber_DOB']")).sendKeys("1985/05/05");
			driver.findElement(By.xpath("//form/table//div[@id='div_ins']/table//table//input[@name='i1subscriber_employer_street']")).sendKeys("BackStreet");
		}
		driver.findElement(By.xpath("//form/table//td//center//input[@id='create']")).click();
		Set <String> windowhandles=driver.getWindowHandles();
		System.out.println(windowhandles.size());
		for(String handle:windowhandles){
			if(!handle.equals(mainWindow)){
				driver.switchTo().window(handle);
				//driver.manage().window().maximize();
				System.out.println("Window Switched");
			}
		}
		//driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		//System.out.println(driver.getPageSource());
		//WebDriverWait wait=new WebDriverWait(driver, 10);
		//wait.until(ExpectedConditions.presenceOfElementLocated((By.xpath("//center/input"))));
		driver.findElement(By.xpath("//center/input")).click();
		driver.switchTo().window(mainWindow);
		//alerPresent();
		try{
		WebDriverWait wait=new WebDriverWait(driver,30);
		wait.until(ExpectedConditions.alertIsPresent());
		if(alerPresent()){
			Alert alert=driver.switchTo().alert();
			alert.accept();
			}
		}
		catch (Exception e){
			e.printStackTrace();
		}
	  
	}

	@When("^We search patient$")
	public void we_search_patient() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		WebElement titleFrame=driver.findElement(By.xpath("//frameset/frame"));
		//System.out.println(driver.getPageSource());
		//driver.switchTo().defaultContent();

		driver.switchTo().frame(titleFrame);
		driver.findElement(By.xpath("//table//table//td/a[@class='css_button_small']")).click();
		driver.switchTo().defaultContent();
		/*if(alerPresent()){
			Alert alert=driver.switchTo().alert();
			alert.accept();
			}*/
		String mainWindow=driver.getWindowHandle();
		//WebElement test =driver.findElement(By.xpath("//*[@id='fsbody']//frame[@name='RTop']"));
		//driver.switchTo().frame(test);
		//System.out.println(driver.getPageSource());
		driver.switchTo().frame(driver.findElement(By.xpath("//frameset/frameset[2]/frame[@name='RTop']")));
		driver.findElement(By.xpath("//form/table//td//center//input[@id='search']")).click();
		Set <String> windowhandles=driver.getWindowHandles();
		System.out.println(windowhandles.size());
		for(String handle:windowhandles){
			if(!handle.equals(mainWindow)){
				driver.switchTo().window(handle);
				driver.manage().window().maximize();
				System.out.println("Window Switched");
			}
		}
		driver.switchTo().window(mainWindow);
	  
	}

	@When("^Patient is present in db$")
	public void patient_is_present_in_db() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	
	}

	@Then("^Scenario is completed$")
	public void scenario_is_completed() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	   
	}

	

	public boolean alerPresent(){
		try{
			driver.switchTo().alert();
			
			return true;
		}
		catch(NoAlertPresentException Ex){
			return false;
		}
	}
	
}
