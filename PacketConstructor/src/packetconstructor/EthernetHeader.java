/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package packetconstructor;

import java.util.BitSet;


public class EthernetHeader extends Section {
    private Field destinationMac; 
    private Field sourceMac; 
    private Field etherType;  
    public EthernetHeader(BitSet source, BitSet destination, BitSet etherType ){
        super();
        if (source.length() != 48 && destination.length() != 48 && etherType.length() != 16){
            System.out.print("Ethernet was initialised with incorrect sized fields");
        }
        this.destinationMac = new Field("DestinationMac", destination, 48, "This is the Destination Mac Address");
        this.sourceMac = new Field("SourceMac", source,48 , "The Source mac address of the ");
        this.etherType = new Field("Ethernet Type", etherType, 16, "The Type of Ethernet type (version?)");
        super.setName("Ethernet Header");
        super.setNumberOfFields(3);
        super.addField(destinationMac);
        super.addField(sourceMac);
        super.addField(this.etherType);
    }
}
