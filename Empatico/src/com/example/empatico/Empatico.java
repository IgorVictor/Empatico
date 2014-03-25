package com.example.empatico;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.List;

import com.example.empatico.models.Component;
import com.example.empatico.utils.IOUtils;
import com.example.empatico.utils.NetworkUtils;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.Display;
import android.media.audiofx.BassBoost.Settings;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.Toast;

public class Empatico extends Activity{
		
	
		private short maxPerLine;
		private String absolutPath;
		@Override
		public void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
			File fileDir = this.getFilesDir();
			absolutPath = fileDir.getAbsolutePath();
			

			//Log.d("Path do aplicativo", fileDir.getAbsolutePath());
			
			
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
			int[] buttonImages = prepareImageArray();
			HorizontalScrollView horizontalLayout = prepareHorizontal();
			ScrollView verticalLayout = prepareVertical();
			TableLayout layout = prepareTable();
			mountLayout(horizontalLayout, verticalLayout,
					layout);
			setButtonsOnLayout(xTam, yTam, buttonImages, layout, left, top, right, bottom);
			super.setContentView(horizontalLayout);
		}

		private void setButtonsOnLayout(int xTam, int yTam, int[] buttonImages,
				TableLayout layout, int left, int top, int right, int bottom) {
			int i = 0;
			TableRow tr = new TableRow(this);
			tr.setPadding(left, top, right, bottom);
			
			HelpButton config = createButton(R.drawable.settings,this);
			tr.setBackgroundResource(R.drawable.softbar);
			tr.addView(config);
			while (i<buttonImages.length){
				if(i%maxPerLine == 0){
					layout.addView(tr);
					tr = new TableRow(this);
					tr.setPadding(0, 10, 0, 10);
					HelpButton button = createButton(buttonImages[i],this);
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
					HelpButton button = createButton(buttonImages[i],this);
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
		private HelpButton createButton(int imageId, Context context){
			HelpButton b = new HelpButton(context);
			b.setImageResource(imageId);
			return b;
		}

		private void mountLayout(
				HorizontalScrollView horizontalLayout,
				ScrollView verticalLayout, TableLayout layout) {
			horizontalLayout.addView(verticalLayout);
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
