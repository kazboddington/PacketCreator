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
    
    public PacketTextField(Field field) {
        this.limit = field.getLength()/4;
	this.field = field;
	addTextUpdateListener(this, (field.getLength()+3)/4,field);
    }

    //ensure that the typed value is valid and update bitset value
    public static void addTextUpdateListener(final TextField tf, final int maxLength, Field field) {
	tf.textProperty().addListener(new ChangeListener<String>() {
	    @Override
	    public void changed(final ObservableValue<? extends String> ov, final String oldValue, final String newValue) {
		//restrict to only a hex value
		if (!newValue.matches("[0-9a-fA-F]+")){
		    tf.setText(oldValue);
		}else if (tf.getText().length() > maxLength) {
		    //ensure the value is not too long
		    String s = tf.getText().substring(0, maxLength);
		    tf.setText(s);
		    field.setValue(new BitSet());
		}
	    }
	});
    }

}
