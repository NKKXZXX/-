package cn.nkkxzxx.propertymgrsys.model.dao;

import cn.nkkxzxx.propertymgrsys.model.entity.Room;

import java.util.List;

/**
 * @author nkkxzxx
 */
public interface RoomDAO {
    public void insertRoom(char building,String floor,String room); //加入房间
    public void deleteRoom(String serialNumber);    //删除房间
    public void updateRoomByRoom(String serialNumber,String room);  //更新房间号
    public void updateRoomByBuilding(String serialNumber,char building);    //更新座次
    public void updateRoomByFloor(String serialNumber,String floor);    //更新楼层
    public void updateRoomByTenement(String serialNumber,String name,String phoneNumber);   //更新住户
    public List<Room> queryByTenementName(String name); //按住户名查询
    public List<Room> queryByTenementPhone(String phone);   //按住户电话查询
    public List<Room> queryByTenement(String name,String phone); //按住户基本信息查询
    public List<Room> queryByRoom(String serialNumber); //按房间号查询
    public List<Room> queryAllRoom();   //取得所有房间
}
