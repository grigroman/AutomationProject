package PlayStore;

import java.net.MalformedURLException;

import Android.AndroidTest;
import io.appium.java_client.remote.AndroidMobileCapabilityType;

public class PlayStoreTest extends AndroidTest{

	public PlayStoreTest(String udID) throws MalformedURLException {
		super(udID);
		
//		dc.setCapability(MobileCapabilityType.APP, "cloud:com.sec.android.app.samsungapps/.SKSamsungMainActivity");
		dc.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, "com.android.vending");
        dc.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, "com.google.android.finsky.activities.MainActivity");
		
	}
	
	

}
