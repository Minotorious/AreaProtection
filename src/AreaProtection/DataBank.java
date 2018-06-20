package AreaProtection;

import net.risingworld.api.gui.GuiLabel;
import net.risingworld.api.gui.GuiPanel;
import net.risingworld.api.utils.Area;
import net.risingworld.api.worldelements.WorldArea;

import java.util.ArrayList;

public class DataBank {
    
    static ArrayList<GuiLabel> GuiPermissionsLabels = new ArrayList<>();
    static GuiPanel GuiPermissionsPanel =  new GuiPanel();
    static ArrayList<AreaProtectionMain.PlayerPermissions> AllPlayerPermissions = new ArrayList<>();
    static ArrayList<AreaProtectionMain.ProtectedArea> AllProtectedAreas = new ArrayList<>();
    static ArrayList<AreaProtectionMain.RolePermissions> AllRolePermissions = new ArrayList<>();
    static ArrayList<Area> AllProtectedAreasAsAreas =  new ArrayList<>();
    static ArrayList<WorldArea> AllProtectedAreasAsWorldAreas = new ArrayList<>();
    
    public void setGuiPanel(GuiPanel panel){
        GuiPermissionsPanel = panel;
    }
    
    public GuiPanel getGuiPanel(){
        return GuiPermissionsPanel;
    }
    
    public void setGuiLabels(ArrayList<GuiLabel> labels){
        GuiPermissionsLabels = labels;
    }
    
    public ArrayList<GuiLabel> getGuiLabels(){
        return GuiPermissionsLabels;
    }
    
    public void setAllPlayerPermissions(ArrayList<AreaProtectionMain.PlayerPermissions> permissions){
        AllPlayerPermissions = permissions;
    }
    
    public ArrayList<AreaProtectionMain.PlayerPermissions> getAllPlayerPermissions(){
        return AllPlayerPermissions;
    }
    
    public void setAllProtectedAreas(ArrayList<AreaProtectionMain.ProtectedArea> areas){
        AllProtectedAreas = areas;
    }
    
    public ArrayList<AreaProtectionMain.ProtectedArea> getAllProtectedAreas(){
        return AllProtectedAreas;
    }
    
    public void setAllProtectedAreasAsAreas(ArrayList<Area> areas){
        AllProtectedAreasAsAreas = areas;
    }
    
    public ArrayList<Area> getAllProtectedAreasAsAreas(){
        return AllProtectedAreasAsAreas;
    }
    
    public void setAllProtectedAreasAsWorldAreas(ArrayList<WorldArea> areas){
        AllProtectedAreasAsWorldAreas = areas;
    }
    
    public ArrayList<WorldArea> getAllProtectedAreasAsWorldAreas(){
        return AllProtectedAreasAsWorldAreas;
    }
    
    public void setAllRolePermissions(ArrayList<AreaProtectionMain.RolePermissions> roles){
        AllRolePermissions = roles;
    }
    
    public ArrayList<AreaProtectionMain.RolePermissions> getAllRolePermissions(){
        return AllRolePermissions;
    }
    
    public AreaProtectionMain.RolePermissions getRolePermissionsByName(String RoleName){
        AreaProtectionMain APM = new AreaProtectionMain();
        AreaProtectionMain.RolePermissions rp = APM.new RolePermissions();
        for (AreaProtectionMain.RolePermissions RP : AllRolePermissions){
            if (RP.RoleName.equals(RoleName)){
                rp = RP;
                break;
            }
        }
        return rp;
    }
}
