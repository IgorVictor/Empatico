package com.example.empaticolistener;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

import android.net.DhcpInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.IBinder;
import android.app.Activity;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;

public class MainService extends Service {

	
	
    private static final int PORT = 5500;
	private Activity mContext;
    
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
    	DatagramSocket socket;
		try {
			socket = new DatagramSocket(PORT);
			socket.setBroadcast(true);
			byte[] buf = new byte[1024];
	    	DatagramPacket packet = new DatagramPacket(buf, buf.length);
	    	socket.receive(packet);
		} catch (SocketException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
       return Service.START_NOT_STICKY;
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
