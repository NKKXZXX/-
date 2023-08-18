package cn.nkkxzxx.propertymgrsys.controller;

import cn.nkkxzxx.propertymgrsys.model.dao.RoomDAOImpl;
import cn.nkkxzxx.propertymgrsys.model.entity.Room;
import cn.nkkxzxx.propertymgrsys.utils.AlertInfo;
import cn.nkkxzxx.propertymgrsys.utils.NumberJudger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.util.ArrayList;
import java.util.List;

public class InitPageController {
    private RoomDAOImpl roomDAO;
    private List<Room> list;

    @FXML
    private TextField buildingNumText;

    @FXML
    private TextField floorNumText;

    @FXML
    private Button generatorBtn;

    @FXML
    private TextField roomNumText;

    /**
     * 初始化
     */
    @FXML
    private void initialize(){
        roomDAO = new RoomDAOImpl();
        list = new ArrayList<>();
    }

    /**
     * 自动生成房间
     */
    @FXML
    void generateRooms(ActionEvent event) {
        if(isCorrect()){    // 输入的数据正确则进行初始化
            initRoom(Integer.parseInt(floorNumText.getText()), Integer.parseInt(buildingNumText.getText()),
                    Integer.parseInt(roomNumText.getText()));
            for (var s : list) {
                roomDAO.insertRoom(s.getBuilding(), s.getFloor(), s.getRoom());
            }
            AlertInfo.displayAlert("成功","初始化成功");
        }
        clearTextField();
    }

    /**
     * 判断输入是否合法
     * @return 合法性
     */
    private boolean isCorrect(){
        if(floorNumText.getText().equals("")||buildingNumText.getText().equals("")||roomNumText.getText().equals("")){
            AlertInfo.displayAlert("输入错误","输入的信息不完整");
            return false;
        }
        else if(!NumberJudger.isNumeric(floorNumText.getText())|| !NumberJudger.isNumeric(buildingNumText.getText())||
                !NumberJudger.isNumeric(roomNumText.getText())|| Integer.parseInt(floorNumText.getText())<=0||
                Integer.parseInt(buildingNumText.getText())<=0||Integer.parseInt(roomNumText.getText())<=0){
            AlertInfo.displayAlert("输入错误","请输入大于0的整数");
            return false;
        }
        else if(!roomDAO.queryAllRoom().isEmpty()){
            AlertInfo.displayAlert("已有房间存在","请除所有的房间后再使用初始化功能");
            return false;
        }
        else
            return true;
    }

    /**
     * 清除输入框
     */
    private void clearTextField(){
        floorNumText.clear();
        buildingNumText.clear();
        roomNumText.clear();
    }

    /**
     * 初始化房间
     * @param floorNum 楼层数
     * @param buildingNum 座号数
     * @param roomNum 房间数
     */
    private void initRoom(int floorNum, int buildingNum, int roomNum){
        for(int f=0; f < floorNum; f++) {
            for (int i = 0; i < buildingNum; i++) {
                for(int j = 0; j < roomNum; j++){
                    var temp = new Room((char) ((char)65+i), String.valueOf(j+1),String.valueOf(f+1) );
                    list.add(temp);
                }
            }
        }
    }

}
