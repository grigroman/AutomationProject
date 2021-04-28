package Android.EriBankTest;

import java.net.MalformedURLException;

import Android.AndroidTest;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.IOSMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;

public class EriBankTest extends AndroidTest {

	public EriBankTest(String udID) throws MalformedURLException {
		super(udID);
		
		dc.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, "com.experitest.ExperiBank");
        dc.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, ".LoginActivity");

	}
	
	@Override
	public String toString() {
		String s = "EriBank Android Test";
		return s;
	}
}
