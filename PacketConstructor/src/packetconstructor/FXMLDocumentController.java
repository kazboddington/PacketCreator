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
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;


public class FXMLDocumentController implements Initializable {
    Packet displayedPacket;
    @FXML
    FlowPane userInputPane;
    
    @FXML
    Button sendButton;
    
    void initializePacket(){
        //create and initialise new packet
        
        displayedPacket = new Packet();
        //5254001235000800276fee3a080600010800060400010800276fee3a0a00020f0000000000000a000201
        BitSet sourceEthAddr = BitSet.valueOf(new byte[]{82,84,0,18,53,0});
        BitSet destinationEthAddr = BitSet.valueOf(new byte[]{8,0,39,111,110,58});
        BitSet etherType = BitSet.valueOf(new byte[]{6,8});
        EthernetHeader ethernetHeader = new EthernetHeader(sourceEthAddr,destinationEthAddr,etherType);
        System.out.println(Utilities.bytesToHex(sourceEthAddr.toByteArray()));
        System.out.println(Utilities.bytesToHex(destinationEthAddr.toByteArray()));
        System.out.println(Utilities.bytesToHex(etherType.toByteArray()));
        displayedPacket.addSection(ethernetHeader);
        
        
        //Print out packet and construct packet to send
        ArrayList<Byte> bytesToSend = new ArrayList<Byte>();
        ArrayList<BitSet> totalPacket = new ArrayList<BitSet>();
        for (Section section : displayedPacket.getSections()){
            System.out.println("Section is: \t" +  section.getName());
            for(Field field: section.getFields()){
                System.out.println("\tField is: \t" + field.getName());
                System.out.println("\t\t\t" + Utilities.bytesToHex(field.getValue().toByteArray()));
                //NOTE this only works for byte-aligned fields at the moment... :(
                for(Byte b: field.getValue().toByteArray()){
                    bytesToSend.add(b.byteValue());
                }
            }
        }
        System.out.println("Sending...");
        byte[] byteStreamToSend = new byte[bytesToSend.size()];
        for (int i = 0; i < bytesToSend.size(); i++){
            byteStreamToSend[i] = bytesToSend.get(i);
        }
        System.out.println(Utilities.bytesToHex(byteStreamToSend));
        new RawPacketSender().sendPacket(byteStreamToSend);
        return;
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initializePacket();
        ComboBox<String> testCombo = new ComboBox<>();
        testCombo.setEditable(true);
        userInputPane.getChildren().add(testCombo);
    } 
    
    @FXML
    public void sendButtonPressed(ActionEvent e){
        System.out.println("GREETINGS!");
        byte[] myBuffer = Utilities.hexStringToByteArray("5254001235000800276fee3a080600010800060400010800276fee3a0a00020f0000000000000a000201");
        new RawPacketSender().sendPacket(myBuffer);
    }
    /*
        @FXML
    public void sendButtonPressed(ActionEvent e){
        System.out.println("GREETINGS!");
        //byte[] myBuffer = Utilities.hexStringToByteArray("5254001235000800276fee3a080600010800060400010800276fee3a0a00020f0000000000000a000201");
        byte[] myBuffer = Utilities.hexStringToByteArray(firstCombo.getValue());
        new RawPacketSender().sendPacket(myBuffer);
    }
    */
    
}
