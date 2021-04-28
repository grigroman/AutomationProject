package iOS;

import java.net.MalformedURLException;
import java.time.Duration;

import MyAutomationProject2.TestManager;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;

import org.openqa.selenium.interactions.touch.TouchActions;
import io.appium.java_client.TouchAction;


import org.openqa.selenium.Dimension;

public class IOSTest extends TestManager{
	
	protected static IOSDriver<IOSElement> driver = null;

	public IOSTest(String UDid) throws MalformedURLException {
		super(UDid);

		dc.setCapability(MobileCapabilityType.PLATFORM_NAME, "iOS");
		dc.setCapability(MobileCapabilityType.DEVICE_NAME, "B0093");
		dc.setCapability(MobileCapabilityType.PLATFORM_VERSION, "13.1.2");
//		dc.setCapability(MobileCapabilityType.UDID, UDid);
	}

	@Override
	public String toString() {
		String s = super.toString();
		s += " - iOS Test!";
		return s;
	}
	
	public static void swipeDown ( ) {
	  Dimension dimension = driver.manage().window().getSize();
	  int start_x = (int) ( dimension.width * 0.5 );
	  int start_y = (int) ( dimension.height * 0.7 );
	  int end_x = (int) ( dimension.width * 0.5 );
	  int end_y = (int) ( dimension.height * 0.3 );
	  TouchAction touch = new TouchAction(driver);
	  touch.press(PointOption.point(start_x, start_y)).waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1))).moveTo(PointOption.point(end_x, end_y)).release().perform();
	}
	
}
