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

public class PlayDotGameTest extends DotGameTest{

	public PlayDotGameTest(String udID) throws MalformedURLException {
		super(udID);

		dc.setCapability("testName", "Android Dot Game - Game test");
		
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
			
			// Login
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='userName']"))).click();
			driver.findElement(By.xpath("//*[@id='userName']")).sendKeys("Roman");
	
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='Password']"))).click();
			driver.findElement(By.xpath("//*[@id='Password']")).sendKeys("Roman");
					
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='LOGIN']")));
			driver.findElement(By.xpath("//*[@text='LOGIN']")).click();
					
			// new Game
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='NEW GAME']")));
			driver.findElement(By.xpath("//*[@text='NEW GAME']")).click();
			
			for(int i=0; i<3; i++)
			{
				wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='target']")));
				driver.findElement(By.xpath("//*[@id='target']")).click();
				int boomCounter = i+1;
				System.out.println("Boom! found: " + boomCounter);
			}
					
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='Game Over']")));
			
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
		String s = "DotGame Play 3 Times Android Test";
		return s;
	}

}
