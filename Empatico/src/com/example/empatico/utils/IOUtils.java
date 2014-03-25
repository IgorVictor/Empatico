package com.example.empatico.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import android.content.res.AssetManager;
import android.util.Log;

import com.example.empatico.models.Component;
import com.google.gson.Gson;

public class IOUtils {
	
	private static final String ROOT_DIRECTORY = "elements";
	private static final String IMG_DIRECTORY = "img";
	private static final String SOUNDS_DIRECTORY = "sounds";
	private static final String JSON_DIRECTORY = "json";
	private static final String USABLE_DIRECTORY = "use";
	private static final String JSON_FILE = "components.json";
	private static final String DEFAULT_DIRECTORY = "default";
	
	public static List<Component> generateComponents(AssetManager assetMgr){
		List<Component> result = new ArrayList<Component>();
		
		try {
			InputStream jsonFile = assetMgr.open(ROOT_DIRECTORY +  File.separator+ USABLE_DIRECTORY + 
										File.separator + JSON_DIRECTORY + File.separator + JSON_FILE );
			
			InputStreamReader reader = new InputStreamReader(jsonFile);
			BufferedReader buffer = new BufferedReader(reader);
			
			String jsonContent = "";
			String line = buffer.readLine();
			while(line != null){
				jsonContent += line;
			}
			
			Log.d("Conteudo JSON",jsonContent);
			
			Gson parser = new Gson();
			//result = parser.fromJson(jsonContent, List.class);
			
			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		
		return result;
	}
	
	
	
	
	
}
