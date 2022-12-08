package Selenium.TricentisObstcaleCourse;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

public class EasyTest extends BaseTest{

	@Test
	public void idsAreNotEverything()
	{
		goToObstacleURL("22505");
		waitForElementToAppear(By.xpath("//a[@onclick='obstacleCompleted();']"));
		driver.findElement(By.xpath("//a[@onclick='obstacleCompleted();']")).click();
	}
	
	@Test
	public void twins()
	{
		goToObstacleURL("12952");
		waitForElementToAppear(By.xpath("//a[@onclick='obstacleCompleted();']"));
		driver.findElement(By.xpath("//a[@onclick='obstacleCompleted();']")).click();
	}
	
	@Test
	public void popupWindows()
	{
		goToObstacleURL("51130");
		driver.findElement(By.id("button")).click();
		
		Set<String> winHandles = driver.getWindowHandles();
		Iterator<String> it = winHandles.iterator();
		String parentWinHandle = it.next();
		String childWinHandle = it.next();
		
		driver.switchTo().window(childWinHandle).close();
		driver.switchTo().window(parentWinHandle);
	}
	
	@Test
	public void waitAMoment()
	{
		goToObstacleURL("33678");
		driver.findElement(By.id("one")).click();
		waitForElementToBeClickable(By.id("two"));
		driver.findElement(By.id("two")).click();
	}
	
	@Test
	public void extractingText()
	{
		goToObstacleURL("81012");
		String ammount = driver.findElement(By.id("alerttext")).getText().split(" ")[8].substring(1);
		driver.findElement(By.id("totalamountText")).sendKeys(ammount);
	}
	
	@Test
	public void tomorrow()
	{
		goToObstacleURL("19875");
		Date dt = new Date();
		Calendar c = Calendar.getInstance(); 
		c.setTime(dt); 
		c.add(Calendar.DATE, 1);
		dt = c.getTime();
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
		String tomorrow =sdf.format(dt);
		
		driver.findElement(By.id("datefield")).sendKeys(tomorrow);
		
	}
	
	@Test
	public void meethingScheduler()
	{
		goToObstacleURL("41037");
		String status = driver.findElement(By.xpath("//tr[3] //td[5]")).getText();
		driver.findElement(By.id("resulttext")).sendKeys(status);
	}
	
	@Test
	public void escape()
	{
		goToObstacleURL("41041");
		String value = driver.findElement(By.id("lblAmount")).getText().split(" ")[1];
		driver.findElement(By.id("resulttext")).sendKeys(value);
	}
	
	@Test
	public void findAndFill() throws InterruptedException
	{
		goToObstacleURL("73590");
		driver.findElement(By.id("pass")).click();
		driver.findElement(By.id("actual")).sendKeys("ABC");
		driver.findElement(By.id("sample")).click();
	}
	
	@Test
	public void redStripe()
	{
		goToObstacleURL("30034");
		driver.findElement(By.id("generate")).click();
		waitForElementToAppear(By.xpath("//div[@id='obstacle']/div[2]")).click();
	}
	
	@Test
	public void theObvious()
	{
		goToObstacleURL("73588");
		driver.findElement(By.id("clickme")).click();
		String text = driver.findElement(By.id("randomtext")).getAttribute("value");
		
		Select dropDown = new Select(driver.findElement(By.id("selectlink")));
		dropDown.selectByVisibleText(text);
		driver.findElement(By.id("submitanswer")).click();
	}
	
	@Test
	public void futureChristmas()
	{
		goToObstacleURL("21269");
		Date dt = new Date();
		Calendar c = Calendar.getInstance();
		c.setTime(dt); 
		c.set(Calendar.DAY_OF_MONTH, 25);
		c.set(Calendar.MONTH, 11);
		c.add(Calendar.YEAR, 2);
		dt = c.getTime();
		
		SimpleDateFormat sdf = new SimpleDateFormat("EEEE");
		String day =sdf.format(dt);

		driver.findElement(By.id("christmasday")).sendKeys(day);
	}
	
	@Test
	public void hiddenElement()
	{
		goToObstacleURL("66666");
		driver.findElement(By.id("clickthis")).click();
	
		
	}
}
