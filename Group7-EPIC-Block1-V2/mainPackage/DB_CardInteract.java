package mainPackage;
import java.sql.*;
public class DB_CardInteract {
    // get all cards from DB
    public static int amountOfCardsTotal() {
        String cards_amount_sql = "SELECT COUNT(*) FROM cards";

        try {
            Connection conn = DB_ConnCreator.connect();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(cards_amount_sql);
            return rs.getInt("COUNT(*)");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("Error in amountOfCards");
        return 0;
    }

    public static int amountOfCardsWithWhere(String whereClause) {
        String cards_amount_sql = "SELECT COUNT(*) FROM cards WHERE " + whereClause;

        try {
            Connection conn = DB_ConnCreator.connect();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(cards_amount_sql);

            return rs.getInt("COUNT(*)");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("Error in amountOfCardsWithWhere");
        return 0;
    }

    public static card[] allCardsIncreasingDifficulty(){
        String sql = "SELECT card_id, question, answers, correct_answer_index, category, difficulty FROM cards ORDER BY difficulty";

        int numberOfCards = amountOfCardsTotal();

        try {
            Connection conn = DB_ConnCreator.connect();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            if (rs == null) {
                System.out.println("No cards in DB");
                return null;
            }

            card[] cards = new card[numberOfCards];

            int count = 0;
            // loop through the result set
            while (rs.next()) {
                card row = new card(rs.getString("card_id"), rs.getString("question"), rs.getString("answers"), rs.getInt("correct_answer_index"), rs.getInt("category"), rs.getInt("difficulty"));
                cards[count] = row;
                count++;
            }
            return cards;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }


    public static card[] allCardsIncreasingScore(String user_id){
        String sql = "Select cards.card_id, cards.question, cards.answers, cards.correct_answer_index, cards.category, cards.difficulty, scores.score from cards LEFT JOIN scores ON scores.card_id = cards.card_id AND scores.user_ID = ? ORDER BY COALESCE(scores.score, 99999), cards.card_id;";

        int numberOfCards = amountOfCardsTotal();

        try {
            Connection conn = DB_ConnCreator.connect();
            java.sql.PreparedStatement pstmt = conn.prepareStatement(sql);
            // set the value
            pstmt.setString(1, user_id);
            //
            ResultSet rs = pstmt.executeQuery();

            if (rs == null) {
                System.out.println("No cards in DB");
                return null;
            }

            card[] cards = new card[numberOfCards];

            int count = 0;
            // loop through the result set
            while (rs.next()) {
                card row = new card(rs.getString("card_id"), rs.getString("question"), rs.getString("answers"), rs.getInt("correct_answer_index"), rs.getInt("category"), rs.getInt("difficulty"));
                cards[count] = row;
                count++;
            }
            return cards;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;

    }
    public static card[] returnAllCards() {
        String sql = "SELECT card_id, question, answers, correct_answer_index, category, difficulty FROM cards";

        int numberOfCards = amountOfCardsTotal();

        try{
            Connection conn = DB_ConnCreator.connect();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            if (rs == null) {
                System.out.println("No cards in DB");
                return null;
            }

            card[] cards = new card[numberOfCards];

            int count = 0;
            // loop through the result set
            while (rs.next()) {
                card row = new card(rs.getString("card_id"), rs.getString("question"), rs.getString("answers"), rs.getInt("correct_answer_index"), rs.getInt("category"), rs.getInt("difficulty"));
                cards[count] = row;
                count++;
            }
            return cards;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public static card returnCardByID(String cardID) {
        // returns all cards with the given ID
        String sql = "SELECT card_id, question, answers, correct_answer_index, category, difficulty FROM cards WHERE card_id = ?";

        try {
            Connection conn = DB_ConnCreator.connect();
            java.sql.PreparedStatement pstmt = conn.prepareStatement(sql);

            // set the value
            pstmt.setString(1, cardID);
            //
            ResultSet rs = pstmt.executeQuery();
            int numberOfCards = amountOfCardsWithWhere("card_id = " + "\"" + cardID + "\"");

            if (numberOfCards == 0) {
                System.out.println("No cards in DB with that ID");
                return null;
            }
            if (numberOfCards > 1) {
                System.out.println("More than one card in DB with that ID, something has gone very wrong.");
                return null;
            }

            return new card(rs.getString("card_id"), rs.getString("question"), rs.getString("answers"), rs.getInt("correct_answer_index"), rs.getInt("category"), rs.getInt("difficulty"));

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return null;
    }


    public static card[] returnCardsByDifficulty(int difficulty) {
        String sql = "SELECT card_id, question, answers, correct_answer_index, category, difficulty FROM cards WHERE difficulty = ?";

        int numberOfCards = amountOfCardsWithWhere("difficulty = " + "\"" + difficulty + "\"");


        try {
            Connection conn = DB_ConnCreator.connect();
            java.sql.PreparedStatement pstmt = conn.prepareStatement(sql);
            // set the value
            pstmt.setString(1, String.valueOf(difficulty));
            //
            ResultSet rs = pstmt.executeQuery();

            if (numberOfCards == 0) {
                System.out.println("No cards in DB with selected difficulty");
                return null;
            }

            card[] cards = new card[numberOfCards];

            int count = 0;
            // loop through the result set
            while (rs.next()) {
                card row = new card(rs.getString("card_id"), rs.getString("question"), rs.getString("answers"), rs.getInt("correct_answer_index"), rs.getInt("category"), rs.getInt("difficulty"));
                cards[count] = row;
                count++;
            }
            return cards;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public static card[] returnCardsByCategory(int category) {
        String sql = "SELECT card_id, question, answers, correct_answer_index, category, difficulty FROM cards WHERE category = ?";

        int numberOfCards = amountOfCardsWithWhere("category = " + "\"" + category + "\"");


        try {
            Connection conn = DB_ConnCreator.connect();
            java.sql.PreparedStatement pstmt = conn.prepareStatement(sql);

            // set the value
            pstmt.setString(1, String.valueOf(category));
            //
            ResultSet rs = pstmt.executeQuery();

            if (numberOfCards == 0) {
                System.out.println("No cards in DB with selected category");
                return null;
            }

            card[] cards = new card[numberOfCards];

            int count = 0;
            // loop through the result set
            while (rs.next()) {
                card row = new card(rs.getString("card_id"), rs.getString("question"), rs.getString("answers"), rs.getInt("correct_answer_index"), rs.getInt("category"), rs.getInt("difficulty"));
                cards[count] = row;
                count++;
            }
            return cards;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }


    public static void addCardToDB(card card_object) {
        String sql = "INSERT INTO cards(card_id,question,answers,correct_answer_index,category,difficulty) VALUES(?,?,?,?,?,?)";

        try  {
            Connection conn = DB_ConnCreator.connect();
            java.sql.PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, card_object.card_id);
            pstmt.setString(2, card_object.Question_content);
            pstmt.setString(3, card_object.Question_answers_original);
            pstmt.setInt(4, card_object.Question_correct_answer);
            pstmt.setInt(5, card_object.Question_category);
            pstmt.setInt(6, card_object.Question_difficulty);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}

