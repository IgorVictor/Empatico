package com.example.empatico.utils;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

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
	
	/**
	 * 
	 * @param components - Componentes que as repetições serão filtradas pelo ID.
	 */
	public static void removeRepeat(List<Component> components){
		
		
	}
	
	public static List<Component> generateComponents(Activity act){
		String jsonContent = IOUtils.getJsonContent(act.getAssets());
		
		Gson gson = new Gson();

		Type t = new TypeToken<List<Component>>(){}.getType();
		
		List<Component> resultTemp = gson.fromJson(jsonContent, t);
		List<Component> result = new LinkedList<Component>();
		
		for(Component c : resultTemp){
			if(!result.contains(c)){
				Log.d("Compoennte", c.toString());
				result.add(c);
			}
		}
		
		
		
		
		
		
		/*Set<Component> filter = new TreeSet<Component>();
		
		
		for(Component c : result){
			filter.add(c);
		}
		
		result = new ArrayList<Component>();
		for(Component c : filter){
			Log.d("Compoennte", c.toString());
			result.add(c);
		}*/
			
		
		
		
		return result;
		
	}
	
}
