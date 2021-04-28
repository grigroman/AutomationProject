package PlayStore;

import static org.junit.Assert.fail;

import java.net.MalformedURLException;
import java.time.Duration;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.touch.offset.PointOption;

//import io.appium.java_client.touch.WaitOptions;
import static io.appium.java_client.touch.WaitOptions.waitOptions;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.ScreenOrientation;

public class PlayStoreTop10FreeAppsTest extends PlayStoreTest{

	public PlayStoreTop10FreeAppsTest(String udID) throws MalformedURLException {
		super(udID);
		
		dc.setCapability("testName", "Android PlayStore - Top10Apps test");
		
		driver = new AndroidDriver<>(Url, dc);
		driver.rotate(ScreenOrientation.PORTRAIT);
		wait = new WebDriverWait(driver, TIME_TO_WAIT);
		touch = new TouchAction<>(driver);
		
		test();
	}
	
	public void test() {
		try {
			// Go to top charts
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='0_resource_name_obfuscated' and ./*[@text='Top charts']]")));
			driver.findElement(By.xpath("//*[@text='Top charts']")).click();
			
			try {
				wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@contentDescription='Close']"))).click();
				
				wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='0_resource_name_obfuscated' and ./*[@text='Top charts']]")));
				driver.findElement(By.xpath("//*[@text='Top charts']")).click();
			}catch (Exception e) {
				System.out.println("No Info Massage This Time :)");
			}
			// Select Top free apps
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(@text,'Top') and @class='android.widget.Button']")));
			driver.findElement(By.xpath("//*[contains(@text,'Top') and @class='android.widget.Button']")).click();
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='Top free']")));
			driver.findElement(By.xpath("//*[@text='Top free']")).click();
			
			// Get all the apps on the screen
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(@contentDescription,'App')]")));
			List<AndroidElement> topApps = driver.findElements(By.xpath("//*[contains(@contentDescription,'App')]"));
			Set<String> targetSet = new HashSet<>();
			for (AndroidElement element : topApps) {
				
				String s = element.getAttribute("content-desc");
				s = s.split(": ")[1];
				s = s.split("\\n")[0];
				targetSet.add(s);
			}
			
			// Convert list to set -> for unique apps
			int index = 0;
			if(topApps.size() >= 10 ) {
				System.out.println("Found 10 top apps");
				
			}
			else
			{
				index++;
				System.out.println("Need to find more apps");

				while(targetSet.size() < 10){
					Dimension size = driver.manage().window().getSize();
					int starty = (int) (size.height * 0.70);
					int endy = (int) (size.height * 0.30);
					int startx = (int) size.width / 2;
//					System.out.println("starty = " + starty + " ,endy = " + endy + " , startx = " + startx);
					
					touch.press(PointOption.point(startx, starty))
						.waitAction(waitOptions(Duration.ofMillis(500)))
						.moveTo(PointOption.point(startx, endy)).release().perform();
					
					List<AndroidElement> otherTopApps = driver.findElements(By.xpath("//*[contains(@contentDescription,'App')]"));
					Set<String> otherTopAppsSet = new HashSet<>();
					for (AndroidElement element : otherTopApps) {
						
						String s = element.getAttribute("content-desc");
						s = s.split(": ")[1];
						s = s.split("\\n")[0];
						targetSet.add(s);
					}
				}

			}
			
			for (String str : targetSet) {
				if(index > 10)
				{
					break;
				}
				System.out.print("App number " + index + ": ");
				System.out.println(str);
				index++;
			}
			

			closeDriver();
		}catch (Exception e) {
			System.out.println(e.toString());
			closeDriver();
			fail("Failed - Play Store Top 10 Free Apps!");
		}

	}
	
	public void closeDriver() {
		driver.close();
		driver.quit();
	}

	@Override
	public String toString() {
		String s = "Play Store Top 10 Free Apps Test";
		return s;
	}
	
	
}
