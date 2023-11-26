package mainPackage;

import java.sql.*;
import java.util.ArrayList;

public class DB_UserInteract {
    // needs to be able to create a new user
    // needs to be able to access a user

    public static void selectAll(){
        String sql = "SELECT user_ID, password FROM users";

        try (Connection conn = DB_ConnCreator.connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){

            // loop through the result set
            while (rs.next()) {
                System.out.println(rs.getString("user_ID") +  "\t" +
                        rs.getString("password") + "\t");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static boolean insert(String user_ID, String password) {
        String sql = "INSERT INTO users(user_ID,password) VALUES(?,?)";

        try (Connection conn = DB_ConnCreator.connect();
            java.sql.PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, user_ID);
            pstmt.setString(2, password);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }

    return true;
    }

    public static boolean loginCheck(String user_ID, String password) {
        String sql = "SELECT user_ID, password FROM users WHERE user_ID = ? AND password = ?";

        try (Connection conn = DB_ConnCreator.connect();
             java.sql.PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // set the value
            pstmt.setString(1,user_ID);
            pstmt.setString(2,password);
            //
            ResultSet rs  = pstmt.executeQuery();

            // arraylist of rows
            // if arraylist is empty, then login failed, if arraylist has one row, then login successful, if arraylist has more than one row, then something went wrong
            ArrayList<String[]> rows = new ArrayList<String[]>(); // Create an ArrayList object

            // loop through the result set
            while (rs.next()) {
                String[] row = {rs.getString("user_ID"), rs.getString("password")};
                rows.add(row);
            }

            if (rows.isEmpty()) {
                return false;
            } else if (rows.size() == 1) {
                return true;
            } else {
                System.out.println("Multiple results - failure. Something went very wrong.");
                return false;
            }

        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return false;
    }
}
