package RestProject.RestProject;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class TestBase {
	private Properties properties;
	private final String propertyFilePath= "configs//Configuration.properties";
 
	
	public TestBase(){
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(propertyFilePath));
			properties = new Properties();
			try {
				properties.load(reader);
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new RuntimeException("Configuration.properties not found at " + propertyFilePath);
		}		
	}
	
	public String getApplicationUrl() {
		String url = properties.getProperty("url");
		if(url != null) return url;
		else throw new RuntimeException("url not specified in the Configuration.properties file.");
	}
	
	public String getAPP_ID() {
		String APP_ID = properties.getProperty("APPID");
		if(APP_ID != null) return APP_ID;
		else throw new RuntimeException("APP_ID not specified in the Configuration.properties file.");
	}
}