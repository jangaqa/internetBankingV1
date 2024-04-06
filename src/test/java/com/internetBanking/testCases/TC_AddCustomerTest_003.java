package com.internetBanking.testCases;

import java.io.IOException;

import org.apache.commons.lang3.RandomStringUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.internetBanking.pageObjects.AddCustomerPage;
import com.internetBanking.pageObjects.LoginPage;

public class TC_AddCustomerTest_003 extends BaseClass 
{
	
	@Test
	public void AddNewCustomer() throws InterruptedException, IOException
	{
		LoginPage lp=new LoginPage(driver);
		lp.setUserName(username);
		lp.setPassword(password);
		lp.clickSubmit();
		
		Thread.sleep(3000);
		
		AddCustomerPage addcust=new AddCustomerPage(driver);
		
		addcust.clickAddNewCustomer();
		
		addcust.custName("Ayaan");
		addcust.custgender("male");
		addcust.custdob("10", "15", "1986");
		Thread.sleep(3000);
		addcust.custaddress("609 8th st SW");
		addcust.custcity("Calgary");
		addcust.custstate("Alberta");
		addcust.custpinno("508284");
		addcust.custtelephoneno("3688877833");
		
		//need to pass unique email-generate random email
		addcust.custemailid(BaseClass.randomeString()+"@gmail.com");
		
		//addcust.custpassword("hsdhsdDS");
		addcust.custsubmit();
		
		Thread.sleep(3000);
		
		boolean res = driver.getPageSource().contains("Access denied for user 'root'@'localhost'");
		
		if(res==true)
		{
			Assert.assertTrue(true);
		}
		else
		{
			captureScreen(driver, "AddNewCustomer");
			Assert.assertTrue(false);
		}
		
		
	}
	


}
