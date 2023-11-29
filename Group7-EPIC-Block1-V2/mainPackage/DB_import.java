package mainPackage;

import java.sql.*;
import java.io.*;
import java.util.Arrays;

public class DB_import {
    // read from CSV file and create mainPackage.DB_Handler.card object from each row, then add to database
    public static void CSVImport(String path_to_file){
        String csvFile = path_to_file;
        String line = "";
        String cvsSplitBy = ",";
        boolean firstLine = true;
        try {
            Connection conn = DB_ConnCreator.connect();
            Statement stmt = conn.createStatement();
            BufferedReader br = new BufferedReader(new FileReader(csvFile));
            while ((line = br.readLine()) != null) {
                if (firstLine) {
                    firstLine = false;
                    continue;
                }
                // use comma as separator
                String[] card_in = line.split(cvsSplitBy);
                System.out.println(Arrays.toString(card_in));
//                System.out.println("Card read in: " + card_in[0] + " " + card_in[1] + " " + card_in[2] + " " + card_in[3] + " " + card_in[4] + " " + card_in[5]);
                // count number of elements in mainPackage.DB_Handler.card
                if (card_in.length == 6) {
                    String card_id = card_in[0];
                    String question_content = card_in[1];
                    String question_answers = card_in[2];
                    int question_correct_answer = Integer.parseInt(card_in[3]);
                    int question_category = Integer.parseInt(card_in[4]);
                    int question_difficulty = Integer.parseInt(card_in[5]);
                    card new_card = new card(card_id, question_content, question_answers, question_correct_answer, question_category, question_difficulty);
                    DB_CardInteract.addCardToDB(new_card);
                }
                if (card_in.length == 5) {
                    String card_id = "";
                    String question_content = card_in[0];
                    String question_answers = card_in[1];
                    int question_correct_answer = Integer.parseInt(card_in[2]);
                    int question_category = Integer.parseInt(card_in[3]);
                    int question_difficulty = Integer.parseInt(card_in[4]);
                    card new_card = new card(card_id, question_content, question_answers, question_correct_answer, question_category, question_difficulty);

                }


            }
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }

    }

    // export database, get all cards using mainPackage.DB_Handler.DB_CardInteract, write to CSV file



public static void CSVExport(String path_to_file){
    try {
        Connection conn = DB_ConnCreator.connect();
        Statement stmt = conn.createStatement();
            String sql = "SELECT * FROM cards";
            ResultSet rs = stmt.executeQuery(sql);
            FileWriter writer = new FileWriter(path_to_file);
            writer.append("card_id,question_content,question_answers,question_correct_answer,question_category,question_difficulty\n");
            while (rs.next()) {
                String card_id = rs.getString("card_id");
                String question_content = rs.getString("question");
                String question_answers = rs.getString("answers");
                int question_correct_answer = rs.getInt("correct_answer_index");
                int question_category = rs.getInt("category");
                int question_difficulty = rs.getInt("difficulty");
                writer.append(card_id); writer.append(","); writer.append(question_content); writer.append(","); writer.append(question_answers); writer.append(",");
                writer.append(String.valueOf(question_correct_answer)); writer.append(","); writer.append(String.valueOf(question_category)); writer.append(","); writer.append(String.valueOf(question_difficulty));
                writer.append("\n");
            }
            writer.flush();
            writer.close();
            rs.close();
        } catch (SQLException | IOException e) {
            System.out.println(e.getMessage());
        }
    }
}