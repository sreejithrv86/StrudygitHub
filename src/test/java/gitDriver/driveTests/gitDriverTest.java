package gitDriver.driveTests;

import org.testng.annotations.Test;
import org.testng.annotations.Test;
import gitTestCases.gitBaseTest.baseTest;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;

public class gitDriverTest extends baseTest {
  WebDriver driver;
  
  @BeforeTest
  @Parameters({"browserName","browserVersion","applicationName","environmentName"})
  public void beforeTest(@Optional("firefox")String browserName, String browserVersion, String applicationName, String  environmentName) {
	  driver = baseTest.yourDriver(browserName, browserVersion, applicationName,environmentName);
	  
  }

  @AfterTest
  public void afterTest() {
	  driver.close();
      driver.quit();
  }
  
}
