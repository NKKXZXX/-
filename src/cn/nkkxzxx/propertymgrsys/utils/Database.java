package cn.nkkxzxx.propertymgrsys.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * 数据库连接类
 * @author nkkxzxx
 */
public class Database {
    private static final String url = "jdbc:sqlite:PropertyManagementDatabase.db";
    private static final String username = "";
    private static final String password = "";
    private static Connection c;

    /*
      数据库连接
     */
    public static Connection getConnection() throws Exception{
            Class.forName("org.sqlite.JDBC");
            Database.c = DriverManager.getConnection(url,username,password);
            return c;
    }
    /*
    关闭数据库
     */
    public static void closeDatabase(){
        try {
            Database.c.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    public static void updateDatabase(String query){
        System.out.println(query);
        try {
            Connection c = Database.getConnection();
            PreparedStatement statement = c.prepareStatement(query);
            statement.executeUpdate();
            c.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("操作完成");
    }
}
