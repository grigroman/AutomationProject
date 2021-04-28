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

public class EriBankPaymentIOSTest extends EriBankIOSTest{

	public EriBankPaymentIOSTest(String UDid) throws MalformedURLException {
		super(UDid);

		dc.setCapability("testName", "EriBank - Payment test - IOS");
		
		driver = new IOSDriver<>(Url, dc);	
		driver.rotate(ScreenOrientation.PORTRAIT);
		wait = new WebDriverWait(driver, TIME_TO_WAIT);
		touch = new TouchAction<>(driver);
		
		test();
	}
	
	private void test() {
		try {
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@placeholder='Username']"))).sendKeys("company");
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@placeholder='Password']"))).sendKeys("company");
			
			driver.findElement(By.xpath("//*[@text='Login']")).click();
			
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(@text,'$') and @class='UIAStaticText']")));
			String res = driver.findElement(By.xpath("//*[contains(@text,'$') and @class='UIAStaticText']")).getText();
	
			res = res.split("\\$")[0];
			double balance = Double.parseDouble(res);
	
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='makePaymentButton']"))).click();
			
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='phoneTextField']"))).sendKeys("0541234567");
			driver.findElement(By.xpath("//*[@id='phoneTextField']")).sendKeys("0541234567");
	        driver.findElement(By.xpath("//*[@id='nameTextField']")).sendKeys("Jon Snow");
	        driver.findElement(By.xpath("//*[@id='amountTextField']")).sendKeys("10");
	        driver.findElement(By.xpath("//*[@id='countryButton']")).click();
	        driver.findElement(By.xpath("//*[@id='Switzerland']")).click();
	        driver.findElement(By.xpath("//*[@id='sendPaymentButton']")).click();
	        driver.findElement(By.xpath("//*[@id='Yes']")).click();
	        
	        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(@text,'$') and @class='UIAStaticText']")));
	        String resAfter = driver.findElement(By.xpath("//*[contains(@text,'$') and @class='UIAStaticText']")).getText();
	        resAfter = resAfter.split("\\$")[0];
			double balanceAfter = Double.parseDouble(resAfter);
			if((balance - 10) != balanceAfter)
			{
				fail("Basa-Sabba");
			}
			
			closeDriver();
			
		}catch (Exception e) {
			System.out.println(e.toString());
			closeDriver();
			fail("EriBank Payment IOS Test - fail!");
		}
		
	}
	
	
	public void closeDriver() {
		driver.close();
		driver.quit();
	}
	
	@Override
	public String toString() {
		String s = "EriBank Payment IOS Test";
		return s;
	}

}
