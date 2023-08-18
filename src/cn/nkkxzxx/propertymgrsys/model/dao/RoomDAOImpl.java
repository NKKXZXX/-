package cn.nkkxzxx.propertymgrsys.model.dao;

import cn.nkkxzxx.propertymgrsys.model.entity.Room;
import cn.nkkxzxx.propertymgrsys.utils.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * @author nkkxzxx
 */
public class RoomDAOImpl implements RoomDAO{

    @Override
    public void insertRoom(char building, String floor, String room) {
        String insertQuery = "INSERT INTO rooms (ser,floor,building,room) VALUES (?,?,?,?)";
        try {
            Connection c = Database.getConnection();
            c.setAutoCommit(false);
            PreparedStatement statement = c.prepareStatement(insertQuery);
            var temp = new Room(building, room, floor);
            statement.setString(1, temp.getSerialNumber());
            statement.setString(2, floor);
            statement.setString(3, String.valueOf(building));
            statement.setString(4, room);
            statement.executeUpdate();
            c.commit();
            Database.closeDatabase();
        }catch (Exception se){
            se.printStackTrace();
        }
    }

    @Override
    public void deleteRoom(String serialNumber) {
        String updateQuery = "DELETE from rooms WHERE ser = '"+serialNumber+"';";
        System.out.println(updateQuery);
        try {
            Connection c = Database.getConnection();
            PreparedStatement statement = c.prepareStatement(updateQuery);
            statement.executeUpdate();
            Database.closeDatabase();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateRoomByRoom(String serialNumber, String room) {
        String selectQuery = "SELECT * FROM rooms WHERE ser = '"+serialNumber+"';"; //数据库语句
        try {
            Connection c = Database.getConnection();   //连接数据库获得数据
            var statement = c.createStatement();
            ResultSet r = statement.executeQuery(selectQuery);
            var temp = new Room(r.getString("building").charAt(0),r.getString("room"),
                    r.getString("floor"),r.getString("te_name"),r.getString("te_phone"));
            temp.setRoom(room);
            c.close();
            insertRoom(temp.getBuilding(), temp.getFloor(), temp.getRoom());    //插入修改的数据
            updateRoomByTenement(temp.getSerialNumber(),temp.getTenement().getName(),
                    temp.getTenement().getPhoneNumber());
            deleteRoom(serialNumber);
            Database.closeDatabase();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateRoomByBuilding(String serialNumber,char building) {
        String selectQuery = "SELECT * FROM rooms WHERE ser = '"+serialNumber+"';";
        try {
            Connection c = Database.getConnection();
            var statement = c.createStatement();
            ResultSet r = statement.executeQuery(selectQuery);
            var temp = new Room(r.getString("building").charAt(0),r.getString("room"),
                    r.getString("floor"),r.getString("te_name"),r.getString("te_phone"));
            temp.setBuilding(building);
            c.close();
            insertRoom(temp.getBuilding(), temp.getFloor(), temp.getRoom());
            updateRoomByTenement(temp.getSerialNumber(),temp.getTenement().getName(),temp.getTenement().getPhoneNumber());
            deleteRoom(serialNumber);
            Database.closeDatabase();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateRoomByFloor(String serialNumber,String floor) {
        String selectQuery = "SELECT * FROM rooms WHERE ser = '"+serialNumber+"';";
        try {
            Connection c = Database.getConnection();
            var statement = c.createStatement();
            ResultSet r = statement.executeQuery(selectQuery);
            var temp = new Room(r.getString("building").charAt(0),r.getString("room"),
                    r.getString("floor"),r.getString("te_name"),r.getString("te_phone"));
            temp.setFloor(floor);
            c.close();
            insertRoom(temp.getBuilding(), temp.getFloor(), temp.getRoom());
            updateRoomByTenement(temp.getSerialNumber(),temp.getTenement().getName(),temp.getTenement().getPhoneNumber());
            deleteRoom(serialNumber);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("操作完成");
    }

    /**
     * 更新房间住户信息
     * @param serialNumber  房间号
     * @param name 住户姓名
     * @param phoneNumber 住户电话号码
     */
    @Override
    public void updateRoomByTenement(String serialNumber, String name, String phoneNumber) {
        String updateQuery = "UPDATE rooms SET te_name = '"+name+"',te_phone = '"+phoneNumber+"' WHERE ser = '"+serialNumber+"';";
        Database.updateDatabase(updateQuery);
    }
    @Override
    public List<Room> queryByTenementName(String name) {
        List<Room> result = new ArrayList<>();
        for(var s : queryAllRoom()){
            if(!"".equals(s.getTenement().getName()) && name.equals(s.getTenement().getName())){
                result.add(s);
            }
        }
        return result;
    }

    @Override
    public List<Room> queryByTenement(String name, String phone) {
        List<Room> temp = new ArrayList<>();
        List<Room> result = new ArrayList<>();
        for(var s : queryAllRoom()){
            if(!"".equals(s.getTenement().getName()) && name.equals(s.getTenement().getName())){
                temp.add(s);
            }
        }
        for(var s : temp){
            if(!"".equals(s.getTenement().getPhoneNumber()) && phone.equals(s.getTenement().getPhoneNumber())){
                result.add(s);
            }
        }
        return result;
    }

    @Override
    public List<Room> queryByTenementPhone(String phone) {
        List<Room> result = new ArrayList<>();
        for(var s : queryAllRoom()){
            if(!"".equals(s.getTenement().getPhoneNumber()) && phone.equals(s.getTenement().getPhoneNumber())){
                result.add(s);
            }
        }
        return result;
    }

    @Override
    public List<Room> queryByRoom(String serialNumber) {
        List<Room> result = new ArrayList<>();
        for(var s : queryAllRoom()){
            if(s.getSerialNumber().equals(serialNumber))
                result.add(s);
        }
        return result;
    }

    public List<Room> queryAllRoom(String serialNumber,String building,String floor,String room) {
        List<Room> result = new ArrayList<>();
        String ser = serialNumber.toUpperCase();
        String query1,query2,query3,query4;
        result.addAll(queryAllRoom());
        if (!ser.isEmpty()){
            query1 = "SELECT * FROM rooms WHERE ser ='" +ser+ "'";
            result.retainAll(getQueryRoomsList(query1));
        }
        if(!building.isEmpty()) {
            query2 = "SELECT * FROM rooms WHERE building ='" + building + "'";
            result.retainAll(getQueryRoomsList(query2));
        }
        if(!floor.isEmpty()) {
            query3 = "SELECT * FROM rooms WHERE floor ='" + floor + "'";
            result.retainAll(getQueryRoomsList(query3));
        }
        if(!room.isEmpty()) {
            query4 = "SELECT * FROM rooms WHERE room ='" + room + "'";
            result.retainAll(getQueryRoomsList(query4));
        }
        return result;
    }
    @Override
    public List<Room> queryAllRoom() {
        List<Room> result = new ArrayList<>();
        String query = "SELECT * FROM rooms;";
        return getQueryRoomsList(query);
    }
    private List<Room> getQueryRoomsList(String query){
        List<Room> result = new ArrayList<>();
        try {
            var stmt = Database.getConnection().createStatement();
            ResultSet r = stmt.executeQuery(query);
            while ( r.next() ) {
                var temp = new Room(r.getString("building").charAt(0),r.getString("room"),r.getString("floor")
                        ,r.getString("te_name"),r.getString("te_phone"));
                result.add(temp);
            }
            r.close();
            stmt.close();
            Database.closeDatabase();
        } catch ( Exception e ) {
            e.printStackTrace();
        }
        return result;
    }
}
