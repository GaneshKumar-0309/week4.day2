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

public class ArchitectCertifications {

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
		
		//Click Resources and mouse hover on Learning On Trailhead
		
		/*
		 * Resources is under a shadow dom
		 * 
		 * We cannot write a xpath for an element under shadow dom
		 * 
		 * so creating an object for shadow dom
		 * 
		 * 
		 */
		
		Shadow dom = new Shadow(driver);
		
		WebElement shdw = dom.findElementByXPath("//span[text()='Learning']");
		
		shdw.click();
		
		Thread.sleep(5000);
		
		//mousehover on Learning On Trailhead
		
		//Learning On Trailhead is a shodow dom
		
		WebElement shdw1 = dom.findElementByXPath("//span[contains(text(),'Trailhead')]");
		
		//for mousehover creating a actions object
		
		Actions builder = new Actions(driver);
		
		builder.click(shdw1).build().perform();
		
		//Clilck on Salesforce Certifications
		
		WebElement shdw2 = dom.findElementByXPath("//a[text()='Salesforce Certification']");
		
		shdw2.click();
		
		Thread.sleep(5000);
		
		driver.findElement(By.xpath("(//img[@class='roleMenu-item_img'])[2]")).click();
		
		//7. Get the Text(Summary) of Salesforce Architect 

		String text = driver.findElement(By.xpath("//h1[@class='cert-site_title slds-p-vertical--large']//following::div")).getText();
	
		System.out.println(text);
		
		//Click on Application Architect 
		
		driver.findElement(By.linkText("Application Architect")).click();
		
		//Get the List of Certifications available
		
		List<WebElement> list = driver.findElements(By.xpath("//div[@class='credentials-card_title']"));
	
	
		//printing all the certifications
		
		for (int i = 0; i < list.size(); i++) {
			
			
			System.out.println(list.get(i).getText());
			
		}
		
	
	}

}
