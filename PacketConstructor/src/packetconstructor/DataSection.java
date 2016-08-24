/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package packetconstructor;

import java.util.BitSet;
import javafx.scene.chart.PieChart;

public class DataSection extends Section{
    private Field allData;
    
    private DataSection(BitSet data, int length){
	super();
	allData = new Field("Dara", data, length, "Sendand raw data here");
    }
    
    
}
