package com.example.empatico.utils;

import java.net.InterfaceAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

public class NetworkUtils {
	public static String getBroadcast() throws SocketException {
	    System.setProperty("java.net.preferIPv4Stack", "true");
	    for (Enumeration<NetworkInterface> niEnum = NetworkInterface.getNetworkInterfaces(); niEnum.hasMoreElements();) {
	        NetworkInterface ni = niEnum.nextElement();
	        if (!ni.isLoopback()) {
	            for (InterfaceAddress interfaceAddress : ni.getInterfaceAddresses()) {
	                return interfaceAddress.getBroadcast().toString().substring(1);
	            }
	        }
	    }
	    return null;
	}
}
