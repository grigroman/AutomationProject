package ESPNTest;

import static org.junit.Assert.fail;

import java.net.MalformedURLException;

import org.openqa.selenium.By;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;

public class EspnMenuTest extends ESPNTest{

	public EspnMenuTest(String udID) throws MalformedURLException {
		super(udID);
		
		dc.setCapability("testName", "Android ESPN - Menu test");
		
		driver = new AndroidDriver<>(Url, dc);
		driver.rotate(ScreenOrientation.PORTRAIT);
		wait = new WebDriverWait(driver, TIME_TO_WAIT);
		touch = new TouchAction<>(driver);
		
		test();
		
	}
	
	public void test() {
		try {
			
			driver.get("https://www.espn.com/");
			try {
				wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@id='onetrust-accept-btn-handler']"))).click();
				System.out.println("Cookie Accepted");
		        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//button[@id='onetrust-accept-btn-handler']")));
			}catch (Exception e) {
				System.out.println("Didnt found cookie");
			}
			
	        Thread.sleep(2500);
	        // click on menu and find all other symbols

	    	driver.findElement(By.xpath("//a[@id='global-nav-mobile-trigger' and @href='#' and @data-route='false']")).click();
	    	wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[@class='menu-trigger nav-search']")));
	    	wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[@class='menu-trigger nav-pillar nav-sports' and @data-route='false']")));
	    	wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[@class='menu-trigger nav-pillar nav-espnplus' and @data-route='false']")));
	    	wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[@class='menu-trigger nav-pillar nav-watch' and @data-route='false']")));
	    	wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[@class='menu-trigger nav-pillar nav-listen' and @data-route='false']")));
	    	wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[@class='menu-trigger nav-pillar nav-fantasy' and @data-route='false']")));
	    	wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[@class='menu-trigger nav-pillar nav-more' and @data-route='false']")));

			closeDriver();
		}catch (Exception e) {
			System.out.println(e.toString());
			closeDriver();
			fail("ESPN Menu - fail!");
		}

	}
	
	public void closeDriver() {
		driver.close();
		driver.quit();
	}
	
	@Override
	public String toString() {
		String s = "ESPN Menu Android Test";
		return s;
	}

}
