package gitTestCases.gitBaseTest;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.github.bonigarcia.wdm.WebDriverManager;


public class baseTest {
	public static WebDriver driver;
	public static Properties properties;
	public static String propertyFilePath="";
	
	public static WebDriver yourDriver(String browserType, String BrowserVersion, String applicationName, String environmentName){
		try{
		switch(browserType){
		
		case "firefox":
			WebDriverManager.firefoxdriver().version(BrowserVersion).setup();
			FirefoxOptions optionz = new FirefoxOptions();
			optionz.addArguments("start-maximized"); 
			optionz.addArguments("enable-automation"); 
			optionz.addArguments("--no-sandbox"); 
			optionz.addArguments("--disable-infobars");
			optionz.addArguments("--disable-dev-shm-usage");
			optionz.addArguments("--disable-browser-side-navigation"); 
			optionz.addArguments("--disable-gpu"); 
			optionz.addArguments("--ignore-certificate-errors");
			optionz.addArguments("--disable-popup-blocking");
			optionz.addArguments("--disable-default-apps");
			optionz.addArguments("--incognito");
			optionz.setAcceptInsecureCerts(true);
            String[] switchez = {"--ignore-certificate-errors"};
            DesiredCapabilities capabilitiez = DesiredCapabilities.firefox();
            capabilitiez.setCapability("firefox.switchez", Arrays.asList(switchez));
            capabilitiez.setCapability (CapabilityType.ACCEPT_SSL_CERTS, true);
            capabilitiez.setCapability(FirefoxOptions.FIREFOX_OPTIONS, optionz);
            driver = new FirefoxDriver(capabilitiez);
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            String URIF = propFileReader(applicationName, environmentName).getProperty("url");
            driver.get(URIF);
            
			break;
		case "chrome":
			WebDriverManager.chromedriver().version(BrowserVersion).setup();
			ChromeOptions options = new ChromeOptions();
			options.addArguments("start-maximized"); 
			options.addArguments("enable-automation"); 
			options.addArguments("--no-sandbox"); 
			options.addArguments("--disable-infobars");
			options.addArguments("--disable-dev-shm-usage");
			options.addArguments("--disable-browser-side-navigation"); 
			options.addArguments("--disable-gpu"); 
			options.addArguments("--ignore-certificate-errors");
            options.addArguments("--disable-popup-blocking");
            options.addArguments("--disable-default-apps");
            options.addArguments("--incognito");
            options.setAcceptInsecureCerts(true);
            String[] switches = {"--ignore-certificate-errors"};
            DesiredCapabilities capabilities = DesiredCapabilities.chrome();
            capabilities.setCapability("chrome.switches", Arrays.asList(switches));
            capabilities.setCapability (CapabilityType.ACCEPT_SSL_CERTS, true);
            capabilities.setCapability(ChromeOptions.CAPABILITY, options);
            driver = new ChromeDriver(capabilities);
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            String URI = propFileReader(applicationName, environmentName).getProperty("url");
            driver.get(URI);
            
			break;
		

		}}

		catch (Exception ex){
			ex.printStackTrace();
			driver.close();
            driver.quit();
            return null;
        }
		

		return driver;
		
	}
	
	public static Properties propFileReader(String applicationName, String environmentName){
		 BufferedReader reader;
		 try {
		

		 if(applicationName.equalsIgnoreCase("phptravels")&&environmentName.equalsIgnoreCase("prod")){
			 propertyFilePath = "config//phpTravels//prodtest.properties";	 
		 }
		 if((applicationName.equalsIgnoreCase("phptravels")&&environmentName.equalsIgnoreCase("uat"))){
			 propertyFilePath = "config//phpTravels//uattest.properties"; 
		 }
		 reader = new BufferedReader(new FileReader(propertyFilePath));
		 properties = new Properties();
	
		 properties.load(reader);
		 reader.close();
		 } catch (Exception e) {
		 e.printStackTrace();
		 return null;
		 }
		return properties;
		
	}
	
	
 
	
}
