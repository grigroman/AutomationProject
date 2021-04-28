package Android.EriBankTest;



import static org.junit.Assert.fail;

import java.net.MalformedURLException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;

public class EriBankPaymentTest extends EriBankTest{
	

	public EriBankPaymentTest(String udID) throws MalformedURLException {
		super(udID);
		
		dc.setCapability("testName", "EriBank - Payment test");
		
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
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='usernameTextField']"))).sendKeys("company");
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='passwordTextField']"))).sendKeys("company");
	
			driver.findElement(By.id("com.experitest.ExperiBank:id/loginButton")).click();
			
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(@text,'Your balance is:')]")));
			MobileElement balanceText = driver.findElement(By.xpath("//*[contains(@text,'Your balance is:')]"));
			String res = balanceText.getText();
			String[] splitedBalance = res.split("\\.");
			splitedBalance = splitedBalance[0].split(" ");
			String strBalance = splitedBalance[splitedBalance.length -1];
			int balance = Integer.parseInt(strBalance);
			
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='Make Payment']"))).click();
			
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='phoneTextField']"))).sendKeys("0541234567");
	        driver.findElement(By.xpath("//*[@id='nameTextField']")).sendKeys("Jon Snow");
	        driver.findElement(By.xpath("//*[@id='amountTextField']")).sendKeys("10");
	        driver.findElement(By.xpath("//*[@id='countryButton']")).click();
	        driver.findElement(By.xpath("//*[@text='Switzerland']")).click();
	        driver.findElement(By.xpath("//*[@id='sendPaymentButton']")).click();
	        driver.findElement(By.xpath("//*[@text='Yes']")).click();
	        
	        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(@text,'Your balance is:')]")));
			MobileElement balanceTextAfter = driver.findElement(By.xpath("//*[contains(@text,'Your balance is:')]"));
			String resAfter = balanceTextAfter.getText();
			String[] splitedBalanceAfter = resAfter.split("\\.");
			splitedBalanceAfter = splitedBalanceAfter[0].split(" ");
			String strBalanceAfter = splitedBalanceAfter[splitedBalanceAfter.length -1];
			int balanceAfter = Integer.parseInt(strBalanceAfter);
			
			if((balance - 10) != balanceAfter)
			{
				closeDriver();
				fail("Failed at calculate payment");
			}
			
			closeDriver();
			
		}catch (Exception e) {
			System.out.println(e.toString());
			closeDriver();
			fail("EriBank Payment - fail!");
		}

	}
	
	public void closeDriver() {
		driver.close();
		driver.quit();
	}
	
	@Override
	public String toString() {
		String s = "EriBank Payment Android Test";
		return s;
	}
}
