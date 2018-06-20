package AreaProtection.Listeners;

import net.risingworld.api.events.Listener;

import net.risingworld.api.events.player.world.PlayerPlaceVegetationEvent;
import net.risingworld.api.events.player.world.PlayerRemoveVegetationEvent;
import net.risingworld.api.events.player.world.PlayerDestroyVegetationEvent;

import net.risingworld.api.objects.Player;
import net.risingworld.api.utils.Vector3f;
import net.risingworld.api.events.EventMethod;

public class AreaProtectionVegetationListener implements Listener{
    
    @EventMethod
    public void onVegetationPlace(PlayerPlaceVegetationEvent event){
        Player player = event.getPlayer();
        Vector3f globalpos = event.getPlantPosition();
        player.sendTextMessage("Plant Placed: " + globalpos.toString());
    }
    
    @EventMethod
    public void onVegetationRemove(PlayerRemoveVegetationEvent event){
        Player player = event.getPlayer();
        Vector3f globalpos = event.getPlantPosition();
        player.sendTextMessage("Plant Removed: " + globalpos.toString());
    }
    
    @EventMethod
    public void onVegetationDestroy(PlayerDestroyVegetationEvent event){
        Player player = event.getPlayer();
        Vector3f globalpos = event.getPlantPosition();
        player.sendTextMessage("Plant Destroyed: " + globalpos.toString());
    }
    
}
