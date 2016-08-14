/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package packetconstructor;

import java.net.URL;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import jdk.nashorn.internal.runtime.CodeStore;


public class FXMLDocumentController implements Initializable {
    
    Packet displayedPacket;
    
    @FXML
    VBox centralDisplay;

    @FXML
    Button sendButton;
    
    void initializePacket(){
        //create and initialise new packet
        displayedPacket = new Packet();
        BitSet sourceEthAddr = Utilities.bitSetFromHex("432534679864");
        BitSet destinationEthAddr = Utilities.bitSetFromHex("005362718394");
        BitSet etherType = Utilities.bitSetFromHex("0811");
        EthernetHeader ethernetHeader = new EthernetHeader(sourceEthAddr,destinationEthAddr,etherType);
        displayedPacket.addSection(ethernetHeader);
        
        //Add IPv4 header
	displayedPacket.addSection(IPv4Header.exampleHeader());
	RawPacketSender.sendPacket(displayedPacket);
    }
    
    public void displayPacket(Packet p){
	    
	//loop through all the fields of the packet
	for (Section section: p.getSections()){
	    FlowPane sectionPane = new FlowPane();
	    sectionPane.setMaxWidth(section.getPreferredWidth()*10);
	    for (Field field: section.getFields()){
		//create VBox to place field in
		VBox fieldBox = new VBox();
		
		//create label to describe field
		Label nameLabel = new Label(field.getName());
		
		//Create custom textBox to display field
		PacketTextField textBox = new PacketTextField(field);
		textBox.setEditable(true);
		textBox.setText(field.getValueAsString());
		textBox.setMaxWidth(field.getLength()*10);
		textBox.setMinSize(field.getLength()*10, textBox.getHeight());
		
		//Bind the value of the box to the field
		    
		//Add elements to the display
		fieldBox.getChildren().add(nameLabel);
		fieldBox.getChildren().add(textBox);
		sectionPane.getChildren().add(fieldBox);
	    }
	    centralDisplay.getChildren().add(sectionPane);
	}
	
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initializePacket();
	displayPacket(displayedPacket);
    } 
    
    @FXML
    public void sendButtonPressed(ActionEvent e){
	RawPacketSender.sendPacket(displayedPacket);
    }
}
