package iOS.EriBankTest;

import static org.junit.Assert.fail;

import java.net.MalformedURLException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.TouchAction;
import io.appium.java_client.ios.IOSDriver;

public class EriBankLoginTestIOS extends EriBankIOSTest{

	public EriBankLoginTestIOS(String UDid) throws MalformedURLException {
		super(UDid);
		
		dc.setCapability("testName", "EriBank - Login test - IOS");
		
		driver = new IOSDriver<>(Url, dc);	
		driver.rotate(ScreenOrientation.PORTRAIT);
		wait = new WebDriverWait(driver, TIME_TO_WAIT);
		touch = new TouchAction<>(driver);
		
		test();
		
	}

	private void test() {
		try {
			List<List<String>> loginParamsForTest = readFromCsv("src\\LoginEriBank.csv");
			for(int i=0; i<8; i++)
			{
				
				wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@placeholder='Username']"))).sendKeys(loginParamsForTest.get(i).get(0));
				wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@placeholder='Password']"))).sendKeys(loginParamsForTest.get(i).get(1));
				driver.findElement(By.xpath("//*[@text='Login']")).click();
				
				wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='Dismiss']"))).click();
				
				// clear the field
				wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@placeholder='Username']"))).sendKeys("");
//				wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@placeholder='Password']"))).sendKeys("");
			}
		
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@placeholder='Username']"))).sendKeys(loginParamsForTest.get(8).get(0));
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@placeholder='Password']"))).sendKeys(loginParamsForTest.get(8).get(1));
			driver.findElement(By.xpath("//*[@text='Login']")).click();
			
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='Your balance is: ']")));
			
			closeDriver();
			
		}catch (Exception e) {
			System.out.println(e.toString());
			closeDriver();
			fail("EriBank Login IOS Test - fail!");
		}
		
	}
	
	
	public void closeDriver() {
		driver.close();
		driver.quit();
	}
	
	@Override
	public String toString() {
		String s = "EriBank Login IOS Test";
		return s;
	}

}
