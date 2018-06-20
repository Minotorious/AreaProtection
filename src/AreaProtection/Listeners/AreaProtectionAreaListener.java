package AreaProtection.Listeners;

import AreaProtection.AreaProtectionMain;
import AreaProtection.DataBank;

import net.risingworld.api.events.Listener;
import net.risingworld.api.events.player.PlayerEnterAreaEvent;
import net.risingworld.api.events.player.PlayerLeaveAreaEvent;
import net.risingworld.api.objects.Player;
import net.risingworld.api.events.EventMethod;
import net.risingworld.api.utils.Area;
import net.risingworld.api.gui.GuiLabel;

public class AreaProtectionAreaListener implements Listener{
    
    @EventMethod
    public void onAreaEnter(PlayerEnterAreaEvent event){
        DataBank DB = new DataBank();
        Player player = event.getPlayer();
        
        int count = 0;
        boolean permfoundcheck = false;
        
        for (Area a : DB.getAllProtectedAreasAsAreas()){
            if (event.getArea().equals(a)){
                for (AreaProtectionMain.PlayerPermissions pp : DB.getAllPlayerPermissions()){
                    if (pp.PlayerUserID == player.getUID() && pp.AreaidNo == DB.getAllProtectedAreas().get(count).idNo){
                        permfoundcheck = true;
                        
                        if (pp.EnterArea == false){
                            event.setCancelled(true);
                        }
                        else{
                            player.setAttribute("MinoArea", DB.getAllProtectedAreas().get(count).AreaName);
                            GuiLabel label = (GuiLabel) player.getAttribute("MinoLabel");
                            label.setText((String) player.getAttribute("MinoArea"));
                        }
                        break;
                    }
                }
                if (permfoundcheck == false){
                    if (DB.getRolePermissionsByName("guest").EnterArea == true){
                        player.setAttribute("MinoArea", DB.getAllProtectedAreas().get(count).AreaName);
                        GuiLabel label = (GuiLabel) player.getAttribute("MinoLabel");
                        label.setText((String) player.getAttribute("MinoArea"));
                    }
                    else{
                        event.setCancelled(true);
                    }
                }
                break;
            }
            count += 1;
        }
    }
    
    @EventMethod
    public void onAreaLeave(PlayerLeaveAreaEvent event){
        DataBank DB = new DataBank();
        Player player = event.getPlayer();
        
        int count = 0;
        boolean permfoundcheck = false;
        
        for (Area a : DB.getAllProtectedAreasAsAreas()){
            if (event.getArea().equals(a)){
                for (AreaProtectionMain.PlayerPermissions pp : DB.getAllPlayerPermissions()){
                    if (pp.PlayerUserID == player.getUID() && pp.AreaidNo == DB.getAllProtectedAreas().get(count).idNo){
                        if (pp.ExitArea == false){
                            event.setCancelled(true);
                        }
                        else{
                            player.setAttribute("MinoArea", "");
                            GuiLabel label = (GuiLabel) player.getAttribute("MinoLabel");
                            label.setText((String) player.getAttribute("MinoArea"));
                        }
                        break;
                    }
                }
                if (permfoundcheck == false){
                    if (DB.getRolePermissionsByName("guest").ExitArea == true){
                        player.setAttribute("MinoArea", "");
                        GuiLabel label = (GuiLabel) player.getAttribute("MinoLabel");
                        label.setText((String) player.getAttribute("MinoArea"));
                    }
                    else{
                        event.setCancelled(true);
                    }
                }
                break;
            }
            count += 1;
        }
    }
}
