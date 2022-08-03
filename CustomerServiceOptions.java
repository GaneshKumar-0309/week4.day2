package week4.day2;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.sukgu.Shadow;

public class CustomerServiceOptions {

	public static void main(String[] args) throws InterruptedException {

		WebDriverManager.chromedriver().setup();

		//launching chrome

		ChromeDriver driver = new ChromeDriver();

		//launching salesforce

		driver.get("https://login.salesforce.com/");

		//maximize

		driver.manage().window().maximize();

		//adding implicit waits

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

		//Enter the username

		driver.findElement(By.id("username")).sendKeys("ramkumar.ramaiah@testleaf.com");

		//Enter the password

		driver.findElement(By.id("password")).sendKeys("Password$123");

		//Click on login

		driver.findElement(By.id("Login")).click();

		//Click on Learn More link in Mobile Publisher

		driver.findElement(By.xpath("//span[text()='Learn More']")).click();

		//switch to the second window

		Set<String> windowHandles = driver.getWindowHandles();

		//converting the set to list

		List<String> windowlst = new ArrayList<String>(windowHandles);

		//first window

		String firstWindow = windowlst.get(0);

		//second window

		String secondWindow = windowlst.get(1);

		//now switching to second window

		driver.switchTo().window(secondWindow);

		//clicking on confirm

		driver.findElement(By.xpath("//button[text()='Confirm']")).click();

		//Click on Products and Mousehover on Service

		//Click Products and mouse hover on Service

		/*
		 * Products is under a shadow dom
		 * 
		 * We cannot write a xpath for an element under shadow dom
		 * 
		 * so creating an object for shadow dom
		 * 
		 * 
		 */

          Shadow dom = new Shadow(driver);
          
          dom.findElementByXPath("//span[text()='Products']").click();
          
          //mouse hover Service
          
          //Service is a shadow dom 
          
          WebElement shdw = dom.findElementByXPath("//div[text()='Service']");
          
          //for mouse hover creating  actions class

           Actions builder = new Actions(driver);
           
           Thread.sleep(5000);
           
           builder.moveToElement(shdw).perform();
           
           //Click on Customer Services
           
          //Customer Services is a shadow dom 
           
           dom.findElementByXPath("//a[text()='Customer Service']").click();
           
           //Get the names Of Services Available 
           
           List<WebElement> list = driver.findElements(By.xpath("//h2[@data-equalize='heading1']//span"));
           
           //ptinting all the services vailable
           
           for (int i = 0; i < list.size(); i++) {
        	   
        	   
        	   System.out.println(list.get(i).getText());
        	   
			}
        
           //Verify the title
           
          System.out.println( "The title of the page is "+driver.getTitle());
	}

}
