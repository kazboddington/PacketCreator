/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package packetconstructor;

import java.math.BigInteger;
import java.util.BitSet;


public final class Utilities {
    final protected static char[] hexArray = "0123456789ABCDEF".toCharArray();
    public static String bytesToHex(byte[] bytes) {
        char[] hexChars = new char[bytes.length * 2];
        for ( int j = 0; j < bytes.length; j++ ) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = hexArray[v >>> 4];
            hexChars[j * 2 + 1] = hexArray[v & 0x0F];
        }
        return new String(hexChars);
    }
    public static byte[] hexStringToByteArray(String s) {
        int len = s.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
                                 + Character.digit(s.charAt(i+1), 16));
        }
    return data;
    }
    public static BitSet bitSetFromHex(String s){
	if (s.length() < 1){
	    System.out.println("Length of Hex too small");
	}
	if((s.length() %2) == 1){
	    s = "0" + s ;
	}
	BitSet returnVal = BitSet.valueOf(hexStringToByteArray(s));
	return returnVal;
    }
    
    public static String insertPeriodically(String text, String insert, int period){
	StringBuilder builder = new StringBuilder(
	     text.length() + insert.length() * (text.length()/period)+1);

	int index = 0;
	String prefix = "";
	while (index < text.length())
	{
	    // Don't put the insert in the very first iteration.
	    // This is easier than appending it *after* each substring
	    builder.append(prefix);
	    prefix = insert;
	    builder.append(text.substring(index, 
		Math.min(index + period, text.length())));
	    index += period;
	}
	return builder.toString();
    }
    
    static String hexToBin(String s) {
	String retVal = new BigInteger(s, 16).toString(2);
	
	if (retVal.length() % 2 == 1)
	    retVal = "0"+ retVal;
	for (int i = 0; i< s.length() ;i++){
	    if (s.charAt(i) == '0'){
		retVal = "0000" + retVal; 
	    }else{
		break;
    	    }
	}
	return retVal;
    }
    
    public static String hexToDecimal(String s){
	//TODO support longer values
	return String.valueOf(Long.parseLong(s, 16));
    }
    
    public static String hexToDecimalBytesWithSpaces(String s){
	
	if (s.length() % 2 == 1 )
	    s = "0" + s;
	    String retVal = "";
	for (int i = 0; i < s.length()-1; i+=2){
	    retVal += Integer.parseInt(s.substring(i,i+2),16) + " ";
	}
	
	return retVal;
    }
    
}
