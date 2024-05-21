package com.ecom.automation.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import com.ecom.automation.pageobjects.LandingPage;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	
	
	public WebDriver driver;
	public LandingPage page;
	public void intializeDriver() throws IOException
	{
		Properties prop= new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\com\\ecom\\automation\\resources\\Global.properties");
		prop.load(fis);
		String brwoserName=prop.getProperty("browser");
		if(brwoserName.equalsIgnoreCase("chrome"))
		{
		WebDriverManager.chromedriver().setup();
		driver= new ChromeDriver();
		
		}
		else if(brwoserName.equalsIgnoreCase("firefox"))
		{
			//firefox
		}
		else if(brwoserName.equalsIgnoreCase("edge"))
		{
			//edge
		}
		driver.manage().window().maximize();
		
	}	
		@BeforeMethod
		public LandingPage launchApplication() throws IOException
		{
			
			intializeDriver();
			page=new LandingPage(driver);
			page.Goto();
			return page;
		}
		
		@AfterMethod
		public void tearDown(ITestResult result) throws IOException
		{
			if(result.getStatus()==ITestResult.FAILURE)
			{
				getScreenShot(result.getName());
			}
			driver.close();
		}
		
		public List<HashMap<String, String>> getJsonData() throws IOException
		{
			String jsonContent=FileUtils.readFileToString(new File(System.getProperty("user.dir")+
				"\\src\\main\\java\\com\\ecom\\automation\\resources\\purchaseOrder.json"),StandardCharsets.UTF_8);
			
			ObjectMapper mapper=new ObjectMapper();
			List<HashMap<String, String>> data=mapper.readValue(jsonContent, new TypeReference
					<List<HashMap<String, String>>>() {
			});
			return data;
		}
		
		public String getScreenShot(String testcaseName) throws IOException
		{
			TakesScreenshot ts=(TakesScreenshot)driver;
			File source=ts.getScreenshotAs(OutputType.FILE);
			File file= new File(System.getProperty("user.dir")+"//ScreenShotreports//"+testcaseName+".png");
			FileUtils.copyFile(source,file);
			return System.getProperty("user.dir")+"//ScreenShotreports//"+testcaseName+".png";
		}
	

}
