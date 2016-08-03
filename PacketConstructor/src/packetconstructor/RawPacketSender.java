/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package packetconstructor;

public class RawPacketSender {
    
    public native void sendPacket(byte[] buffer);
    
    static {
        System.load("/home/zak/NetBeansProjects/RawPacketOutputterJNI/dist/libRawPacketOutputterJNI.so");
    }
}
