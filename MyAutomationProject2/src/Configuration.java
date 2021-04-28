import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Scanner;

import com.google.gson.Gson;

public class Configuration {
	
	private String cloudUrl = "";
	private String username = "";
	private String accessKey = "";
	private int numOfDevices;
	private String serialNumber = "";
	private String testToRun = "";
	private int repeat;
	private String udID = "";
	
	public Configuration() throws FileNotFoundException, IOException {
		String[] data = new String[8];
		try {
		      File myObj = new File("src/Configuration File.txt");
		      Scanner myReader = new Scanner(myObj);
		      int index = 0;
		      while (myReader.hasNextLine()) {
		    	  data[index] = myReader.nextLine().split("=")[1];
		    	  index++;
		      }
		      myReader.close();
		    } catch (FileNotFoundException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }
		this.cloudUrl = data[0];
		this.username = data[1];
		this.accessKey = data[2];
		this.numOfDevices = Integer.parseInt(data[3]);
		this.serialNumber = data[4];
		this.testToRun = data[5];
		this.repeat = Integer.parseInt(data[6]);
		this.udID = data[7];
	}
	
	
	public String getUdID() {
		return udID;
	}


	public void setUdID(String udID) {
		this.udID = udID;
	}


	public String getCloudUrl() {
		return cloudUrl;
	}


	public void setCloudUrl(String cloudUrl) {
		this.cloudUrl = cloudUrl;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getAccessKey() {
		return accessKey;
	}


	public void setAccessKey(String accessKey) {
		this.accessKey = accessKey;
	}


	public int getNumOfDevices() {
		return numOfDevices;
	}


	public void setNumOfDevices(int numOfDevices) {
		this.numOfDevices = numOfDevices;
	}


	public String getSerialNumber() {
		return serialNumber;
	}


	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}


	public String getTestToRun() {
		return testToRun;
	}


	public void setTestToRun(String testToRun) {
		this.testToRun = testToRun;
	}


	public int getRepeat() {
		return repeat;
	}


	public void setRepeat(int repeat) {
		this.repeat = repeat;
	}


	@Override
	public String toString() {
		String toReturn = "cloudUrl: " + cloudUrl + ", \n" +
							"username: " + username + ", \n" +
							"accessKey: " + accessKey + ", \n" +
							"numOfDevices: " + numOfDevices + ", \n" +
							"serialNumber: " + serialNumber + ", \n" +
							"testToRun: " + testToRun + ", \n" +
							"repeat: " + repeat + ", \n";
		
		return toReturn;
	}
	
	
}
