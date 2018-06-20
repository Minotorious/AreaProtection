package AreaProtection.Listeners;

import AreaProtection.AreaProtectionMain;
import AreaProtection.DatabaseStuff;
import AreaProtection.DataBank;

import net.risingworld.api.events.Listener;
import net.risingworld.api.events.player.PlayerCommandEvent;
import net.risingworld.api.objects.Player;
import net.risingworld.api.events.EventMethod;
import net.risingworld.api.utils.Area;
import net.risingworld.api.utils.Utils;
import net.risingworld.api.utils.Vector3f;
import net.risingworld.api.utils.Vector3i;
import net.risingworld.api.worldelements.WorldArea;

public class AreaProtectionCommandListener implements Listener{
    
    @EventMethod
    public void onCommand(PlayerCommandEvent event){
        Player player = event.getPlayer();
        String command = event.getCommand();
        
        String[] cmd = command.split(" ");
        if (cmd[0].equals("/ap")){
            if (cmd.length >= 2){
                DatabaseStuff DbS = new DatabaseStuff();
                DataBank DB = new DataBank();
                AreaProtectionMain APM = new AreaProtectionMain();
                switch (cmd[1]){
                    case "select":
                        if (player.isAdmin()){
                            player.enableAreaSelectionTool();
                        }
                        break;
                    case "cancel":
                        player.disableAreaSelectionTool();
                        break;
                    case "create":
                        if (player.isAdmin()){
                            if (cmd.length == 3){
                                String AreaName = cmd[2];
                                player.getAreaSelectionData((Area protectedarea) -> {
                                    if(protectedarea == null){
                                        player.sendTextMessage("[#FF0000]No area is selected! Use /portal select to select an area first");
                                    }
                                    else{
                                        protectedarea.rearrange();
                                        Vector3i StartChunkPos = protectedarea.getStartChunkPosition();
                                        Vector3i StartBlockPos = protectedarea.getStartBlockPosition();
                                        Vector3f GlobalStartPos = Utils.ChunkUtils.getGlobalPosition(StartChunkPos, StartBlockPos);

                                        Vector3i EndChunkPos = protectedarea.getEndChunkPosition();
                                        Vector3i EndBlockPos = protectedarea.getEndBlockPosition();
                                        Vector3f GlobalEndPos = Utils.ChunkUtils.getGlobalPosition(EndChunkPos, EndBlockPos);

                                        AreaProtectionMain.ProtectedArea PA = APM.new ProtectedArea();
                                        PA.AreaName = AreaName;
                                        PA.StartChunkposX = StartChunkPos.getX();
                                        PA.StartChunkposY = StartChunkPos.getY();
                                        PA.StartChunkposZ = StartChunkPos.getZ();
                                        PA.StartBlockposX = StartBlockPos.getX();
                                        PA.StartBlockposY = StartBlockPos.getY();
                                        PA.StartBlockposZ = StartBlockPos.getZ();
                                        PA.GlobalStartposX = GlobalStartPos.getX();
                                        PA.GlobalStartposY = GlobalStartPos.getY();
                                        PA.GlobalStartposZ = GlobalStartPos.getZ();
                                        PA.EndChunkposX = EndChunkPos.getX();
                                        PA.EndChunkposY = EndChunkPos.getY();
                                        PA.EndChunkposZ = EndChunkPos.getZ();
                                        PA.EndBlockposX = EndBlockPos.getX();
                                        PA.EndBlockposY = EndBlockPos.getY();
                                        PA.EndBlockposZ = EndBlockPos.getZ();
                                        PA.GlobalEndposX = GlobalEndPos.getX();
                                        PA.GlobalEndposY = GlobalEndPos.getY();
                                        PA.GlobalEndposZ = GlobalEndPos.getZ();

                                        int idNo = DbS.createArea(PA);
                                        if (idNo > 0){
                                            PA.idNo = idNo;
                                        }
                                        else{
                                            PA.idNo = DB.getAllProtectedAreas().get(DB.getAllProtectedAreas().size()-1).idNo + 1;
                                        }
                                        DB.getAllProtectedAreas().add(PA);

                                        Vector3i StartChunk = new Vector3i(PA.StartChunkposX,PA.StartChunkposY,PA.StartChunkposZ);
                                        Vector3i StartBlock = new Vector3i(PA.StartBlockposX,PA.StartBlockposY,PA.StartBlockposZ);
                                        Vector3i EndChunk = new Vector3i(PA.EndChunkposX,PA.EndChunkposY,PA.EndChunkposZ);
                                        Vector3i EndBlock = new Vector3i(PA.EndBlockposX,PA.EndBlockposY,PA.EndBlockposZ);

                                        Area a = new Area(StartChunk, StartBlock, EndChunk, EndBlock);
                                        DB.getAllProtectedAreasAsAreas().add(a);

                                        APM.getServer().addArea(a);

                                        WorldArea WA = new WorldArea(a);
                                        WA.setAlwaysVisible(true);
                                        WA.setFrameVisible(true);
                                        int color = Utils.GeneralUtils.nextRandomColor(false);
                                        float red = Utils.GeneralUtils.intToColorR(color);
                                        float green = Utils.GeneralUtils.intToColorG(color);
                                        float blue = Utils.GeneralUtils.intToColorB(color);
                                        float alpha = 0.2f;
                                        WA.setColor(red, green, blue, alpha);

                                        DB.getAllProtectedAreasAsWorldAreas().add(WA);

                                        player.sendTextMessage("[#FF8800]Protected Area \"" +  AreaName +  "\" was successfully created!");
                                        player.disableAreaSelectionTool();
                                    }
                                });
                            }
                        }                        
                        break;
                    case "delete":
                        if (player.isAdmin()){
                            if (cmd.length == 3){
                                try{
                                    int idNo = Integer.parseInt(cmd[2]);
                                    DbS.deleteArea(idNo);
                                    boolean areafoundcheck = false;
                                    for (int i=0; i < DB.getAllProtectedAreas().size(); i++){
                                        if (DB.getAllProtectedAreas().get(i).idNo == idNo){
                                            areafoundcheck = true;
                                            
                                            String AreaName = DB.getAllProtectedAreas().get(i).AreaName;
                                            
                                            DB.getAllProtectedAreasAsWorldAreas().get(i).destroy();
                                            APM.getServer().removeArea(DB.getAllProtectedAreasAsAreas().get(i));
                                            
                                            DB.getAllProtectedAreas().remove(i);
                                            DB.getAllProtectedAreasAsAreas().remove(i);
                                            DB.getAllProtectedAreasAsWorldAreas().remove(i);
                                            
                                            player.sendTextMessage("[#FF8800]Area " + Integer.toString(idNo) + " named \"" + AreaName +  "\" was successfully removed!");
                                            break;
                                        }
                                    }
                                    if (areafoundcheck == false){
                                        player.sendTextMessage("[#FF0000]No Area with idNo " + Integer.toString(idNo) + " was found!");
                                    }
                                }
                                catch (NumberFormatException e){
                                    player.sendTextMessage("[#FF0000]Please enter a valid Area number");
                                }
                            }
                        }
                        break;
                    case "add":
                        if (cmd.length == 4){
                            Vector3f playerPos = player.getPosition();
                        }
                        AreaProtectionMain.PlayerPermissions pp = APM.new PlayerPermissions();
                        DbS.createPermissions(pp);
                        break;
                    case "remove":
                        break;
                    case "addclan":
                        if (player.isAdmin()){
                            
                        }
                        break;
                    case "removeclan":
                        if (player.isAdmin()){
                            
                        }
                        break;
                    case "perm":
                        if (cmd.length == 4){
                            String PermissionName = cmd[2];
                            boolean PermissionValue = Boolean.parseBoolean(cmd[3]);
                            String AreaName = (String) player.getAttribute("MinoArea");
                            
                        }
                        break;
                    case "list":
                        if (player.isAdmin()){
                            
                        }
                        break;
                    case "info":
                        break;
                    case "show":
                        for (WorldArea wa : DB.getAllProtectedAreasAsWorldAreas()){
                            player.addWorldElement(wa);
                        }
                        break;
                    case "hide":
                        for (WorldArea wa : DB.getAllProtectedAreasAsWorldAreas()){
                            player.removeWorldElement(wa);
                        }
                        break;
                    case "tp":
                        if (player.isAdmin()){
                            
                        }
                        break;
                    case "reload":
                        if (player.isAdmin()){
                            
                        }
                        break;
                    case "help":
                        player.sendTextMessage("Available AreaProtection Commands:");
                        
                        if (player.isAdmin()){
                            
                        }
                        break;
                    default:
                        player.sendTextMessage("[#FF0000]You have to enter a correct AreaProtection command. Type /ap help to see all valid inputs");
                        break;
                }
            }
        }
    }
    
}
