package com.internetBanking.testCases;

import java.io.IOException;

import org.openqa.selenium.NoAlertPresentException;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.internetBanking.pageObjects.LoginPage;
import com.internetBanking.utilities.XLUtils;

public class TC_LoginDDT_002 extends BaseClass 
{
	@Test(dataProvider="LoginData")
	public void loginDDT(String user, String pwd)
	{
		LoginPage lp=new LoginPage(driver);
		lp.setUserName(user);
		lp.setPassword(pwd);
		lp.clickSubmit();
		
		if(isAlertPresent()==true)
		{
			driver.switchTo().alert().accept();
			driver.switchTo().defaultContent();
			Assert.assertTrue(false);
		}
		else
		{
			Assert.assertTrue(true);
			lp.clickLogout();
			driver.switchTo().alert().accept(); //close logout alert
			driver.switchTo().defaultContent();
		}
		
		
	}
	
	public boolean isAlertPresent()  //user defind method created to check alert present or not
	{
		try 
		{
		driver.switchTo().alert();
		return true;
		}
		catch(NoAlertPresentException e)
		{
			return false;
		}
		
	}
	
	@DataProvider(name="LoginData")
	String [][]getData() throws IOException
	{
		String path=System.getProperty("user.dir")+"/src/test/java/com/internetBanking/testData/LoginData.xlsx";
		
		int rownum=XLUtils.getRowCount(path, "Sheet1");
		int colcount=XLUtils.getCellCount(path, "Sheet1", 1);
		
		String logindata[][]=new String[rownum][colcount];
		
		for(int i=1;i<=rownum;i++)
		{
			for(int j=0;j<colcount;j++)
			{
				logindata[i-1][j]=XLUtils.getCellData(path, "Sheet1", i, j); //1 0
				
			}
		}
	return logindata;
	}

}
