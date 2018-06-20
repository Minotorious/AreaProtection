package AreaProtection.Listeners;

import AreaProtection.DataBank;

import net.risingworld.api.events.Listener;

import net.risingworld.api.events.player.gui.PlayerGuiElementClickEvent;

import net.risingworld.api.objects.Player;
import net.risingworld.api.events.EventMethod;
import net.risingworld.api.gui.GuiElement;
import net.risingworld.api.gui.GuiLabel;

public class AreaProtectionGuiListener implements Listener{
    
    @EventMethod
    public void onGuiElementClick(PlayerGuiElementClickEvent event){
        Player player = event.getPlayer();
        GuiElement element = event.getGuiElement();
        player.sendTextMessage("label clicked: " + element.toString()); 
        DataBank DB = new DataBank();
        
        for (GuiLabel label : DB.getGuiLabels()){
            player.sendTextMessage(label.toString());
            if (element.equals(label)){
                player.sendTextMessage("label if check: " + label.toString());
            }
        }
    }
}
