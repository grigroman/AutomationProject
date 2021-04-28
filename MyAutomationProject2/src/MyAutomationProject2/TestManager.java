package MyAutomationProject2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;

import Android.AndroidTest;
import io.appium.java_client.TouchAction;

//import static org.junit.Assert.fail;

//public class TestManager implements Runnable{
public class TestManager{
	
	protected int TIME_TO_WAIT = 20;
	protected URL Url;
	protected static DesiredCapabilities dc;
	protected static WebDriverWait wait;
	protected static TouchAction touch;
	
	private String accessKeyQACloud = "eyJhbGciOiJIUzI1NiJ9.eyJ4cC51Ijo0MjU5ODQ4LCJ4cC5wIjozOTQ5MDQ1LCJ4cC5tIjoxNjE4MTM5NTk2ODQ4LCJleHAiOjE5MzM0OTk1OTYsImlzcyI6ImNvbS5leHBlcml0ZXN0In0.PXLgQvx3w0xdhdYNV4Kkhoqbt6wbFePwhklVG5nhBY0";
	
	

	public TestManager(String UDid) throws MalformedURLException{
		
		dc = new DesiredCapabilities();
		Url = new URL("https://qacloud.experitest.com/wd/hub");
		dc.setCapability("accessKey", accessKeyQACloud);
		
		
//		if(device.equals("Android"))
//		{
//			AndroidTest androidTest = new AndroidTest(test_kind);
//		}
		
	}
	
	
	
	public DesiredCapabilities getDc() {
		return dc;
	}

	public void setDc(DesiredCapabilities dc) {
		TestManager.dc = dc;
	}

	public WebDriverWait getWait() {
		return wait;
	}

	public void setWait(WebDriverWait wait) {
		TestManager.wait = wait;
	}

	public TouchAction getTouch() {
		return touch;
	}

	public void setTouch(TouchAction touch) {
		TestManager.touch = touch;
	}
	
	public static List<List<String>> readFromCsv(String path){
		boolean first = true;

		List<List<String>> result = new ArrayList<>();
		try (BufferedReader br = new BufferedReader(new FileReader(path))) {

            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",", -1);
                if(first)
                {
                	first=false;
                }
                else
                {
                	result.add(Arrays.asList(values));
                }
            }
//            System.out.println(result);
            
        }catch (Exception e) {
        	System.out.println(e.toString());
		}
		return result;
	}
	
	

//	@Override
//	public void run() {
//		// TODO Auto-generated method stub
//		
//	}

}
