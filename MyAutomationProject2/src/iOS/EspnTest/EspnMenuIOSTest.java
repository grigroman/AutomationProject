package iOS.EspnTest;

import static org.junit.Assert.fail;

import java.net.MalformedURLException;

import org.openqa.selenium.By;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.TouchAction;
import io.appium.java_client.ios.IOSDriver;

public class EspnMenuIOSTest extends EspnIOSTest{

	public EspnMenuIOSTest(String UDid) throws MalformedURLException {
		super(UDid);
		
		dc.setCapability("testName", "ESPN - Menu test - IOS");
		
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
			
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='global-nav-mobile-trigger']")));
			Thread.sleep(1500);
			driver.findElement(By.xpath("//*[@id='global-nav-mobile-trigger']")).click();
			
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='Search']"))).click();
			driver.findElement(By.xpath("//*[@text='sports' and ./parent::*[@nodeName='A']]")).click();
			driver.findElement(By.xpath("//*[@text='ESPN+']")).click();
			driver.findElement(By.xpath("//*[@text='Watch']")).click();
			driver.findElement(By.xpath("//*[@text='Listen']")).click();
			driver.findElement(By.xpath("//*[@text='Fantasy']")).click();
			driver.findElement(By.xpath("//*[@text='More']")).click();
			
			closeDriver();
			
		}catch (Exception e) {
			System.out.println(e.toString());
			closeDriver();
			fail("ESPN Menu IOS Test - fail!");
		}
		
	}
	
	
	public void closeDriver() {
		driver.close();
		driver.quit();
	}
	

	
	@Override
	public String toString() {
		String s = "ESPN Menu IOS Test";
		return s;
	}

}
