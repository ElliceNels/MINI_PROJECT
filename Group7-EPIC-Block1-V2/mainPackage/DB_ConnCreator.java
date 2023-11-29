package mainPackage;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;

public class DB_ConnCreator {

    private static Connection conn = null;

    private DB_ConnCreator() {
        // private constructor to prevent instantiation
    }

    public static Connection connect() {
        if (conn == null) {
            System.out.println("Connecting to database...");
            // print calling file
            System.out.println("Calling file: " + Thread.currentThread().getStackTrace()[2].getFileName());
            String url = "jdbc:sqlite:" + System.getenv("APPDATA") + "/DQuiz/DQuiz.db";
            try {
                conn = DriverManager.getConnection(url);
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        System.out.println("Connected to database, returning connection...");
        return conn;
    }

}