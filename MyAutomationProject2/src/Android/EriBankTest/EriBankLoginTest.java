package Android.EriBankTest;



import static org.junit.Assert.fail;

import java.net.MalformedURLException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;

public class EriBankLoginTest extends EriBankTest{
	

	public EriBankLoginTest(String udID) throws MalformedURLException {
		super(udID);
		
		dc.setCapability("testName", "EriBank - Login test - Android");
		
//		dc.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, "com.experitest.ExperiBank");
//        dc.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, ".LoginActivity");
        
        driver = new AndroidDriver<>(Url, dc);
		driver.rotate(ScreenOrientation.PORTRAIT);
		wait = new WebDriverWait(driver, TIME_TO_WAIT);
		touch = new TouchAction<>(driver);
		
		test();
		
	}
	
	public void test() {
		try {
			List<List<String>> loginParamsForTest = readFromCsv("src\\LoginEriBank.csv");
			
			for(int i=0; i<8; i++)
			{
				wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='usernameTextField']"))).sendKeys(loginParamsForTest.get(i).get(0));
				wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='passwordTextField']"))).sendKeys(loginParamsForTest.get(i).get(1));
				driver.findElement(By.id("com.experitest.ExperiBank:id/loginButton")).click();
				
				wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='Close']"))).click();
			}
			
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='usernameTextField']"))).sendKeys(loginParamsForTest.get(8).get(0));
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='passwordTextField']"))).sendKeys(loginParamsForTest.get(8).get(1));
			driver.findElement(By.id("com.experitest.ExperiBank:id/loginButton")).click();
			
			
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(@text,'Your balance is:')]")));
			
			closeDriver();
			
		}catch (Exception e) {
			System.out.println(e.toString());
			closeDriver();
			fail("EriBank login - fail!");
		}

	}
	
	public void closeDriver() {
		driver.close();
		driver.quit();
	}
	
	@Override
	public String toString() {
		String s = "EriBank Login Android Test";
		return s;
	}
	

}
