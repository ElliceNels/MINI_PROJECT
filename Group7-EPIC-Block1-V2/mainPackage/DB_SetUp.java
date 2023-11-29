package mainPackage;

import java.io.File;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DB_SetUp {

    public static void createNewDatabase() {
        // check if DQuiz folder exists in appdata
        String appdataPath = System.getenv("APPDATA");
        // DQuiz database folder path in APPDATA
        String DquizPath = appdataPath + "/DQuiz";

        File DquizFolder = new File(DquizPath);
        if (!DquizFolder.exists()) {
            if (!DquizFolder.mkdir())
            {
                System.out.println("Failed to create DQuiz folder");
                return;
            }
        }

        String url = "jdbc:sqlite:" + DquizFolder +"/" + "DQuiz.db";

        // test if database exists
        File dbFile = new File(DquizFolder + "/" + "DQuiz.db");
        if (dbFile.exists()) {
            System.out.println("Database already exists");
            return;
        }
        try (Connection conn = DriverManager.getConnection(url)) {
            if (conn != null) {
                DatabaseMetaData meta = conn.getMetaData();
                System.out.println("The driver name is " + meta.getDriverName());
                System.out.println("A new database has been created.");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}


