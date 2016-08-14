/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package packetconstructor;

import java.util.BitSet;

public class RawPacketSender {
    
    public static void sendPacket(Packet p){
            BitSet bitsToSend = new BitSet();
        int currentLength = 0;
        for (Section section : p.getSections()){
            System.out.println("Section is: \t" +  section.getName());
	    int buff = 8;
            for(Field field: section.getFields()){
                System.out.println("\tField is: \t" + field.getName());
                System.out.println("\t\t\t" + Utilities.bytesToHex(field.getValue().toByteArray()));
		int remaining = field.getLength();
		BitSet bitsOfField = field.getValue();
		int sectionPos = 0;
		while(remaining > 0){
		    if(remaining >= buff){
			for (int j = 0; j < buff; j++){
			    bitsToSend.set(currentLength + j, bitsOfField.get(sectionPos + j));
			}
			remaining -= buff;
			buff = 8;
			currentLength += 8;
			sectionPos += 8;
		    }else{
			for(int j = 0; j < remaining; j++){
			    bitsToSend.set(currentLength + buff - remaining + j, bitsOfField.get(sectionPos + j));
			}
			    buff -= remaining;
			    remaining = 0; 
		    }
		}
            }
        }
	
        System.out.println("Sending..." +bitsToSend);
        byte[] bytesToSend = bitsToSend.toByteArray();
        System.out.println(Utilities.bytesToHex(bytesToSend));
        new RawPacketSender().sendPacket(bytesToSend);
    }
    
    
    public native void sendPacket(byte[] buffer);
    
    static {
        System.load("/home/zak/NetBeansProjects/RawPacketOutputterJNI/dist/libRawPacketOutputterJNI.so");
    }
}
