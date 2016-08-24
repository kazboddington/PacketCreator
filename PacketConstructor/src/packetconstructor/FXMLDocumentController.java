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
import javafx.beans.property.ObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;
import jdk.nashorn.internal.runtime.CodeStore;


public class FXMLDocumentController implements Initializable {
    
    @FXML VBox rightSideCustomContent;
    Packet displayedPacket;
    @FXML Label rightPanelFieldTitleLabel;
    @FXML VBox rightSideVBox;
    @FXML Label descriptionText; 
    @FXML TextFlow descriptionTextFlow;
    @FXML VBox centralDisplay;
    @FXML Button sendButton;
    @FXML Label userMacLabel;
    @FXML Label userIpLabel;
    ArrayList<PacketTextField> packetTextFields = new ArrayList<>();
    private InternetConfigurations configurations;
    
    
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
    }
    
    public void displayPacket(Packet p){
	    
	//loop through all the fields of the packet
	for (Section section: p.getSections()){
	    Label sectionTitle = new Label(section.getName());
	    sectionTitle.setFont(Font.font(null, FontWeight.BOLD, 14));
	    FlowPane sectionPane = new FlowPane();
	    sectionPane.setMaxWidth(section.getPreferredWidth()*10);
	    for (Field field: section.getFields()){
		
		//create VBox to place field in
		VBox fieldBox = new VBox();
		
		//create label to describe field
		Label fieldNameLabel = new Label(field.getName());
		fieldNameLabel.setMaxWidth(field.getLength()*10);
		//Create custom textBox to display field
		PacketTextField textBox = new PacketTextField(field);
		packetTextFields.add(textBox);
		
		addTextUpdateListener(textBox, (field.getLength()+3)/4,field);
		textBox.setEditable(true);
		textBox.setText(field.getValueAsString());
		textBox.setMaxWidth(field.getLength()*10);
		textBox.setMinSize(field.getLength()*10, textBox.getHeight());
		textBox.focusedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
		    //Sort out Side Bar
		    if (newValue){
			userIpLabel.setText("Your IP is: " + configurations.getIp());
			userMacLabel.setText("Mac address is " + InternetConfigurations.getMacAddress());
			descriptionText.setText(field.getDescription());
			rightPanelFieldTitleLabel.setText(field.getName());
			rightSideCustomContent.getChildren().clear();
			addSideBarInfo(field, rightSideCustomContent, textBox);
		    }
		});
		
		
		//Add elements to the display
		fieldBox.getChildren().add(fieldNameLabel);
		fieldBox.getChildren().add(textBox);
		sectionPane.getChildren().add(fieldBox);
	    }
	    centralDisplay.getChildren().addAll(sectionTitle, sectionPane);
	}
	
    }
    
    
    public void addSideBarInfo(Field field, VBox container, PacketTextField fieldTextBox){
	//Allow field to add its own custom items
	field.getCustomUIDrawer().drawCustomUI(container, fieldTextBox, displayedPacket);

	
	Label lengthLabel = new Label("\nField Length (bits): " + field.getLength());
	lengthLabel.setWrapText(true);
	
	String decimalString = Utilities.hexToDecimal(field.getValueAsString());
	Label decimalValue = new Label("\nDecimal: \n" + decimalString);
	decimalValue.setWrapText(true);
	
	String bytesAsDecimalString  = Utilities.hexToDecimalBytesWithSpaces(field.getValueAsString());
	Label bytesAsDecimal = new Label("\nDecimal Bytes: \n" + bytesAsDecimalString);
	bytesAsDecimal.setWrapText(true);
	
	Label hexValue = new Label("\nHex: \n" + Utilities.insertPeriodically(field.getValueAsString()," ", 2) + "\n");
	hexValue.setWrapText(true);
	
	String binaryString = Utilities.insertPeriodically(Utilities.hexToBin(field.getValueAsString()), " ", 4);
	Label binaryValue = new Label("\nBinary: \n" + binaryString );
	binaryValue.setWrapText(true);
	
	container.getChildren().addAll(lengthLabel, decimalValue, bytesAsDecimal, hexValue, binaryValue);
	
	
	
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
	configurations = new InternetConfigurations();
        initializePacket();
	displayPacket(displayedPacket);
    } 
    
    @FXML
    public void sendButtonPressed(ActionEvent e){
	
	for (PacketTextField tf: packetTextFields){
	    StringBuilder s = new StringBuilder();
	    s.append(tf.getField().getValueAsString());
	    
	    /*
	    for (int i = 0; i < tf.getField().getLength()- tf.getText().length(); i++){
		s.insert(0, "0");
		
	    }
	    */
	    tf.setText(s.toString());
	    System.out.println(s.toString());
	}
	RawPacketSender.sendPacket(displayedPacket);
    }
    
    public void addTextUpdateListener(final PacketTextField tf, final int maxLength, Field field) {
	tf.textProperty().addListener
	((final ObservableValue<? extends String> ov, final String oldValue, final String newValue)
		-> {
	    if(newValue.length() == 0){
		tf.setText("0");
		tf.setTextShouldBeZero(true);
	    }else if (!newValue.matches("[0-9a-fA-F]+")){
		tf.setText(oldValue);
	    }else if (tf.getText().length() > maxLength) {
		tf.setText(oldValue);
	    }else if (tf.isTextShouldBeZero() && newValue.length() == 2 ){
		tf.setText(newValue.substring(1));
		tf.setTextShouldBeZero(false);
	    } 
	    
	    field.setValue(Utilities.bitSetFromHex(tf.getText()));
	    rightSideCustomContent.getChildren().clear();
	    addSideBarInfo(field, rightSideCustomContent, tf);
	});
    }
}
    