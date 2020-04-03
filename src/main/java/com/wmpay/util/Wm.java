package com.wmpay.util;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class Wm {
	
	/**
	 * 根据对象将为空对象属性剔除
	 * @param object
	 * @return
	 */
	public static Map<String, Object> getPastEmptyField(Object object) {
		Map<String, Object> responseMap = new HashMap<String, Object>();
		Field[] fields = object.getClass().getDeclaredFields();
	      for(int i = 0; i < fields.length; i++) {
		    	try {
		    		Field field = fields[i];
		    	  	String fieldName = field.getName();
		    	  	field.setAccessible(true);
		    		if (field.get(object) != null) {
		    			responseMap.put(fieldName, field.get(object));
		    		}
				} catch (Exception e) {
					e.printStackTrace();
				}	
	      }
	   return responseMap;
	}
	
}
