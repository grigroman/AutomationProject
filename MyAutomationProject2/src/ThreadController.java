import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Android.AndroidTest;
import Android.EriBankTest.EriBankLoginTest;
import Android.EriBankTest.EriBankPaymentTest;
import DotGame.DotGameLoginTest;
import DotGame.PlayDotGameTest;
import MyAutomationProject2.TestManager;
import PlayStore.AndroidDownloadAppTest;
import PlayStore.PlayStoreTop10FreeAppsTest;
import iOS.AppStore.AppStoreDownloadIOSTest;

public class ThreadController extends Thread{
	

	String[] androidTests = {"Android PlayStore Top10","Android PlayStore Download",
							 "Android EriBank Login","Android EriBank Payment",
							 "Android DotGame Login","Android DotGame Play"};
	
	String[] iOSTests = {"Android PlayStore Top10","Android PlayStore Download",
							 "Android EriBank Login","Android EriBank Payment",
							 "Android DotGame Login","Android DotGame Play"};

	public ThreadController() {
		
	}
	
	public TestManager getTestByName(String testName, String udId) throws MalformedURLException {
		if( testName.contains("Android"))
		{
			if(testName.contains("PlayStore"))
			{
				if(testName.contains("Top10"))
				{
					return new PlayStoreTop10FreeAppsTest(udId);
				}
				if(testName.contains("Download"))
				{
					return new AndroidDownloadAppTest(udId);
				}
			}
			
			if(testName.contains("EriBank"))
			{
				if(testName.contains("Login"))
				{
					return new EriBankLoginTest(udId);
				}
				if(testName.contains("Payment"))
				{
					return new EriBankPaymentTest(udId);
				}
			}
			
			if(testName.contains("DotGame"))
			{
				if(testName.contains("Login"))
				{
					return new DotGameLoginTest(udId);
				}
				if(testName.contains("Play"))
				{
					return new PlayDotGameTest(udId);
				}
			}
		}
		if( testName.contains("IOS"))
		{
			System.out.println("Im here - IOS");
		}
		return null;
	}
}
