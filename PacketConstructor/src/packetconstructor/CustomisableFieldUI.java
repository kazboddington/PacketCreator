/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package packetconstructor;

import javafx.scene.layout.VBox;

public interface CustomisableFieldUI {
    public void drawCustomUI(VBox conatiner, PacketTextField textField, Packet packet);
}
