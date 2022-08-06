package week4.day2;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SnapDeal {

	public static void main(String[] args) throws InterruptedException, IOException {


		WebDriverManager.chromedriver().setup();

		//launching chrome

		ChromeDriver driver = new ChromeDriver();

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		//launching nykaa

		driver.get("https://www.snapdeal.com/");

		//maximize

		driver.manage().window().maximize();

		//adding implicit waits

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

		//Go to Mens Fashion

		WebElement findElement = driver.findElement(By.xpath("//span[@class='catText']"));

		//mousehover on Men's fashion

		Actions builder = new Actions(driver);

		builder.moveToElement(findElement).perform();

		//Go to Sports Shoes

		driver.findElement(By.xpath("//span[text()='Sports Shoes']")).click();

		//4. Get the count of the sports shoes

		String catcount = driver.findElement(By.xpath("//span[@class='category-name category-count']")).getText();

		System.out.println("The count of sports shoes is "+catcount);

		//Click Training shoes

		driver.findElement(By.xpath("//div[text()='Training Shoes']")).click();

		//Sort by Low to High

		driver.findElement(By.xpath("//div[@class='sort-selected']")).click();

		driver.findElement(By.xpath("//li[@class='search-li']")).click();

		//Check if the items displayed are sorted correctly

		String sort = driver.findElement(By.xpath("//div[@class='sort-selected']")).getText();

		System.out.println("The sorting is done by "+sort);

		//Select the price range (900-1200)
		//changing the price to 500 - 1200 , as there are no shoes available in 900-1200

		driver.findElement(By.name("fromVal")).clear();

		driver.findElement(By.name("fromVal")).sendKeys("500");

		driver.findElement(By.name("toVal")).clear();

		driver.findElement(By.name("toVal")).sendKeys("1200");

		driver.findElement(By.xpath("//div[contains(text(),'GO')]")).click();

		//Filter with color Navy

		Thread.sleep(3000);

		driver.findElement(By.xpath("//label[@for='Color_s-Navy']")).click();

		//verify the all applied filters 

		List<WebElement> filters = driver.findElements(By.xpath("//div[@class='navFiltersPill']"));

		for (int i = 0; i < filters.size(); i++) {

			System.out.println("The filters applied are "+filters.get(i).getText());

		}


		//Mouse Hover on first resulting Training shoes

		WebElement firstShoe = driver.findElement(By.xpath("//img[@class='product-image wooble']"));


		try {
			builder.moveToElement(firstShoe).perform(); // all success 
		} catch (StaleElementReferenceException e) { // staless
			System.out.println("Failed due to Staleness of the element");
			wait.until(ExpectedConditions.stalenessOf(firstShoe));
			builder.moveToElement(firstShoe).perform();
		}
		//click QuickView button

		driver.findElement(By.xpath("//div[contains(text(),'Quick View')]")).click();

		//Print the cost and the discount percentage

		List<WebElement> price = driver.findElements(By.xpath("//div[@class='product-price pdp-e-i-PAY-l clearfix']//span"));


		System.out.println("The price is "+price.get(0).getText()+" and the discounted percentage is "+price.get(1).getText());


		//Take the snapshot of the shoes.

		File screenshotAs = driver.getScreenshotAs(OutputType.FILE);

		File dest = new File("./Output/snapdeal.png");
		FileUtils.copyFile(screenshotAs, dest);

		//15. Close the current window
		driver.findElement(By.xpath("//div[@class='close close1 marR10']")).click();

		//16. Close the main window
		driver.close();
	}

}
