package com.example.empatico;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.List;

import com.example.empatico.models.Component;
import com.example.empatico.utils.IOUtils;
import com.example.empatico.utils.JSONUtils;
import com.example.empatico.utils.NetworkUtils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Display;
import android.util.Log;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.ImageButton;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.Toast;

public class Empatico extends Activity{
		
	
		private short maxPerLine;
		private String absolutPath;
		private List<Component> components;
		
		
		@Override
		public void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
			File fileDir = this.getFilesDir();
			absolutPath = fileDir.getAbsolutePath();

			//Log.d("Path do aplicativo", fileDir.getAbsolutePath());

			if(!IOUtils.verifyJsonExists(this)){
				IOUtils.generateJson(this);
				Log.d("JSON EXIST", "JSON nao existe e foi criado");
				if(IOUtils.verifyJsonExists(this))
					Log.v("JSON EXIST", "JSON foi criado");
			}else{
				Log.v("JSON EXIST", "JSON já existe");
			}
			
			IOUtils.copyFromDefault(this);
			
			components = JSONUtils.generateComponents(this);
			
			for(Component c : components){
				if(IOUtils.verifyFileExists(this, c.getImagePath()))
					Log.d("VERIFICA ARQUIVO", c.getImagePath() + " foi copiado e pode ser aberto");
				else
					Log.e("VERIFICA ARQUIVO", c.getImagePath() + " não foi copiado e não pode ser aberto");
				
			}
			Display display = getWindowManager().getDefaultDisplay();
			int width = display.getWidth();
			int height = display.getHeight();
			
			maxPerLine = 3;
			int xTam = width/3;
			int yTam = height/2;
			
			int top = 0;
			int left = 0;
			int right = 0;
			int bottom = 20;
			//int[] buttonImages = prepareImageArray();
			ScrollView verticalLayout = prepareVertical();
			TableLayout layout = prepareTable();
			mountLayout(verticalLayout,
					layout);
			setButtonsOnLayout(xTam, yTam, components, layout, left, top, right, bottom);
			super.setContentView(verticalLayout);
		}

		private void setButtonsOnLayout(int xTam, int yTam, List<Component> buttonImages,
				TableLayout layout, int left, int top, int right, int bottom) {
			int i = 0;
			TableRow tr = new TableRow(this);
			tr.setPadding(left, top, right, bottom);
			
			ImageButton config = new ImageButton(this);
			config.setImageResource(R.drawable.settings);
			config.setOnClickListener(new View.OnClickListener() {
				 
				@Override
				public void onClick(View v) {
					
					Intent intent = new Intent(Empatico.this, Config.class);
					startActivity(intent);
				}
			});
		
			tr.setBackgroundResource(R.drawable.softbar);
			tr.addView(config);
			while (i<buttonImages.size()){
				if(i%maxPerLine == 0){ 
					layout.addView(tr);
					tr = new TableRow(this);
					tr.setPadding(0, 10, 0, 10);
					ImageButton button = createButton(buttonImages.get(i).getImagePath(),this);
					button.setPadding(0, 0, 20, 0);
					button.setOnClickListener(new View.OnClickListener() {
						 
						@Override
						public void onClick(View v) {
							
							sendUDPMessage("Teste de mensagem");
							
							Log.i("Path", absolutPath);
							Toast t = Toast.makeText(getApplicationContext(), "Mensagem: 'Teste de mensagem' enviada", Toast.LENGTH_SHORT);
							t.show();
							
						}
					});

					tr.addView(button, xTam,yTam);
					i++;
				}
				else {
					ImageButton button = createButton(buttonImages.get(i).getImagePath(),this);;
					button.setPadding(0, 0, 20, 0);
					button.setOnClickListener(new View.OnClickListener() {
						
						@Override
						public void onClick(View v) {
							sendUDPMessage("Teste de mensagem");
							
							Log.i("Path", absolutPath);
							Toast t = Toast.makeText(getApplicationContext(), "Mensagem: 'Teste de mensagem' enviada", Toast.LENGTH_SHORT);
							t.show();
						}
					});
					tr.addView(button, xTam,yTam);
					i++;
				}
			}
			if (!(i%maxPerLine == 0)){
				layout.addView(tr);
			}
		}
		private ImageButton createButton(String string, Context context){
			ImageButton b = new ImageButton(context);
			Bitmap bMap = getBitmapFromAsset("elements/default/img/" + string);
			Log.d("BitMap é NULL?", String.valueOf(bMap==null) + " " + string);
			b.setImageBitmap(bMap);
			return b;
		}
		//Isso tá sendo necessário porque estamos lendo de asset por enquanto. Tem como saber quando é de
		//asser e quando é external?
		private Bitmap getBitmapFromAsset(String strName)
		{
		    AssetManager assetManager = getAssets();
		    InputStream istr = null;
		    try {
		        istr = assetManager.open(strName);
		    } catch (IOException e) {
		        e.printStackTrace();
		    }
		    Bitmap bitmap = BitmapFactory.decodeStream(istr);
		    return bitmap;
		}

		private void mountLayout(
				ScrollView verticalLayout, TableLayout layout) {
			verticalLayout.addView(layout);
		}

		private TableLayout prepareTable() {
			TableLayout layout = new TableLayout(this);
			layout.setBackgroundResource(R.drawable.tela_back2);
			return layout;
		}

		private ScrollView prepareVertical() {
			ScrollView verticalLayout = new ScrollView(this);
			verticalLayout.setFillViewport(true);
			return verticalLayout;
		}

		private HorizontalScrollView prepareHorizontal() {
			HorizontalScrollView horizontalLayout = new HorizontalScrollView(this);
			horizontalLayout.setFillViewport(true);
			return horizontalLayout;
		}

		private int[] prepareImageArray() {
			int[] buttonImages = {R.drawable.banheiro, R.drawable.beber, R.drawable.brincar, R.drawable.comer,
					R.drawable.dormir, R.drawable.mal_estar, R.drawable.sair, R.drawable.triste, R.drawable.vestir};
			return buttonImages;
		}
		
		
		private static void sendUDPMessage(String msg) {

		    try {
		        DatagramSocket clientSocket = new DatagramSocket();

		        clientSocket.setBroadcast(true);
		        InetAddress address = InetAddress.getByName(NetworkUtils.getBroadcast());

		        byte[] sendData;

		        sendData = msg.getBytes();
		        DatagramPacket sendPacket = new DatagramPacket(sendData,
		                sendData.length, address, 5000);
		        clientSocket.send(sendPacket);

		        clientSocket.close();
		    } catch (Exception e) {
		        e.printStackTrace();
		    }

		}
		

}
