package Academy;

import java.io.IOException;
import java.util.Iterator;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import resources.base;

public class validateLogin extends base {
	public WebDriver driver;
	public static Logger log = LogManager.getLogger(base.class.getName());

	@BeforeTest
	public void initialize() throws IOException {

		driver = initializeDriver();
		log.info("Driver is initialized");

		driver.get(prop.getProperty("url"));
		System.out.println("User on " + driver.getTitle() + "Page");
		log.info("Navigated to https://www.nnnow.com/ Page");

	}

	@Test

	public void validateLoginUser() throws IOException, InterruptedException {

		driver.findElement(By.xpath("//a[@href='https://www.nnnow.com/mens-wear']")).click();
		System.out.println("User successfully clicked on MEN Tab");
		log.info("User successfully clicked on MEN Tab");
		Thread.sleep(3000);

		// Scroll down

		JavascriptExecutor jse = (JavascriptExecutor) driver;

		jse.executeScript("scroll(0, 250)"); // y value '250' can be altered

		driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[2]/div/div/div[4]/div/div/div[2]/div[1]")).click();
		System.out.println("User successfully clicked on JEANS");
		log.info("User successfully clicked on JEANS");

		driver.findElement(By.xpath("//div[contains(@itemtype,'http://schema.org/ItemList')]/div[2]")).click();
		System.out.println("Product successfully opened in the new tab");
		log.info("Product successfully opened in the new tab");

		// Iterate through window ids and go to childwindow id
		Set<String> ids = driver.getWindowHandles();
		Iterator<String> it = ids.iterator();
		String parentID = it.next();
		String childID = it.next();

		driver.switchTo().window(childID); // navigate to child window

		// Select size
		driver.findElement(By.xpath(
				"//*[@id=\"root\"]/div/div/div/div/div/div[1]/div/div/div/div/div[2]/div[1]/div[4]/div[2]/button[1]"))
				.click();
		System.out.println("User successfully selected size of JEANS");
		log.info("User successfully selected size of JEANS");

		// Click on add to bag
		driver.findElement(
				By.xpath("//button[contains(@class,'nwc-btn nwc-ripple-container nwc-btn-primary nw-pdp-addtobag')]"))
				.click();
		System.out.println("User clicked on ADD BAG ");
		log.info("User clicked on ADD BAG");

		Thread.sleep(2000); // wait 2sec
		driver.findElement(By
				.xpath("//div[contains(@class,'nwc-grid-col nwc-grid-col-xs nw-desktopheader-buttonscol')]//button[1]"))
				.click();

		Thread.sleep(2000); // wait 2sec

		// Print Product Information
		String prodInfo = driver.findElement(By.xpath("//div[contains(@class,'nwc-grid-col nwc-grid-col-xs-7')]"))
				.getText();
		System.out.println("Product Information:" + prodInfo);

		// click on view shopping bag

		driver.findElement(By.xpath("//button[normalize-space()='VIEW SHOPPING BAG']")).click();
		System.out.println("User successfully clicked on VIEW SHOPPING BAG");
		log.info("User successfully clicked on VIEW SHOPPING BAG");

		// click on apply promo code
		driver.findElement(By.xpath("//span[@class='nw-applypromo-btntext']")).click();
		System.out.println("User Successfully clicked on APPLY PROMO CODE");
		log.info("User Successfully clicked on APPLY PROMO CODE");

		// switch toAPPLY promo code and type ABCD

		driver.findElement(By.xpath("//input[@id='nw-app-promo-input']")).sendKeys("ABCD"); // Addpromo code ABCD
		System.out.println("User successfully typed PROMO CODE ABCD");
		log.info("User successfully typed PROMO CODE ABCD");

		driver.findElement(By.xpath("//button[normalize-space()='apply promo']")).click();
		System.out.println("User successfully clicked on APPLY PROMO CODE");
		log.info("User successfully clicked on APPLY PROMO CODE");

		// verify error message

		String error = driver.findElement(By.xpath("//div[contains(@class,'nw-applpromo-errormessage')]")).getText();

		if (error != null) {
			System.out.println("ERROR MSG When User typed wrong PROMO CODE:" + error);

		}

		// Close APPLY/SELECT PROMO code window

		driver.findElement(By.xpath("//button[contains(@class,'nwc-btn nwc-close nwc-close-normal nwc-modal-close')]"))
				.click();
		System.out.println("User successfully clicked on closed PROMO CODE Window");
		log.info("User successfully clicked on closed PROMO CODE Window");

		// click on check out
		driver.findElement(By.xpath("//a[normalize-space()='CHECKOUT']")).click();
		System.out.println("User successfully clicked on CHEK OUT");
		log.info("User successfully clicked on CHEK OUT");

		// click on continue
		driver.findElement(By.xpath("//button[normalize-space()='CONTINUE']")).click();
		System.out.println("User successfully clicked on CONTINUED");
		log.info("User successfully clicked on CONTINUED");

		Assert.assertEquals(driver.getTitle(), "Login Page");
		log.info("Successfully validated Text message");
		System.out.println("Test completed");

		;

	}

	@AfterTest
	public void teardown() {

		driver.quit();

	}

}
