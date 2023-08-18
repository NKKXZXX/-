package cn.nkkxzxx.propertymgrsys.utils;

import javafx.scene.control.Alert;

public class AlertInfo {
    private static Alert alert;
    private static String info;
    private static String title;

    public static void displayAlert(String title,String info){
        alert = new Alert(Alert.AlertType.INFORMATION);
        AlertInfo.info = info;
        AlertInfo.title = title;
        display();
    }
    /**
     * 显示提示
     */
    private static void display(){
        alert.setHeaderText(title);
        alert.setContentText(info);
        alert.show();
    }
}
