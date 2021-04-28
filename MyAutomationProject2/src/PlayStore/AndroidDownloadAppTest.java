package PlayStore;

import static org.junit.Assert.fail;

import java.net.MalformedURLException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

public class AndroidDownloadAppTest extends PlayStoreTest{

	public AndroidDownloadAppTest(String udID) throws MalformedURLException {
		super(udID);

		dc.setCapability("testName", "Android PlayStore - Download App test");
		
		driver = new AndroidDriver<>(Url, dc);
		driver.rotate(ScreenOrientation.PORTRAIT);
		wait = new WebDriverWait(driver, TIME_TO_WAIT);
		touch = new TouchAction<>(driver);
		
		test();
	}
	
	public void test() {
		try {
			
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='For you']")));
			driver.findElement(By.xpath("//*[@text='For you']")).click();
			
			// get first app in page
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='mini_blurb']")));
			List<AndroidElement> recommendedApps = driver.findElements(By.xpath("//*[@id='mini_blurb']"));
			recommendedApps.get(0).click();
			
			// click on install button
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='Install']")));
			driver.findElement(By.xpath("//*[@text='Install']")).click();
			
			// wait for download to done
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='Cancel']"))).click();

			closeDriver();
		}catch (Exception e) {
			System.out.println(e.toString());
			closeDriver();
			fail("Android Download App - fail!");
		}
		
	}
	
	
	public void closeDriver() {
		driver.close();
		driver.quit();
	}
	
	@Override
	public String toString() {
		String s = "Play Store Download app Test";
		return s;
	}

}
