package DotGame;

import java.net.MalformedURLException;

import Android.AndroidTest;
import io.appium.java_client.remote.AndroidMobileCapabilityType;

public class DotGameTest extends AndroidTest{

	public DotGameTest(String udID) throws MalformedURLException {
		super(udID);
		
		dc.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, "com.example.talbirmangame");
        dc.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, ".MainActivity");
        
	}
	
	@Override
	public String toString() {
		String s = "Dot Game Android Test";
		return s;
	}

}
