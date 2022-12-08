package Selenium.TricentisObstcaleCourse;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {

	public WebDriver driver;
	public WebDriverWait eWait;
	String url = "https://obstaclecourse.tricentis.com/Obstacles/";
	
	public void goToObstacleURL(String obstacleNumber)
	{
		driver.get(url + obstacleNumber);
	}
	
	public WebElement successMessage()
	{
		return driver.findElement(By.tagName("h2"));
	}
	
	public WebElement waitForElementToAppear(By locBy)
	{
		
		eWait.until(ExpectedConditions.visibilityOfElementLocated(locBy));
		return driver.findElement(locBy);
	}
	
	public WebElement waitForElementToBeClickable(By locBy)
	{
		eWait.until(ExpectedConditions.elementToBeClickable(locBy));
		return driver.findElement(locBy);
	}

	@BeforeMethod(alwaysRun=true)
	public WebDriver initializeDriver() throws IOException
	{

		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\tricentisObstacleCourse\\resources\\GlobalData.properties");
		prop.load(fis);
		
		//Maven/Jenkins Integration
		String browserName = System.getProperty("browser")!=null ? System.getProperty("browser") : prop.getProperty("browser");
		
		if(browserName.contains("chrome"))
		{
			
			ChromeOptions options = new ChromeOptions();
			
			if(browserName.contains("headless"))
			{
			options.addArguments("headless");
			}
			
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver(options);
			driver.manage().window().setSize(new Dimension(1440,900));
		}
		else if(browserName.contains("firefox"))
		{
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		}
		else if(browserName.contains("edge"))
		{
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		}

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		
		eWait = new WebDriverWait(driver, Duration.ofSeconds(30));

		return driver;
	}
	
	@AfterMethod(alwaysRun=true)
	public void tearDown()
	{
		waitForElementToAppear(By.tagName("h2"));
		Assert.assertTrue(successMessage().isDisplayed());
		driver.close();
	}

}
