/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package packetconstructor;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.BitSet;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;


public class EthernetHeader extends Section {
    private Field destinationMac; 
    private Field sourceMac; 
    private Field etherType;  
    public EthernetHeader(BitSet source, BitSet destination, BitSet etherType ){
        super();
        if (source.length() != 48 && destination.length() != 48 && etherType.length() != 16){
            System.out.print("Ethernet was initialised with incorrect sized fields");
        }
        this.destinationMac = new Field("Destination Mac", destination, 48, "The Destination Maca address of the packet.");
	destinationMac.setCustomUIDrawer((VBox container, PacketTextField textField, Packet packet) -> {
	    Button setToCurrentMac= new Button("Set to My Mac");
	    setToCurrentMac.setOnAction((ActionEvent event) -> {
		textField.setText(InternetConfigurations.getMacAddress());
	    });
	    container.getChildren().add(setToCurrentMac);
	});
	this.sourceMac = new Field("Source Mac", source,48 , "The Source mac address of the packet.");
	sourceMac.setCustomUIDrawer((VBox container, PacketTextField textField, Packet packet) -> {
	    Button setToCurrentMac= new Button("Set to My Mac");
	    setToCurrentMac.setOnAction((ActionEvent event) -> {
		textField.setText(InternetConfigurations.getMacAddress());
	    });
	    container.getChildren().add(setToCurrentMac);
	});
        this.etherType = new Field("Ethernet Type", etherType, 16, "The version of Ethernet.");
        this.etherType.setCustomUIDrawer((VBox container, PacketTextField tf, Packet packet)->{
	    Button ipV4Button = new Button("Set to IPV4 etherType");
	    ipV4Button.setOnAction((ActionEvent e)->{
		tf.setText("0800");
	    });
	    container.getChildren().add(ipV4Button);
	});
	super.setName("Ethernet Header");
        super.setNumberOfFields(3);
        super.addField(destinationMac);
        super.addField(sourceMac);
        super.addField(this.etherType);
	
    }
}
