/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package packetmaker;

import java.util.BitSet;

public class Field {
    private int length;
    private BitSet value;
    private String description;
    private String name;
    
    public Field(String name ,int length, String description){
        this.name = name;
        this.length = length;
        this.description = description;
        
    }
    public int getLength(){
        return length;
    }
    public BitSet getValue(){
        return value;
    }
    public String getDescription(){
        return description;
    }
    public String getName(){
        return name;
    }
}
