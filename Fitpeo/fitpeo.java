package Build001;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class fitpeo {
	
WebDriver driver;
	
	@BeforeSuite
	public void SetupSuite()
	{
		
		System.out.println("setting up test suite");
	}
	
	
	@BeforeTest
	public void setup_browser()
	{
		//setting up chrome browser
		driver=new FirefoxDriver();
		
	}
	
	
	@BeforeClass
	public void validating_browser() 
	{
		//verifying whether the tab opened successfully or not
		if(driver !=null)
		{
			System.out.println("Browser opened succesfully");
		}
	}
	
	
	@Test(priority=1)
	public void homepage()
	{
		 // Setting implicit wait for 10 seconds
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		//maximizing the browser window
		driver.manage().window().maximize();
		
		//opening the url
		driver.get("https://fitpeo.com");
	}
	
	@Test(priority=2)
	public void navigatetorevenuecalculatorpage()
	{
		//navigating to revenue tab using click action
		driver.findElement(By.xpath("//div[text()='Revenue Calculator']")).click();
	}
	
	@Test(priority=3)
	public void scrollToSlider() 
	{
		//scrolling down the page by using pixel value
		
		 JavascriptExecutor js = (JavascriptExecutor) driver;
		 
		js.executeScript("window.scrollBy(0,500)");  
		
	}
	
	@Test(priority=4)
	public void adjustSliderTo820() 
	{
		//setting the window size to 1280*800 pixels to avoid out of bounds exception
		//because window size vary from one display to other
		 driver.manage().window().setSize(new Dimension(1280, 800));
		
		WebElement from = driver.findElement(By.xpath("//input[@data-index='0' and @value='200']"));
		
	        Actions action = new Actions(driver);
	        action.dragAndDropBy(from , 93,  0).perform();  
	       
	       
	      
	        System.out.println("Slider adjusted to 820.");
	}
	
	
	@Test(priority=5)
	public void slideto560() 
	{
		 //clearing the slider text field 
		driver.findElement(By.xpath("//input[@type='number']")).clear();
		
		//entering desired number in the slider text field
		driver.findElement(By.xpath("//input[@type='number']")).sendKeys("560");
		
		
	}
	
	 @Test(priority = 6)
	 public void validateslider() 
	 {
		 //validating the slider value using xpath as the value is changed or not
		 WebElement element = driver.findElement(By.xpath("//input[@data-index='0' and @value='560']"));
		 if(element.isDisplayed())
		 {
			 System.out.println("slider updated succesfully to 560");
		 }
		 else
		 {
			 System.out.println("slider updated failed");
		 }
	 }
	
	 @Test(priority = 7 )
	 public void selectCPTCodes() 
	 {
	   //clicking all the required check boxes
	   driver.findElement(By.xpath("(//input[@type='checkbox'])[1]")).click();
	   driver.findElement(By.xpath("(//input[@type='checkbox'])[2]")).click();
	   driver.findElement(By.xpath("(//input[@type='checkbox'])[3]")).click();
	   driver.findElement(By.xpath("(//input[@type='checkbox'])[8]")).click();
	   
	   
	  }
	 @Test(priority = 8)
	 public void validateTotalReimbursement() throws InterruptedException 
	 {
		 //re setting the slider value to 820 to get total reimbursement value to 110700
		 
		 driver.findElement(By.xpath("//input[@type='number']")).clear();
		driver.findElement(By.xpath("//input[@type='number']")).sendKeys("820");
		Thread.sleep(2000);
		
		//getting text from the reimbursement paragraph field
	   WebElement count=driver.findElement(By.xpath("(//p[@class='MuiTypography-root MuiTypography-body1 inter css-12bch19'])[3]"));
	   if(count.getText().equals("$110700"))
	   {
		   System.out.println("validation succesfull");
	   }
	   else
	   {
		   System.out.println("validation failed");
	   }
	 }


	
	@AfterClass
	public void pagecleanup()
	{
		//deleting all the cookies
		
		driver.manage().deleteAllCookies();
		System.out.println("All cookies deleted Succesfully");
	}
	
	@AfterTest
	public void closing_browser()
	{
		//validating whether the browser is closed or not
		
		if(driver !=null)
		{
			driver.quit();
			System.out.println("Browser closed succesfully");
		}
	}
	
	@AfterSuite
	public void ClosingSuite()
	{
		System.out.println("Closing test suite");
	}

}
