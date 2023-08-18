package cn.nkkxzxx.propertymgrsys.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

import java.io.IOException;

public class MainController {

    @FXML
    private StackPane pageContainer;

    @FXML
    private Button initPageBtn;

    @FXML
    private Button roomEditPageBtn;

    @FXML
    private Button tenementEditPage;

    @FXML
    private Button queryBtn;

    @FXML
    void initRoomPage(ActionEvent event) {
        loadPage("/cn/nkkxzxx/propertymgrsys/view/initPage.fxml");
    }
    @FXML
    void roomEditPage(){
        loadPage("/cn/nkkxzxx/propertymgrsys/view/roomEditPageMain.fxml");
    }
    @FXML
    void tenementEdit(){
        loadPage("/cn/nkkxzxx/propertymgrsys/view/tenementEditPage.fxml");
    }
    @FXML
    void queryInfoPage(){
        loadPage("/cn/nkkxzxx/propertymgrsys/view/queryByRoomInfoPage.fxml");
    }
    @FXML
    private void initialize(){
    }


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
