/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package packetmaker;

import java.util.ArrayList;

public abstract class Header {
    private String name;
    private int numberOfFields;
    private ArrayList<Field> fields;

    void setName(String name) {
        this.name = name;
        
    }

    void setNumberOfFields(int i) {
        this.numberOfFields = i;
    }

    void addField(Field destinationMac) {
        this.fields.add(destinationMac);
    }
    
}
