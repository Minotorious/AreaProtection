package AreaProtection.Listeners;

import net.risingworld.api.events.Listener;

import net.risingworld.api.events.player.world.PlayerPlaceGrassEvent;
import net.risingworld.api.events.player.world.PlayerPlaceTerrainEvent;
import net.risingworld.api.events.player.world.PlayerPlaceWaterEvent;
import net.risingworld.api.events.player.world.PlayerRemoveGrassEvent;
import net.risingworld.api.events.player.world.PlayerRemoveWaterEvent;
import net.risingworld.api.events.player.world.PlayerDestroyTerrainEvent;

import net.risingworld.api.objects.Player;
import net.risingworld.api.utils.Utils.ChunkUtils;
import net.risingworld.api.utils.Vector3f;
import net.risingworld.api.utils.Vector3i;
import net.risingworld.api.events.EventMethod;

public class AreaProtectionTerrainListener implements Listener{
    
    @EventMethod
    public void onGrassPlace(PlayerPlaceGrassEvent event){
        Player player = event.getPlayer();
        Vector3i chunkpos = new Vector3i(event.getChunkPositionX(), event.getChunkPositionY(), event.getChunkPositionZ());
        Vector3i blockpos = new Vector3i(event.getBlockPositionX(), event.getBlockPositionY(), event.getBlockPositionZ());
        Vector3f globalpos = new Vector3f();
        ChunkUtils.getGlobalPosition(chunkpos, blockpos, globalpos);
        
        player.sendTextMessage("Grass Placed: " + globalpos.toString());
    }
    
    @EventMethod
    public void onTerrainPlace(PlayerPlaceTerrainEvent event){
        Player player = event.getPlayer();
        Vector3i chunkpos = new Vector3i(event.getChunkPositionX(), event.getChunkPositionY(), event.getChunkPositionZ());
        Vector3i blockpos = new Vector3i(event.getBlockPositionX(), event.getBlockPositionY(), event.getBlockPositionZ());
        Vector3f globalpos = new Vector3f();
        ChunkUtils.getGlobalPosition(chunkpos, blockpos, globalpos);
        
        player.sendTextMessage("Terrain Placed: " + globalpos.toString());
    }
    
    @EventMethod
    public void onWaterPlace(PlayerPlaceWaterEvent event){
        Player player = event.getPlayer();
        Vector3i chunkpos = new Vector3i(event.getChunkPositionX(), event.getChunkPositionY(), event.getChunkPositionZ());
        Vector3i blockpos = new Vector3i(event.getBlockPositionX(), event.getBlockPositionY(), event.getBlockPositionZ());
        Vector3f globalpos = new Vector3f();
        ChunkUtils.getGlobalPosition(chunkpos, blockpos, globalpos);
        
        player.sendTextMessage("Water Placed: " + globalpos.toString());
    }
    
    @EventMethod
    public void onGrassRemove(PlayerRemoveGrassEvent event){
        Player player = event.getPlayer();
        Vector3i chunkpos = new Vector3i(event.getChunkPositionX(), event.getChunkPositionY(), event.getChunkPositionZ());
        Vector3i blockpos = new Vector3i(event.getBlockPositionX(), event.getBlockPositionY(), event.getBlockPositionZ());
        Vector3f globalpos = new Vector3f();
        ChunkUtils.getGlobalPosition(chunkpos, blockpos, globalpos);
        
        player.sendTextMessage("Grass Removed: " + globalpos.toString());
    }
    
    @EventMethod
    public void onWaterRemove(PlayerRemoveWaterEvent event){
        Player player = event.getPlayer();
        Vector3i chunkpos = new Vector3i(event.getChunkPositionX(), event.getChunkPositionY(), event.getChunkPositionZ());
        Vector3i blockpos = new Vector3i(event.getBlockPositionX(), event.getBlockPositionY(), event.getBlockPositionZ());
        Vector3f globalpos = new Vector3f();
        ChunkUtils.getGlobalPosition(chunkpos, blockpos, globalpos);
        
        player.sendTextMessage("Water Removed: " + globalpos.toString());
    }
    
    @EventMethod
    public void onTerrainDestroy(PlayerDestroyTerrainEvent event){
        Player player = event.getPlayer();
        Vector3i chunkpos = new Vector3i(event.getChunkPositionX(), event.getChunkPositionY(), event.getChunkPositionZ());
        Vector3i blockpos = new Vector3i(event.getBlockPositionX(), event.getBlockPositionY(), event.getBlockPositionZ());
        Vector3f globalpos = new Vector3f();
        ChunkUtils.getGlobalPosition(chunkpos, blockpos, globalpos);
        
        player.sendTextMessage("Terrain Destroyed: " + globalpos.toString());
    }
    
}
