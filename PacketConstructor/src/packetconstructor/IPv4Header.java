/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package packetconstructor;

import com.sun.org.apache.bcel.internal.generic.F2D;
import java.util.BitSet;


public class IPv4Header extends Section{
    
    private Field version;
    private Field headerLength;
    private Field typeOfService;
    private Field totalLength;
    private Field identifier;
    private Field flags;
    private Field fragmentOffset;
    private Field timeToLive;
    private Field protocol;
    private Field headerChecksum;
    private Field sourceAddress;
    private Field destinationAddress;
    private Field options;// Note OPTIONAL
    
    public static IPv4Header exampleHeader(){
        //IPv4Header header = new IPv4Header();
        BitSet version = Utilities.bitSetFromHex("4");
	BitSet headerLength = Utilities.bitSetFromHex("5");
	BitSet typeOfService = Utilities.bitSetFromHex("ab");
	BitSet totalLength = Utilities.bitSetFromHex("4ab5");
	BitSet identifier = Utilities.bitSetFromHex("4453");
	BitSet flags = Utilities.bitSetFromHex("7");
	BitSet fragmentOffset = Utilities.bitSetFromHex("0ba1");
	BitSet timeToLive = Utilities.bitSetFromHex("44");
	BitSet protocol = Utilities.bitSetFromHex("44");
	BitSet headerChecksum = Utilities.bitSetFromHex("4444");
	BitSet sourceAddress = Utilities.bitSetFromHex("45678912");
	BitSet destinationAddress = Utilities.bitSetFromHex("98765432");
	
	
	
	System.out.println("Created header! ");
	return new IPv4Header(	 version, 
			 headerLength, 
			 typeOfService,
			 totalLength,
			 identifier,
			 flags,
			 fragmentOffset,
			 timeToLive,
			 protocol,
			 headerChecksum,
			 sourceAddress,
			 destinationAddress);
       
    }
    
    public IPv4Header(	BitSet version, 
			BitSet headerLength, 
			BitSet typeOfService,
			BitSet totalLength,
			BitSet identifier,
			BitSet flags,
			BitSet fragmentOffset,
			BitSet timeToLive,
			BitSet protocol,
			BitSet headerChecksum,
			BitSet sourceAddress,
			BitSet destinationAddress){
	super();
	this.version = new Field("Version", version, 4, "IP version, such as 4 or 6");
	this.headerLength = new Field("Header Length", headerLength, 4, "The length of this header / 32 (bits)");
	this.typeOfService = new Field("Type Of Service", typeOfService, 8, "");//TODO
	this.totalLength = new Field("Total Length", totalLength, 16, "The total Length of the IPv4 Packet");
	this.identifier = new Field("Identifier", identifier, 16, "");//TODO
	this.flags = new Field("Flags", flags, 3, "");//TODO
	this.fragmentOffset = new Field("Fragment Offset", fragmentOffset, 13, "");//TODO
	this.timeToLive = new Field("Time to Live", timeToLive, 8, "");//TODO
	this.protocol = new Field("Protocol", protocol, 8, "");
	this.headerChecksum = new Field("Header Checksum", headerChecksum, 16, "");
	this.sourceAddress = new Field("Source Address", sourceAddress, 32, "The IP of the sender of the packet");
	this.destinationAddress = new Field("Destination Address", destinationAddress, 32, "The IP of the reciever of the packet");
	
	super.setName("IPv4 Header");
        super.setNumberOfFields(12);
	super.setPreferredWidth(32);
	
	super.addField(this.version);
	super.addField(this.headerLength);
	super.addField(this.typeOfService);
	super.addField(this.totalLength);
	super.addField(this.identifier);
	super.addField(this.flags);
	super.addField(this.fragmentOffset);
	super.addField(this.timeToLive);
	super.addField(this.protocol);
	super.addField(this.headerChecksum);
	super.addField(this.sourceAddress);
	super.addField(this.destinationAddress);
    }
    
    
}
