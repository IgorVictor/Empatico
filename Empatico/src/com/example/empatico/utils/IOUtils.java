package com.example.empatico.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

import com.example.empatico.models.Component;
import com.google.gson.Gson;

import android.app.Activity;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.util.Log;



public class IOUtils {
	
	private static final String ROOT_DIRECTORY = "elements";
	private static final String JSON_DIRECTORY = "json";
	private static final String DEFAULT_DIRECTORY = "default";
	private static final String JSON_FILE = "components.json";
	private static final String IMG_DIR = "img";
	
	public static String getJsonContent(AssetManager asset){
		String content = "";
		try {
			InputStream is = asset.open(ROOT_DIRECTORY + File.separator + DEFAULT_DIRECTORY + File.separator + 
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
	
	
	public static boolean verifyFileExists(Activity act, String fileName){
		return (new File(act.getApplicationContext().getFilesDir(), fileName)).exists();
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
	
	
	
	
	public static void copyFromDefault(Activity act){
		AssetManager asset = act.getAssets();
		
		try {
			
			List<String> files = Arrays.asList("a01.png", "a02.png", "adefinir.png", "banheiro.png", "beber.png", "brincar.png",
								  "comer.png", "dormir.png", "mal_estar.png", "sair.png", "settings.png", "tela_back.png",
								  "tela_inicial.png", "triste.png", "vestir.png");
			
			
			InputStream fileContent;
			
			for(String s : files){
				fileContent = asset.open(ROOT_DIRECTORY + File.separator + DEFAULT_DIRECTORY + File.separator + 
									 IMG_DIR + File.separator + s);
				
				int size = fileContent.available();
				byte[] buffer = new byte[size];
				fileContent.read(buffer);
				fileContent.close();
				String content = new String(buffer);
				
				File f = new File(act.getApplicationContext().getFilesDir(), s);
				FileOutputStream os;
				try {
					os = new FileOutputStream(f);
					os.write(content.getBytes());
					os.close();
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
			}
			
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
	}
	
	
}
