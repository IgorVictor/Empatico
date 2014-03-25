package com.example.empatico.utils;

import java.util.List;

import com.example.empatico.models.Component;
import com.google.gson.Gson;

public class JSONUtils {
	
	
	
	public static String generateJSonString(List<Component> components){
		Gson json = new Gson();
		
		String jsonContent = json.toJson(components);
		
		return jsonContent;
	}
	
}
