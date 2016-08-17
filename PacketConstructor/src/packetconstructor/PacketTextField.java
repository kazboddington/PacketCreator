/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package packetconstructor;

import java.util.BitSet;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TextField;


public class PacketTextField extends TextField {

    private final int limit;
    private final Field field;
    private  boolean textShouldBeZero = false;
	    
    public PacketTextField(Field field) {
        this.limit = field.getLength()/4;
	this.field = field;
    }
    
  
    public boolean isTextShouldBeZero() {
	return textShouldBeZero;
    }

  
    public void setTextShouldBeZero(boolean textShouldBeZero) {
	this.textShouldBeZero = textShouldBeZero;
    }
    

}
