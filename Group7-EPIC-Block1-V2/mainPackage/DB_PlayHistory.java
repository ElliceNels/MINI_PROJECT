package mainPackage;

import java.sql.*;

public class DB_PlayHistory {
    // add a new mainPackage.history record to the database
    public static void addHistory(String user_id, int score_of_round, int wins, int losses) {
        String sql = "INSERT INTO play_history(user_ID,score_of_round, wins, losses, date) VALUES(?,?,?,?,date());";

        try (Connection conn = DB_ConnCreator.connect();
             java.sql.PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, user_id);
            pstmt.setInt(2, score_of_round);
            pstmt.setInt(3, wins);
            pstmt.setInt(4, losses);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // return the amount of mainPackage.history records in the database
    public static int amountOfHistory() {
        String sql = "SELECT COUNT(*) FROM play_history";

        try (Connection conn = DB_ConnCreator.connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){
            return rs.getInt(1);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return 0;
    }

    // return the amount of mainPackage.history records in the database with a where clause
    public static int amountOfHistoryWithWhere(String whereClause) {
        String sql = "SELECT COUNT(*) FROM play_history WHERE " + whereClause;

        try (Connection conn = DB_ConnCreator.connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){
            return rs.getInt(1);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return 0;
    }

    // return all mainPackage.history records in the database
    public static history[] returnAllHistory() {
        String sql = "SELECT user_ID, score_of_round, wins, losses, date FROM play_history";

        int numberOfHistory = amountOfHistory();

        try (Connection conn = DB_ConnCreator.connect();
             java.sql.PreparedStatement pstmt = conn.prepareStatement(sql)) {

            ResultSet rs = pstmt.executeQuery();

            if (numberOfHistory == 0) {
                System.out.println("No mainPackage.history in DB");
                return null;
            }

            history[] history = new history[numberOfHistory];

            int count = 0;
            // loop through the result set
            while (rs.next()) {
                history row = new history(rs.getString("user_ID"), rs.getInt("score_of_round"), rs.getInt("wins"), rs.getInt("losses"), rs.getString("date"));
                history[count] = row;
                count++;
            }
            return history;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    // return all mainPackage.history records of a user in the database
    public static history[] returnAllHistoryOfUser(String user_id) {
        String sql = "SELECT user_ID, score_of_round, wins, losses, date FROM play_history WHERE user_ID = ?";

        int numberOfHistory = amountOfHistoryWithWhere("user_ID = '" + user_id + "'");

        try (Connection conn = DB_ConnCreator.connect();
             java.sql.PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, user_id);
            ResultSet rs = pstmt.executeQuery();

            if (numberOfHistory == 0) {
                System.out.println("No mainPackage.history in DB");
                return null;
            }

            history[] history = new history[numberOfHistory];

            int count = 0;
            // loop through the result set
            while (rs.next()) {
                mainPackage.history row = new history(rs.getString("user_ID"), rs.getInt("score_of_round"), rs.getInt("wins"), rs.getInt("losses"), rs.getString("date"));
                history[count] = row;
                count++;
            }
            return history;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
