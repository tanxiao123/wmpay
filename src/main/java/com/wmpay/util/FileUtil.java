package com.wmpay.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


public class FileUtil {
	
	public static Properties properties;
	
	public static void loadProperties(String filePath) {
		if (FileUtil.properties == null) {
			FileUtil.properties = new Properties();
		}
		
		InputStream input = new Object().getClass().getResourceAsStream(filePath);
		try {
			FileUtil.properties.load(input);
		}catch (IOException e) {
			System.out.println("出错了："+e.getMessage());
		}finally {
			try {
				input.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public static String getPropertiesValue(String key) {
		return FileUtil.properties.get(key).toString();
	}
}
