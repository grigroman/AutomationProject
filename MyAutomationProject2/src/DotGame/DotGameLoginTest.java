package DotGame;

import static org.junit.Assert.fail;

import java.net.MalformedURLException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;

public class DotGameLoginTest extends DotGameTest{

	public DotGameLoginTest(String udID) throws MalformedURLException {
		super(udID);
		
		dc.setCapability("testName", "Android Dot Game - Login test");
		
		driver = new AndroidDriver<>(Url, dc);
		driver.rotate(ScreenOrientation.PORTRAIT);
		wait = new WebDriverWait(driver, TIME_TO_WAIT);
		touch = new TouchAction<>(driver);
		
		test();
	}
	
	public void test() {
		try {
			try {
				wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='Allow only while using the app']"))).click();
			}catch (Exception e) {
				System.out.println("No need location promise");
			}
			
			List<List<String>> loginParamsForTest = readFromCsv("src\\Login.csv");
//			System.out.println("After Reading");
			
			// try to login - need to fail
			for(int i=0; i<2; i++)
			{
				wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='userName']"))).click();
				driver.findElement(By.xpath("//*[@id='userName']")).sendKeys(loginParamsForTest.get(i).get(0));
				
				wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='Password']"))).click();
				driver.findElement(By.xpath("//*[@id='Password']")).sendKeys(loginParamsForTest.get(i).get(1));
				
				wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='LOGIN']")));
				driver.findElement(By.xpath("//*[@text='LOGIN']")).click();
				
				wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='Incorrect user name or password']")));
//				System.out.println("Didnt success :)");
			}
			
			// Login
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='userName']"))).click();
			driver.findElement(By.xpath("//*[@id='userName']")).sendKeys(loginParamsForTest.get(2).get(0));
	
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='Password']"))).click();
			driver.findElement(By.xpath("//*[@id='Password']")).sendKeys(loginParamsForTest.get(2).get(1));
			
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='LOGIN']")));
			driver.findElement(By.xpath("//*[@text='LOGIN']")).click();
			
			// Check the login!
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='NEW GAME']")));
			
			closeDriver();
		}catch (Exception e) {
			System.out.println(e.toString());
			closeDriver();
			fail("Dot Game Login Test - fail!");
		}
	}
	
	public void closeDriver() {
		driver.close();
		driver.quit();
	}
	
	
	@Override
	public String toString() {
		String s = "DotGame Login Android Test";
		return s;
	}
}
