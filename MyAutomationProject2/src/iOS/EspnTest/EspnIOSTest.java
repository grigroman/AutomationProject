package iOS.EspnTest;

import java.net.MalformedURLException;

import iOS.IOSTest;
import io.appium.java_client.remote.IOSMobileCapabilityType;


public class EspnIOSTest extends IOSTest{

	public EspnIOSTest(String UDid) throws MalformedURLException {
		super(UDid);

		dc.setCapability(IOSMobileCapabilityType.BUNDLE_ID, "com.apple.mobilesafari");
        
	}

}
