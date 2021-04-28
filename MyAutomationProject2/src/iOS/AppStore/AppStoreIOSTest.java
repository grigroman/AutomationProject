package iOS.AppStore;

import java.net.MalformedURLException;

import iOS.IOSTest;
import io.appium.java_client.remote.IOSMobileCapabilityType;

public class AppStoreIOSTest extends IOSTest{

	public AppStoreIOSTest(String UDid) throws MalformedURLException {
		super(UDid);

		dc.setCapability(IOSMobileCapabilityType.BUNDLE_ID, "com.apple.AppStore");
	}

}
