/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package packetconstructor;

import java.util.ArrayList;

public abstract class Section {
    private String name;
    private int numberOfFields;
    private ArrayList<Field> fields = new ArrayList<Field>();

    void setName(String name) {
        this.name = name;
    }

    void setNumberOfFields(int i) {
        this.numberOfFields = i;
    }

    void addField(Field destinationMac) {
        this.getFields().add(destinationMac);
    }

    public ArrayList<Field> getFields() {
        return fields;
    }

    public String getName() {
        return name;
    }
    
    
}
