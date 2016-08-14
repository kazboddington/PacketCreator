/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package packetconstructor;

import java.util.BitSet;

public class Field {
    private int length;
    private BitSet value;
    private String description;
    private String name;
    
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
	if (retVal.length()*4 -3 > this.getLength()){
	    retVal = retVal.substring(1);
	}else if (retVal.length()*4 < this.getLength()){
	    //adjust for missing chars before;
	    for (int i = 0; i < (this.getLength()-retVal.length()*4); i++){
		retVal = "0" + retVal;
	    }   
	}
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
}
