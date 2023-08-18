package cn.nkkxzxx.propertymgrsys.controller;

import cn.nkkxzxx.propertymgrsys.model.dao.RoomDAOImpl;
import cn.nkkxzxx.propertymgrsys.model.entity.QueryInfoForm;
import cn.nkkxzxx.propertymgrsys.model.entity.Room;
import cn.nkkxzxx.propertymgrsys.utils.AlertInfo;
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

public class QueryByTenementController{
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
    private TextField tenementNameText;

    @FXML
    private TextField tenementPhoneText;
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
    void query(ActionEvent event) {
        ObservableList<QueryInfoForm> result = FXCollections.observableArrayList();
        //输入的信息合法则检索匹配的房间
        List<Room> temp = new ArrayList<>(roomDAO.queryAllRoom());
        if(!tenementNameText.getText().isEmpty())
            temp.retainAll(roomDAO.queryByTenementName(tenementNameText.getText()));
        if(!tenementPhoneText.getText().isEmpty())
            temp.retainAll(roomDAO.queryByTenementPhone(tenementPhoneText.getText()));
        if(tenementPhoneText.getText().isEmpty() && tenementNameText.getText().isEmpty()){
            AlertInfo.displayAlert("输入错误","请输入至少一条信息");
            return;
        }
        for(var s : temp){
            result.add(new QueryInfoForm(s.getSerialNumber(),s.getFloor(),String.valueOf(s.getBuilding()),s.getRoom(),
                    s.getTenement().getName(),s.getTenement().getPhoneNumber()));
        }
        if(!result.isEmpty())
            displayInTable(result);
        else
            AlertInfo.displayAlert("提示", "未找到匹配的结果");
    }
    @FXML
    void displayRoomInfoPage(ActionEvent event) {
        loadPage("/cn/nkkxzxx/propertymgrsys/view/queryByRoomInfoPage.fxml");
    }

    @FXML
    void displayTenementPage(ActionEvent event) {
        loadPage("/cn/nkkxzxx/propertymgrsys/view/queryByTenementPage.fxml");
    }
    private void clearTextFields(){
        tenementNameText.clear();
        tenementPhoneText.clear();
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
