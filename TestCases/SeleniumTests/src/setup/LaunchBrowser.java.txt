package setup;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
//import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

public class LaunchBrowser {
	//public static HtmlUnitDriver driver;
	public static WebDriver driver;
	@BeforeSuite
	@Parameters({"url"})
	public static void launchBrowser(String url){
		

		/*String Xport = System.getProperty(
                "lmportal.xvfb.id", ":1");
        final File firefoxPath = new File(System.getProperty(
                "lmportal.deploy.firefox.path", "/usr/local/firefox/"));
        FirefoxBinary firefoxBinary = new FirefoxBinary(firefoxPath);
        firefoxBinary.setEnvironmentProperty("DISPLAY", Xport);

        // Start Firefox driver
       driver = new FirefoxDriver(firefoxBinary, null);*/
		driver=new FirefoxDriver();

		
//driver=new PhantomJSDriver();
		
		
		
		//System.setProperty("phantomjs.binary.path", "chromedriver\\phantomjs.exe");
		//DesiredCapabilities cap=new DesiredCapabilities();
		//((DesiredCapabilities) cap).setJavascriptEnabled(true);
		//((DesiredCapabilities) cap).setCapability("handlesAlerts",true);
		//driver=new PhantomJSDriver();
		//driver=new HtmlUnitDriver();
		 //((HtmlUnitDriver)driver).setJavascriptEnabled(true);
		/*if(browser.equalsIgnoreCase("chrome")){
		System.setProperty("webdriver.chrome.driver", "chromedriver\\chromedriver.exe");
		driver=new ChromeDriver();
		}
		else if(browser.equalsIgnoreCase("firefox")){
			driver=new FirefoxDriver();
		}
		else{
			System.out.println("browser should be either Firefox or Chrome");
		}*/
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.get(url);
	}
		

	public static WebDriver returnDriver(){
		return driver;
	}
	

}
