package com.internetBanking.testCases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.internetBanking.pageObjects.LoginPage;

public class TC_LoginTest_001 extends BaseClass {

	@Test
	public void loginTest() throws IOException {

		// logger.info("URL is opened");

		LoginPage lp = new LoginPage(driver);
		lp.setUserName(username);
		// logger.info("Entered username");
		lp.setPassword(password);
		// logger.info("Entered password");
		lp.clickSubmit();

		if (driver.getTitle().equals("GTPL Bank Manager HomePage")) {
			Assert.assertTrue(true);
			// logger.info("Login Test passed");
		} else 
		{
			captureScreen(driver,"loginTest");
			Assert.assertTrue(false);
			// logger.info("Login Test Failed");
		}

	}

}
