package cn.nkkxzxx.propertymgrsys.utils;

public class CorrectJudger {
    public static boolean isCorrect(String building,String floor,String room){
        if(building.isEmpty() || floor.isEmpty() || room.isEmpty()){
            AlertInfo.displayAlert("数据输入不完整","请填写完所有信息后再试");
            return false;
        }
         else if(NumberJudger.isNumeric(floor)&&NumberJudger.isNumeric(room)
                &&Integer.parseInt(floor)>0&&Integer.parseInt(room)>0) {
            if (building.charAt(0) > 'Z' || building.charAt(0) < 'A' || building.length() > 1) {
                AlertInfo.displayAlert("座次范围错误", "请输入 A ~ Z 之间的字母");
                return false;
            }
            else
                return true;
        }
        else{
            AlertInfo.displayAlert("输入错误","楼层数和房间号请输入大于0的整数");
            return false;
        }
    }
    public static boolean isCorrectInQuery(String building,String floor,String room) {
        if (!building.isEmpty() && (building.charAt(0) > 'Z' || building.charAt(0) < 'A' || building.length() > 1)) {
            AlertInfo.displayAlert("座次范围错误", "请输入 A ~ Z 之间的字母");
            return false;
        }
        if(!floor.isEmpty() && !NumberJudger.isNumeric(floor)) {
            AlertInfo.displayAlert("输入错误", "楼层数和房间号请输入整数");
            return false;
        }
        if(!room.isEmpty() && !NumberJudger.isNumeric(room)){
            AlertInfo.displayAlert("输入错误", "楼层数和房间号请输入整数");
            return false;
        }
        return true;
    }
}
