module PropertyManagementSystem {
    requires javafx.base;
    requires javafx.fxml;
    requires javafx.controls;
    requires javafx.graphics;
    requires java.sql;

    exports cn.nkkxzxx.propertymgrsys;
    opens cn.nkkxzxx.propertymgrsys;
    exports cn.nkkxzxx.propertymgrsys.utils;
    opens cn.nkkxzxx.propertymgrsys.utils;
    exports cn.nkkxzxx.propertymgrsys.controller;
    opens cn.nkkxzxx.propertymgrsys.controller;
    exports cn.nkkxzxx.propertymgrsys.model.entity;
    opens cn.nkkxzxx.propertymgrsys.model.entity;
}