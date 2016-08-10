package tests;

import java.util.List;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
//import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import setup.LaunchBrowser;

public class Test1 {

	public static WebDriver driver;
	public static Actions action;

	@Test()
	public void checkHeader(){
		driver=LaunchBrowser.returnDriver();
		String title=driver.getTitle();
		Assert.assertEquals(title, "Login");

	}

	@Test(dependsOnMethods={"checkHeader"})

	public void login(){

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

	@Test(dependsOnMethods={"login"})
	public void addPatient(){

		//System.out.println(driver);
		WebElement titleFrame=driver.findElement(By.xpath("//frameset/frame"));
		//System.out.println(driver.getPageSource());
		//driver.switchTo().defaultContent();

		driver.switchTo().frame(titleFrame);
		driver.findElement(By.xpath("//table//table//td/a[@class='css_button_small']")).click();
	}
	
	@Test(dependsOnMethods={"addPatient"}, groups={"add"})
	public void addInfo(){
		//WebElement test=driver.findElement(By.name("RTop"));
		
		driver.switchTo().defaultContent();
		String mainWindow=driver.getWindowHandle();
		//WebElement test =driver.findElement(By.xpath("//*[@id='fsbody']//frame[@name='RTop']"));
		//driver.switchTo().frame(test);
		//System.out.println(driver.getPageSource());
		driver.switchTo().frame(driver.findElement(By.xpath("//frameset/frameset[2]/frame[@name='RTop']")));
		Select select=new Select(driver.findElement(By.xpath("//form/table//td[2]//select")));
		select.selectByVisibleText("Mr.");
		driver.findElement(By.xpath("//form/table//td[2]//input[@name='form_fname']")).sendKeys("John");
		driver.findElement(By.xpath("//form/table//td[2]//input[@name='form_lname']")).sendKeys("Smith");
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
				driver.findElement(By.xpath("//form//table//input[@name='form_name']")).sendKeys("ABC Insurance");
				
					
					
				driver.findElement(By.xpath("//form//table//input[@name='form_city']")).sendKeys("Kentucky");
				driver.findElement(By.xpath("//form//table//input[@name='form_attn']")).sendKeys("ABC");
				driver.findElement(By.xpath("//form//table//input[@name='form_addr1']")).sendKeys("99th Street");
				driver.findElement(By.xpath("//form//table//input[@name='form_addr2']")).sendKeys("Straight Road");
				driver.findElement(By.xpath("//form//table//input[@name='form_state']")).sendKeys("UK");
				
				driver.findElement(By.xpath("//form//table//input[@name='form_zip']")).sendKeys("40503");
				driver.findElement(By.xpath("//form//input[@name='form_save']")).click();
				System.out.println("ABC Insurance is added");
				driver.switchTo().defaultContent();
				driver.switchTo().frame(driver.findElement(By.xpath("//frameset/frameset[2]/frame[@name='RTop']")));
				select.selectByVisibleText("ABC Insurance");
			 }
			 else{
				 select.selectByIndex(1);
			 }
			 
			driver.findElement(By.xpath("//form/table//div[@id='div_ins']/table//table//input[@name='i1plan_name']")).sendKeys("PlanA");
			driver.findElement(By.xpath("//form/table//div[@id='div_ins']/table//table//input[@name='i1effective_date']")).sendKeys("2016/07/07");
			driver.findElement(By.xpath("//form/table//div[@id='div_ins']/table//table//input[@name='i1policy_number']")).sendKeys("ABC12345GFC");
			driver.findElement(By.xpath("//form/table//div[@id='div_ins']/table//input[@name='i1subscriber_fname']")).sendKeys("John");
			driver.findElement(By.xpath("//form/table//div[@id='div_ins']/table//input[@name='i1subscriber_lname']")).sendKeys("Smith");
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
		//confirmDialog(driver);
		if(alerPresent()){
			Alert alert=driver.switchTo().alert();
			alert.accept();
			}
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}
	
	@Test(dependsOnMethods={"addPatient","addInfo"}, groups={"search"})
	public void searchPatient(){
		
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
	@Test(dependsOnMethods={"addPatient","addInfo","searchPatient"}, groups={"negative"})
	public void negativeTest(){
		driver.switchTo().frame(driver.findElement(By.xpath("//frameset/frameset[2]/frame[@name='RTop']")));
		driver.findElement(By.xpath("//form/table//input[@name='form_cb_ins']")).click();
		Select select=new Select(driver.findElement(By.xpath("//form/table//div[@id='div_ins']/table//select[@name='i1provider']")));
		try{
		select.selectByVisibleText("XYZ Insurer");
	}
	catch(Exception e){
		//e.printStackTrace();
	}
		//SoftAssert asert=new SoftAssert();
		//asert.fail("Test case is failed because it is trying to add Insurer XYZ which is not present");
		Assert.fail("Test case is failed because it is trying to add Insurer XYZ which is not present");
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
	/*static void confirmDialog(WebDriver driver) {
	    if (driver instanceof PhantomJSDriver) {
	        PhantomJSDriver phantom = (PhantomJSDriver) driver;
	        phantom.executeScript("window.alert = function(){}");
	        phantom.executeScript("window.confirm = function(){return true;}");
	    } else driver.switchTo().alert().accept();
	}*/
}
