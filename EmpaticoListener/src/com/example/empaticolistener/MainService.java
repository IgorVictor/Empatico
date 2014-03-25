package com.example.empaticolistener;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

import android.net.DhcpInfo;
import android.net.wifi.WifiManager;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.widget.Toast;

@SuppressLint("NewApi") public class MainService extends Service {



	private static final int PORT = 5500;
	private Activity mContext;

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		runUdpServer();
		return Service.START_NOT_STICKY;
	}
	private AsyncTask<Void, Void, Void> async;
    private boolean Server_aktiv = true;

    public void runUdpServer() 
    {
        async = new AsyncTask<Void, Void, Void>() 
        {
            protected Void doInBackground(Void... params)
            {   
                byte[] lMsg = new byte[1000];
                DatagramPacket dp = new DatagramPacket(lMsg, lMsg.length);
                DatagramSocket ds = null;

                try 
                {
                    ds = new DatagramSocket(PORT);

                    while(Server_aktiv)
                    {
                        ds.receive(dp);
                        
                    }
                } 
                catch (Exception e) 
                {
                    e.printStackTrace();
                } 
                finally 
                {
                    if (ds != null) 
                    {
                        ds.close();
                    }
                }

                return null;
            }
        };

        if (Build.VERSION.SDK_INT >= 11) async.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        else async.execute();
    }

    public void stop_UDP_Server()
    {
        Server_aktiv = false;
    }
	
	InetAddress getBroadcastAddress() throws IOException {
		WifiManager wifi = (WifiManager) mContext.getSystemService(Context.WIFI_SERVICE);
		DhcpInfo dhcp = wifi.getDhcpInfo();
		// handle null somehow

		int broadcast = (dhcp.ipAddress & dhcp.netmask) | ~dhcp.netmask;
		byte[] quads = new byte[4];
		for (int k = 0; k < 4; k++)
			quads[k] = (byte) ((broadcast >> k * 8) & 0xFF);
		return InetAddress.getByAddress(quads);
	}



	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}

}
