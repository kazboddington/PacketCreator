/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package packetconstructor;

import java.util.ArrayList;

public class Packet {
    
    ArrayList<Section> sections = new ArrayList<Section>();
    
    public ArrayList<Section> getSections() {
        return sections;
    }
    
    public void addSection(Section section){
        this.sections.add(section);
    }    
    
    
}
