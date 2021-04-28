package ESPNTest;

import java.net.MalformedURLException;

import Android.AndroidTest;

public class ESPNTest  extends AndroidTest{

	public ESPNTest(String udID) throws MalformedURLException {
		super(udID);
		
		dc.setCapability("browserName", "Chrome");
		dc.setCapability("profile.default_content_settings.cookies", 2);
		
	}
	
	@Override
	public String toString() {
		String s = "ESPN Android Test";
		return s;
	}

}
