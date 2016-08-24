/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package packetconstructor;

import java.util.BitSet;
import javafx.scene.layout.VBox;

public class Field implements CustomisableFieldUI {
    private int length;
    private BitSet value;
    private String description;
    private String name;
    private CustomisableFieldUI customUIDrawer = (VBox conatiner, PacketTextField textField, Packet packet) ->{/*NOTE THAT DEFAULT BEHAVIOUR IS DO NOTHING*/ };
    
    
    public Field(String name, BitSet value, int length, String description){
        this.name = name;
        this.length = length;
        this.description = description;
        this.value = value;
    }
    public int getLength(){
        return length;
    }
    public BitSet getValue(){
        return value;
    }
    public String getValueAsString(){
	String retVal = Utilities.bytesToHex(this.getValue().toByteArray());
	//adjust for extra chars at front
	System.out.println(this.name + " is " +  retVal);
	if (retVal.length()*4 -3 > this.getLength()){
	    retVal = retVal.substring(1);
	}else if (retVal.length()*4 < this.getLength()){
	    //adjust for missing chars before;
	    
	    int numberOfMissingZeros = (this.getLength()-retVal.length()*4)/4;
	    System.out.println(numberOfMissingZeros);
	    for (int i = 0; i <numberOfMissingZeros ; i++){
		retVal = "0" + retVal;
	    }   
	}
	System.out.println(this.name + " is " +  retVal);
	return retVal;
    }
    public String getDescription(){
        return description;
    }
    public String getName(){
        return name;
    }

    public void setValue(BitSet value) {
	this.value = value;
    }
    public void drawCustomUI(VBox comtainer, PacketTextField textField, Packet packet){
	
    }


    public CustomisableFieldUI getCustomUIDrawer() {
	return customUIDrawer;
    }

    public void setCustomUIDrawer(CustomisableFieldUI customUIDrawer) {
	this.customUIDrawer = customUIDrawer;
    }
	
    
}
