package AreaProtection.Listeners;

import net.risingworld.api.events.Listener;

import net.risingworld.api.events.player.world.PlayerPlaceObjectEvent;
import net.risingworld.api.events.player.world.PlayerRemoveObjectEvent;
import net.risingworld.api.events.player.world.PlayerDestroyObjectEvent;
import net.risingworld.api.events.player.world.PlayerChangeObjectStatusEvent;

import net.risingworld.api.objects.Player;
import net.risingworld.api.utils.Vector3f;
import net.risingworld.api.events.EventMethod;

public class AreaProtectionObjectListener implements Listener{
    
    @EventMethod
    public void onObjectStatusChange(PlayerChangeObjectStatusEvent event){
        Player player = event.getPlayer();
        Vector3f globalpos = event.getObjectPosition();
        player.sendTextMessage("Object Status Changed: " + globalpos.toString());
    }
    
    @EventMethod
    public void onObjectPlace(PlayerPlaceObjectEvent event){
        Player player = event.getPlayer();
        Vector3f globalpos = event.getObjectPosition();
        player.sendTextMessage("Object Placed: " + globalpos.toString());
    }
    
    @EventMethod
    public void onObjectRemove(PlayerRemoveObjectEvent event){
        Player player = event.getPlayer();
        Vector3f globalpos = event.getObjectPosition();
        player.sendTextMessage("Object Removed: " + globalpos.toString());
    }
    
    @EventMethod
    public void onObjectDestroy(PlayerDestroyObjectEvent event){
        Player player = event.getPlayer();
        Vector3f globalpos = event.getObjectPosition();
        player.sendTextMessage("Object Destroyed: " + globalpos.toString());
    }
    
}
