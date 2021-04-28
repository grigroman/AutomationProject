package Android;

import java.net.MalformedURLException;

import MyAutomationProject2.TestManager;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;

public class AndroidTest extends TestManager{

	protected static AndroidDriver<AndroidElement> driver = null;
	
	public AndroidTest(String UDid) throws MalformedURLException {
		super(UDid);
		
		// android phone setup
		dc.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
		dc.setCapability(MobileCapabilityType.DEVICE_NAME, "SM-G960F");
		dc.setCapability(MobileCapabilityType.PLATFORM_VERSION, "10");
//		dc.setCapability(MobileCapabilityType.UDID, UDid);



		
	}

	@Override
	public String toString() {
		return "Android Test!";
	}

}
