package com.qa.base;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class TestBase {
//Declared property file gobally to the class	
public Properties properties;

public static int RESPONSE_CODE_200 =200;
public int RESPONSE_CODE_500 =500;
public int RESPONSE_CODE_400 =400;
public int RESPONSE_CODE_401 =401;
public static int RESPONSE_CODE_201 =201;

	public TestBase() {
		BufferedReader reader;
		String propertyFilePath = System.getProperty("user.dir")+"\\src\\test\\java\\com\\qa\\config\\config.properties";
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

}
