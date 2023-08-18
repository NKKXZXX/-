package cn.nkkxzxx.propertymgrsys.controller;

import cn.nkkxzxx.propertymgrsys.model.dao.RoomDAOImpl;
import cn.nkkxzxx.propertymgrsys.model.entity.Room;
import cn.nkkxzxx.propertymgrsys.utils.AlertInfo;
import cn.nkkxzxx.propertymgrsys.utils.CorrectJudger;
import cn.nkkxzxx.propertymgrsys.utils.ExistJudger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.util.List;

public class RoomEditPageEditController {
    private RoomDAOImpl roomDAO;
    private Room currentRoom;
    @FXML
    private TableColumn<Room, String> buildingColumn;

    @FXML
    private TextField buildingText;

    @FXML
    private Button clearTextBtn;

    @FXML
    private Button displayAllRoomsBtn;

    @FXML
    private TableColumn<Room, String> floorColumn;

    @FXML
    private TextField floorText;

    @FXML
    private TableView<Room> infoTable;

    @FXML
    private TableColumn<Room, String> roomColumn;

    @FXML
    private TextField roomText;

    @FXML
    private ListView<Room> roomsListView;

    @FXML
    private Button searchBtn;

    @FXML
    private TableColumn<Room, String> serColumn;

    @FXML
    private TextField serText;

    @FXML
    private Button updateBtn;
    @FXML
    private void initialize(){
        roomDAO = new RoomDAOImpl();
        selectRoomsInList(roomDAO.queryAllRoom());
        infoTable.setSelectionModel(null);
    }

    @FXML
    void clearTextFields(ActionEvent event) {
        clearText();
    }

    @FXML
    void displayAllRooms(ActionEvent event) {
        selectRoomsInList(roomDAO.queryAllRoom());
    }

    @FXML
    void searchRooms(ActionEvent event) {
        selectRoomsInList(roomDAO.queryByRoom(serText.getText()));
    }

    @FXML
    void updateRoom(ActionEvent event) {
        if(currentRoom == null) // 如果没选择房间
            AlertInfo.displayAlert("错误","请选择一个房间");
        else if(judgeChange() && CorrectJudger.isCorrect(buildingText.getText(),floorText.getText(),roomText.getText())){
            var temp = new Room(buildingText.getText().charAt(0),roomText.getText(),floorText.getText());
            if(!ExistJudger.isExist(temp.getSerialNumber())) {  //根据输入的信息更改房间
                if (!floorText.getText().equals(currentRoom.getFloor())) {
                    roomDAO.updateRoomByFloor(currentRoom.getSerialNumber(), floorText.getText());
                    currentRoom.setFloor(floorText.getText());
                }
                if (!(buildingText.getText().charAt(0) == currentRoom.getBuilding())) {
                    roomDAO.updateRoomByBuilding(currentRoom.getSerialNumber(), buildingText.getText().charAt(0));
                    currentRoom.setBuilding(buildingText.getText().charAt(0));
                }
                if (!roomText.getText().equals(currentRoom.getRoom())) {
                    roomDAO.updateRoomByRoom(currentRoom.getSerialNumber(), roomText.getText());
                    currentRoom.setRoom(roomText.getText());
                }
                AlertInfo.displayAlert("成功","编辑成功");
                selectRoomsInList(roomDAO.queryAllRoom());
                infoTable.getItems().clear();
                infoTable.getItems().add(currentRoom);
            }
        }
    }

    @FXML
    public void selectRooms(){
        roomsListView.setOnMouseClicked(mouseEvent -> {
            Room selectRoom = roomsListView.getSelectionModel().getSelectedItem();
            this.currentRoom = selectRoom;
            if(selectRoom != null){
                infoTable.getItems().clear();
                infoTable.getItems().add(selectRoom);
                floorText.setText(selectRoom.getFloor());
                buildingText.setText(String.valueOf(selectRoom.getBuilding()));
                roomText.setText(selectRoom.getRoom());
            }
        });
    }

    /**
     * 显示房间
     */
    private void selectRoomsInList(List<Room> temp){
        ObservableList<Room> list = FXCollections.observableArrayList();
        list.addAll(temp);
        roomsListView.setItems(list);
    }

    /**
     * 清除输入框
     */
    private void clearText(){
        floorText.clear();
        buildingText.clear();
        roomText.clear();
    }

    /**
     * 判断更改是否合法
     */
    private boolean judgeChange(){
        return !floorText.getText().equals(currentRoom.getFloor()) ||
                !(buildingText.getText().charAt(0) == currentRoom.getBuilding()) ||
                !roomText.getText().equals(currentRoom.getRoom());
    }
}
