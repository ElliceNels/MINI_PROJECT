package mainPackage;

import java.sql.*;

public class DB_ScoreInteract {
    // given user_id and card_id, if record doesn't exist create it. add 1 to win/loss depending, and reduce or increase score depending
    public static void addWin(String user_id, String card_id) {
        String sql = "SELECT score, wins, losses FROM scores WHERE user_ID = ? AND card_id = ?";

        try (Connection conn = DB_ConnCreator.connect();
            java.sql.PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // set the value
            pstmt.setString(2, card_id);
            pstmt.setString(1, user_id);
            //
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                // if record exists, add 1 to wins, and change score by 1
                int wins = rs.getInt("wins");
                int score = rs.getInt("score");
                wins++;
                score++;
                changeWins(user_id, card_id, wins, conn);
                changeScore(user_id, card_id, score, conn);
                changeLastPlayedValue(true, user_id, card_id, conn);
            } else {
                // if record doesn't exist, create it, add 1 to wins, and change score by 1
                createRecord(user_id, card_id, conn);
                changeWins(user_id, card_id, 1, conn);
                changeScore(user_id, card_id, 1, conn);
                changeLastPlayedValue(true, user_id, card_id, conn);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void addLoss(String user_id, String card_id) {
        String sql = "SELECT score, wins, losses FROM scores WHERE user_ID = ? AND card_id = ?";

        try (Connection conn = DB_ConnCreator.connect();
            java.sql.PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // set the value
            pstmt.setString(2, card_id);
            pstmt.setString(1, user_id);
            //
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                // if record exists, add 1 to losses, and change score by -1
                int losses = rs.getInt("losses");
                int score = rs.getInt("score");
                losses++;
                score--;
                changeLosses(user_id, card_id, losses, conn);
                changeScore(user_id, card_id, score, conn);
                changeLastPlayedValue(false, user_id, card_id, conn);
            } else {
                // if record doesn't exist, create it, add 1 to losses, and change score by -1
                createRecord(user_id, card_id, conn);
                changeLosses(user_id, card_id, 1, conn);
                changeScore(user_id, card_id, -1, conn);
                changeLastPlayedValue(false, user_id, card_id, conn);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void createRecord(String user_id, String card_id, Connection conn) {
        String sql = "INSERT INTO scores(user_ID, card_id) VALUES(?,?)";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, user_id);
            pstmt.setString(2, card_id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public static void changeWins(String user_id, String card_id, int wins, Connection conn) {
        String sql = "UPDATE scores SET wins = ? WHERE user_ID = ? AND card_id = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, wins);
            pstmt.setString(2, user_id);
            pstmt.setString(3, card_id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public static void changeScore(String user_id, String card_id, int score, Connection conn) {
        String sql = "UPDATE scores SET score = ? WHERE user_ID = ? AND card_id = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, score);
            pstmt.setString(2, user_id);
            pstmt.setString(3, card_id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void changeLosses(String user_id, String card_id, int losses, Connection conn) {
        String sql = "UPDATE scores SET losses = ? WHERE user_ID = ? AND card_id = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, losses);
            pstmt.setString(2, user_id);
            pstmt.setString(3, card_id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void changeLastPlayedValue(Boolean correct, String user_id, String card_id, Connection conn) {
        String sql = "UPDATE scores SET was_correct_last_played = ? WHERE user_ID = ? AND card_id = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setBoolean(1, correct);
            pstmt.setString(2, user_id);
            pstmt.setString(3, card_id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
