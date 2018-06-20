package AreaProtection.Listeners;

import net.risingworld.api.events.Listener;

import net.risingworld.api.events.player.world.PlayerCreateBlueprintEvent;
import net.risingworld.api.events.player.world.PlayerPlaceBlueprintEvent;

import net.risingworld.api.objects.Player;
import net.risingworld.api.utils.Vector3f;
import net.risingworld.api.events.EventMethod;

public class AreaProtectionBlueprintListener implements Listener{
    
    @EventMethod
    public void onBlueprintCreate(PlayerCreateBlueprintEvent event){
        Player player = event.getPlayer();
        Vector3f globalpos = event.getPosition();
        player.sendTextMessage("Blueprint Created: " + globalpos.toString());
    }
    
    @EventMethod
    public void onBlueprintPlace(PlayerPlaceBlueprintEvent event){
        Player player = event.getPlayer();
        Vector3f globalpos = event.getPosition();
        player.sendTextMessage("Blueprint Placed: " + globalpos.toString());
    }
    
}
