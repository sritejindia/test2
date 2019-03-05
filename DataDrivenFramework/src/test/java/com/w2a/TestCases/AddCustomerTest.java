package com.w2a.TestCases;

import org.junit.Assert;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Reporter;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.w2a.base.TestBase;

public class AddCustomerTest extends TestBase{
	
	@Test(dataProvider="getData")
	public void addCustomer(String firstName, String lastName, String postCode,String alertText) throws InterruptedException
	{
		driver.findElement(By.cssSelector(or.getProperty("addCustBtn"))).click();
		Thread.sleep(3000);
		
		driver.findElement(By.cssSelector(or.getProperty("firstName"))).sendKeys(firstName); 
		driver.findElement(By.cssSelector(or.getProperty("lastName"))).sendKeys(lastName); 
		driver.findElement(By.cssSelector(or.getProperty("postCode"))).sendKeys(postCode); 
		driver.findElement(By.cssSelector(or.getProperty("addBtn"))).click();
		 
		 Alert alert= wait.until(ExpectedConditions.alertIsPresent());
		 //alertText="Customer added successfully";
		 Assert.assertTrue(alert.getText().contains(alertText)); 
		 Thread.sleep(3000); 
		 alert.accept();
		 Reporter.log("Customer added successfully");
		 }
	@DataProvider
    public Object[][] getData()
    {
    	String sheetName ="AddCustomerTest";
    	int rows= excel.getRowCount(sheetName);//3
    	int cols= excel.getColumnCount(sheetName);//4
    	
    	Object[][] data= new Object[rows-1][cols];
    	
    	for (int rowNum=2;rowNum<=3;rowNum++)
    	{
    		for (int colNum=0;colNum < cols;colNum++)
    		{
    			data[rowNum-2][colNum]=excel.getCellData(sheetName,colNum,rowNum);
    		}
    	}
    	
		return data;
   }
}
