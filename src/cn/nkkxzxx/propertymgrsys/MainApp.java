package cn.nkkxzxx.propertymgrsys;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApp extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/cn/nkkxzxx/propertymgrsys/view/main.fxml"));
        Scene scene = new Scene(root,924,576);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.setTitle("物业管理系统");
        primaryStage.show();
    }
}
