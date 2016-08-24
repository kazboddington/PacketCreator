/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package packetconstructor;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.Logger;


public class InternetConfigurations {
    private static String ip;
    private static String mac;
    
    public InternetConfigurations(){
	try {
	    Enumeration<NetworkInterface> network = NetworkInterface.getNetworkInterfaces();
	    while (network.hasMoreElements()){
		NetworkInterface networkInterface = network.nextElement();
		byte[] macAddr = networkInterface.getHardwareAddress();
		//don't use interfaces without mac addresses
		if (macAddr != null){
		    mac =  Utilities.bytesToHex(macAddr);
		    Enumeration<InetAddress> inetArresses = networkInterface.getInetAddresses();
		    while (inetArresses.hasMoreElements()){
			InetAddress address = inetArresses.nextElement();
			//use this to see the off things it puts out
			if (address.getAddress().length == 4){
			    ip = address.getHostAddress();
			}
		    }
		}
	    }
	    System.out.println(ip);
	    System.out.println(mac);
	} catch (SocketException ex) {
	    System.err.println("Error in getting network");
	}

	
   }
    public static String getIp(){
	return ip;
    }
    public static String getMacAddress() { 
	return mac;
    }
}
