package usefulResources;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Calendar;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.io.FileHandler;

public class base {

	public static WebDriver driver;
	public Properties prop;
	public String browserName;

	public static Logger log = LogManager.getLogger(base.class.getName());

	public WebDriver initialiseDriver() throws IOException {

		prop = new Properties();

		FileInputStream fis = new FileInputStream(
				System.getProperty("user.dir") + "\\src\\main\\java\\usefulResources\\data.properties");

		prop.load(fis);
		browserName = prop.getProperty("browser");

		// intialize chrome driver
		if (browserName.toLowerCase().contains("chrome")) {
			System.setProperty("webdriver.chrome.driver",
					System.getProperty("user.dir") + "\\src\\main\\java\\usefulResources\\chromedriver.exe");
			ChromeOptions options = new ChromeOptions();
			if (browserName.toLowerCase().contains("headless")) {
				options.addArguments("headless");
			}
			driver = new ChromeDriver(options);

			// initialize firefox driver
		} else if (browserName.equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.gecko.driver",
					System.getProperty("user.dir") + "\\src\\main\\java\\usefulResources\\geckodriver.exe");
			driver = new FirefoxDriver();

			// initialize internet explorer driver
		}
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();

		// implicit wait for 10 seconds
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		return driver;
	}

	Calendar now = Calendar.getInstance();

	// function to get screenshot when a test fails
	public void getScreenShots(String result) throws IOException {
		File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileHandler.copy(screenshot, new File(".\\Screenshots\\" + result + now.get(Calendar.HOUR_OF_DAY) + "_"
				+ now.get(Calendar.MINUTE) + "screenshot.png"));
	}
	
	

}
