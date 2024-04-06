package com.internetBanking.testCases;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

//import java.util.logging.Logger;

//import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import com.internetBanking.utilities.ReadConfig;

public class BaseClass {

	public static WebDriver driver;
	// public static Logger logger;

	ReadConfig readconfig = new ReadConfig(); // to read the config values

	public String baseURL = readconfig.getApplicationURL();
	public String username = readconfig.getUserName();
	public String password = readconfig.getPassword();

	@Parameters("browser") // this is coming from Testng.xml parameter
	@BeforeClass
	public void setup(String br) {
		// logger=Logger.getLogger("ebanking");
		// PropertyConfigurator.configure("Log4j.properties");
		if (br.equals("chrome")) {
			driver = new ChromeDriver();
		} else if (br.equals("firefox")) {
			driver = new FirefoxDriver();
		} else {
			driver = new EdgeDriver();
		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get(baseURL);
		driver.manage().window().maximize();

	}

	@AfterClass
	public void tearDown() {
		driver.quit();
	}

	public void captureScreen(WebDriver driver, String tname) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File target = new File(System.getProperty("user.dir") + "/Screenshots/" + tname + ".png");
		FileUtils.copyFile(source, target);
		System.out.println("Screenshot taken");

	}
	
	public static String randomeString()
	{
		String generatedstring=RandomStringUtils.randomAlphabetic(5);
		return generatedstring;
	}
	
	public static String randomeNum()
	{
		String generatedstring2=RandomStringUtils.randomAlphanumeric(5);
		return generatedstring2;
	}

}
