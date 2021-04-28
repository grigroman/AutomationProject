package iOS.EspnTest;

import static org.junit.Assert.fail;

import java.net.MalformedURLException;

import org.openqa.selenium.By;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.TouchAction;
import io.appium.java_client.ios.IOSDriver;

public class EspnMenuClickIOSTest extends EspnIOSTest{

	public EspnMenuClickIOSTest(String UDid) throws MalformedURLException {
		super(UDid);

		dc.setCapability("testName", "ESPN - Click test - IOS");
		
		driver = new IOSDriver<>(Url, dc);	
		driver.rotate(ScreenOrientation.PORTRAIT);
		wait = new WebDriverWait(driver, TIME_TO_WAIT);
		touch = new TouchAction<>(driver);
		
		test();
		
	}
	
	private void test() {
		try {
			Thread.sleep(1500);
			driver.get("https://www.espn.com/");
			Thread.sleep(1500);
			// need to scroll down
			int countSwipes = 0;
			boolean foundCookieMassage = true;
			while (driver.findElements(By.xpath("//*[@id='onetrust-accept-btn-handler']")).size() == 0 ) {
	            swipeDown();
	            countSwipes++;
	            foundCookieMassage = true;
	            if(countSwipes > 5)
	            {
	            	foundCookieMassage = false;
	            	System.out.println("No cookie massage");
	            	break;
	            }
	        }
	
			if(foundCookieMassage)
			{
				wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='onetrust-accept-btn-handler']"))).click();
				wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id='onetrust-accept-btn-handler']")));
				Thread.sleep(2500);
			}
			
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='global-nav-mobile-trigger']"))).click();
			
	    	wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[@class='menu-trigger nav-search']"))).click();
	    	wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@id='global-search-mobile' and @class='global-search']")));
	
	    	wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[@class='menu-trigger nav-pillar nav-sports' and @data-route='false']"))).click();
	    	wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[@class='sports menu-trigger' and @name='&lpos=sitenavmobile+sports+nfl']")));
	
	    	wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[@class='menu-trigger nav-pillar nav-espnplus' and @data-route='false']"))).click();
	    	wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[@name='&lpos=sitenavmobile+espnplus+featured']")));
	
	    	wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[@class='menu-trigger nav-pillar nav-watch' and @data-route='false']"))).click();
	    	wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[@name='&lpos=sitenavmobile+watch+index']")));
	
	    	wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[@class='menu-trigger nav-pillar nav-listen' and @data-route='false']"))).click();
	    	wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[@name='&lpos=sitenavmobile+listen+espn_radio']")));
	
	    	wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[@class='menu-trigger nav-pillar nav-fantasy' and @data-route='false']"))).click();
	    	wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[@name='&lpos=sitenavmobile+fantasy+index']")));
	
	    	wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[@class='menu-trigger nav-pillar nav-more' and @data-route='false']"))).click();
	    	wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[@name='&lpos=sitenavmobile+more_espnespnw']")));
			
			closeDriver();
			
		}catch (Exception e) {
			System.out.println(e.toString());
			closeDriver();
			fail("ESPN Menu Click IOS Test - fail!");
		}
		
	}
	
	
	public void closeDriver() {
		driver.close();
		driver.quit();
	}
	

	
	@Override
	public String toString() {
		String s = "ESPN Menu Click IOS Test";
		return s;
	}

}
