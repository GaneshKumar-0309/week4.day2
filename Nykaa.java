package week4.day2;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.commons.collections4.Get;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Nykaa {

	public static void main(String[] args) throws InterruptedException {


		WebDriverManager.chromedriver().setup();

		//launching chrome

		ChromeDriver driver = new ChromeDriver();

		//launching nykaa

		driver.get("https://www.nykaa.com/");

		//maximize

		driver.manage().window().maximize();

		//adding implicit waits

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		
		//Mouseover on Brands and Search L'Oreal Paris
		
		WebElement mse = driver.findElement(By.xpath("//a[text()='brands']"));

		Actions builder = new Actions(driver);
		
		builder.moveToElement(mse).perform();
		
		//Search L'Oreal Paris
		
		driver.findElement(By.id("brandSearchBox")).sendKeys("L'Oreal Paris");
		
		Thread.sleep(5000);
		
		//Click on L'Oreal paris
		
		driver.findElement(By.xpath("//div[@class='css-ov2o3v']//a")).click();
		
		//Click sort By and select customer top rated
		
		driver.findElement(By.xpath("//span[@class= 'sort-name']")).click();
		
		driver.findElement(By.xpath("(//div[@class='control-indicator radio '])[3]")).click();
		
		Thread.sleep(3000);
		
		//Click Category and click Hair->Click haircare->Shampoo
		
		driver.findElement(By.xpath("//span[text()='Category']")).click();
		
		driver.findElement(By.xpath("//span[text()='Hair']")).click();
		
		driver.findElement(By.xpath("//span[text()='Hair Care']")).click();
		
		driver.findElement(By.xpath("//div[@class='control-indicator checkbox ']")).click();
		
		Thread.sleep(3000);
		
		//Click->Concern->Color Protection
		
		driver.findElement(By.xpath("//span[text()='Concern']")).click();
		
		driver.findElement(By.xpath("//span[text()='Color Protection']")).click();
		
		//check whether the Filter is applied with Shampoo
		
		List<WebElement> filter = driver.findElements(By.className("filter-value"));
		
		
		for (int i = 0; i < filter.size(); i++) {
			
			System.out.println("The filters applied are "+filter.get(i).getText());
			
		}
		
	    //Click on L'Oreal Paris Colour Protect Shampoo
		
		driver.findElement(By.xpath("//div[@class='css-43m2vm']//img")).click();
		
		//GO to the new window and select size as 175ml
		
		Set<String> windowHandles = driver.getWindowHandles();
		
		//convert the string to list
		
		List<String> windowlst = new ArrayList<String>(windowHandles);
		
		String firstWindow = windowlst.get(0);
		
		String secondWindow = windowlst.get(1);
		
		//switching to second window
		
		driver.switchTo().window(secondWindow);
		
		//The size is already 175
		
		//Print the MRP of the product
		
		String price = driver.findElement(By.xpath("//span[@class='css-u05rr']//following::span")).getText();
		
		System.out.println("The price is "+price);
		
		//Click on ADD to BAG
		
		driver.findElement(By.xpath("//span[@class='btn-text']")).click();
		
		//Go to Shopping Bag 
		
		driver.findElement(By.xpath("//button[@class='css-g4vs13']")).click();
		
		//Print the Grand Total amount (it is inside a frame)
		
		driver.switchTo().frame(0);
		
		
		String price1 = driver.findElement(By.xpath("//div[@class='name medium-strong']//following::div")).getText();
		
		System.out.println("The grandTotal is "+price1);
		
		//Click Proceed
		
		driver.findElement(By.xpath("//span[text()='Proceed']")).click();
		
		//Click on Continue as Guest
		
		driver.findElement(By.xpath("//button[@class='btn full big']")).click();
		
		//Check if this grand total is the same in step 14
		
		String price2 = driver.findElement(By.xpath("(//div[@class='value']//span)[2]")).getText();
		
		if (price1==price2) {
			
			System.out.println("The GrandTotal is same");
		} else {

			System.out.println("The GrandTotal is different");
		}
		
	}

}
