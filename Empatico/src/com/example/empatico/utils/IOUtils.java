package com.example.empatico.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import com.example.empatico.models.Component;
import com.google.gson.Gson;

import android.app.Activity;
import android.content.res.AssetManager;



public class IOUtils {
	
	private static final String ROOT_DIRECTORY = "elements";
	private static final String JSON_DIRECTORY = "json";
	private static final String USABLE_DIRECTORY = "use";
	private static final String JSON_FILE = "components.json";
	
	public static String getJsonContent(AssetManager asset){
		String content = "";
		try {
			InputStream is = asset.open(ROOT_DIRECTORY + File.separator + USABLE_DIRECTORY + File.separator + 
										JSON_DIRECTORY + File.separator + JSON_FILE);
			
			int size = is.available();
			byte[] buffer = new byte[size];
			is.read(buffer);
			is.close();
			content = new String(buffer);
						
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return content;
	}
	
	
	public static void generateJson(Activity act){
		String string = getJsonContent(act.getAssets());
		
		File f = new File(act.getApplicationContext().getFilesDir(), JSON_FILE);
		FileOutputStream os;
		try {
			os = new FileOutputStream(f);
			os.write(string.getBytes());
			os.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	/*	try {
			FileOutputStream fos = act.openFileOutput(JSON_FILE, Context.MODE_PRIVATE);
			
			fos.write(string.getBytes());
			fos.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
		}*/
	}
	
	public static boolean verifyJsonExists(Activity act){
		return (new File(act.getApplicationContext().getFilesDir(), JSON_FILE)).exists();
	}
	
	
	public static void saveJson(List<Component> components, Activity act){
		String contentFile =  JSONUtils.generateJSonString(components);
		
			

		File f = new File(act.getApplicationContext().getFilesDir(), JSON_FILE);
		FileOutputStream os;
		try {
			os = new FileOutputStream(f);
			os.write(contentFile.getBytes());
			os.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}
