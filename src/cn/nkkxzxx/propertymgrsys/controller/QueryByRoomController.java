package cn.nkkxzxx.propertymgrsys.controller;


import cn.nkkxzxx.propertymgrsys.model.dao.RoomDAOImpl;
import cn.nkkxzxx.propertymgrsys.model.entity.QueryInfoForm;
import cn.nkkxzxx.propertymgrsys.model.entity.Room;
import cn.nkkxzxx.propertymgrsys.utils.AlertInfo;
import cn.nkkxzxx.propertymgrsys.utils.CorrectJudger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class QueryByRoomController {
    private RoomDAOImpl roomDAO;
    @FXML
    private Button clearTextBtn;
    @FXML
    private TableView<QueryInfoForm> infoTable;
    @FXML
    private Button queryBtn;
    @FXML
    private AnchorPane setPane;
    @FXML
    private TextField serialNumberText;
    @FXML
    private TextField roomText;
    @FXML
    private TextField floorText;
    @FXML
    private TextField buildingText;
    @FXML
    private void initialize(){
        infoTable.setSelectionModel(null);
        roomDAO = new RoomDAOImpl();
    }
    @FXML
    void clearText(ActionEvent event) {
        clearTextFields();
    }

    @FXML
    void displayRoomInfoPage(ActionEvent event) {
        loadPage("/cn/nkkxzxx/propertymgrsys/view/queryByRoomInfoPage.fxml");
    }

    @FXML
    void displayTenementPage(ActionEvent event) {
        loadPage("/cn/nkkxzxx/propertymgrsys/view/queryByTenementPage.fxml");
    }

    /**
     * 按输入的信息查找
     */
    @FXML
    void query(ActionEvent event) {
        ObservableList<QueryInfoForm> list = FXCollections.observableArrayList();
        // 输入合法则查找匹配项
        if(CorrectJudger.isCorrectInQuery(buildingText.getText(),floorText.getText(),roomText.getText())) {
            for (var s : roomDAO.queryAllRoom(serialNumberText.getText(), buildingText.getText(),
                    floorText.getText(), roomText.getText())) {
                list.add(new QueryInfoForm(s.getSerialNumber(), s.getFloor(), String.valueOf(s.getBuilding()),
                        s.getRoom(), s.getTenement().getName(), s.getTenement().getPhoneNumber()));
            }
            if(list.isEmpty()) {
                AlertInfo.displayAlert("提示", "未找到匹配的结果");
            }
        }
        displayInTable(list);
    }

    /**
     * 列出所有房间
     */
    @FXML
    void queryAllRooms(ActionEvent event) {
        ObservableList<QueryInfoForm> list = FXCollections.observableArrayList();
        for(var s : roomDAO.queryAllRoom()){
            list.add(new QueryInfoForm(s.getSerialNumber(),s.getFloor(),String.valueOf(s.getBuilding()),s.getRoom(),
                    s.getTenement().getName(),s.getTenement().getPhoneNumber()));
        }
        displayInTable(list);
    }
    private void clearTextFields(){
        buildingText.clear();
        floorText.clear();
        serialNumberText.clear();
        roomText.clear();
    }
    /**
     * 加载一个页面
     * @param url FXML文件路径
     */
    private void loadPage(String url){
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource(url));
            Pane page = loader.load();
            setPane.getChildren().clear();
            setPane.getChildren().add(page);
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    void displayInTable(ObservableList<QueryInfoForm> form){
        infoTable.getItems().clear();
        infoTable.getItems().addAll(form);
    }
}
