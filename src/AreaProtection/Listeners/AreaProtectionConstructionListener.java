package AreaProtection.Listeners;

import net.risingworld.api.events.Listener;

import net.risingworld.api.events.player.world.PlayerPlaceBlockEvent;
import net.risingworld.api.events.player.world.PlayerPlaceConstructionEvent;
import net.risingworld.api.events.player.world.PlayerRemoveConstructionEvent;
import net.risingworld.api.events.player.world.PlayerDestroyBlockEvent;
import net.risingworld.api.events.player.world.PlayerDestroyConstructionEvent;

import net.risingworld.api.objects.Player;
import net.risingworld.api.utils.Utils.ChunkUtils;
import net.risingworld.api.utils.Vector3i;
import net.risingworld.api.utils.Vector3f;
import net.risingworld.api.events.EventMethod;

public class AreaProtectionConstructionListener implements Listener{
    
    @EventMethod
    public void onBlockPlace(PlayerPlaceBlockEvent event){
        Player player = event.getPlayer();
        Vector3i chunkpos = new Vector3i(event.getChunkPositionX(), event.getChunkPositionY(), event.getChunkPositionZ());
        Vector3i blockpos = new Vector3i(event.getBlockPositionX(), event.getBlockPositionY(), event.getBlockPositionZ());
        Vector3f globalpos = new Vector3f();
        ChunkUtils.getGlobalPosition(chunkpos, blockpos, globalpos);
        
        player.sendTextMessage("Block Placed: " + globalpos.toString());
    }
    
    @EventMethod
    public void onBlockDestroy(PlayerDestroyBlockEvent event){
        Player player = event.getPlayer();
        Vector3i chunkpos = new Vector3i(event.getChunkPositionX(), event.getChunkPositionY(), event.getChunkPositionZ());
        Vector3i blockpos = new Vector3i(event.getBlockPositionX(), event.getBlockPositionY(), event.getBlockPositionZ());
        Vector3f globalpos = new Vector3f();
        ChunkUtils.getGlobalPosition(chunkpos, blockpos, globalpos);
        
        player.sendTextMessage("Block Destroyed: " + globalpos.toString());
    }
    
    @EventMethod
    public void onConstructionPlace(PlayerPlaceConstructionEvent event){
        Player player = event.getPlayer();
        Vector3f globalpos = event.getConstructionPosition();
        player.sendTextMessage("Construction Placed: " + globalpos.toString());
    }
    
    @EventMethod
    public void onConstructionRemove(PlayerRemoveConstructionEvent event){
        Player player = event.getPlayer();
        Vector3f globalpos = event.getConstructionPosition();
        player.sendTextMessage("Construction Removed: " + globalpos.toString());
    }
    
    @EventMethod
    public void onConstructionDestroy(PlayerDestroyConstructionEvent event){
        Player player = event.getPlayer();
        Vector3f globalpos = event.getConstructionPosition();
        player.sendTextMessage("Construction Destroyed: " + globalpos.toString());
    }
}
