package mainPackage;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;

public class DB_ConnCreator {

    public static Connection connect() {
        String url = "jdbc:sqlite:" + System.getenv("APPDATA") + "/DQuiz/DQuiz.db";
        // SQLite connection string
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }
}
