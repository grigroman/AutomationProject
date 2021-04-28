package iOS.AppStore;

import static org.junit.Assert.fail;

import java.net.MalformedURLException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.TouchAction;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

public class AppStoreTopFreeAppsTest extends AppStoreIOSTest{

	public AppStoreTopFreeAppsTest(String UDid) throws MalformedURLException {
		super(UDid);
		
		dc.setCapability("testName", "App Store - Top 10 free apps test - IOS");
		
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
			// btn of see all top free apps
			driver.findElement(By.xpath("//*[@text='See All' and @class='UIAStaticText' and ./parent::*[./parent::*[@text='Top Free Apps']]]")).click();
			
			List<IOSElement> topApps = driver.findElements(By.xpath("//*[@knownSuperClass='UICollectionViewCell']"));
			Set<String> targetSet = new HashSet<>();
			for (IOSElement iosElement : topApps) {
				if(!(iosElement.getText().equals("drifting games")
						|| iosElement.getText().equals("music player")
						|| iosElement.getText().equals("parking games")
						||iosElement.getText().equals("wood carving")))
				{
					targetSet.add(iosElement.getText());
				}
				
			}
			
			// Convert list to set -> for unique apps
			if(targetSet.size() >= 10 ) {
				System.out.println("Found 10 top apps");
			}
			else
			{
				System.out.println("Need to find more apps");
				
				countSwipes = 0;
				while(targetSet.size() < 11){
					swipeDown();
					
					List<IOSElement> otherTopApps = driver.findElements(By.xpath("//*[@knownSuperClass='UICollectionViewCell']"));
					for (IOSElement iosElement : otherTopApps) {
//						System.out.println(iosElement.getText());
						if(!(iosElement.getText().equals("drifting games")
								|| iosElement.getText().equals("music player")
								|| iosElement.getText().equals("parking games")
								||iosElement.getText().equals("wood carving")))
						{
							targetSet.add(iosElement.getText());
						}
						
					}
//					System.out.println(targetSet.size());
				}
				countSwipes++;
	            if(countSwipes > 10)
	            {
	            	fail("Didnt found top 10 free apps");
	            }
	            
	
			}
//			System.out.println("------------------");
//			System.out.println(targetSet);
//			System.out.println("------------------");
			for (String s : targetSet) {
				System.out.println(s.split(",")[1]);
			}
			
			closeDriver();
			
		}catch (Exception e) {
			System.out.println(e.toString());
			closeDriver();
			fail("App Store top 10 apps IOS Test - fail!");
		}
		
	}
	
	
	public void closeDriver() {
		driver.close();
		driver.quit();
	}
	

	
	@Override
	public String toString() {
		String s = "App Store top 10 apps IOS Test";
		return s;
	}

}
