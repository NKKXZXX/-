package cn.nkkxzxx.propertymgrsys.controller;

import cn.nkkxzxx.propertymgrsys.model.dao.RoomDAOImpl;
import cn.nkkxzxx.propertymgrsys.model.entity.Room;
import cn.nkkxzxx.propertymgrsys.model.entity.Tenement;
import cn.nkkxzxx.propertymgrsys.model.entity.TenementInfoForm;
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

import java.util.List;

public class TenementEditPageController {
    private RoomDAOImpl roomDAO;
    private Room currentRoom;
    @FXML
    private TableColumn<TenementInfoForm, String> buildingColumn;

    @FXML
    private TextField teNameText;

    @FXML
    private Button clearTextBtn;

    @FXML
    private Button displayAllRoomsBtn;

    @FXML
    private TableColumn<TenementInfoForm, String> floorColumn;

    @FXML
    private TextField tePhoneText;

    @FXML
    private TableView<TenementInfoForm> infoTable;

    @FXML
    private TableColumn<TenementInfoForm, String> roomColumn;

    @FXML
    private TextField roomText;

    @FXML
    private ListView<Room> roomsListView;

    @FXML
    private Button searchBtn;

    @FXML
    private TableColumn<TenementInfoForm, String> serColumn;

    @FXML
    private TextField serText;

    @FXML
    private Button updateBtn;

    @FXML
    private Button deleteTenementBtn;
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
    void selectRooms(MouseEvent event) {
        roomsListView.setOnMouseClicked(mouseEvent -> {
            Room selectRoom = roomsListView.getSelectionModel().getSelectedItem();
            this.currentRoom = selectRoom;
            if(selectRoom != null){
                var form = new TenementInfoForm(currentRoom.getSerialNumber(),
                        currentRoom.getTenement().getName(),
                        currentRoom.getTenement().getPhoneNumber());
                infoTable.getItems().clear();
                infoTable.getItems().add(form);
                teNameText.setText(currentRoom.getTenement().getName());
                tePhoneText.setText(currentRoom.getTenement().getPhoneNumber());
            }
        });
    }
    @FXML
    void deleteTenement(){
        roomDAO.updateRoomByTenement(currentRoom.getSerialNumber(), "", "");
        var form = new TenementInfoForm(currentRoom.getSerialNumber(),
                roomDAO.queryByRoom(currentRoom.getSerialNumber()).get(0).getTenement().getName(),
                roomDAO.queryByRoom(currentRoom.getSerialNumber()).get(0).getTenement().getPhoneNumber());
        infoTable.getItems().clear();
        infoTable.getItems().add(form);
        clearText();
    }
    @FXML
    void updateTenement(ActionEvent event) {
        String teName = new String();
        String tePhone = new String();
        teName = teNameText.getText();
        tePhone = tePhoneText.getText();
        if(teNameText.getText().isEmpty())  //使得存入的数据有一定的格式
            teName = "";
        if(tePhoneText.getText().isEmpty())
            tePhone = "";
        roomDAO.updateRoomByTenement(currentRoom.getSerialNumber(), teNameText.getText(), tePhoneText.getText());
        var form = new TenementInfoForm(currentRoom.getSerialNumber(),
                    roomDAO.queryByRoom(currentRoom.getSerialNumber()).get(0).getTenement().getName(),
                roomDAO.queryByRoom(currentRoom.getSerialNumber()).get(0).getTenement().getPhoneNumber());
        infoTable.getItems().clear();
        infoTable.getItems().add(form);
    }
    /**
     * 显示房间
     */
    private void selectRoomsInList(List<Room> temp){
        ObservableList<Room> list = FXCollections.observableArrayList();
        list.addAll(temp);
        roomsListView.setItems(list);
    }
    private void clearText(){
        teNameText.clear();
        tePhoneText.clear();
    }
}
