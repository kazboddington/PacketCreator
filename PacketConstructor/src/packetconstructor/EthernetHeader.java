/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package packetconstructor;

import java.util.BitSet;


public class EthernetHeader extends Header {
    private Field destinationMac = new Field("DestinationMac", 48, "This is the Destination Mac Address");
    private Field sourceMac = new Field("SourceMac", 48, "The Source mac address of the ");
    private Field etherType = new Field("Ethernet Type", 16, "The Type of Ethernet type (version?)"); 
    public EthernetHeader(){
        super();
        super.setName("Ethernet Header");
        super.setNumberOfFields(3);
        super.addField(destinationMac);
        super.addField(sourceMac);
        super.addField(etherType);
    }
    

}
