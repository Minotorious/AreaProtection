package AreaProtection.Listeners;

import net.risingworld.api.events.Listener;

import net.risingworld.api.events.player.inventory.PlayerChestDropEvent;
import net.risingworld.api.events.player.inventory.PlayerChestToInventoryEvent;
import net.risingworld.api.events.player.inventory.PlayerInventoryToChestEvent;
import net.risingworld.api.events.player.inventory.PlayerInventoryAddEvent;

import net.risingworld.api.objects.Player;
import net.risingworld.api.utils.Vector3f;
import net.risingworld.api.events.EventMethod;

public class AreaProtectionInventoryListener implements Listener{
    
    @EventMethod
    public void onChestDrop(PlayerChestDropEvent event){
        Player player = event.getPlayer();
        int chest = event.getChestID();
        player.sendTextMessage("Chest drop " + chest);
    }
    
    @EventMethod
    public void onChestToInv(PlayerChestToInventoryEvent event){
        Player player = event.getPlayer();
        int chest = event.getChestID();
        player.sendTextMessage("Chest to Inventory: " + chest);
    }
    
    @EventMethod
    public void onInvToChestDrop(PlayerInventoryToChestEvent event){
        Player player = event.getPlayer();
        int chest = event.getChestID();
        player.sendTextMessage("Inventory to Chest: " + chest);
    }
    
    @EventMethod
    public void onInvAdd(PlayerInventoryAddEvent event){
        Player player = event.getPlayer();
        Vector3f globalpos = player.getPosition();
        player.sendTextMessage("Inventory Add: " + globalpos.toString());
    }
}
