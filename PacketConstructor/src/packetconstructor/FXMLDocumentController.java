/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package packetconstructor;

import java.net.URL;
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
    
    @FXML
    ComboBox<String> firstCombo; 
    
    @FXML
    Button sendButton;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        //firstCombo.setPromptText("WAZZUP");
        //firstTextField.setText("Greetings");
    } 
    
    @FXML
    public void sendButtonPressed(ActionEvent e){
        System.out.println("GREETINGS!");
        //byte[] myBuffer = Utilities.hexStringToByteArray("5254001235000800276fee3a080600010800060400010800276fee3a0a00020f0000000000000a000201");
        byte[] myBuffer = Utilities.hexStringToByteArray(firstCombo.getValue());
        new RawPacketSender().sendPacket(myBuffer);
    }
    
}
