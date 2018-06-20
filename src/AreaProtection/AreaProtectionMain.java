package AreaProtection;

import AreaProtection.Listeners.AreaProtectionCommandListener;
import AreaProtection.Listeners.AreaProtectionObjectListener;
import AreaProtection.Listeners.AreaProtectionVegetationListener;
import AreaProtection.Listeners.AreaProtectionConstructionListener;
import AreaProtection.Listeners.AreaProtectionTerrainListener;
import AreaProtection.Listeners.AreaProtectionBlueprintListener;
import AreaProtection.Listeners.AreaProtectionAreaListener;
import AreaProtection.Listeners.AreaProtectionInventoryListener;
import AreaProtection.Listeners.AreaProtectionGuiListener;

import net.risingworld.api.gui.GuiLabel;
import net.risingworld.api.gui.GuiPanel;
import net.risingworld.api.Plugin;
import net.risingworld.api.events.Listener;
import net.risingworld.api.events.EventMethod;
import net.risingworld.api.events.player.PlayerSpawnEvent;
import net.risingworld.api.database.Database;
import net.risingworld.api.objects.Player;
import net.risingworld.api.utils.Area;
import net.risingworld.api.utils.Vector3i;
import net.risingworld.api.utils.Utils.GeneralUtils;
import net.risingworld.api.worldelements.WorldArea;

import java.util.ArrayList;
import java.io.File;
import net.risingworld.api.utils.Utils;

public class AreaProtectionMain extends Plugin implements Listener{
    
    @Override
    public void onEnable(){
        //Register event listener
        AreaProtectionCommandListener APComL = new AreaProtectionCommandListener();
        AreaProtectionConstructionListener APConL = new AreaProtectionConstructionListener();
        AreaProtectionObjectListener APObjL = new AreaProtectionObjectListener();
        AreaProtectionVegetationListener APVegL = new AreaProtectionVegetationListener();
        AreaProtectionTerrainListener APTerL = new AreaProtectionTerrainListener();
        AreaProtectionBlueprintListener APBluL = new AreaProtectionBlueprintListener();
        AreaProtectionAreaListener APAreL = new AreaProtectionAreaListener();
        AreaProtectionInventoryListener APInvL = new AreaProtectionInventoryListener();
        AreaProtectionGuiListener APGuiL = new AreaProtectionGuiListener();
        
        registerEventListener(APComL);
        /*
        registerEventListener(APConL);
        registerEventListener(APObjL);
        registerEventListener(APVegL);
        registerEventListener(APTerL);
        registerEventListener(APBluL);
        */
        registerEventListener(APAreL);
        //registerEventListener(APInvL);
        
        registerEventListener(APGuiL);
        registerEventListener(this);
        
        Database sqlite = getSQLiteConnection(getPath() + "/assets/areaprotection_" + getWorld().getName() + ".db");
        DatabaseStuff DbS = new DatabaseStuff();
        DbS.setDB(sqlite);
        DbS.initDB();
        DataBank DB = new DataBank();
        ArrayList<ProtectedArea> areas = DbS.getAreas();
        ArrayList<PlayerPermissions> permissions = DbS.getPermissions();
        DB.setAllProtectedAreas(areas);
        DB.setAllPlayerPermissions(permissions);
        CreateWorldAreas();
        ReadRolesFiles();
    }
    
    public void ReadRolesFiles(){
        DataBank DB = new DataBank();
        
        ArrayList<RolePermissions> roles = new ArrayList<>();
        
        File rolesDir = new File(getPath() +  "/assets/roles");
        File[] RoleFiles = rolesDir.listFiles();
        
        for (File f : RoleFiles){
            if (f.exists()){
                if (f.getName().endsWith(".role")){
                    String content = Utils.FileUtils.readStringFromFile(f);
                    if(content != null && !content.isEmpty()){
                        String[] lines = content.split("\r\n|\n|\r");
                        RolePermissions RP = new RolePermissions();
                        RP.RoleName = f.getName().replace(".role", "");
                        for (String line : lines) {
                            String[] cline = line.split("=", 2);
                            switch (cline[0]){
                                case "AddPlayers":
                                    RP.AddPlayers = Boolean.parseBoolean(cline[1]);
                                    break;
                                case "PlaceObject":
                                    RP.PlaceObject = Boolean.parseBoolean(cline[1]);
                                    break;
                                case "PlaceConstruction":
                                    RP.PlaceConstruction = Boolean.parseBoolean(cline[1]);
                                    break;
                                case "PlaceBlock":
                                    RP.PlaceBlock = Boolean.parseBoolean(cline[1]);
                                    break;
                                case "PlaceVegetation":
                                    RP.PlaceVegetation = Boolean.parseBoolean(cline[1]);
                                    break;
                                case "PlaceGrass":
                                    RP.PlaceGrass = Boolean.parseBoolean(cline[1]);
                                    break;
                                case "PlaceTerrain":
                                    RP.PlaceTerrain = Boolean.parseBoolean(cline[1]);
                                    break;
                                case "PlaceWater":
                                    RP.PlaceWater = Boolean.parseBoolean(cline[1]);
                                    break;
                                case "RemoveObject":
                                    RP.RemoveObject = Boolean.parseBoolean(cline[1]);
                                    break;
                                case "RemoveConstruction":
                                    RP.RemoveConstruction = Boolean.parseBoolean(cline[1]);
                                    break;
                                case "RemoveVegetation":
                                    RP.RemoveVegetation = Boolean.parseBoolean(cline[1]);
                                    break;
                                case "RemoveGrass":
                                    RP.RemoveGrass = Boolean.parseBoolean(cline[1]);
                                    break;
                                case "RemoveWater":
                                    RP.RemoveWater = Boolean.parseBoolean(cline[1]);
                                    break;
                                case "DestroyObject":
                                    RP.DestroyObject = Boolean.parseBoolean(cline[1]);
                                    break;
                                case "DestroyConstruction":
                                    RP.DestroyConstruction = Boolean.parseBoolean(cline[1]);
                                    break;
                                case "DestroyBlock":
                                    RP.DestroyBlock = Boolean.parseBoolean(cline[1]);
                                    break;
                                case "DestroyVegetation":
                                    RP.DestroyVegetation = Boolean.parseBoolean(cline[1]);
                                    break;
                                case "DestroyTerrain":
                                    RP.DestroyTerrain = Boolean.parseBoolean(cline[1]);
                                    break;
                                case "ChangeObjectStatus":
                                    RP.ChangeObjectStatus = Boolean.parseBoolean(cline[1]);
                                    break;
                                case "CreateBlueprint":
                                    RP.CreateBlueprint = Boolean.parseBoolean(cline[1]);
                                    break;
                                case "PlaceBlueprint":
                                    RP.PlaceBlueprint = Boolean.parseBoolean(cline[1]);
                                    break;
                                case "EnterArea":
                                    RP.EnterArea = Boolean.parseBoolean(cline[1]);
                                    break;
                                case "ExitArea":
                                    RP.ExitArea = Boolean.parseBoolean(cline[1]);
                                    break;
                                case "ChestDrop":
                                    RP.ChestDrop = Boolean.parseBoolean(cline[1]);
                                    break;
                                case "ChestToInventory":
                                    RP.ChestToInventory = Boolean.parseBoolean(cline[1]);
                                    break;
                                case "InventoryToChest":
                                    RP.InventoryToChest = Boolean.parseBoolean(cline[1]);
                                    break;
                                case "InventoryAdd":
                                    RP.InventoryAdd = Boolean.parseBoolean(cline[1]);
                                    break;
                                default:
                                    break;
                            }
                        }
                        roles.add(RP);
                    }
                }
            }
        }
        DB.setAllRolePermissions(roles);
    }
    
    @EventMethod
    public void onPlayerSpawn(PlayerSpawnEvent event){
        Player player = event.getPlayer();
        
        AreaProtectionGUI APGUI =  new AreaProtectionGUI();
        GuiLabel AreaNameLabel = APGUI.CreateAreaNameGUI("Default Area");
        player.setAttribute("MinoLabel", AreaNameLabel);
        player.addGuiElement(AreaNameLabel);
        /*
        PlayerPermissions DefaultPerm = new PlayerPermissions();
        DefaultPerm.idNo = 0;
        DefaultPerm.AreaName = "DefaultArea";
        DefaultPerm.PlayerName = "DefaultPlayer";
        GuiPanel PermissionsPanel = APGUI.CreatePermissionsGuiPanel(DefaultPerm);
        player.addGuiElement(PermissionsPanel);
        ArrayList<GuiLabel> PermissionsLabels = APGUI.CreatePermissionsGuiLabels(DefaultPerm);
        
        DataBank DB = new DataBank();
        DB.setGuiLabels(PermissionsLabels);
        DB.setGuiPanel(PermissionsPanel);
        
        for (GuiLabel label : PermissionsLabels){
            PermissionsPanel.addChild(label);
            player.addGuiElement(label);
        }
        //player.setMouseCursorVisible(true);
        */
        DataBank DB = new DataBank();
        ArrayList<RolePermissions> roles = DB.getAllRolePermissions();
        for (RolePermissions role : roles){
            player.sendTextMessage(role.RoleName);
        }
    }
    
    @Override
    public void onDisable(){
        
    }
    
    public void CreateWorldAreas(){
        ArrayList<Area> areas = new ArrayList<>();
        ArrayList<WorldArea> worldareas = new ArrayList<>();
        
        DataBank DB = new DataBank();
        
        for (ProtectedArea pa : DB.getAllProtectedAreas()){
            Vector3i StartChunk = new Vector3i(pa.StartChunkposX,pa.StartChunkposY,pa.StartChunkposZ);
            Vector3i StartBlock = new Vector3i(pa.StartBlockposX,pa.StartBlockposY,pa.StartBlockposZ);
            Vector3i EndChunk = new Vector3i(pa.EndChunkposX,pa.EndChunkposY,pa.EndChunkposZ);
            Vector3i EndBlock = new Vector3i(pa.EndBlockposX,pa.EndBlockposY,pa.EndBlockposZ);
            
            Area a = new Area(StartChunk, StartBlock, EndChunk, EndBlock);
            getServer().addArea(a);
            areas.add(a);
            
            WorldArea wa = new WorldArea(a);
            wa.setAlwaysVisible(true);
            wa.setFrameVisible(true);
            int color = GeneralUtils.nextRandomColor(false);
            float red = GeneralUtils.intToColorR(color);
            float green = GeneralUtils.intToColorG(color);
            float blue = GeneralUtils.intToColorB(color);
            float alpha = 0.2f;
            wa.setColor(red, green, blue, alpha);
            worldareas.add(wa);
        }
        DB.setAllProtectedAreasAsWorldAreas(worldareas);
        DB.setAllProtectedAreasAsAreas(areas);
    }
    
    public class ProtectedArea{
        public int idNo;
        public String AreaName;
        public int StartChunkposX;
        public int StartChunkposY;
        public int StartChunkposZ;
        public int StartBlockposX;
        public int StartBlockposY;
        public int StartBlockposZ;
        public float GlobalStartposX;
        public float GlobalStartposY;
        public float GlobalStartposZ;
        public int EndChunkposX;
        public int EndChunkposY;
        public int EndChunkposZ;
        public int EndBlockposX;
        public int EndBlockposY;
        public int EndBlockposZ;
        public float GlobalEndposX;
        public float GlobalEndposY;
        public float GlobalEndposZ;
    }
    
    public class PlayerPermissions{
        public int idNo;
        public int AreaidNo;
        public long PlayerUserID;
        public boolean AddPlayers = false;
        public boolean PlaceObject = false;
        public boolean PlaceConstruction = false;
        public boolean PlaceBlock = false;
        public boolean PlaceVegetation = false;
        public boolean PlaceGrass = false;
        public boolean PlaceTerrain = false;
        public boolean PlaceWater = false;
        public boolean RemoveObject = false;
        public boolean RemoveConstruction = false;
        public boolean RemoveVegetation = false;
        public boolean RemoveGrass = false;
        public boolean RemoveWater = false;
        public boolean DestroyObject = false;
        public boolean DestroyConstruction = false;
        public boolean DestroyBlock = false;
        public boolean DestroyVegetation = false;
        public boolean DestroyTerrain = false;
        public boolean ChangeObjectStatus = false;
        public boolean CreateBlueprint = false;
        public boolean PlaceBlueprint = false;
        public boolean EnterArea = true;
        public boolean ExitArea = true;
        public boolean ChestDrop = false;
        public boolean ChestToInventory = false;
        public boolean InventoryToChest = false;
        public boolean InventoryAdd = false;
    }
    
    public class RolePermissions{
        public String RoleName;
        public boolean AddPlayers = false;
        public boolean PlaceObject = false;
        public boolean PlaceConstruction = false;
        public boolean PlaceBlock = false;
        public boolean PlaceVegetation = false;
        public boolean PlaceGrass = false;
        public boolean PlaceTerrain = false;
        public boolean PlaceWater = false;
        public boolean RemoveObject = false;
        public boolean RemoveConstruction = false;
        public boolean RemoveVegetation = false;
        public boolean RemoveGrass = false;
        public boolean RemoveWater = false;
        public boolean DestroyObject = false;
        public boolean DestroyConstruction = false;
        public boolean DestroyBlock = false;
        public boolean DestroyVegetation = false;
        public boolean DestroyTerrain = false;
        public boolean ChangeObjectStatus = false;
        public boolean CreateBlueprint = false;
        public boolean PlaceBlueprint = false;
        public boolean EnterArea = true;
        public boolean ExitArea = true;
        public boolean ChestDrop = false;
        public boolean ChestToInventory = false;
        public boolean InventoryToChest = false;
        public boolean InventoryAdd = false;
    }
}
