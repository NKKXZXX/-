package cn.nkkxzxx.propertymgrsys.controller;

import cn.nkkxzxx.propertymgrsys.model.dao.RoomDAOImpl;
import cn.nkkxzxx.propertymgrsys.model.entity.Room;
import cn.nkkxzxx.propertymgrsys.utils.AlertInfo;
import cn.nkkxzxx.propertymgrsys.utils.CorrectJudger;
import cn.nkkxzxx.propertymgrsys.utils.ExistJudger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class RoomEditPageAddController {
    private RoomDAOImpl roomDAO;

    @FXML
    private TextField buildingText;

    @FXML
    private Button clearTextBtn;

    @FXML
    private TextField floorText;

    @FXML
    private TextField roomText;

    @FXML
    private Button submitBtn;

    @FXML
    private void initialize(){
        roomDAO = new RoomDAOImpl();
    }

    @FXML
    void clearTextField(ActionEvent event) {
        clearText();
    }

    @FXML
    void submitRoom(ActionEvent event) {
        // 判断输入的数据正确之后添加输入的房间
        if(CorrectJudger.isCorrect(buildingText.getText(),roomText.getText(),floorText.getText())){
            var temp = new Room(buildingText.getText().charAt(0),roomText.getText(),floorText.getText());
            if(!ExistJudger.isExist(temp.getSerialNumber())) {
                roomDAO.insertRoom(temp.getBuilding(), temp.getFloor(), temp.getRoom());
                AlertInfo.displayAlert("成功", "增加房间成功");
            }
        }
        clearText();
    }

    private boolean  isExist(String serialNumber){
        for(var s : roomDAO.queryAllRoom()){
            if (s.getSerialNumber().equals(serialNumber)){
                AlertInfo.displayAlert("增加房间错误","该房间已经存在");
                return true;
            }
        }
        return false;
    }

    private void clearText(){
        buildingText.clear();
        floorText.clear();
        roomText.clear();
    }
}
