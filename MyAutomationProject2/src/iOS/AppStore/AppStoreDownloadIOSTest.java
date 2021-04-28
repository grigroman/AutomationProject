package iOS.AppStore;

import static org.junit.Assert.fail;

import java.net.MalformedURLException;

import org.openqa.selenium.By;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.TouchAction;
import io.appium.java_client.ios.IOSDriver;

public class AppStoreDownloadIOSTest extends AppStoreIOSTest{

	public AppStoreDownloadIOSTest(String UDid) throws MalformedURLException {
		super(UDid);
		
		dc.setCapability("testName", "App Store - Download App test - IOS");
		
		driver = new IOSDriver<>(Url, dc);	
		driver.rotate(ScreenOrientation.PORTRAIT);
		wait = new WebDriverWait(driver, TIME_TO_WAIT);
		touch = new TouchAction<>(driver);
		
		test();
		
	}
	
	private void test() {
		try {
			
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='Apps']"))).click();
			int countSwipes = 0;
			while (driver.findElements(By.xpath("//*[@text='See All' and @class='UIAButton' and ./parent::*[@text='Top Free Apps']]")).size() == 0 ) {
	            swipeDown();
	            countSwipes++;
	            if(countSwipes > 10)
	            {
	            	fail("Didnt found top 10 free apps button");
	            }
	        }
			
			swipeDown();
			// btn of see all free apps
			driver.findElement(By.xpath("//*[@text='See All' and @class='UIAStaticText' and ./parent::*[./parent::*[@text='Top Free Apps']]]")).click();
			
			if(driver.findElements(By.xpath("//*[@text='get']")).size() > driver.findElements(By.xpath("//*[@value='redownload']")).size())
			{
				wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='get']"))).click();
				wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='downloading']"))).click();
			}
			else
			{
				wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@value='redownload']"))).click();
				wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='downloading']"))).click();
			}

			closeDriver();
			
		}catch (Exception e) {
			System.out.println(e.toString());
			closeDriver();
			fail("App Store Download app IOS Test - fail!");
		}
		
	}
	
	public void closeDriver() {
		driver.close();
		driver.quit();
	}
	

	
	@Override
	public String toString() {
		String s = "App Store Download app IOS Test";
		return s;
	}
}
