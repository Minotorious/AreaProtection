package AreaProtection;

import net.risingworld.api.gui.GuiPanel;
import net.risingworld.api.gui.GuiTextField;
import net.risingworld.api.gui.GuiLabel;
import net.risingworld.api.gui.PivotPosition;
import net.risingworld.api.gui.Font;

import java.util.ArrayList;

public class AreaProtectionGUI {
    
    public GuiLabel CreateAreaNameGUI(String AreaName){
        GuiLabel AreaNameLabel = new GuiLabel(AreaName,0.05f,0.05f,true);
        AreaNameLabel.setPivot(PivotPosition.BottomLeft);
        AreaNameLabel.setFont(Font.Default);
        AreaNameLabel.setFontColor(1.0f, 1.0f, 1.0f, 1.0f);
        AreaNameLabel.setColor(0.0f, 0.0f, 0.0f, 0.6f);
        AreaNameLabel.setFontSize(18);
                
        return AreaNameLabel;
    }
    
    public GuiPanel CreatePermissionsGuiPanel(AreaProtectionMain.PlayerPermissions PP){

        GuiPanel PermissionsPanel = new GuiPanel(0.8f,0.5f,true,0.3f,0.3f,true);
        PermissionsPanel.setColor(0.0f, 0.0f, 0.0f, 0.6f);
        PermissionsPanel.setPivot(PivotPosition.Center);
        PermissionsPanel.setBorderColor(0, 0, 1.0f, 1.0f);
        PermissionsPanel.setBorderThickness(2, false);

        return PermissionsPanel;
    }

    public ArrayList<GuiLabel> CreatePermissionsGuiLabels(AreaProtectionMain.PlayerPermissions PP, String AreaName){

        ArrayList<GuiLabel> PermissionsLabels = new ArrayList<>();

        GuiLabel AreaNameLabel = new GuiLabel(AreaName,0.5f,0.5f,true);
        AreaNameLabel.setFont(Font.Default);
        AreaNameLabel.setFontColor(1.0f, 1.0f, 1.0f, 1.0f);
        AreaNameLabel.setClickable(true);
        AreaNameLabel.setFontSize(18);
        PermissionsLabels.add(AreaNameLabel);

        GuiLabel PlayerNameLabel = new GuiLabel(Long.toString(PP.PlayerUserID),0,0,true);
        GuiLabel AddPlayersLabel = new GuiLabel(Boolean.toString(PP.AddPlayers),0,0,true);
        GuiLabel PlaceObjectLabel = new GuiLabel(Boolean.toString(PP.PlaceObject),0,0,true);
        GuiLabel PlaceConstructionLabel = new GuiLabel(Boolean.toString(PP.PlaceConstruction),0,0,true);
        GuiLabel PlaceBlockLabel = new GuiLabel(Boolean.toString(PP.PlaceBlock),0,0,true);
        GuiLabel PlaceVegetationLabel = new GuiLabel(Boolean.toString(PP.PlaceVegetation),0,0,true);
        GuiLabel PlaceGrassLabel = new GuiLabel(Boolean.toString(PP.PlaceGrass),0,0,true);
        GuiLabel PlaceTerrainLabel = new GuiLabel(Boolean.toString(PP.PlaceTerrain),0,0,true);
        GuiLabel PlaceWaterLabel = new GuiLabel(Boolean.toString(PP.PlaceWater),0,0,true);
        GuiLabel RemoveObjectLabel = new GuiLabel(Boolean.toString(PP.RemoveObject),0,0,true);
        GuiLabel RemoveConstructionLabel = new GuiLabel(Boolean.toString(PP.RemoveConstruction),0,0,true);
        GuiLabel RemoveVegetationLabel = new GuiLabel(Boolean.toString(PP.RemoveVegetation),0,0,true);
        GuiLabel RemoveGrassLabel = new GuiLabel(Boolean.toString(PP.RemoveGrass),0,0,true);
        GuiLabel RemoveWaterLabel = new GuiLabel(Boolean.toString(PP.RemoveWater),0,0,true);
        GuiLabel DestroyObjectLabel = new GuiLabel(Boolean.toString(PP.DestroyObject),0,0,true);
        GuiLabel DestroyConstructionLabel = new GuiLabel(Boolean.toString(PP.DestroyConstruction),0,0,true);
        GuiLabel DestroyBlockLabel = new GuiLabel(Boolean.toString(PP.DestroyBlock),0,0,true);
        GuiLabel DestroyVegetationLabel = new GuiLabel(Boolean.toString(PP.DestroyVegetation),0,0,true);
        GuiLabel DestroyTerrainLabel = new GuiLabel(Boolean.toString(PP.DestroyTerrain),0,0,true);
        GuiLabel ChangeObjectStatusLabel = new GuiLabel(Boolean.toString(PP.ChangeObjectStatus),0,0,true);
        GuiLabel CreateBlueprintLabel = new GuiLabel(Boolean.toString(PP.CreateBlueprint),0,0,true);
        GuiLabel PlaceBlueprintLabel = new GuiLabel(Boolean.toString(PP.PlaceBlueprint),0,0,true);
        GuiLabel EnterAreaLabel = new GuiLabel(Boolean.toString(PP.EnterArea),0,0,true);
        GuiLabel ExitAreaLabel = new GuiLabel(Boolean.toString(PP.ExitArea),0,0,true);
        GuiLabel ChestDropLabel = new GuiLabel(Boolean.toString(PP.ChestDrop),0,0,true);
        GuiLabel ChestToInventoryLabel = new GuiLabel(Boolean.toString(PP.ChestToInventory),0,0,true);
        GuiLabel InventoryToChestLabel = new GuiLabel(Boolean.toString(PP.InventoryToChest),0,0,true);
        GuiLabel InventoryAddLabel = new GuiLabel(Boolean.toString(PP.InventoryAdd),0,0,true);

        return PermissionsLabels;
    }
    
}
