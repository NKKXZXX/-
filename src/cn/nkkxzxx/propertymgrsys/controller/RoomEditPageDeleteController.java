package cn.nkkxzxx.propertymgrsys.controller;

import cn.nkkxzxx.propertymgrsys.model.dao.RoomDAOImpl;
import cn.nkkxzxx.propertymgrsys.model.entity.Room;
import cn.nkkxzxx.propertymgrsys.utils.AlertInfo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;
import java.util.List;

public class RoomEditPageDeleteController {
    private RoomDAOImpl roomDAO = new RoomDAOImpl();
    private Room currentRoom;
    @FXML
    private TableColumn<Room, String> buildingColumn;

    @FXML
    private Button deleteAllBtn;

    @FXML
    private Button deleteSelectBtn;

    @FXML
    private Button displayAllRoomsBtn;

    @FXML
    private TableColumn<Room, String> floorColumn;

    @FXML
    private TableView<Room> infoTable;

    @FXML
    private TableColumn<Room, String> roomColumn;

    @FXML
    private ListView<Room> roomsListView;

    @FXML
    private Button searchBtn;

    @FXML
    private TableColumn<Room, String> serColumn;

    @FXML
    private TextField serText;
    @FXML
    private void initialize(){
        roomDAO = new RoomDAOImpl();
        selectRoomsInList(roomDAO.queryAllRoom());
        infoTable.setSelectionModel(null);
    }

    /**
     * 删除所有房间
     */
    @FXML
    void deleteAllRooms(ActionEvent event) {
        List<Room> temp = new ArrayList<>();
        temp.addAll(roomDAO.queryAllRoom());
        for(var s : temp){
            roomDAO.deleteRoom(s.getSerialNumber());
        }
        selectRoomsInList(roomDAO.queryAllRoom());
        infoTable.getItems().clear();
    }

    /**
     * 删除选择的房间
     */
    @FXML
    void deleteSelectRoom(ActionEvent event) {
        if(currentRoom != null){
            roomDAO.deleteRoom(currentRoom.getSerialNumber());
            selectRoomsInList(roomDAO.queryAllRoom());
            infoTable.getItems().clear();
        }
        else
            AlertInfo.displayAlert("错误","请选择一个房间");
    }
    /**
     * 显示所有的房间
     */

    @FXML
    void displayAllRooms(ActionEvent event) {
        selectRoomsInList(roomDAO.queryAllRoom());
    }
    /**
     * 查找房间
     */
    @FXML
    void searchRooms(ActionEvent event) {
        selectRoomsInList(roomDAO.queryByRoom(serText.getText()));
    }

    @FXML
    void selectRooms(MouseEvent event) {
        roomsListView.setOnMouseClicked(mouseEvent -> {
            Room selectRoom = roomsListView.getSelectionModel().getSelectedItem();
            this.currentRoom = selectRoom;
            if(selectRoom != null){
                infoTable.getItems().clear();
                infoTable.getItems().add(selectRoom);
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
}
