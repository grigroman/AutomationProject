package iOS.EriBankTest;

import java.net.MalformedURLException;

import iOS.IOSTest;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.IOSMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;

public class EriBankIOSTest extends IOSTest{

	public EriBankIOSTest(String UDid) throws MalformedURLException {
		super(UDid);
		
        dc.setCapability(MobileCapabilityType.APP, "cloud:com.experitest.ExperiBank");
        dc.setCapability(IOSMobileCapabilityType.BUNDLE_ID, "com.experitest.ExperiBank");
	}

}
