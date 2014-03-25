package com.example.empatico.utils;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.util.Log;

import com.example.empatico.models.Component;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class JSONUtils {
	
	
	
	public static String generateJSonString(List<Component> components){
		Gson json = new Gson();
		
		String jsonContent = json.toJson(components);
		
		return jsonContent;
	}
	
	
	public static List<Component> generateComponents(Activity act){
		String jsonContent = IOUtils.getJsonContent(act.getAssets());
		
		Gson gson = new Gson();

		Type t = new TypeToken<List<Component>>(){}.getType();
		
		List<Component> result = gson.fromJson(jsonContent, t);
		
		
		for(Component c : result)
			Log.d("Compoennte", c.toString());
		
		
		
		return result;
		
	}
	
}
