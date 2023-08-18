package cn.nkkxzxx.propertymgrsys.utils;

import cn.nkkxzxx.propertymgrsys.model.dao.RoomDAOImpl;

public class ExistJudger {
    private static RoomDAOImpl roomDAO = new RoomDAOImpl();
    public static boolean isExist(String serialNumber){
        for(var s : roomDAO.queryAllRoom()){
            if (s.getSerialNumber().equals(serialNumber)){
                AlertInfo.displayAlert("错误","该房间已经存在");
                return true;
            }
        }
        return false;
    }

}
