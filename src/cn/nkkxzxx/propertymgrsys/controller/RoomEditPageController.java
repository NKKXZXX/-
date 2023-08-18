package cn.nkkxzxx.propertymgrsys.controller;

import javafx.event.ActionEvent;
import javafx.scene.layout.Pane;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import java.io.IOException;

public class RoomEditPageController {

    @FXML
    private Button addBtn;

    @FXML
    private Button deleteBtn;

    @FXML
    private Button editBtn;

    @FXML
    private StackPane pageContainer;
    @FXML
    private void initialize(){loadPage("/cn/nkkxzxx/propertymgrsys/view/roomEditPageAdd.fxml");}
    @FXML
    void addRoom(ActionEvent event) {
        loadPage("/cn/nkkxzxx/propertymgrsys/view/roomEditPageAdd.fxml");
    }
    @FXML
    void deleteRoom(ActionEvent event) {
        loadPage("/cn/nkkxzxx/propertymgrsys/view/roomEditPageDelete.fxml");
    }
    @FXML
    void editRoom(ActionEvent event) {loadPage("/cn/nkkxzxx/propertymgrsys/view/roomEditPageEditor.fxml");}
    /**
     * 加载一个页面
     * @param url FXML文件路径
     */
    private void loadPage(String url){
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource(url));
            Pane page = loader.load();
            pageContainer.getChildren().clear();
            pageContainer.getChildren().add(page);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

}
