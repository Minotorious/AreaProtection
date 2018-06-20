package AreaProtection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import net.risingworld.api.database.Database;
import net.risingworld.api.utils.Vector3f;

public class DatabaseStuff {
    
    public static Database data;
    
    public void setDB(Database db){
        data = db;
    }
    
    public void initDB(){
        data.execute("CREATE TABLE IF NOT EXISTS 'Areas' ("
                + "'idNo' INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "'AreaName' VARCHAR(64), "
                + "'StartChunkposX' INTEGER, "
                + "'StartChunkposY' INTEGER, "
                + "'StartChunkposZ' INTEGER, "
                + "'StartBlockposX' INTEGER, "
                + "'StartBlockposY' INTEGER, "
                + "'StartBlockposZ' INTEGER, "
                + "'GlobalStartposX' FLOAT, "
                + "'GlobalStartposY' FLOAT, "
                + "'GlobalStartposZ' FLOAT, "
                + "'EndChunkposX' INTEGER, "
                + "'EndChunkposY' INTEGER, "
                + "'EndChunkposZ' INTEGER, "
                + "'EndBlockposX' INTEGER, "
                + "'EndBlockposY' INTEGER, "
                + "'EndBlockposZ' INTEGER, "
                + "'GlobalEndposX' FLOAT, "
                + "'GlobalEndposY' FLOAT, "
                + "'GlobalEndposZ' FLOAT "
                + ")");
        
        data.execute("CREATE TABLE IF NOT EXISTS 'Permissions' ("
                + "'idNo' INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "'AreaidNo' INTEGER, "
                + "'PlayerUserID' LONG, "
                + "'AddPlayers' BOOLEAN, "
                + "'PlaceObject' BOOLEAN, "
                + "'PlaceConstruction' BOOLEAN, "
                + "'PlaceBlock' BOOLEAN, "
                + "'PlaceVegetation' BOOLEAN, "
                + "'PlaceGrass' BOOLEAN, "
                + "'PlaceTerrain' BOOLEAN, "
                + "'PlaceWater' BOOLEAN, "
                + "'RemoveObject' BOOLEAN, "
                + "'RemoveConstruction' BOOLEAN, "
                + "'RemoveVegetation' BOOLEAN, "
                + "'RemoveGrass' BOOLEAN, "
                + "'RemoveWater' BOOLEAN, "
                + "'DestroyObject' BOOLEAN, "
                + "'DestroyConstruction' BOOLEAN, "
                + "'DestroyBlock' BOOLEAN, "
                + "'DestroyVegetation' BOOLEAN, "
                + "'DestroyTerrain' BOOLEAN, "
                + "'ChangeObjectStatus' BOOLEAN, "
                + "'CreateBlueprint' BOOLEAN, "
                + "'PlaceBlueprint' BOOLEAN, "
                + "'EnterArea' BOOLEAN, "
                + "'ExitArea' BOOLEAN, "
                + "'ChestDrop' BOOLEAN, "
                + "'ChestToInventory' BOOLEAN, "
                + "'InventoryToChest' BOOLEAN, "
                + "'InventoryAdd' BOOLEAN"
                + ")");
    }
    
    public int createArea(AreaProtectionMain.ProtectedArea pa){
        Connection con = data.getConnection();
        int row = 0;
        try{
            PreparedStatement prep = con.prepareStatement("INSERT INTO Areas ("
                    + "AreaName, "
                    + "StartChunkposX, "
                    + "StartChunkposY, "
                    + "StartChunkposZ, "
                    + "StartBlockposX, "
                    + "StartBlockposY, "
                    + "StartBlockposZ, "
                    + "GlobalStartposX, "
                    + "GlobalStartposY, "
                    + "GlobalStartposZ, "
                    + "EndChunkposX, "
                    + "EndChunkposY, "
                    + "EndChunkposZ, "
                    + "EndBlockposX, "
                    + "EndBlockposY, "
                    + "EndBlockposZ, "
                    + "GlobalEndposX, "
                    + "GlobalEndposY, "
                    + "GlobalEndposZ"
                    + ") VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
            prep.setString(1, pa.AreaName);
            prep.setInt(2, pa.StartChunkposX);
            prep.setInt(3, pa.StartChunkposY);
            prep.setInt(4, pa.StartChunkposZ);
            prep.setInt(5, pa.StartBlockposX);
            prep.setInt(6, pa.StartBlockposY);
            prep.setInt(7, pa.StartBlockposZ);
            prep.setFloat(8, pa.GlobalStartposX);
            prep.setFloat(9, pa.GlobalStartposY);
            prep.setFloat(10, pa.GlobalStartposZ);
            prep.setInt(11, pa.EndChunkposX);
            prep.setInt(12, pa.EndChunkposY);
            prep.setInt(13, pa.EndChunkposZ);
            prep.setInt(14, pa.EndBlockposX);
            prep.setInt(15, pa.EndBlockposY);
            prep.setInt(16, pa.EndBlockposZ);
            prep.setFloat(17, pa.GlobalEndposX);
            prep.setFloat(18, pa.GlobalEndposY);
            prep.setFloat(19, pa.GlobalEndposZ);
            row = prep.executeUpdate();
        }
        catch (SQLException e){
        }
        
        return row;
    }
    
    public void deleteArea(int idNo){
        Connection con = data.getConnection();
        try{
            PreparedStatement prep = con.prepareStatement("DELETE FROM Areas WHERE idNo LIKE ?");
            prep.setInt(1, idNo);
            prep.executeUpdate();
        }
        catch (SQLException e){
        }
    }
    
    public void createPermissions(AreaProtectionMain.PlayerPermissions pp){
        Connection con = data.getConnection();
        try{
            PreparedStatement prep = con.prepareStatement("INSERT INTO Permissions ("
                    + "AreaidNo, "
                    + "PlayerUserID, "
                    + "AddPlayers, "
                    + "PlaceObject, "
                    + "PlaceConstruction, "
                    + "PlaceBlock, "
                    + "PlaceVegetation, "
                    + "PlaceGrass, "
                    + "PlaceTerrain, "
                    + "PlaceWater, "
                    + "RemoveObject, "
                    + "RemoveConstruction, "
                    + "RemoveVegetation, "
                    + "RemoveGrass, "
                    + "RemoveWater, "
                    + "DestroyObject, "
                    + "DestroyConstruction, "
                    + "DestroyBlock, "
                    + "DestroyVegetation, "
                    + "DestroyTerrain, "
                    + "ChangeObjectStatus, "
                    + "CreateBlueprint, "
                    + "PlaceBlueprint, "
                    + "EnterArea, "
                    + "ExitArea, "
                    + "ChestDrop, "
                    + "ChestToInventory, "
                    + "InventoryToChest, "
                    + "InventoryAdd"
                    + ") VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
            prep.setInt(1, pp.AreaidNo);
            prep.setLong(2, pp.PlayerUserID);
            prep.setBoolean(3, pp.AddPlayers);
            prep.setBoolean(4, pp.PlaceObject);
            prep.setBoolean(5, pp.PlaceConstruction);
            prep.setBoolean(6, pp.PlaceBlock);
            prep.setBoolean(7, pp.PlaceVegetation);
            prep.setBoolean(8, pp.PlaceGrass);
            prep.setBoolean(9, pp.PlaceTerrain);
            prep.setBoolean(10, pp.PlaceWater);
            prep.setBoolean(11, pp.RemoveObject);
            prep.setBoolean(12, pp.RemoveConstruction);
            prep.setBoolean(13, pp.RemoveVegetation);
            prep.setBoolean(14, pp.RemoveGrass);
            prep.setBoolean(15, pp.RemoveWater);
            prep.setBoolean(16, pp.DestroyObject);
            prep.setBoolean(17, pp.DestroyConstruction);
            prep.setBoolean(18, pp.DestroyBlock);
            prep.setBoolean(19, pp.DestroyVegetation);
            prep.setBoolean(20, pp.DestroyTerrain);
            prep.setBoolean(21, pp.ChangeObjectStatus);
            prep.setBoolean(22, pp.CreateBlueprint);
            prep.setBoolean(23, pp.PlaceBlueprint);
            prep.setBoolean(24, pp.EnterArea);
            prep.setBoolean(25, pp.ExitArea);
            prep.setBoolean(26, pp.ChestDrop);
            prep.setBoolean(27, pp.ChestToInventory);
            prep.setBoolean(28, pp.InventoryToChest);
            prep.setBoolean(29, pp.InventoryAdd);
            prep.executeUpdate();
        }
        catch (SQLException e){
        }
    }
    
    public void deletePermissions(int AreaidNo, String PlayerName){
        Connection con = data.getConnection();
        try{
            PreparedStatement prep = con.prepareStatement("DELETE FROM Permissions WHERE (AreaidNo LIKE ? AND PlayerName LIKE ?)");
            prep.setInt(1, AreaidNo);
            prep.setString(2, PlayerName);
            prep.executeUpdate();
        }
        catch (SQLException e){
        }
    }
    
    public void updatePermission(int AreaidNo, String PlayerName, String PermissionName, boolean PermissionValue){
        Connection con = data.getConnection();
        try{
            PreparedStatement prep = con.prepareStatement("UPDATE Permissions SET ? LIKE ? WHERE (AreaidNo LIKE ? AND PlayerName LIKE ?)");
            prep.setString(1, PermissionName);
            prep.setBoolean(2, PermissionValue);
            prep.setInt(3, AreaidNo);
            prep.setString(4, PlayerName);
            prep.executeUpdate();
        }
        catch (SQLException e){
        }
    }
    
    public ArrayList<AreaProtectionMain.ProtectedArea> getAreas(){
        ArrayList<AreaProtectionMain.ProtectedArea> areas = new ArrayList<>();
        AreaProtectionMain APM = new AreaProtectionMain();
        
        try(ResultSet result = data.executeQuery("SELECT * FROM Areas")){
            while (result.next()){
                AreaProtectionMain.ProtectedArea a =  APM.new ProtectedArea();
                a.idNo = result.getInt("idNo");
                a.AreaName = result.getString("AreaName");
                a.StartChunkposX = result.getInt("StartChunkposX");
                a.StartChunkposY = result.getInt("StartChunkposY");
                a.StartChunkposZ = result.getInt("StartChunkposZ");
                a.StartBlockposX = result.getInt("StartBlockposX");
                a.StartBlockposY = result.getInt("StartBlockposY");
                a.StartBlockposZ = result.getInt("StartBlockposZ");
                a.GlobalStartposX = result.getFloat("GlobalStartposX");
                a.GlobalStartposY = result.getFloat("GlobalStartposY");
                a.GlobalStartposZ = result.getFloat("GlobalStartposZ");
                a.EndChunkposX = result.getInt("EndChunkposX");
                a.EndChunkposY = result.getInt("EndChunkposY");
                a.EndChunkposZ = result.getInt("EndChunkposZ");
                a.EndBlockposX = result.getInt("EndBlockposX");
                a.EndBlockposY = result.getInt("EndBlockposY");
                a.EndBlockposZ = result.getInt("EndBlockposZ");
                a.GlobalEndposX = result.getFloat("GlobalEndposX");
                a.GlobalEndposY = result.getFloat("GlobalEndposY");
                a.GlobalEndposZ = result.getFloat("GlobalEndposZ");
                areas.add(a);
            }
        }
        catch (SQLException e){
        }
        
        return areas;
    }
    
    public ArrayList<AreaProtectionMain.PlayerPermissions> getPermissions(){
        ArrayList<AreaProtectionMain.PlayerPermissions> permissions = new ArrayList<>();
        AreaProtectionMain APM = new AreaProtectionMain();
        
        try(ResultSet result = data.executeQuery("SELECT * FROM Permissions")){
            while (result.next()){
                AreaProtectionMain.PlayerPermissions p =  APM.new PlayerPermissions();
                p.idNo = result.getInt("idNo");
                p.AreaidNo = result.getInt("AreaidNo");
                p.PlayerUserID = result.getLong("PlayerUserID");
                p.AddPlayers = result.getBoolean("AddPlayers");
                p.PlaceObject = result.getBoolean("PlaceObject");
                p.PlaceConstruction = result.getBoolean("PlaceConstruction");
                p.PlaceBlock = result.getBoolean("PlaceBlock");
                p.PlaceVegetation = result.getBoolean("PlaceVegetation");
                p.PlaceGrass = result.getBoolean("PlaceGrass");
                p.PlaceTerrain = result.getBoolean("PlaceTerrain");
                p.PlaceWater = result.getBoolean("PlaceWater");
                p.RemoveObject = result.getBoolean("RemoveObject");
                p.RemoveConstruction = result.getBoolean("RemoveConstruction");
                p.RemoveVegetation = result.getBoolean("RemoveVegetation");
                p.RemoveGrass = result.getBoolean("RemoveGrass");
                p.RemoveWater = result.getBoolean("RemoveWater");
                p.DestroyObject = result.getBoolean("DestroyObject");
                p.DestroyConstruction = result.getBoolean("DestroyConstruction");
                p.DestroyBlock = result.getBoolean("DestroyBlock");
                p.DestroyVegetation = result.getBoolean("DestroyVegetation");
                p.DestroyTerrain = result.getBoolean("DestroyTerrain");
                p.ChangeObjectStatus = result.getBoolean("ChangeObjectStatus");
                p.CreateBlueprint = result.getBoolean("CreateBlueprint");
                p.PlaceBlueprint = result.getBoolean("PlaceBlueprint");
                p.EnterArea = result.getBoolean("EnterArea");
                p.ExitArea = result.getBoolean("ExitArea");
                p.ChestDrop = result.getBoolean("ChestDrop");
                p.ChestToInventory = result.getBoolean("ChestToInventory");
                p.InventoryToChest = result.getBoolean("InventoryToChest");
                p.InventoryAdd = result.getBoolean("InventoryAdd");
                permissions.add(p);
            }
        }
        catch (SQLException e){
        }
        
        return permissions;
    }
}
