package cs151.application.DAO;

import cs151.application.Flashcard;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class FlashcardDAO {


    public static void insertFlashcard(int deckId, String front, String back) {
        String sql = """
            INSERT INTO flashcards(deck_id, front_text, back_text, status, creation_date, last_review_date)
            VALUES (?, ?, ?, ?, datetime('now'), datetime('now'))
            """;

        try (Connection conn = DataBase.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, deckId);
            stmt.setString(2, front.trim());
            stmt.setString(3, back.trim());
            stmt.setString(4, "New");
            stmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static List<Flashcard> getAllFlashcards() {
        List<Flashcard> list = new ArrayList<>();
        String sql = """
                SELECT id, deck_id, front_text, back_text, creation_date
                FROM flashcards
                ORDER BY creation_date DESC
                """;


        try (Connection conn = DataBase.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {


            while (rs.next()) {
                list.add(new Flashcard(
                        rs.getInt("id"),
                        rs.getInt("deck_id"),
                        rs.getString("front_text"),
                        rs.getString("back_text"),
                        rs.getString("creation_date")
                ));
            }


        } catch (Exception e) {
            e.printStackTrace();
        }


        return list;
    }
}

